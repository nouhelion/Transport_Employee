package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import java.util.HashMap;
import java.util.Map;

public class trajet_det_driver extends AppCompatActivity {
    ImageView menu,add;
    TextView depart,date;
    EditText editRetard;
    String retard,immatriculation,stationAC;
    String currentDateandTime;

    // creating a variable for firebasefirestore.
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trajet_det_driver);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(trajet_det_driver.this,navigation_bar_driver.class);
                startActivity(intent);
            }
        });
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        currentDateandTime = sdf.format(new Date());
        depart=(TextView) findViewById(R.id.stationAC);
        date=(TextView) findViewById(R.id.date);
        date.setText(currentDateandTime);
        editRetard=(EditText)findViewById(R.id.rettard);


        add=(ImageView) findViewById(R.id.arrow);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retard=editRetard.getText().toString();

                db.collection("Vehicule")
                        .whereEqualTo("idConducteur",user.getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        immatriculation=document.getString("immatriculation");
                                        db.collection("Retard")
                                                .document(user.getUid())
                                                .update("immatriculation",immatriculation);
                                    }
                                }
                            }
                        });

                db.collection("conducteur")
                        .document(user.getUid())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.getResult().exists()){

                                    stationAC=task.getResult().getString("stationActuelle");
                                    db.collection("Retard")
                                            .document(user.getUid())
                                            .update("stationActuelle",stationAC);

                                }

                            }
                        });

                db.collection("Retard")
                        .document(user.getUid())
                        .update("cause",retard,
                                "date", currentDateandTime)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(trajet_det_driver.this, "Ajout de Retard Réussie", Toast.LENGTH_LONG).show();
                            }
                        });


            }
        });
    }

    @Override
    public void onStart(){
        super.onStart();

        DocumentReference reference,refCond,redVehicule;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();

        refCond=firestore.collection("conducteur").document(user.getUid());
        refCond.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String stationDepart;
                            stationDepart=task.getResult().getString("stationActuelle");
                            depart.setText(stationDepart);

                        }

                    }
                });





    }
}