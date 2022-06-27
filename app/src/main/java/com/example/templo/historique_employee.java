package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class historique_employee extends AppCompatActivity {
    ImageView menu;
    TextView trajet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique_employee);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(historique_employee.this,navigation_bar_employee.class);
                startActivity(intent);
            }
        });
        trajet=(TextView) findViewById(R.id.trajet_en_c);
        trajet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(historique_employee.this,tencours_employee.class);
                startActivity(intent);
            }
        });
    }
}