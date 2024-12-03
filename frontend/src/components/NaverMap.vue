최종
<template>
  <div class="map-container">
    <div id="map"></div>

    <div class="zoom-controls">
      <button @click="zoomIn" class="zoom-button">+</button>
      <button @click="zoomOut" class="zoom-button">-</button>
      <div id="real_cost">
        <button @click="fetchRealCost" id="real_cost" class="realcost-button">
          실거래가
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import { useAiChatbotStore } from "@/stores/aiChatbot.js";
import { useMapInstanceStore } from "@/stores/map.js";
import {storeToRefs} from "pinia";
import Wkt from "terraformer-wkt-parser";
import proj4 from "proj4";


// const map = ref(null);
const locations = ref([]);
const markers = ref([]);
const aiChatbotStore = useAiChatbotStore();
const { chatBotData } = storeToRefs(aiChatbotStore);

const mapStore = useMapInstanceStore();
const { map } = storeToRefs(mapStore);
const polygons = ref([]);

const initMap = async () => {
  map.value = new naver.maps.Map("map", {
    center: new naver.maps.LatLng(37.516042, 127.034881),
    zoom: 17,
    zoomControl: false,
    mapTypeControl: true,
  });

  // 위치 데이터 초기화
  await updateLocations();
  updateMarkersAndCenter();
};

const updateLocations = async () => {

  if (chatBotData?.value.length > 0) {
    const resultData = chatBotData?.value[chatBotData?.value.length-1].result.result_data || [];
    console.log(resultData);
    locations.value = resultData.map((location) => ({
      // 쿼리결과가 반대로나옴 시간없어서 일단 프론트 단에서 바꿔서 기입
      latitude: location.longitude,
      longitude: location.latitude,
      name: location.prp_name,
      price: location.prp_price,
      prpTransType: location.prp_trans_type,
    }));
  } else {
    try {
      const response = await axios.get("/api/properties/map");
      locations.value = response.data.map((location) => ({
        latitude: location.latitude,
        longitude: location.longitude,
        name: location.name,
        price: location.price,
        prpTransType: location.prpTransType,
      }));
    } catch (error) {
      console.error("데이터를 가져오는 중 오류 발생:", error);
    }
  }
};

const updateMarkersAndCenter = () => {
  // 기존 마커 제거
  markers.value.forEach((marker) => marker.setMap(null));
  markers.value = [];

  // 새로운 마커 생성
  locations.value.forEach((location, index) => {
    const content = `
      <div style="margin: 0; display: table; padding: 0.5rem; table-layout: auto; border-radius: 2.3rem; border: 0.2rem solid var(--color-bg-blue1); background: white; cursor: pointer; position: relative; z-index: 2;">
        <div style="position: absolute; top: -10px; left: -10px; width: 25px; height: 25px; background-color: orange; border-radius: 50%; z-index: 3; border: 2px solid var(--color-bg-blue1);display: flex; align-items: center; justify-content: center;"> ${
        location.prpTransType === 0
            ? "매"
            : location.prpTransType === 1
                ? "전"
                : "월"
    }</div>
        <div style="max-width: 23rem; height: 1rem; padding: 0 0.8rem; text-overflow: ellipsis; white-space: nowrap; display: table-cell; vertical-align: middle; font-weight: 600;">
          <p>${location.name}</p>
          <p>${
        location.price >= 100000000
            ? `${Math.floor(location.price / 100000000)}억 ${Math.floor((location.price % 100000000) / 10000)}만`
            : location.price >= 10000
                ? `${Math.floor(location.price / 10000)}억 ${location.price % 10000} 만원`
                : `${location.price}만원`
    }</p>
        </div>
        <span style="position: absolute; border-style: solid; border-width: 1.2rem 1rem 0 1rem; border-color: #ffffff transparent; display: block; width: 0; z-index: 1; top: 2.5rem; left: 2.0rem;"></span>
        <span style="position: absolute; border-style: solid; border-width: 1.1rem 1rem 0 1rem; border-color: var(--color-bg-blue2) transparent; display: block; width: 0; z-index: 0; top: 2.933rem; left: 2.0rem;"></span>
      </div>
    `;

    const marker = new naver.maps.Marker({
      position: new naver.maps.LatLng(location.latitude, location.longitude),
      map: map.value,
      icon: {
        content: content,
        size: new naver.maps.Size(38, 58),
        anchor: new naver.maps.Point(19, 58),
      },
    });

    markers.value.push(marker);

    // 첫 번째 위치를 지도 중심으로 설정
    if (index === 0) {
      map.value.setCenter(
          new naver.maps.LatLng(location.latitude, location.longitude)
      );
    }
  });
};

// 줌 컨트롤 함수
const zoomIn = () => {
  if (map.value) {
    map.value.setZoom(map.value.getZoom() + 1);
  }
};

const zoomOut = () => {
  if (map.value) {
    map.value.setZoom(map.value.getZoom() - 1);
  }
};

const fetchRealCost = async () => {
  try {
    polygons.value = []; // 초기화

    const response = await fetch("/api/realcost/coordinates");

    if (!response.ok) {
      throw new Error("서버 오류 발생");
    }

    const data = await response.json();
    console.log("응답 데이터:", data);

    const cleanedData = data.map((item) => {
      if (typeof item.realCost === "number" && item.realCost % 1 === 0) {
        item.realCost = item.realCost.toString().replace(/\.0$/, "");
      }
      return item;
    });

    const sourceProjection =
        "+proj=tmerc +lat_0=38 +lon_0=127 +k=1 +x_0=200000 +y_0=500000 +ellps=GRS80 +units=m +no_defs";
    const targetProjection = "EPSG:4326";

    cleanedData.forEach((item) => {
      const geoJsonString = item.geom;

      if (geoJsonString) {
        const parsedGeoJson = JSON.parse(geoJsonString);

        if (parsedGeoJson.type === "MultiPolygon" && parsedGeoJson.coordinates) {
          parsedGeoJson.coordinates.forEach((polygonCoords) => {
            const paths = polygonCoords.map((polygon) =>
                polygon.map((coord) => {
                  let convertedCoord = proj4(
                      sourceProjection,
                      targetProjection,
                      coord
                  );

                  convertedCoord[1] = convertedCoord[1] - 0.90091;
                  convertedCoord[0] = convertedCoord[0] + 0.00037;

                  return new naver.maps.LatLng(convertedCoord[1], convertedCoord[0]);
                })
            );

            paths.forEach((path) => {
              const latLngPath = path.map(
                  (coord) =>
                      coord instanceof naver.maps.LatLng
                          ? coord
                          : new naver.maps.LatLng(coord[1], coord[0])
              );

              map.value.setCenter(latLngPath[0]);
              map.value.setZoom(12);

              const polygon = new naver.maps.Polygon({
                map: map.value,
                paths: latLngPath,
                fillColor: "#FF0000",
                fillOpacity: 0.3,
                strokeColor: "#FF0000",
                strokeOpacity: 1,
                strokeWeight: 3,
                clickable: true,
              });

              polygons.value.push(polygon);

              naver.maps.Event.addListener(polygon, "click", () => {
                const center = getPolygonCenter(latLngPath);
                createMarker(center, item.realCost);
              });
            });
          });
        }
      }
    });
  } catch (error) {
    console.error("실거래가 데이터 가져오기 오류:", error);
  }
};

const getPolygonCenter = (latLngPath) => {
  let latSum = 0;
  let lngSum = 0;
  latLngPath.forEach((latLng) => {
    latSum += latLng.lat();
    lngSum += latLng.lng();
  });
  return new naver.maps.LatLng(latSum / latLngPath.length, lngSum / latLngPath.length);
};

const createMarker = (center, realCost) => {
  const marker = new naver.maps.Marker({
    position: center,
    map: map.value,
    icon: {
      content: `<div style="background-color: white; padding: 10px; border-radius: 5px; border: 1px solid #FF0000;">
                  <p>실거래가: ${realCost}만원</p>
                </div>`,
      size: new naver.maps.Size(100, 50),
      anchor: new naver.maps.Point(50, 50),
    },
  });

  markers.value.push(marker);
};

const removeAllMarkers = () => {
  markers.value.forEach((marker) => marker.setMap(null));
  markers.value = [];
};

onMounted(() => {
  const script = document.createElement("script");
  script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i&submodules=geocoder`;
  script.onload = () => {
    initMap();
  };
  document.head.appendChild(script);
});

// watch로 chatBotData 변경 감지
watch(
    () => chatBotData.value,
    async () => {
      await updateLocations();
      updateMarkersAndCenter();
    },
    { deep: true }
);

// 스크립트 로드 후 지도 초기화
onMounted(() => {
  const script = document.createElement("script");
  script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=gsnvytbrvz&submodules=geocoder`;
  script.onload = initMap;
  document.head.appendChild(script);
});
</script>


<style scoped>
.map-container {
  width: 100vw;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
}

#map {
  width: 100%;
  height: 100%;
}

.zoom-controls {
  position: absolute;
  bottom: 20px;
  right: 20px;
  display: flex;
  flex-direction: column;
}

.zoom-button {
  width: 40px;
  height: 40px;
  font-size: 20px;
  margin-top: 5px;
  background-color: rgb(255, 255, 255);
  border: 1px solid #ccc;
  border-radius: 4px;
  cursor: pointer;
}

.zoom-button:hover {
  background-color: #f0f0f0;
}

@media (max-width: 700px) {
  .zoom-controls {
    position: absolute;
    top: 5rem;
    right: 1rem;
    display: flex;
    flex-direction: column;
  }

  .zoom-button {
    width: 30px;
    height: 30px;
    font-size: 20px;
    margin-top: 5px;
    background-color: var(--color-bg-blue1);
    border: 1px solid #ccc;
    color: var(--color-bg-white);
    border-radius: 4px;
    cursor: pointer;
  }

  .zoom-button:hover {
    background-color: var(--color-bg-third);
  }
}

.price-label {
  background-color: rgba(255, 255, 255, 0.7);
  padding: 5px;
  border-radius: 5px;
  font-weight: bold;
  color: #000;
}

/* 실거래가 버튼 스타일 */
.realcost-button {
  background-color: var(--color-bg-third);
  color: white;
  padding: 10px;
  border: none;
  cursor: pointer;
  margin-top: 1ex;
  border-radius: 1ex;
}

.realcost-button:hover {
  background-color: var(--color-bg-third);
}
</style>
