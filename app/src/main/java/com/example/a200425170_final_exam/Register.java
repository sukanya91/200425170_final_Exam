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

public class Register extends AppCompatActivity {

    EditText editTextEmail1;
    EditText editTextPassword1;
    EditText editTextConfirmPassword;
    Button buttonSignUp;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail1= (EditText) findViewById(R.id.editTextEmail1);
        editTextPassword1=( EditText) findViewById(R.id.editTextPassword1);
        editTextConfirmPassword= (  EditText) findViewById(R.id.editTextConfirmPassword);
        buttonSignUp= (Button) findViewById(R.id.buttonRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

    }

    public void registerNow(View view) {
        String email = editTextEmail1.getText().toString().trim();
        String password = editTextPassword1.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter your Password", Toast.LENGTH_LONG).show();
            return;

        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please confirm your Password", Toast.LENGTH_LONG).show();
            return;

        }


        if (confirmPassword.equals(password)) {
            progressDialog.setMessage("Registering please wait...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //checking if success
                    if (task.isSuccessful()) {
                        //display some message
                        Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Register.this, MainActivity.class));

                        //set text fields null
                        editTextEmail1.setText("");
                        editTextPassword1.setText("");
                        editTextConfirmPassword.setText("");

                    } else {
                        //display some message here
                        Toast.makeText(Register.this, "Registration Error", Toast.LENGTH_LONG).show();
                    }
                    progressDialog.dismiss();

                }
            });


        }else {
            Toast.makeText(this, "Confirm password is incorrect", Toast.LENGTH_SHORT).show();
            return;
        }

    }

}
