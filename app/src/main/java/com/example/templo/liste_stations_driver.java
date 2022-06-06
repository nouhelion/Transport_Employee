package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class liste_stations_driver extends AppCompatActivity {
    ImageView menu;
    TextView employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_stations_driver);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(liste_stations_driver.this,navigation_bar_driver.class);
                startActivity(intent);
            }
        });
        employees=(TextView) findViewById(R.id.liste_des_e);
        employees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(liste_stations_driver.this,liste_employes_driver.class);
                startActivity(intent);
            }
        });
    }
}