package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView employe;
    ImageView driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employe=(ImageView) findViewById(R.id.employeimg);
        employe.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this,on1_employee.class);
                startActivity(mainIntent);
            }
        });
        driver=(ImageView) findViewById(R.id.driverimg);
        driver.setOnClickListener(new View.OnClickListener() {
            // Start new list activity
            public void onClick(View v) {
                Intent mainIntent = new Intent(MainActivity.this,on1_driver.class);
                startActivity(mainIntent);
            }
        });
    }
}