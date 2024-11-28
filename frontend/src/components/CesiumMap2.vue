<template>
  <div ref="cesiumContainer" class="cesium-container"></div>
</template>

<script setup>
import { onMounted, ref } from "vue";

const cesiumContainer = ref(null);

onMounted(() => {
  Cesium.Ion.defaultAccessToken =
    "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJqdGkiOiJkZjgzNTg0MC00YjlhLTRjMTYtOTQyMC0wODAxMzIxYjRiODAiLCJpZCI6MjI0NjgwLCJpYXQiOjE3MzE1NDg1MDR9.GGzCrXjXz_CXpgIad5PUtPoSFLbZ2qFpz3oaMDOH0Vo"; // 실제 토큰

  if (!cesiumContainer.value) {
    console.error("Cesium container not found");
    return;
  }

  const viewer = new Cesium.Viewer(cesiumContainer.value, {
    terrainProvider: Cesium.createWorldTerrain(),
    timeline: false,
    animation: false,
    selectionIndicator: false,
    navigationHelpButton: true, // 네비게이션 도움말 활성화
    infoBox: true, // 모델 정보 활성화
  });

  const buildingTileset = viewer.scene.primitives.add(Cesium.createOsmBuildings());

  viewer.camera.flyTo({
    destination: Cesium.Cartesian3.fromDegrees(127.0016, 37.584, 1000), // 카메라 고도와 모델 위치 조정
    orientation: {
      heading: Cesium.Math.toRadians(0.0),
      pitch: Cesium.Math.toRadians(-25.0),
      roll: Cesium.Math.toRadians(0.0),
    },
    duration: 3,
  });

  const scene = viewer.scene;
  const position = Cesium.Cartesian3.fromDegrees(127.0016, 37.584, 20);
  const modelMatrix = Cesium.Transforms.eastNorthUpToFixedFrame(position);

  try {
    const model = Cesium.Model.fromGltf({
      url: "/images/cesium.gltf", // 경로 확인
      modelMatrix: modelMatrix,
      scale: 1.0, // 적절한 모델 크기 설정
    });

    scene.primitives.add(model);

    model.readyPromise
      .then(function () {
        console.log("모델 로드 완료");
        viewer.zoomTo(model);
        alert("데이터 모델 로드 완료")
      })
      .catch(function (error) {
        console.error("모델 로드 후 에러:", error);
      });
  } catch (error) {
    console.error("모델 로딩 실패:", error);
  }
});
</script>

<style scoped>
.cesium-container {
  width: 100%;
  height: 100vh;
}
</style>
