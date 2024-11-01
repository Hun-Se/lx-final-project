<template>
  <div class="map-container">
 <div id="map"></div>
    <div class="zoom-controls">
      <button @click="zoomIn" class="zoom-button">+</button>
      <button @click="zoomOut" class="zoom-button">-</button>
    </div>
    <div>
  <CustomOverlay
  v-for="(location, index) in locations"
  :key="index"
  :map="map"
  :position="{ lat: location.latitude, lng: location.longitude }"
  :name="location.name"
  :price="location.price"
/>
</div>
 </div>

</template>

<script>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import CustomOverlay from './CustomOverlay.vue';

export default {
  components: { CustomOverlay },
  setup() {
    const map = ref(null);
    const locations = ref([]);

    onMounted(() => {
      // 네이버 지도 API 스크립트 로드
      const script = document.createElement("script");
      script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i&submodules=geocoder`;
      script.onload = initMap;
      document.head.appendChild(script);
    });

    // 지도 초기화 함수
    const initMap = async () => {
      map.value = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.516042, 127.034881),
        zoom: 15,
        zoomControl: false,
        mapTypeControl: true,
      });

      // 위치 데이터 가져오기
      try {
        const response = await axios.get('/api/properties/map');
        console.log("API 응답 데이터:", response.data); // 1. API 응답 데이터 확인

        locations.value = response.data.map((location) => ({
          latitude: location.latitude,  // API에서 계산된 위도
          longitude: location.longitude,  // API에서 계산된 경도
          name: location.name,
          price: location.price,
        }));
        console.log("변환된 locations 데이터:", locations.value); // 변환된 데이터 확인

      } catch (error) {
        console.error("데이터를가져오는중 오류발생:", error);
      }
    };
    return {
      map,
      locations,
      zoomIn,
      zoomOut,
    };
  },
};
    // 확대 및 축소 기능
    const zoomIn = () => {
      if (overlay) {
        console.log("overlay");
        overlay.setMap(null); // 오버레이 제거
      }
      console.log("hi");
      if (map.value) map.value.setZoom(map.value.getZoom() + 1);
    };

    const zoomOut = () => {
      if (map.value) map.value.setZoom(map.value.getZoom() - 1);
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
</style>
