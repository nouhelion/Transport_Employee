package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class rate_us_employee extends AppCompatActivity {

    RatingBar rating;

    ImageView menu,add;

    // creating variables for our edit text
    private EditText editCommentaire;

    // creating a strings for storing our values from Edittext fields.
    private String Commentaire,Nom,Prenom;
    private Float numberStars;

    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us_employee);

        // initializing our edittext
        editCommentaire= findViewById(R.id.evalinput);

        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(rate_us_employee.this,navigation_bar_employee.class);
                startActivity(intent);
            }
        });
        rating=(RatingBar) findViewById(R.id.RatingBar);
        LayerDrawable stars = (LayerDrawable) rating.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(ContextCompat.getColor(this, R.color.green), PorterDuff.Mode.SRC_ATOP);


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
        numberStars=rating.getRating();
        add=(ImageView) findViewById(R.id.arrow);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Commentaire=editCommentaire.getText().toString();
                Map<String, Object> data = new HashMap<>();
                data.put("nom complet",Nom +" "+Prenom);
                data.put("message", Commentaire);
                data.put("nombre etoiles",numberStars);
                db.collection("Commentaire")
                        .document(currentuid)
                        .set(data)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(rate_us_employee.this, "Commentaire ajoutée avec succes "+numberStars, Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(rate_us_employee.this, "Erreur", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });



    }
}