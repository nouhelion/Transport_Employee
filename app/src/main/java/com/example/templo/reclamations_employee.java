package com.example.templo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class reclamations_employee extends AppCompatActivity {
    ImageView menu,add;

    // creating variables for our edit text
    private EditText editReclamation;

    // creating a strings for storing our values from Edittext fields.
    private String Reclamation;

    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference documentReference;

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

        add=(ImageView) findViewById(R.id.arrow);
       add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Reclamation=editReclamation.getText().toString();
                Map<String, Object> data = new HashMap<>();
                data.put("message", Reclamation);
                db.collection("Reclamations")
                     .add(data)
                     .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                         @Override
                         public void onSuccess(DocumentReference documentReference) {
                             Toast.makeText(reclamations_employee.this,"success", Toast.LENGTH_LONG).show();
                         }
                     });
            }
        });
    }







}