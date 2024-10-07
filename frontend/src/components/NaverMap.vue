<template>
  <div class="map-container">
    <div id="map"></div>
    <div class="zoom-controls">
      <button @click="zoomIn" class="zoom-button">+</button>
      <button @click="zoomOut" class="zoom-button">-</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      map: null,
      properties: [], // 매물 데이터를 저장할 배열
    };
  },
  mounted() {
    this.initMap();
    this.fetchProperties(); // 매물 데이터 가져오기
    window.addEventListener("resize", this.resizeMap);
  },
  beforeUnmount() {
    window.removeEventListener("resize", this.resizeMap);
  },
  methods: {
    initMap() {
      const script = document.createElement("script");
      script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i`;
      script.onload = () => {
        this.map = new naver.maps.Map("map", {
          center: new naver.maps.LatLng(37.516042, 127.034881), //LX위치
          zoom: 17,
          zoomControl: false,
          mapTypeControl: true,
        });
      };
      document.head.appendChild(script);
    },
    fetchProperties() {
      axios.get('http://localhost:9000/api/properties')
        .then(response => {
          this.properties = response.data; // API에서 받아온 매물 데이터
          this.addMarkers(); // 마커 추가
        })
        .catch(error => {
          console.error('Failed to fetch properties:', error);
        });
    },
    addMarkers() {
  this.properties.forEach(property => {
    const { name, price, latitude, longitude } = property;

    // 커스터마이징된 말풍선
    const contentString = `
      
        <div class="map-marker-small-div">${name}</div>
        <div class="map-marker-sub-small-div">
          <span
            class="to-area-field"
          >${price} 원</span>
        </div>
      </div>
    `;

    const marker = new naver.maps.Marker({
      position: new naver.maps.LatLng(latitude, longitude),
      map: this.map,
    });

    const infowindow = new naver.maps.InfoWindow({
      content: contentString,
    });

    // 마커 클릭 시 인포윈도우 열기
    naver.maps.Event.addListener(marker, 'click', () => {
      infowindow.open(this.map, marker.getPosition());
    });

    // 맵이 로드되자마자 첫 번째 마커의 인포윈도우 열기
    if (this.properties.indexOf(property) === 0) { // 첫 번째 마커일 경우
      infowindow.open(this.map, marker.getPosition());
    }
     // 마커 숨기기
     marker.setMap(null); // 마커를 맵에서 제거
  });
},
    resizeMap() {
      if (this.map) {
        this.map.setSize(
          new naver.maps.Size(window.innerWidth, window.innerHeight)
        );
      }
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
  },
};
</script>

<style scoped>
.map-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
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

.map-marker-div {
  pointer-events: none;
  position: relative;
  z-index: 2;
  display: table;
  min-width: 44px;
  min-height: 34px;
  font-size: 13px;
  box-shadow: 0 2px 4px #aaa;
  background: #fff;
  border-radius: 16px;
  border: solid 0.5px #aaa;
}

.map-marker-div::after {
  content: "";
  position: absolute;
  border-top: 5px solid #fff;
  border-right: 5px solid transparent;
  border-left: 5px solid transparent;
  bottom: -4px;
  left: 50%;
  width: max-content;
  margin-left: -4px;
}
</style>
