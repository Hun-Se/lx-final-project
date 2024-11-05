<template>
  <div class="container">
    <!-- 상단 정보 섹션 -->
    <div class="header-section">
      <div class="info-group">
        <label>관리번호:</label>
        <span>CG589BD</span>
      </div>
      <div class="info-group">
        <label>신청일:</label>
        <span>2024-10-31</span>
      </div>
      <div class="info-group">
        <label>신청인:</label>
        <span>홍길동</span>
      </div>
      <div class="checkbox-group">
        <label class="circle-checkbox">
          <input type="checkbox" v-model="selectedOptions" value="피신고대상자정보조회" />
          <span class="custom-checkbox"></span>
          피신고 대상자 정보 조회
        </label>
        <label class="circle-checkbox">
          <input type="checkbox" v-model="selectedOptions" value="허위매물정보조회" />
          <span class="custom-checkbox"></span>
          허위 매물 정보 조회
        </label>
      </div>
    </div>

    <!-- 지도 및 위치 표시 섹션 -->
    <div class="map-section">
      <div class="map-display">
        <div class="map-pin"></div>
      </div>
      <div class="location-info">
        <p>위도: 37.5665</p>
        <p>경도: 126.9780</p>
      </div>
    </div>

    <!-- 세부 정보 섹션 -->
    <div class="details-section">
      <h3>세부 정보</h3>
      <ul>
        <li>신고인: 홍길동</li>
        <li>접수일자: 2024-10-31</li>
        <li>진행 상태: 접수 완료</li>
        <li>진행 상황: 준비 중</li>
      </ul>
    </div>

    <!-- 오디오 재생/정지 툴바 -->
    <div class="audio-toolbar">
      <audio
        ref="audioPlayer"
        :src="audioSource"
        @timeupdate="updateProgress"
        @loadedmetadata="setDuration"
        @ended="onAudioEnded"
      ></audio>
      
      <!-- 재생/정지 버튼 -->
      <button @click="togglePlay" class="play-button">
        {{ isPlaying ? '정지' : '재생' }}
      </button>
      
      <!-- 진행 바 및 시간 표시 -->
      <div class="progress-container">
        <span class="time">{{ currentTime }}</span>
        <input
          type="range"
          class="progress-bar"
          min="0"
          max="100"
          v-model="progress"
          @input="seekAudio"
        />
        <span class="time">{{ duration }}</span>
      </div>
    </div>

    <!-- 대화 내용 섹션 -->
    <div class="list-section">
      <h3>대화 내용</h3>
      <div class="chat-container">
        <div class="message" v-for="(msg, index) in messages" :key="index" :class="{ 'user-message': msg.isUser }">
          <div class="message-bubble">
            <p class="message-text">{{ msg.text }}</p>
            <span class="message-time">{{ msg.time }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selectedOptions: [],
      isPlaying: false,
      audioSource: "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3", // 오디오 파일 URL
      currentTime: "00:00", // 현재 재생 시간
      duration: "00:00", // 총 길이
      progress: 0, // 진행 상태 (0-100)
      messages: [
        { text: "안녕하세요! 집있나요?", time: "10:00 AM", isUser: true },
        { text: "안녕하세요! 아쉽게도 그 집은 계약되고 없어요.", time: "10:01 AM", isUser: false },
        { text: "이미 계약된 집인가요?.", time: "10:01 AM", isUser: true },
        { text: "네. 대신 비슷한 집들 보여드릴테니 방문해주세요.", time: "10:01 AM", isUser: false }
      ],
    };
  },
  methods: {
    fetchInfo() {
      // 정보 조회 함수 로직
    },
    deleteInfo() {
      // 정보 삭제 함수 로직
    },
    togglePlay() {
      const audio = this.$refs.audioPlayer;
      if (this.isPlaying) {
        audio.pause();
      } else {
        audio.play();
      }
      this.isPlaying = !this.isPlaying;
    },
    setDuration() {
      const audio = this.$refs.audioPlayer;
      this.duration = this.formatTime(audio.duration); // 오디오 총 길이 설정
    },
    updateProgress() {
      const audio = this.$refs.audioPlayer;
      this.currentTime = this.formatTime(audio.currentTime); // 현재 시간 업데이트
      this.progress = (audio.currentTime / audio.duration) * 100; // 진행 상태 업데이트
    },
    seekAudio() {
      const audio = this.$refs.audioPlayer;
      audio.currentTime = (this.progress / 100) * audio.duration; // 원하는 위치로 이동
    },
    formatTime(seconds) {
      const minutes = Math.floor(seconds / 60)
        .toString()
        .padStart(2, "0");
      const secs = Math.floor(seconds % 60)
        .toString()
        .padStart(2, "0");
      return `${minutes}:${secs}`;
    },
    onAudioEnded() {
      this.isPlaying = false; // 오디오가 끝나면 isPlaying을 false로 설정
      this.progress = 0; // 진행 바 초기화
      this.currentTime = "00:00"; // 현재 시간 초기화
    },
  },
};
</script>

<style scoped>
.container {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  padding: 20px;
}

/* 상단 정보 섹션 */
.header-section {
  grid-column: span 2;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  align-items: center;
  border-bottom: 1px solid #ccc;
  padding-bottom: 10px;
}

.info-group {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

.checkbox-group {
  display: flex;
  gap: 10px;
  align-items: center;
}

.circle-checkbox {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.circle-checkbox input[type="checkbox"] {
  display: none;
}

.custom-checkbox {
  width: 15px;
  height: 15px;
  border: 2px solid #007bff;
  border-radius: 50%;
  margin-right: 5px;
  background-color: transparent;
  transition: background-color 0.3s;
}

.circle-checkbox input[type="checkbox"]:checked + .custom-checkbox {
  background-color: #007bff;
}

button {
  padding: 5px 10px;
  cursor: pointer;
  margin-left: 5px;
}

/* 지도 및 위치 표시 섹션 */
.map-section {
  display: flex;
  align-items: center;
}

.map-display {
  width: 100px;
  height: 100px;
  background-color: #e0e0e0;
  position: relative;
  margin-right: 20px;
}

.map-pin {
  width: 10px;
  height: 10px;
  background-color: red;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 50%;
}

/* 세부 정보 섹션 */
.details-section {
  grid-column: span 2;
  margin-top: 20px;
}

.details-section ul {
  list-style-type: none;
  padding: 0;
}

/* 오디오 재생/정지 툴바 */
.audio-toolbar {
  grid-column: span 2;
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 10px;
  background-color: #f8f9fa;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.play-button {
  padding: 5px 15px;
  font-size: 14px;
  cursor: pointer;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
}

.play-button:hover {
  background-color: #0056b3;
}

.progress-container {
  display: flex;
  align-items: center;
  gap: 10px;
  width: 100%;
}

.time {
  font-size: 12px;
  color: #555;
}

.progress-bar {
  flex: 1;
  cursor: pointer;
}

/* 채팅 UI 스타일 */
.chat-container {
  max-height: 200px;
  overflow-y: auto;
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 5px;
}

.message {
  display: flex;
  max-width: 80%;
  margin-bottom: 10px;
}

.user-message {
  align-self: flex-end;
  background-color: #d1e7ff;
}

.message-bubble {
  padding: 10px;
  border-radius: 10px;
  font-size: 14px;
  color: #333;
}

.message-time {
  font-size: 10px;
  color: #888;
  margin-top: 5px;
  text-align: right;
}
</style>
