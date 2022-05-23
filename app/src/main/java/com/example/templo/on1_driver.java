package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class on1_driver extends AppCompatActivity {

    Button inactive;
    TextView ignorer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on1_driver);
        inactive=(Button) findViewById(R.id.inactive);
        inactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(on1_driver.this,on2_driver.class);
                startActivity(intent);
            }
        });
        ignorer=(TextView) findViewById(R.id.ignorer);
        ignorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(on1_driver.this,connect_driver.class);
                startActivity(intent);
            }
        });
    }
}