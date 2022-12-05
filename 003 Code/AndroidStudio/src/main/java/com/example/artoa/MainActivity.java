package com.example.artoa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button01 = findViewById(R.id.helmet_01);
        button01.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                Intent intent = new Intent(getApplicationContext(), helmet01.class);
                startActivity(intent);
            }

        });


        Button button02 = findViewById(R.id.helmet_02);
        button02.setOnClickListener(new View.OnClickListener(){


            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), helmet02.class);
                startActivity(intent);


            }

        });
    }


}