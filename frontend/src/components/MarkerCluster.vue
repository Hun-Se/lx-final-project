// MarkerCluster.vue - 마커 클러스터 컴포넌트
<template>
  <div></div>
</template>

<script>
export default {
  props: ['map', 'markers'],
  mounted() {
    console.log('MarkerCluster mounted - map:', this.map, 'markers:', this.markers);
    this.initMarkerCluster();
  },
  methods: {
    initMarkerCluster() {
      if (!this.map) {
        console.error('initMarkerCluster - map 객체가 없습니다.');
        return;
      }
      if (!this.markers || this.markers.length === 0) {
        console.warn('initMarkerCluster - 마커가 없습니다.');
        return;
      }
      console.log('initMarkerCluster - 마커 클러스터 생성 시작');
      const markerCluster = new naver.maps.MarkerClustering({
        minClusterSize: 2,
        maxZoom: 15,
        map: this.map,
        markers: this.markers,
        disableClickZoom: false,
        gridSize: 120,
        icons: [
          {
            content: `
              <div style="background: rgba(0, 101, 255, 0.7); color: white; border-radius: 50%; padding: 10px; text-align: center;">
                <span class="cluster-count">{count}</span>
              </div>
            `,
            size: naver.maps.Size(50, 50),
            anchor: naver.maps.Point(25, 25)
          }
        ],
        indexGenerator: [2, 5, 10],
        stylingFunction: function(clusterMarker, count) {
          clusterMarker.getElement().querySelector('.cluster-count').textContent = count;
        }
      });
      console.log('initMarkerCluster - 마커 클러스터 생성 완료');
    }
  }
};
</script>

// MapMarker.vue - 개별 마커를 처리하는 컴포넌트
<template>
  <div></div>
</template>

<script>
export default {
  props: ['map', 'properties'],
  data() {
    return {
      markers: []
    };
  },
  mounted() {
    console.log('MapMarker mounted - map:', this.map, 'properties:', this.properties);
    this.initMarkers();
  },
  methods: {
    initMarkers() {
      if (!this.map) {
        console.error('initMarkers - map 객체가 없습니다.');
        return;
      }
      if (!this.properties || this.properties.length === 0) {
        console.warn('initMarkers - properties가 없습니다.');
        return;
      }
      console.log('initMarkers - 마커 생성 시작');
      this.properties.forEach((property) => {
        console.log('initMarkers - 마커 생성 중: ', property);
        const marker = new naver.maps.Marker({
          position: new naver.maps.LatLng(property.latitude, property.longitude),
          map: this.map,
          title: property.name,
        });
        this.markers.push(marker);
      });
      console.log('initMarkers - 마커 생성 완료:', this.markers);
      // 클러스터링 컴포넌트에서 사용할 마커들을 전달
      this.$emit('markers-ready', this.markers);
    }
  }
};
</script>

// MapView.vue - MapMarker와 MarkerCluster를 통합하는 메인 지도 뷰 컴포넌트
<template>
  <div class="map-container">
    <div id="map"></div>
    <zoom-controls :map="map" />
    <MapMarker :map="map" :properties="properties" @markers-ready="onMarkersReady" ref="mapMarker" />
    <MarkerCluster v-if="map && markers.length > 0" :map="map" :markers="markers" />
  </div>
</template>

<script>
import MapMarker from './MapMarker.vue';
import MarkerCluster from './MarkerCluster.vue';
import ZoomControls from './ZoomControls.vue';

export default {
  components: { MapMarker, MarkerCluster, ZoomControls },
  data() {
    return {
      map: null,
      properties: [],
      markers: []
    };
  },
  mounted() {
    console.log('MapView mounted - 스크립트 로드 시작');
    const script = document.createElement("script");
    script.src = "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=yi1l80sw0i_ID&submodules=geocoder";
    script.onload = () => {
      console.log('MapView mounted - 스크립트 로드 완료');
      this.initMap();
      this.loadProperties();
    };
    script.onerror = () => {
      console.error('MapView mounted - 스크립트 로드 실패');
    };
    document.head.appendChild(script);
  },
  methods: {
    initMap() {
      console.log('initMap - 지도 초기화 시작');
      this.map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(37.516042, 127.034881),
        zoom: 10,
        zoomControl: false,
        mapTypeControl: true,
      });
      console.log('initMap - 지도 초기화 완료:', this.map);
    },
    loadProperties() {
      console.log('loadProperties - 매물 데이터 로드 시작');
      fetch('/api/properties/map')
        .then(response => {
          if (!response.ok) {
            throw new Error('loadProperties - 응답 오류: ' + response.status);
          }
          return response.json();
        })
        .then(data => {
          console.log('loadProperties - 매물 데이터 로드 완료:', data);
          this.properties = data;
        })
        .catch(error => {
          console.error('loadProperties - 매물 데이터 로드 실패:', error);
        });
    },
    onMarkersReady(markers) {
      console.log('onMarkersReady - 마커 준비 완료:', markers);
      this.markers = markers;
    }
  }
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
</style>
