<template>
  <div class="map-container">   
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
  const script = document.createElement("script");
  script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i&submodules=geocoder`;
  script.onload = () => {
    this.initMap();
    this.fetchProperties();
  };
  document.head.appendChild(script);
  window.addEventListener("resize", this.resizeMap);
},
  beforeUnmount() {
    window.removeEventListener("resize", this.resizeMap);
  },
  methods: {
    initMap() {
  const script = document.createElement("script");
  script.src = `https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i&submodules=geocoder`;
  script.onload = () => {
    this.map = new naver.maps.Map("map", {
      center: new naver.maps.LatLng(37.516042, 127.034881),
      zoom: 17,
      zoomControl: false,
      mapTypeControl: true,
    });
    this.naverMapsLoaded = true; // 플래그 설정
  };
  document.head.appendChild(script);
},tchProperties() {
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
  const hours = parseInt(this.hours) || 0;  // 빈 문자열이나 NaN일 경우 0으로 처리
  const minutes = parseInt(this.minutes) || 0;
  const totalMinutes = hours * 3600 + minutes *60;

  if (!this.location) {
    alert('위치를 입력해주세요.');
    return;
  }
  if (!this.selectedTransport) {
    alert('이동수단을 선택해주세요.');
    return;
  }
  if (isNaN(this.hours) || isNaN(this.minutes)) {
    alert('시간과 분에는 숫자만 입력해주세요.');
    return;
  }

  // 시간이 0이어도 검색 진행
  this.geocodeAddress(this.location)
    .then(coords => {
      console.log(coords); // 좌표 확인
      return axios.get(`/api/isochrone/search`, {
        params: {
          lat: coords.lat,
          lng: coords.lng,
          transport: this.selectedTransport,
          duration: totalMinutes
        }
      });
    })
    .then(response => {
      this.drawIsochrone(response.data);
    })
    .catch(error => {
      console.error('Error:', error);
      if (error.message === 'No results found') {
        alert('입력한 주소를 찾을 수 없습니다. 주소를 다시 확인해 주세요.');
      } else {
        alert('검색 중 오류가 발생했습니다.');
      }
    });
},
// 주소를 좌표로 변환하는 메소드 (네이버 지도 API 사용)
geocodeAddress(address) {
  return new Promise((resolve, reject) => {
    if (!naver.maps.Service) {
      reject('Naver maps service is not loaded');
      return;
    }
    naver.maps.Service.geocode({
      query: address
    }, (status, response) => {
      if (status !== naver.maps.Service.Status.OK) {
        reject('Geocoding failed: ' + status);
        return;
      }
      const result = response.v2.addresses[0];
      if (result) {
        resolve({
          lat: parseFloat(result.y),
          lng: parseFloat(result.x)
        });
      } else {
        reject('No results found');
      }
    });
  });
},

// 등시선을 지도에 그리는 메소드
drawIsochrone(data) {
  if (this.isochrone) {
    this.isochrone.setMap(null);
  }
  const coords = data.results[0].shapes[0].shell.map(point => 
    new naver.maps.LatLng(point.lat, point.lng)
  );
  this.isochrone = new naver.maps.Polygon({
    map: this.map,
    paths: coords,
    fillColor: '#ff0000',
    fillOpacity: 0.3,
    strokeColor: '#ff0000',
    strokeOpacity: 0.6,
    strokeWeight: 3
  });
  
  // 등시선의 중심으로 지도 이동
  this.map.setCenter(coords[0]);

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
  flex-shrink: 0; 
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
    width: 100%; 
  }
}

@media (min-width: 769px) and (max-width: 1200px) {
  .search-bar {
    width: 70%; 
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
