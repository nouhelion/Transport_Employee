package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class tencours_employee extends AppCompatActivity {
    ImageView menu,add;
    ConstraintLayout details;
    TextView history,depart,date;
    EditText editStation;
    Integer tod,tom,currentHour;
    Calendar rightNow;
    RadioGroup radioGroup;
    RadioButton today,tomorrow;
    String currentDateandTime,time,Dept;
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
        currentDateandTime = sdf.format(new Date());
        SimpleDateFormat sf = new SimpleDateFormat("HH:mm");
        time = sf.format(new Date());
        depart=(TextView) findViewById(R.id.stationDepart);
        date=(TextView) findViewById(R.id.date);
        date.setText(currentDateandTime);



        //radio shit
        radioGroup = (RadioGroup)findViewById(R.id.group);
        today=(RadioButton)findViewById(R.id.today);//1000080
        tomorrow=(RadioButton)findViewById(R.id.tomorrow);//1000018





        rightNow = Calendar.getInstance();
        currentHour =rightNow.get(Calendar.HOUR);
        // initializing our edittext
        editStation=(EditText) findViewById(R.id.dep);

        add=(ImageView) findViewById(R.id.arrow);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dept=editStation.getText().toString();
                int btn = radioGroup.getCheckedRadioButtonId();
                switch (btn) {
                    case R.id.today:
                        if(currentHour<07){
                            db.collection("Employe")
                                    .document(user.getUid())
                                    .update("station",Dept)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(tencours_employee.this, "changement de "+Dept, Toast.LENGTH_LONG).show();
                                        }
                                    });
                        }else if (currentHour>7) {
                            Toast.makeText(tencours_employee.this, "Impossible de changer la station", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.tomorrow:
                        //other checks for the other RadioButtons ids from the RadioGroup
                        db.collection("Employe")
                                .document(user.getUid())
                                .update("station",Dept)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(tencours_employee.this, "changement de station Réussie pour demain", Toast.LENGTH_LONG).show();
                                    }
                                });
                        break;
                    case -1:
                        // no RadioButton is checked inthe Radiogroup
                        Toast.makeText(tencours_employee.this, "Veuillez choisir la journée", Toast.LENGTH_LONG).show();
                        break;
                }


            }
        });


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