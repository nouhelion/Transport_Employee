package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class liste_stations_driver extends AppCompatActivity {
    ImageView menu;
    TextView employees;




    // creating a variable for our
    // grid view, arraylist and
    // firebase Firestore.
    GridView Stations;
    ArrayList<Station> StationArrayList;

    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference reference;
    
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

        // below line is use to initialize our variables.
        Stations = findViewById(R.id.idStations);

        StationArrayList = new ArrayList<>();
        // initializing our variable for firebase
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();


        // here we are calling a method 
        // to load data in our list view.
        loadDatainGridView();


    }

    private void loadDatainGridView() {

        // below line is use to get data from Firebase
        // firestore using collection in android.
        db.collection("Station")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // after getting the data we are calling on success method
                        // and inside this method we are checking if the received
                        // query snapshot is empty or not.
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // if the snapshot is not empty we are hiding our
                            // progress bar and adding our data in a list.
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {

                                // after getting this list we are passing
                                // that list to our object class.
                                Station dataModal = d.toObject(Station.class);

                                // after getting data from Firebase
                                // we are storing that data in our array list
                               StationArrayList.add(dataModal);
                            }
                            // after that we are passing our array list to our adapter class.
                           StationAdapter adapter = new StationAdapter(liste_stations_driver.this, StationArrayList);

                            // after passing this array list
                            // to our adapter class we are setting
                            // our adapter to our list view.
                            Stations.setAdapter(adapter);
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(liste_stations_driver.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // we are displaying a toast message
                        // when we get any error from Firebase.
                        Toast.makeText(liste_stations_driver.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}