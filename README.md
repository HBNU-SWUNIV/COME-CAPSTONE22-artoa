# 한밭대학교 컴퓨터공학과 ARTOA팀

**팀 구성**
- 20191757 최혜연 
- 20191753 전영주
- 20171591 이용혁

## <u>SmartHelmet</u> Project Background
- ### 필요성
 <img width="245" alt="중대재해처벌법2" src="https://user-images.githubusercontent.com/96764364/205545794-c0730f80-c860-4e98-b06a-9bba336b460f.png"> <img width="290" alt="중대재해처벌법" src="https://user-images.githubusercontent.com/96764364/205548362-2ff68bba-8322-440f-9ed2-d8e97c302dec.png">
 
 출처 <br/>https://www.joongang.co.kr/article/25010300#home <br/>https://angelswing.io/blog/insight-occupational-safety-and-health-act/

  - 인권 강화 및 작업자 사망 시 대표가 책임을 지는 중대 재해 처벌법이 2022년 1월 27일부터 시행되며 안전 예방 강화의 중요성이 대두되었습니다. 
  - 현장에서 사고 발생 시 외부에서 상황 파악 및 빠른 대처가 어려웠습니다.
  - 이에 근로자가 위험 상황에 처했을 때, 추가 피해가 발생하기 전 빠른 조치를 하기 위한 플랫폼 기술의 확보가 필요하다고 여겨 본 프로젝트를 진행하였습니다.
  - 추가로 안전모의 무게를 고려하여 작은 크기의 장치 구성을 필요로 하였습니다.

Arduino, BLE, Raspberry pi, Linux, Python, MQTT, Android Studio, Java
<img src="https://img.shields.io/badge/이름-색상코드?style=flat-square&logo=로고명&logoColor=로고색"/>

## System Design
<img src="https://img.shields.io/badge/Arduino-00979D?style=flat-square&logo=arduino&logoColor=white"/> <img src="https://img.shields.io/badge/BLE-0082FC?style=flat-square&logo=bluetooth&logoColor=black"/> <img src="https://img.shields.io/badge/Raspberry Pi-A22846?style=flat-square&logo=raspberrypi&logoColor=black"/> <img src="https://img.shields.io/badge/Linux-FCC624?style=flat-square&logo=Linux&logoColor=black"/> <img src="https://img.shields.io/badge/Python-3776AB?style=flat-square&logo=Python&logoColor=black"/> <img src="https://img.shields.io/badge/MQTT-660066?style=flat-square&logo=mqtt&logoColor=white"/>
 <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=flat-square&logo=android&logoColor=black"/> <img src="https://img.shields.io/badge/Java-F7DF1E?style=flat-square&logo=JavaScript&logoColor=black"/>


- ### System Requirements
  - Arduino IDE로 ESP wroom 32 메인보드의 데이터 전송(BLE) 환경 구축
  - Raspberry Pi로 Bleak 모듈 및 MQTT를 활용해 데이터 송수신 환경 구축
  - Android Studio로 Raspberry Pi로부터 수신 받은 데이터를 모니터링 할 Application 구축
  
  
  ![구성도](https://user-images.githubusercontent.com/96764364/206141817-3c6733fb-a367-4f13-ad89-1a889ce1421f.png)


## Case Study
  - ### 추가 예정
  
  
## Conclusion
  - #### 메인보드 및 센서 구조
      ![보드](https://user-images.githubusercontent.com/96764364/206141809-2a037e86-0a0e-43d3-89d8-765317662e2e.png)
      
      - ESP32 보드에 충격 센서(SW-420)와 가스 센서(MQ-7)를 연결
      - 무게 최소화를 위한 이온 리튬 폴리머 배터리 사용
      - 충전 단자를 위한 보호 충전 모듈 연결

  - #### 데이터 송수신
    <img width="86" alt="측정1" src="https://user-images.githubusercontent.com/96764364/206150803-e0fe1287-19ad-40a5-9151-9c2bfcabc885.png">
    <img width="283" alt="측정2" src="https://user-images.githubusercontent.com/96764364/206150814-42d6b072-8bd2-4bca-8e81-72d4387dbbdf.png">

    <img width="304" alt="측정3" src="https://user-images.githubusercontent.com/96764364/206150826-fda8d4bf-bcda-48b6-8c9c-9eb36ddf3fed.png">
     

 - #### 케이스 제작
   <img width="229" alt="메인케이스1" src="https://user-images.githubusercontent.com/96764364/206144444-b2bb1de7-fb6c-4255-a785-1ca8ce2ce736.png"> <img width="229" alt="가스케이스1" src="https://user-images.githubusercontent.com/96764364/206144704-3310c6e1-0ec2-4519-b0e0-7fd612479e04.png">
      - 3D printing 맞춤 케이스 제작
  - #### 모바일 앱 작동 화면
      <img width="185" alt="화면1" src="https://user-images.githubusercontent.com/96764364/206146657-5dc538d4-d7ed-4cf6-ba82-80159b7ccb3c.png"> <img width="185" alt="화면2" src="https://user-images.githubusercontent.com/96764364/206146668-c9fa72bf-0b41-429d-b392-87ab2d180e44.png">
      

      - 센서부에서 충격 및 유해 가스 감지 시 수치가 오르며, 일정 이상이 감지될 경우 경고
      - 위험 수치에 도달하면 '일정 이상의 충격(또는 가스)이 발생했습니다' 푸시 알림 생성
      
  - #### 활용 방안
      - 기존 센서 외에도 다양한 소형 센서 모듈을 별도 연결하여 업데이트 및 유지 보수 가능
      - Bluetooth Low Energy & MQTT 데이터 송수신 기술을 이용해 실시간 데이터 전송이 필요한 다양한 현장에서 관련 장비를 이용해 작업, 인원 관리 및 데이터 처리 환경을 구축할 수 있음.
