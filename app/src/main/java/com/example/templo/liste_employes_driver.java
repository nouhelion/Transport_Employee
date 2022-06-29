package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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
    String text;

    // creating a variable for our
    // grid view, arraylist and
    // firebase Firestore.
    GridView Employes;
    ArrayList<Employe> EmployeArrayList;




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
        Stations.add("station");
        StationsRef
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

        // below line is use to initialize our variables.
        Employes = findViewById(R.id.idEmployes);
        EmployeArrayList = new ArrayList<>();
        // initializing our variable for firebase
        // firestore and getting its instance.
        db = FirebaseFirestore.getInstance();

        //stat=(TextView)findViewById(R.id.status) ;


        stations_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 text=Stations.get(i);
                Toast.makeText(liste_employes_driver.this, "station : "+ text, Toast.LENGTH_SHORT).show();
                EmployeArrayList.clear();
                 loadDatainGridView(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void loadDatainGridView(String text) {

        // below line is use to get data from Firebase
        // firestore using collection in android.
        db.collection("Employe")
                .whereEqualTo("station",text)
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
                               Employe dataModal = d.toObject(Employe.class);
                                // after getting data from Firebase
                                // we are storing that data in our array list
                                EmployeArrayList.add(dataModal);
                            }
                            // after that we are passing our array list to our adapter class.
                            EmployeAdapter adapter = new EmployeAdapter(liste_employes_driver.this, EmployeArrayList);

                            // after passing this array list
                            // to our adapter class we are setting
                            // our adapter to our list view.
                            Employes.setAdapter(adapter);
                        } else {
                            // if the snapshot is empty we are displaying a toast message.
                            Toast.makeText(liste_employes_driver.this, "No data found in Database", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // we are displaying a toast message
                        // when we get any error from Firebase.
                        Toast.makeText(liste_employes_driver.this, "Fail to load data..", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}

