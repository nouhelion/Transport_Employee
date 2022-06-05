package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.templo.Employe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class informations_employee extends AppCompatActivity implements View.OnClickListener {

    // creating variables for our edit text
    private EditText editNom,editPrenom,editCin,editAdresse,editTelephone,editDatenaissance;

    // creating a strings for storing our values from Edittext fields.
    private String Nom,Prenom,Cin,Adresse,Telephone,Datenaissance;

    private Employe employe;

    //button
    Button modify;

    // creating a variable for firebasefirestore.
    private FirebaseFirestore db;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations_employee);
        employe =new Employe();
        employe = (Employe) getIntent().getSerializableExtra("Employe");

        // getting our instance from Firebase Firestore.
        db = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        // initializing our edittext and buttons
        editNom= findViewById(R.id.nom);
        editPrenom = findViewById(R.id.prenom);
        editCin = findViewById(R.id.cin);
        editAdresse= findViewById(R.id.adresse);
        editTelephone = findViewById(R.id.telephone);
        editDatenaissance = findViewById(R.id.datenaissance);

        editNom.setText(employe.getNom());
        editPrenom.setText(employe.getPrenom());
        editCin.setText(employe.getCin());
        editAdresse.setText(employe.getAdresse());
        editTelephone.setText(employe.getTelephone());
        editDatenaissance.setText(employe.getDatenaissance());

        // creating variable for button
        findViewById(R.id.cont).setOnClickListener(this);

    }

    private boolean hasValidationErrors( String Nom, String Prenom, String Cin, String Adresse, String Datenaissance, String Telephone) {
        if (Nom.isEmpty()) {
            editNom.setError("Name required");
            editNom.requestFocus();
            return true;
        }

        if (Prenom.isEmpty()) {
            editPrenom.setError("Brand required");
            editPrenom.requestFocus();
            return true;
        }

        if (Cin.isEmpty()) {
            editCin.setError("Description required");
            editCin.requestFocus();
            return true;
        }

        if (Adresse.isEmpty()) {
            editAdresse.setError("Price required");
            editAdresse.requestFocus();
            return true;
        }

        if (Datenaissance.isEmpty()) {
            editDatenaissance.setError("Quantity required");
            editDatenaissance.requestFocus();
            return true;
        }

        if (Telephone.isEmpty()) {
            editTelephone.setError("Quantity required");
            editTelephone.requestFocus();
            return true;
        }
        return false;
    }


    private void updateDocument() {
        Nom=editNom.getText().toString().trim();
        Prenom=editPrenom.getText().toString().trim();
        Cin=editCin.getText().toString().trim();
        Adresse=editAdresse.getText().toString().trim();
        Telephone=editTelephone.getText().toString().trim();
        Datenaissance=editDatenaissance.getText().toString().trim();
        if (!hasValidationErrors(Nom,Prenom,Cin,Adresse,Datenaissance,Telephone)) {
            Employe e = new Employe(Nom, Prenom, Cin, Adresse, Datenaissance, Telephone);
            db.collection("Employe")
                    .document(user.getUid())
                    .update("nom",e.getNom(),
                            "prenom",e.getPrenom(),
                            "cin",e.getCin(),
                            "adresse",e.getAdresse(),
                            "telephone",e.getTelephone(),
                            "datenaissance",e.getDatenaissance()
                    )
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(informations_employee.this, "Modification Réussie", Toast.LENGTH_LONG).show();
                        }
                    });
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cont:
                updateDocument();
                break;
        }
    }
}