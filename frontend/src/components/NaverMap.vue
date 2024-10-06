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
export default {
  data() {
    return {
      map: null,
    };
  },
  mounted() {
    this.initMap();
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
          center: new naver.maps.LatLng(37.5160426164, 127.034881136055), //공간정보아카데미주소
          zoom: 17,
          zoomControl: false,
          mapTypeControl: true,
        });
      };
      document.head.appendChild(script);
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
</style>
