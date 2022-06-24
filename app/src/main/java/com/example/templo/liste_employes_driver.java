package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class liste_employes_driver extends AppCompatActivity {
    ImageView menu;
    TextView stations;
    Spinner stations_spinner;
    CollectionReference StationsRef;
    FirebaseFirestore db;
    List<String> Stations ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_employes_driver);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(liste_employes_driver.this,navigation_bar_driver.class);
                startActivity(intent);
            }
        });
        stations=(TextView) findViewById(R.id.liste_des_s);
        stations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(liste_employes_driver.this,liste_stations_driver.class);
                startActivity(intent);
            }
        });


        db= FirebaseFirestore.getInstance();
        StationsRef = db.collection("Station");
        stations_spinner= (Spinner) findViewById(R.id.stations_spinner);
        Stations= new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.color_spinner_layout, Stations);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        stations_spinner.setAdapter(adapter);
        StationsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String station = document.getString("nom");
                        Stations.add(station);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });



    }


}

