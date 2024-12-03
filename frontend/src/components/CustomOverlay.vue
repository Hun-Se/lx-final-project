<!-- <template>
  <div ref="overlayElement" :style="markerStyle" v-if="visible">
    <div :style="topMarkerStyle">{{ name }}</div>
    <div :style="bottomMarkerStyle">{{ price }}</div>
  </div>
</template>

<script>
import { onMounted, onUnmounted, ref } from 'vue';

export default {
  name: 'CustomOverlay',
  props: {
    map: { type: Object, required: true },
    position: { type: Object, required: true }, // { lat, lng } 형태의 객체
    name: { type: String, default: '매물이름' },
    price: { type: Number, default: 0 },
  },
  setup(props) {
    const overlayElement = ref(null);
    const visible = ref(true);
    let overlay;

// 스타일 설정


    const markerStyle = {  
      position: 'absolute',
      margin: '50px',
      width: '120px',
      height: '90px',
      borderRadius: '100%', // 원형으로 만듦
      display: 'flex',
      flexDirection: 'column',
      overflow: 'hidden' // 내부 요소가 원을 벗어나지 않도록 설정
    };

    const topMarkerStyle = {
      width: '100%', // 가로 너비를 100%로 설정
      height: '50%', // 세로 높이를 50%로 설정
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      fontWeight: 'bold',
      backgroundColor: 'lightblue'
    };

    const bottomMarkerStyle = {
      width: '100%', // 가로 너비를 100%로 설정
      height: '50%', // 세로 높이를 50%로 설정
      display: 'flex',
      alignItems: 'center',
      justifyContent: 'center',
      fontWeight: 'bold',
      backgroundColor: 'pink',
      position: 'relative'
    };

    // // 화살표 스타일
    // const style = document.createElement('style');
    // style.textContent = `
    // .bottomMarkerStyle::after = { 
    //   borderTop: '10px solid pink', // 아래쪽에 화살표 모양 만들기
    //   borderLeft: '10px solid transparent',
    //   borderRight: '10px solid transparent',
    //   content: '""',
    //   position: 'absolute',
    //   bottom: '-8px', // 아래쪽에 위치
    //   left: '50%',
    //   transform: 'translateX(-50%)' // 중앙 정렬
    // }
    // `;
    // document.head.appendChild(style);


    // 오버레이 추가 메서드
    function onAdd() {
      const overlayLayer = props.map.getPanes().overlayLayer;
      overlayElement.value.style.position = 'absolute';
      overlayLayer.appendChild(overlayElement.value);
    }

    function draw() {
  // 지도 객체가 설정되지 않았다면 draw 메서드를 종료
  if (!props.map) return;

  // projection 객체를 통해 LatLng 좌표를 화면 좌표로 변환
  const projection = props.map.getProjection();
  const position = new naver.maps.LatLng(props.position.lat, props.position.lng);
  const pixelPosition = projection.fromCoordToOffset(position);

//   if (overlayElement.value && pixelPosition) {
//     // 변환된 픽셀 좌표로 overlayElement의 위치를 설정
//     overlayElement.value.style.left = `${pixelPosition.x}px`;
//     overlayElement.value.style.top = `${pixelPosition.y}px`;
//   }
}
    // 오버레이 삭제 메서드
    function onRemove() {
      if (overlayElement.value) {
        overlayElement.value.remove();
      }
    }

    onMounted(() => {
      if (overlay) {
        overlay.setMap(null); // 오버레이 제거
      }
      overlay = new naver.maps.OverlayView();
      overlay.onAdd = onAdd;
      overlay.draw = draw;
      overlay.onRemove = onRemove;
      overlay.setMap(props.map);
    });



    return {
      overlayElement,
      markerStyle,
      topMarkerStyle,
      bottomMarkerStyle,
      price : props.price,
      visible,
    };
  },
};
</script>

<style scoped>
.overlay-element {
  position: absolute;
  width: 100px;
  height: 100px;
  border-radius: 50%;
  background-color: #fff;
  border: 2px solid #333;
  display: flex;
  flex-direction: column;
  text-align: center;
  font-weight: bold;
}
</style> -->
