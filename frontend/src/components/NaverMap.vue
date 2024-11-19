<template>
  <div class="map-container">
    <div id="map"></div>

    <div class="zoom-controls">
      <!--<button class="btn btn-primary" onclick="showCesium()">3D</button>-->
      <button @click="zoomIn" class="zoom-button">+</button>
      <button @click="zoomOut" class="zoom-button">-</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      map: null,
      locations: [], // 마커 데이터를 저장할 배열
    };
  },
  mounted() {
    const script = document.createElement("script");
    script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i&submodules=geocoder`;
    script.onload = () => {
      this.initMap();
    };
    document.head.appendChild(script);
  },
  methods: {
    async initMap() {
      this.map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.516042, 127.034881),
        zoom: 17,
        zoomControl: false,
        //mapTypeControl: true,
      });
      window.mapInstance = this.map; // 전역 변수 설정

      // 위치 데이터 가져오기
      try {
        const response = await axios.get("/api/properties/map");
        this.locations = response.data.map((location) => ({
          latitude: location.latitude,
          longitude: location.longitude,
          name: location.name,
          price: location.price,
        }));
      } catch (error) {
        console.error("데이터를 가져오는 중 오류 발생:", error);
      }

      // 마커 생성
      this.locations.forEach((location) => {
        const content = `
          <div style="margin: 0; display: table; padding: 0.5rem; table-layout: auto; border-radius: 2.3rem; border: 0.2rem solid var(--color-bg-blue2); background: white; cursor: pointer; position: relative; z-index: 2">
            <div style="max-width: 23rem; height: 1rem; padding: 0 0.8rem; text-overflow: ellipsis; white-space: nowrap; display: table-cell; vertical-align: middle; font-weight: 600;">
              <p>${location.name}</p>
              <p>${location.price}원</p>
            </div>
            <span style="position: absolute; border-style: solid; border-width: 1.2rem 1rem 0 1rem; border-color: #ffffff transparent; display: block; width: 0; z-index: 1; top: 2.5rem; left: 4.0rem;"></span>
            <span style="position: absolute; border-style: solid; border-width: 1.1rem 1rem 0 1rem; border-color: var(--color-bg-blue2) transparent; display: block; width: 0; z-index: 0; top: 2.933rem; left: 4.0rem;"></span>
          </div>
        `;

        new naver.maps.Marker({
          position: new naver.maps.LatLng(location.latitude, location.longitude),
          map: this.map,
          icon: {
            content: content,
            size: new naver.maps.Size(38, 58),
            anchor: new naver.maps.Point(19, 58),
          },
        });
      });
    },
    zoomIn() {
      if (this.map) {
        this.map.setZoom(this.map.getZoom() + 1);
      }
    },
    zoomOut() {
      if (this.map) {
        this.map.setZoom(this.map.getZoom() - 1);
      }
    },
    resetMap() {
      if (this.map) {
        // 초기 위치와 줌 레벨로 지도를 리셋
        this.map.setCenter(
          new naver.maps.LatLng(this.initialCenter.lat, this.initialCenter.lng)
        );
        this.map.setZoom(this.initialZoom);
      }
    },
  },
};
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
    top:5rem;
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
</style>
