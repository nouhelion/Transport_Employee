package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class navigation_bar_employee extends AppCompatActivity {
    ImageView info,accueil,reclamations,rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar_employee);
        info=(ImageView) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_employee.this,informations_employee.class);
                startActivity(intent);
            }
        });
        accueil=(ImageView) findViewById(R.id.acc);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_employee.this,tencours_employee.class);
                startActivity(intent);
            }
        });
        reclamations=(ImageView) findViewById(R.id.recla);
        reclamations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_employee.this,reclamations_employee.class);
                startActivity(intent);
            }
        });
        rate=(ImageView) findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_employee.this,rate_us_employee.class);
                startActivity(intent);
            }
        });

    }
}