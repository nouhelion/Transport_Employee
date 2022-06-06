package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class navigation_bar_driver extends AppCompatActivity {
    ImageView info,accueil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar_driver);
        info=(ImageView) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_driver.this,informations_driver.class);
                startActivity(intent);
            }
        });
        accueil=(ImageView) findViewById(R.id.acc);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_driver.this,tencours_driver.class);
                startActivity(intent);
            }
        });
    }
}