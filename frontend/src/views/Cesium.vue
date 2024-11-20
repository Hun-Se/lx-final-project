<template>
    <div>
      <button class="btn btn-primary" @click="showCesium">3D</button>
      <div v-if="showMap" id="cesiumContainer"></div>
    </div>
</template>
  
<script>
  import "https://cesium.com/downloads/cesiumjs/releases/1.96/Build/Cesium/Cesium.js";
  import "https://cesium.com/downloads/cesiumjs/releases/1.96/Build/Cesium/Widgets/widgets.css";
  
  export default {
    name: "CesiumMap",
    data() {
      return {
        showMap: false,
        cesiumViewer: null
      };
    },
    methods: {
      showCesium() {
        this.showMap = true;
        if (!this.cesiumViewer) {
          Cesium.Ion.defaultAccessToken = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkZjgzNTg0MC00YjlhLTRjMTYtOTQyMC0wODAxMzIxYjRiODAiLCJpZCI6MjI0NjgwLCJpYXQiOjE3MzE1NDg1MDR9.GGzCrXjXz_CXpgIad5PUtPoSFLbZ2qFpz3oaMDOH0Vo';
          
          this.cesiumViewer = new Cesium.Viewer("cesiumContainer", {
            terrainProvider: Cesium.createWorldTerrain()
          });
  
          this.cesiumViewer.scene.primitives.add(Cesium.createOsmBuildings());
  
          this.cesiumViewer.camera.flyTo({
            destination: Cesium.Cartesian3.fromDegrees(127.0016, 37.584, 60),
            orientation: {
              heading: Cesium.Math.toRadians(0.0),
              pitch: Cesium.Math.toRadians(-15.0)
            }
          });
        }
      }
    }
  };
</script>
  
<style scoped>
  #cesiumContainer {
    width: 100%;
    height: 100vh;
  }
</style>
  