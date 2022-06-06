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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class informations_employee extends AppCompatActivity {

    // creating variables for our edit text
    private EditText editNom,editPrenom,editCin,editAdresse,editTelephone,editDatenaissance;

    // creating a strings for storing our values from Edittext fields.
    private String Nom,Prenom,Cin,Adresse,Telephone,Datenaissance;

    private Employe employe;

    //button
    Button modify;

    //imageview
    ImageView menu;

    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations_employee);


        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(informations_employee.this,navigation_bar_employee.class);
                startActivity(intent);
            }
        });


        //employe = (Employe) getIntent().getSerializableExtra("Employe");

        // initializing our edittext and buttons
        editNom= findViewById(R.id.nom);
        editPrenom = findViewById(R.id.prenom);
        editCin = findViewById(R.id.cin);
        editAdresse= findViewById(R.id.adresse);
        editTelephone = findViewById(R.id.telephone);
        editDatenaissance = findViewById(R.id.datenaissance);



        // creating variable for button
        //findViewById(R.id.cont).setOnClickListener(this);
       modify=(Button) findViewById(R.id.cont);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDocument();
            }
        });

    }

    private boolean hasValidationErrors( String Nom, String Prenom, String Cin, String Adresse, String Datenaissance, String Telephone) {
        if (Nom.isEmpty()) {
            editNom.setError("Remplissez votre nom");
            editNom.requestFocus();
            return true;
        }

        if (Prenom.isEmpty()) {
            editPrenom.setError("Remplissez votre prenom");
            editPrenom.requestFocus();
            return true;
        }

        if (Cin.isEmpty()) {
            editCin.setError("Remplissez votre cin");
            editCin.requestFocus();
            return true;
        }

        if (Adresse.isEmpty()) {
            editAdresse.setError("Remplissez votre adresse");
            editAdresse.requestFocus();
            return true;
        }

        if (Datenaissance.isEmpty()) {
            editDatenaissance.setError("Remplissez votre date de naissance");
            editDatenaissance.requestFocus();
            return true;
        }

        if (Telephone.isEmpty()) {
            editTelephone.setError("Remplissez votre telephone");
            editTelephone.requestFocus();
            return true;
        }
        return false;
    }

 ////aaaaaaaaaaaaa it worked worked ooof
    private void updateDocument() {
        Nom=editNom.getText().toString();
        Prenom=editPrenom.getText().toString();
        Cin=editCin.getText().toString();
        Adresse=editAdresse.getText().toString();
        Telephone=editTelephone.getText().toString();
        Datenaissance=editDatenaissance.getText().toString();
        if (!hasValidationErrors(Nom,Prenom,Cin,Adresse,Datenaissance,Telephone)) {
            db.collection("Employe")
                    .document(user.getUid())
                    .update("nom",Nom,
                            "prenom",Prenom,
                            "cin",Cin,
                            "adresse",Adresse,
                            "telephone",Telephone,
                            "datenaissance",Datenaissance
                    )
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(informations_employee.this, "Modification Réussie", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }


    ///dont touch it its working oooooooooof hmd hmd
    @Override
    public void onStart(){
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid=user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        reference=firestore.collection("Employe").document(currentuid);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String Nom,Prenom,Cin,Adresse,Datenaissance,Telephone;
                            Nom=task.getResult().getString("nom");
                            Prenom=task.getResult().getString("prenom");
                            Cin=task.getResult().getString("cin");
                            Adresse=task.getResult().getString("adresse");
                            Datenaissance=task.getResult().getString("datenaissance");
                            Telephone=task.getResult().getString("telephone");

                            editNom.setText(Nom);
                            editPrenom.setText(Prenom);
                            editCin.setText(Cin);
                            editAdresse.setText(Adresse);
                            editDatenaissance.setText(Datenaissance);
                            editTelephone.setText(Telephone);
                        }

                    }
                });
    }


}