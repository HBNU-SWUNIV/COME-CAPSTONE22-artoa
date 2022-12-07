#include <BLEDevice.h>
#include <BLEServer.h>
#include <BLEUtils.h> 
#include <BLE2902.h>
#include "MQ7.h"

BLEServer *pServer = NULL;
BLECharacteristic * pTxCharacteristic1;
BLECharacteristic * pTxCharacteristic2; 

bool deviceConnected = false;
bool oldDeviceConnected = false;
uint8_t txValue = 0;

uint8_t crashPin();
uint8_t crash_val = digitalRead(2);
uint8_t CO_val= analogRead(25);
uint8_t measurement;

#define crashPin 2
#define mq7Pin 25
#define VOLTAGE 5

#define SERVICE_UUID           "beb5483e-36e1-4688-b7f5-ea07361b26a6"
#define CHARACTERISTIC_UUID_RX "beb5483e-36e1-4688-b7f5-ea07361b26a7" // ESP32가 데이터를 입력 받는 캐릭터리스틱 UUID (Rx)
#define CHARACTERISTIC_UUID_TX1 "beb5483e-36e1-4688-b7f5-ea07361b26a8" // ESP32에서 외부로 데이터 보낼 캐릭터리스틱 UUID (Tx)
#define CHARACTERISTIC_UUID_TX2 "beb5483e-36e1-4688-b7f5-ea07361b26a9"

MQ7 mq7(mq7Pin, VOLTAGE);
 
// ESP32 연결 상태 콜백함수
class MyServerCallbacks: public BLEServerCallbacks {
    void onConnect(BLEServer* pServer) {
      //onConnect 연결 되는 시점에 호출 됨
      deviceConnected = true;
    };

    void onDisconnect(BLEServer* pServer) {
      //onDisconnect 연결이 해제되는 시점에 호출 됨
      deviceConnected = false;
    }
};
  
// ESP32 데이터를 입력 받는 콜백함수
class MyCallbacks: public BLECharacteristicCallbacks {  
    void onWrite(BLECharacteristic *pCharacteristic) {
      //onWrite 외부에서 데이터를 보내오면 호출됨 
      //보내온 데이터를 변수에 데이터 저장
      std::string rxValue = pCharacteristic->getValue();
      
      if (rxValue.length() > 0) {
        //시리얼 모니터에 출력
        Serial.println("*********");
        Serial.print("Received Value: ");
        for (int i = 0; i < rxValue.length(); i++)
          Serial.print(rxValue[i]);

        Serial.println();
        Serial.println("*********");
      }
    }
};


void setup() {
  Serial.begin(115200);   //시리얼 활성
  pinMode(crashPin,INPUT);
  pinMode(mq7Pin,OUTPUT);

  Serial.println("System ON & Excuting Calibration...");
  mq7.calibrate();
  Serial.println("Calibration Done");
  
  BLEDevice::init("SmartHelmet1");   //BLE 생성
  pServer = BLEDevice::createServer();              // 서버(Peripheral, 주변기기) 생성
  pServer->setCallbacks(new MyServerCallbacks());   // 연결 상태(연결/해제) 콜백함수 등록 
  BLEService *pService = pServer->createService(SERVICE_UUID);    // 서비스 UUID 등록

  // ESP32에서 외부로 데이터 보낼 캐릭터리스틱 생성 (Tx)
  pTxCharacteristic1 = pService->createCharacteristic(
                    CHARACTERISTIC_UUID_TX1,
                    BLECharacteristic::PROPERTY_NOTIFY
                  );
  pTxCharacteristic1->addDescriptor(new BLE2902());
  

  //2번째 캐릭터리스틱
  pTxCharacteristic2 = pService->createCharacteristic(
                    CHARACTERISTIC_UUID_TX2,
                    BLECharacteristic::PROPERTY_NOTIFY
                  );
  pTxCharacteristic2->addDescriptor(new BLE2902());

  // Client가 ESP32로 보내는 캐릭터리스틱 생성 (Rx)
  BLECharacteristic * pRxCharacteristic = pService->createCharacteristic(
                       CHARACTERISTIC_UUID_RX,
                      BLECharacteristic::PROPERTY_WRITE
                    );
  // pRxCharacteristic에 client가 보낸 데이터를 처리할 콜백 함수 등록
  pRxCharacteristic->setCallbacks(new MyCallbacks());

  pService->start();
  pServer->getAdvertising()->start();
  Serial.println("Waiting a client connection to notify...");
}

void loop() {
    uint8_t measurement=TP_init();
    if (deviceConnected) {
        //measurement = digitalRead(crashPin);
        CO_val = mq7.readPpm();
        CDS_val = analogRead(CDS_SENSOR_PIN);

        pTxCharacteristic1->setValue(&CO_val, 1);
        pTxCharacteristic2->setValue(&measurement, 1);
        
        //notify 함수를 이용해 setValure로 써넣은 값을 외부로 전송함
        pTxCharacteristic1->notify();
        pTxCharacteristic2->notify();

        Serial.print("Crash_Value = "); Serial.print(measurement); Serial.print(" \t");
        Serial.print("CO PPM = "); Serial.print(mq7.readPpm()); Serial.println(" ");
        delay(250);
  }
    
    // 연결상태가 변경되었는데 연결 해제된 경우
    if (!deviceConnected && oldDeviceConnected) {
        delay(500); // 연결이 끊어지고 잠시 대기
        //BLE가 연결되면 어드버타이징이 정지 되기때문에 연결이 해제되면 다시 어드버타이징을 시작시킨다.
        pServer->startAdvertising(); // 어드버타이징을 다시 시작시킨다.
        Serial.println("disconnecting");        
        Serial.println("start advertising");
        oldDeviceConnected = deviceConnected;   // 이전 상태를 갱신 함
    }
    // 연결상태가 변경되었는데 연결 된 경우
    if (deviceConnected && !oldDeviceConnected) {
    // 이전 상태를 갱신 함
        oldDeviceConnected = deviceConnected;
        Serial.println("connecting");
    }
}

//충격 센서 값 측정
int TP_init(){
  delay(125);
  uint8_t measurement=pulseIn(crashPin, HIGH);
  return measurement;
}
