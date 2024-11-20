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
    terrainProvider: Cesium.createWorldTerrain(), // 전 세계 지형 활성화
    timeline: false,
    animation: false,
    selectionIndicator: false,
    navigationHelpButton: true, // 네비게이션 도움말 활성화
    infoBox: true, // 모델 정보 활성화
  });

  // 카메라 이동 설정
  viewer.camera.flyTo({
    destination: Cesium.Cartesian3.fromDegrees(127.0016, 37.584, 1000), // 카메라 고도와 모델 위치 조정
    orientation: {
      heading: Cesium.Math.toRadians(0.0),
      pitch: Cesium.Math.toRadians(-25.0),
      roll: Cesium.Math.toRadians(0.0),
    },
    duration: 3,
  });

  // 건물 모델 로딩 제거: Cesium.createOsmBuildings() 만 사용
  const buildingTileset = viewer.scene.primitives.add(Cesium.createOsmBuildings());

  // 히트맵 데이터 포인트 배열 예시
  const points = [
  { lat: 37.58409, lng: 126.9706, weight: 120 },
{ lat: 37.57228, lng: 126.9656, weight: 560 },
{ lat: 37.585, lng: 126.9819, weight: 230 },
{ lat: 37.5924, lng: 126.9641, weight: 780 },
{ lat: 37.60533, lng: 126.9668, weight: 430 },
{ lat: 37.57647, lng: 126.957, weight: 650 },
{ lat: 37.57192, lng: 126.962, weight: 650 },
{ lat: 37.58005, lng: 126.9848, weight: 340 },
{ lat: 37.5745, lng: 126.9902, weight: 190 },
{ lat: 37.57206, lng: 127.005, weight: 460 },
{ lat: 37.57698, lng: 127.0043, weight: 380 },
{ lat: 37.58686, lng: 127.0006, weight: 840 },
{ lat: 37.57071, lng: 127.0163, weight: 920 },
{ lat: 37.57445, lng: 127.0109, weight: 990 },
{ lat: 37.578, lng: 127.0147, weight: 280 },
{ lat: 37.57782, lng: 127.0156, weight: 550 },
{ lat: 37.57487, lng: 127.02, weight: 300 },
];

  // 히트맵을 위한 포인트를 추가
  const billboardCollection = viewer.scene.primitives.add(new Cesium.BillboardCollection());
  const names = [
  "청운효자동", "사직동", "삼청동", "부암동", "평창동", "무악동", "교남동", "가회동",
  "종로14가동", "종로56가동", "이화동", "혜화동", "창신제1동", "창신제2동",
  "창신제3동", "숭인제1동", "숭인제2동"
];
  points.forEach((point,index) => {
    const position = Cesium.Cartesian3.fromDegrees(point.lng, point.lat, 200);
      // 경중률에 따라 색상 결정
      let texture;
      let alpha = 0.6; // 0은 완전 투명, 1은 불투명

  if (point.weight <= 300) {
    texture = "images/green.png"; // 노란색 그라데이션 텍스처
  } else if (point.weight <= 600) {
    texture = "images/orange.png"; // 주황색 그라데이션 텍스처
  } else {
    texture = "images/red.png"; // 빨간색 그라데이션 텍스처
  }

  billboardCollection.add({
      position: position, // 위치
      image: texture, // 이미지 텍스처
      width: 280, // 이미지 너비
      height: 250, // 이미지 높이
      color: Cesium.Color.fromAlpha(Cesium.Color.WHITE, alpha), // alpha 값을 통해 투명도 조정

    });
     // 해당 좌표에 텍스트 추가
     const label = viewer.entities.add({
      position: position,
      label: {
        text: names[index], // 각 이름을 텍스트로 표시
        font: '14px sans-serif', // 글꼴 크기 설정
        color: Cesium.Color.BLACK, // 글꼴 색상
        pixelOffset: new Cesium.Cartesian2(0, 0), // 텍스트 위치 조정 (위로 50픽셀)
        scale: 2.0, // 텍스트 크기 설정
        style: Cesium.LabelStyle.FILL_AND_OUTLINE, // 텍스트 스타일
        outlineWidth: 2, // 텍스트 외곽선 두께
        outlineColor: Cesium.Color.black, // 텍스트 외곽선 색상
      }
    });
  });
});
</script>

<style scoped>
.cesium-container {
  width: 100%;
  height: 100vh;
}
</style>