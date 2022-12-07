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
  - 
- ### 기존의 문제점
  - 현장에서 사고 발생 시 빠른 판단과 대처가 어려웠기 때문에 피해의 규모가 어느 정도인지 현장 외에서는 즉각 파악이 어려웠습니다.
  - 관련 제품들이 있었으나 과한 무게, 메인 보드와 센서의 전력 문제로 인한 대용량 배터리의 필수성 등이 문제되었습니다.
  
## System Design
<img src="https://img.shields.io/badge/Arduino-00979D?style=flat-square&logo=arduino&logoColor=white"/> <img src="https://img.shields.io/badge/Raspberry Pi-A22846?style=flat-square&logo=raspberrypi&logoColor=black"/>  <img src="https://img.shields.io/badge/Android Studio-3DDC84?style=flat-square&logo=android&logoColor=black"/>   <img src="https://img.shields.io/badge/BLE-0082FC?style=flat-square&logo=bluetooth&logoColor=black"/>   <img src="https://img.shields.io/badge/MQTT-660066?style=flat-square&logo=mqtt&logoColor=white"/>

- ### System Requirements
  - Arduino IDE로 ESP wroom 32 메인보드의 데이터 전송(BLE) 환경 구축
  - Raspberry Pi로 Bleak 모듈 및 MQTT를 활용해 데이터 송수신 환경 구축
  - Android Studio로 Raspberry Pi로부터 수신 받은 데이터를 모니터링 할 Application 구축
  
  
  <img width="373" alt="안전모" src="https://user-images.githubusercontent.com/96764364/205545231-fa3c192d-b644-407f-8be1-7bc86ff3c058.png">


## Case Study
  - ### Description
  
  
## Conclusion
  - #### 메인보드 및 센서 구조
      ![구조](https://user-images.githubusercontent.com/96764364/205832424-a36b0513-75ce-422b-b32b-310f44d2abde.jpg)
      
      - 무게 최소화를 위한 이온 리튬 폴리머 배터리를 사용, 충전 단자를 위한 보호 충전 모듈 연결
      
  - #### 메인 케이스 및 가스 센서 케이스
      ![케이스](https://user-images.githubusercontent.com/96764364/205832392-c8936c7d-c57c-4ed5-a551-2df5a82ad107.jpg)
  - #### 모바일 앱 작동 화면
      ![작동화면](https://user-images.githubusercontent.com/96764364/205832442-ff7c6295-78b8-4d15-be5f-65cd7bac59bc.jpg)
      
      - 센서부에서 감지 시 수치가 오르며, 일정 치 이상일 경우 경고 & 모바일 알람 발생
      
  - #### 활용 방안
      - 기존 센서 외에도 다양한 소형 센서 모듈을 별도 연결하여 업데이트 및 유지 보수 가능
      - Bluetooth Low Energy & MQTT 데이터 송수신 기술을 이용해 실시간 데이터 전송이 필요한 다양한 현장에서 관련 장비를 이용해 작업, 인원 관리 및 데이터 처리 환경을 구축할 수 있음.
