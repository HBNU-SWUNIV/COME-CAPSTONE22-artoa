# Source Code
## 주의사항
 - 다른 Open Source SW를 사용하는 경우 저작권을 명시해야 함
 - 산학연계 캡스톤의 경우 기업의 기밀이 담긴 데이터는 제외할 것.
 <span style="color:red">
 - **기업 기밀 데이터가 Github에 공개되었을 시의 책임은 공개한 학생에게 있음**
 </span>

# ESP32
 - 아두이노 IDE 설치 후 상단의 파일 > 환경설정에서 추가적인 보드 매니저 URLs 끝의 버튼 클릭
 - https://raw.githubusercontent.com/espressif/arduino-esp32/gh-pages/package_esp32_index.json 추가 후 확인
 - 상단 툴 > 보드:__ > 보드 매니저... 선택 후 ESP32 입력, esp32 by Espressif System 설치
 - 설치 후 상단 툴 > 보드:__ > ESP32 Dev Module 선택, 코드 작성
 - 코드 업로딩 후 실행되면 상단 우측 시리얼 모니터를 열어 가스 센서 작동 확인 후 라즈베리파이와 연결 대기

# Android Studio
- helmet01.java 와 helmet02.jave 파일에서 connectMqtt() 클래스 내에 있는 mqttClient(n) 변수의 IP를 연결된 WIFI의 IP로 변경하여 저장
- 안드로이드 스튜디오 상단에서 Build-Build Bundle(s) / APK(s) - Build APK(s)로 APK 파일 추출
- 우측 하단의 Build APK(s)창에서 locate를 눌러 APK 파일이 저장된 위치 확인
- 어플을 설치할 휴대폰으로 APK 파일을 설치하고, 라즈베리파이와 동일한 IP에 연결 후 어플리케이션 실행
