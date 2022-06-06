package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class tencours_driver extends AppCompatActivity {
    ImageView menu;
    TextView stations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencours_driver);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tencours_driver.this,navigation_bar_driver.class);
                startActivity(intent);
            }
        });
        stations=(TextView) findViewById(R.id.liste_des_s);
        stations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tencours_driver.this,liste_stations_driver.class);
                startActivity(intent);
            }
        });

    }
}