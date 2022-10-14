package com.example.newsapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login1 extends AppCompatActivity {

    TextView textsignup;
    Button login;
    EditText username;
    EditText password;
    FirebaseAuth firebaseAuth;
    AlertDialog.Builder reset_alert;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        firebaseAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        textsignup = findViewById(R.id.signuptextview);
        login = findViewById(R.id.btnlogin);
        reset_alert=new AlertDialog.Builder(this);
        inflater =this.getLayoutInflater();

        textsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login1.this, register.class));

            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //extract and validate
                if(username.getText().toString().isEmpty())
                {
                    username.setError("Email is missing");
                    return;
                }

                if(password.getText().toString().isEmpty())
                {
                    password.setError("Password is missing");
                    return;
                }
                //data is valid
                //login user
                firebaseAuth.signInWithEmailAndPassword(username.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login1.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
    }
}