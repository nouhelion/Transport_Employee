package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class navigation_bar_driver extends AppCompatActivity {
    ImageView info,accueil;
    private TextView name;
    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar_driver);
        info=(ImageView) findViewById(R.id.info);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_driver.this,informations_driver.class);
                startActivity(intent);
            }
        });
        accueil=(ImageView) findViewById(R.id.acc);
        accueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(navigation_bar_driver.this,tencours_driver.class);
                startActivity(intent);
            }
        });
        name=(TextView) findViewById(R.id.nam);

    }

    @Override
    public void onStart(){
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuid=user.getUid();
        DocumentReference reference;
        FirebaseFirestore firestore=FirebaseFirestore.getInstance();
        reference=firestore.collection("conducteur").document(currentuid);

        reference.get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.getResult().exists()){
                            String Nom,Prenom;
                            Nom=task.getResult().getString("nom");
                            Prenom=task.getResult().getString("prenom");

                            name.setText(new StringBuilder().append(Nom).append(" ").append(Prenom).toString());

                        }

                    }
                });
    }
}