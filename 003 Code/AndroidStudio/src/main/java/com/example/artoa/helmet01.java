package com.example.artoa;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class helmet01 extends AppCompatActivity {

    private static final String CHANNEL_ID = "notify test";
    private static final CharSequence CHANEL_NAME = "notiname";
    private String TOPIC1 = "helmet1/mq7";
    private String TOPIC2 = "helmet1/sw";

    private MqttClient mqttClient1 = null;
    private MqttClient mqttClient2 = null;

    public void onBackPressed() {
        super.onBackPressed();
        /*Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
*/
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helmet01);

        ActionBar ac = getSupportActionBar();
        ac.setTitle("1번 안전모");


        try {
            connectMqtt();
            Log.d("MQTTService", "Connect");
        } catch (Exception e) {
            Log.d("MQTTService", "Mqtt Error");
        }
    }

    private void showNotimq7() {
        NotificationCompat.Builder builder = null;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //버전 오레오 이상일 경우
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );

            builder = new NotificationCompat.Builder(this,CHANNEL_ID);

            //하위 버전일 경우
        }else{
            builder = new NotificationCompat.Builder(this);
        }

        //알림창 제목
        builder.setContentTitle("1번 안전모");

        //알림창 메시지
        builder.setContentText("일정 농도 이상의 가스가 감지되었습니다.");

        //알림창 아이콘
        builder.setSmallIcon(R.drawable.icon);


        Notification notification = builder.build();

        //알림창 실행
        manager.notify(1,notification);
    }

    private void showNotisw() {
        NotificationCompat.Builder builder = null;
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //버전 오레오 이상일 경우
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            manager.createNotificationChannel(
                    new NotificationChannel(CHANNEL_ID, CHANEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            );

            builder = new NotificationCompat.Builder(this,CHANNEL_ID);

            //하위 버전일 경우
        }else{
            builder = new NotificationCompat.Builder(this);
        }

        //알림창 제목
        builder.setContentTitle("1번 안전모");

        //알림창 메시지
        builder.setContentText("일정 이상의 충격이 발생되었습니다.");

        //알림창 아이콘
        builder.setSmallIcon(R.drawable.icon);


        Notification notification = builder.build();

        //알림창 실행
        manager.notify(1,notification);
    }


    public void connectMqtt() throws Exception{

        mqttClient1 = new MqttClient("tcp://192.168.166.185:1883", MqttClient.generateClientId(), null);
        mqttClient2 = new MqttClient("tcp://192.168.166.185:1883", MqttClient.generateClientId(), null);
        mqttClient1.connect();
        mqttClient2.connect();
        mqttClient1.subscribe(TOPIC1);
        mqttClient2.subscribe(TOPIC2);

        try {
            mqttClient1.setCallback(new MqttCallback() {//Callback
                TextView mqdata = findViewById(R.id.text2);

                ImageView mq7imgcolorBox = (ImageView)findViewById(R.id.gas);

                @Override
                public void connectionLost(Throwable throwable) {
                    Log.d("MQTTService", "Mqtt ReConnect");
                    try {
                        connectMqtt();
                    } catch (Exception e) {
                        Log.d("MQTTService", "MqttReConnect Error");
                    }
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage1) throws Exception {
                    Log.d("MQTTService", "Message Arrived : " + mqttMessage1.toString());
                    int mq_mqttm = Integer.parseInt(mqttMessage1.toString());
                    mqdata.setText(mq_mqttm+"ppm");

                    if(mq_mqttm>200){
                        showNotimq7();
                        mq7imgcolorBox.setImageResource(R.drawable.gas2);

                    }
                    else{
                        mq7imgcolorBox.setImageResource(R.drawable.gas1);
                    }
                    connectMqtt();
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    Log.d("MQTTService", "Delivery Complete");
                }
            });

            mqttClient2.setCallback(new MqttCallback() {
                TextView swdata = findViewById(R.id.text4);
                ImageView swimgcolorBox = (ImageView)findViewById(R.id.hammer);

                @Override
                public void connectionLost(Throwable cause) {
                    Log.d("MQTTService", "Mqtt ReConnect");
                    try {
                        connectMqtt();
                    } catch (Exception e) {

                        Log.d("MQTTService", "MqttReConnect Error");
                    }
                }

                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage2) throws Exception {
                    Log.d("MQTTService", "Message Arrived : " + mqttMessage2.toString());
                    float sw_mqttm = Float.parseFloat(mqttMessage2.toString());
                    swdata.setText((sw_mqttm/255*100)+"%");

                    if(sw_mqttm>150){
                        showNotisw();
                        swimgcolorBox.setImageResource(R.drawable.hammer2);


                    }
                    else{
                        swimgcolorBox.setImageResource(R.drawable.hammer1);
                    }
                    connectMqtt();
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    Log.d("MQTTService", "Delivery Complete");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}