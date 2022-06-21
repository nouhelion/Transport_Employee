package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

import java.util.HashMap;
import java.util.Map;

public class reclamations_employee extends AppCompatActivity {
    ImageView menu,add;

    // creating variables for our edit text
    private EditText editReclamation;

    // creating a strings for storing our values from Edittext fields.
    private String Reclamation,Nom,Prenom;

    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamations_employee);

        // initializing our edittext
        editReclamation= findViewById(R.id.reclinput);

        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(reclamations_employee.this,navigation_bar_employee.class);
                startActivity(intent);
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid=user.getUid();
        reference=db.collection("Employe").document(currentuid);
        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            Nom=task.getResult().getString("nom");
                            Prenom=task.getResult().getString("prenom");
                        }

                    }
                });
        add=(ImageView) findViewById(R.id.arrow);
       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reclamation=editReclamation.getText().toString();
                Map<String, Object> data = new HashMap<>();
                data.put("nom complet",Nom +" "+Prenom);
                data.put("message", Reclamation);
                db.collection("Reclamations")
                        .document(currentuid)
                     .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(reclamations_employee.this, "Réclamation ajoutée avec succes", Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(reclamations_employee.this, "Erreur", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }







}