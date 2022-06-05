package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class navigation_bar_employee extends AppCompatActivity {
    ImageView info;
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

    }
}