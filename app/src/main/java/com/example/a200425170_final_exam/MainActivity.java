package com.example.a200425170_final_exam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPassword;
    Button buttonLogin;
    private FirebaseAuth db;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        progressDialog = new ProgressDialog(this);
        db = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        final String loginUser = editTextEmail.getText().toString().trim();
        String loginPassword = editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(loginUser)){
            Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(loginPassword)){
            Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Logging in please wait...");
        progressDialog.show();

        db.signInWithEmailAndPassword(loginUser, loginPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                  Intent i = new Intent(MainActivity.this, Send_Email.class);

                    i.putExtra("user",loginUser);
                    startActivity(i);


                    //set text field vale null

                    // display some message
                    Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();


                }
                else{
                    //display some message here
                    Toast.makeText(MainActivity.this, "Login Error", Toast.LENGTH_SHORT).show();

                }
                progressDialog.dismiss();
            }
        });

    }

    public void signIn(View view) {
        Intent i = new Intent(MainActivity.this, Register.class);
                    startActivity(i);

    }
}

