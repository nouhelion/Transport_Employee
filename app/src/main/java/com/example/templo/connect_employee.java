package com.example.templo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class connect_employee extends AppCompatActivity implements View.OnClickListener{
    ImageView back;
    Button login;
    private EditText editTextemail,editTextpassword;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_employee);
        back=(ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(connect_employee.this,MainActivity.class);
                startActivity(intent);
            }
        });

        //firebase code

        editTextemail=(EditText) findViewById(R.id.in1);
        editTextpassword=(EditText) findViewById(R.id.in2);

        mAuth = FirebaseAuth.getInstance();
        login=(Button)findViewById(R.id.cont);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.cont:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email=editTextemail.getText().toString().trim();
        String password=editTextpassword.getText().toString().trim();
        if(email.isEmpty()){
            editTextemail.setError("l'email est obligatoire");
            editTextemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextemail.setError("entrez un email valid");
            editTextemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextpassword.setError("le mot de passe est obligatoire");
            editTextpassword.requestFocus();
            return;
        }
        if(password.length()<6){
            editTextpassword.setError("le mot de passe est invalide");
            editTextpassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //redirect to welcome
                    Intent intent=new Intent(connect_employee.this,tencours_employee.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(connect_employee.this,"failed to connect! try again!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}