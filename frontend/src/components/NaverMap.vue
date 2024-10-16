<template>
  <div class="map-container">
    <!-- 검색 바 -->
    <div class="search-bar">
      <input class="orgin"
        type="text"
        v-model="location"
        placeholder="위치를 입력하세요"
      />
      <select class="trans" v-model="selectedTransport">
        <option disabled value="">이동수단</option>
        <option value="walking">도보</option>
        <option value="car">자차</option>
        <option value="publicTransport">대중교통</option>
      </select>
      <input class="input-hour"
        type="number"
        v-model="hours"
        placeholder="시간 입력"
        min="0"
        step="1"
      /><span>시간</span>
      <input class="input-min"
        type="number"
        v-model="minutes"
        placeholder="분 입력"
        min="0"
      /><span>분</span>
      <button @click="submit">검색</button>
  </div>
    <!-- 지도 -->
    <div id="map"></div>

    <div class="zoom-controls">
      <button @click="zoomIn" class="zoom-button">+</button>
      <button @click="zoomOut" class="zoom-button">-</button>
    </div>

    <!-- 검색 결과 출력 -->
    <div v-if="result">
      <h2>검색 결과:</h2>
      <p>위치: {{ result.location }}</p>
      <p>이동 수단: {{ result.transport }}</p>
      <p>걸리는 시간: {{ result.duration }}</p>
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
      location: '',
      selectedTransport: '',
      hours: '',
      minutes: '',
      result: null,
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
      axios.get('/api/properties')
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

        const contentString = `
          <div class="map-marker-small-div">${name}</div>
          <div class="map-marker-sub-small-div">
            <span class="to-area-field">${price} 원</span>
          </div>
        `;

        const marker = new naver.maps.Marker({
          position: new naver.maps.LatLng(latitude, longitude),
          map: this.map,
        });

        const infowindow = new naver.maps.InfoWindow({
          content: contentString,
        });

        naver.maps.Event.addListener(marker, 'click', () => {
          infowindow.open(this.map, marker.getPosition());
        });

        if (this.properties.indexOf(property) === 0) {
          infowindow.open(this.map, marker.getPosition());
        }

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
    submit() {
      const totalMinutes = parseInt(this.hours * 60) + parseInt(this.minutes);
      if (this.location && this.selectedTransport && totalMinutes) {
        this.result = {
          location: this.location,
          transport: this.selectedTransport,
          duration: totalMinutes,
        };
      } else {
        alert('모든 필드를 채워주세요!');
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

.search-bar {
  position: absolute;
  top: 10px;
  left: 10px;
  background: white;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  display: flex;
  align-items: center;
  gap: 10px;
  z-index: 100;
  justify-content: center;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.search-wrap{
  justify-content: center;
  display: flex;
}



button {
  padding: 10px;
  background-color: #2336c4;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

button:hover {
  background-color: #8c9ee5;
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
.search-bar {
  position: absolute;
  top: 10px;
  left: 0;
  right: 0;
  margin: 0 auto;
  background: white;
  padding: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-wrap: wrap; /* 아이템이 화면 너비에 따라 자동으로 줄바꿈 되도록 설정 */
  gap: 10px;
  z-index: 100;
  width: calc(100% - 20px); /* 화면 크기에 따라 너비 조절 */
  max-width: 800px; /* 최대 너비 제한 */
  justify-content: center; /* 가로 중앙 정렬 */
  flex-direction:row;
}



button {
  padding: 10px;
  background-color: #2336c4;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  flex-shrink: 0; /* 버튼이 줄어들지 않도록 설정 */
}

button:hover {
  background-color: #8c9ee5;
}

@media (max-width: 768px) {
  .search-bar {
    flex-direction: column; /* 작은 화면에서는 세로로 배치 */
    width: 95%; /* 화면을 거의 다 채우도록 조정 */
  }

  input, select, button {
    width: 100%; /* 작은 화면에서는 모든 요소가 전체 너비를 차지 */
  }
}

@media (min-width: 769px) and (max-width: 1200px) {
  .search-bar {
    width: 70%; /* 중간 화면 크기에서 너비를 70%로 조정 */
  }
}

.trans, .orgin {
  padding: 8px;
  flex: 1; 
  min-width: 100px; 
  border-radius: 5px;
  border-color: #beb5b55a;
}
.input-hour, .input-min{
  padding: 8px;
  width: 90px; 
  border-radius: 5px;
  border-color: #beb5b55a;
}
</style>
