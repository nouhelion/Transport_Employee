package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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

import java.text.SimpleDateFormat;
import java.util.Date;

public class tencours_employee extends AppCompatActivity {
    ImageView menu;
    ConstraintLayout details;
    TextView history,depart,date;
    // creating a variable for firebasefirestore.
    private FirebaseUser user;
    FirebaseFirestore db= FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tencours_employee);
        menu=(ImageView) findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tencours_employee.this,navigation_bar_employee.class);
                startActivity(intent);
            }
        });
        history=(TextView) findViewById(R.id.historique_);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tencours_employee.this,historique_employee.class);
                startActivity(intent);
            }
        });
        details=(ConstraintLayout)findViewById(R.id.pickup);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(tencours_employee.this,trajet_det_employee.class);
                startActivity(intent);
            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String currentDateandTime = sdf.format(new Date());
        depart=(TextView) findViewById(R.id.stationDepart);
        date=(TextView) findViewById(R.id.date);
        date.setText(currentDateandTime);
    }

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
                            String stationDepart;
                            stationDepart=task.getResult().getString("station");
                            depart.setText(stationDepart);

                        }

                    }
                });
    }
}