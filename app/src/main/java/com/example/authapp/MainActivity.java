package com.example.authapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
EditText et_email , et_password;
Button signUp;
ProgressBar progressBar ;
 FirebaseAuth mAuth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.progressbar);


        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.passwd);
        signUp = findViewById(R.id.btnregister);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddNewUser();
            }
        });

    FirebaseMessaging.getInstance().subscribeToTopic("dareen")
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Log.e("d","done");
                    }else {
                        Log.e("d","failed");
                    }
                }
            });










      /* FirebaseMessaging.getInstance().subscribeToTopic("dareen")
               .addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {
                       Log.e("d","Done");

                       if(!task.isSuccessful()){
                           Log.e("d","failed");

                       }
                   }
               });

*/





    }

   public void AddNewUser(){
        progressBar.setVisibility(View.VISIBLE);
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(MainActivity.this, "Please fill email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(MainActivity.this, "Please fill password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "welcome", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(MainActivity.this, "sign up failed", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });

   }
}