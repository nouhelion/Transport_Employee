package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class trajet_det_employee extends AppCompatActivity {
    ImageView menu;
    TextView depart,date,actuelle,nomCond,numVehicule,couleur,marque;
    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trajet_det_employee);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(trajet_det_employee.this,navigation_bar_employee.class);
                startActivity(intent);
            }
        });

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());
        depart=(TextView) findViewById(R.id.stationDepart);
        actuelle=(TextView) findViewById(R.id.actuelle);
        nomCond=(TextView) findViewById(R.id.NOMconducteur);
        numVehicule=(TextView) findViewById(R.id.Numero_vehicule);
        couleur=(TextView) findViewById(R.id.couleur);
        marque=(TextView) findViewById(R.id.marque);
        date=(TextView) findViewById(R.id.date);
        date.setText(currentDateandTime);

    }

    @Override
    public void onStart(){
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid=user.getUid();
        DocumentReference reference,refCond,redVehicule;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        reference=firestore.collection("Employe").document(currentuid);
        refCond=firestore.collection("conducteur").document("JvvA1NBzRHZ8kimjF7bxvKE0w322");
        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String stationDepart;
                            stationDepart=task.getResult().getString("station");
                            depart.setText(stationDepart);

                        }

                    }
                });
        refCond
               .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String Nom,Prenom;
                            Nom=task.getResult().getString("nom");
                            Prenom=task.getResult().getString("prenom");

                            nomCond.setText(new StringBuilder().append(Nom).append(" ").append(Prenom).toString());

                        }

                    }
                });

        db.collection("Vehicule")
                .whereEqualTo("idConducteur","JvvA1NBzRHZ8kimjF7bxvKE0w322")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String immatriculation,cleur,type;
                                immatriculation=document.getString("immatriculation");
                                cleur=document.getString("couleur");
                                 type=document.getString("marque");
                                numVehicule.setText(immatriculation);
                                couleur.setText(cleur);
                                marque.setText(type);


                            }
                        }
                    }
                });
    }
}