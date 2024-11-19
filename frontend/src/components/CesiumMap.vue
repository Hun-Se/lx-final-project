<template>
  <div ref="cesiumContainer" class="cesium-container"></div>
</template>

<script setup>
import { onMounted, ref } from "vue";

const cesiumContainer = ref(null);
onMounted(() => {
  Cesium.Ion.defaultAccessToken =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkZjgzNTg0MC00YjlhLTRjMTYtOTQyMC0wODAxMzIxYjRiODAiLCJpZCI6MjI0NjgwLCJpYXQiOjE3MzE1NDg1MDR9.GGzCrXjXz_CXpgIad5PUtPoSFLbZ2qFpz3oaMDOH0Vo";

  // Cesium Viewer 초기화
  const viewer = new Cesium.Viewer(cesiumContainer.value, {
    terrainProvider: Cesium.createWorldTerrain(),
  });

  // Add Cesium OSM Buildings, a global 3D buildings layer.
  const buildingTileset = viewer.scene.primitives.add(Cesium.createOsmBuildings());

  // 예제: 서울로 카메라 이동
  viewer.camera.flyTo({
    destination: Cesium.Cartesian3.fromDegrees(127.0016, 37.584, 60),
    orientation: {
      heading: Cesium.Math.toRadians(0.0),
      pitch: Cesium.Math.toRadians(-15.0),
    },
  });
});
</script>

<style scoped></style>
