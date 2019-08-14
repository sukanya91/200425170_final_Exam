package com.example.a200425170_final_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Send_Email extends AppCompatActivity {

    EditText editTextAddress;
    EditText editTextSubject;
    EditText editTextMessage;
    TextView textViewEmail;
    Button buttonSend;
    String user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send__email);

        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextSubject = ( EditText) findViewById(R.id.editTextSubject);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);
        textViewEmail = (TextView) findViewById(R.id.textViewEmailId);
        buttonSend = (Button) findViewById(R.id.buttonSend);

        Bundle bundle = getIntent().getExtras();
               user1=bundle.getString("user");

                textViewEmail.setText(user1);

    }

    public void SendEmail(View view) {

        String addressEmail= editTextAddress.getText().toString();
        String subject = editTextSubject.getText().toString();
        String message = editTextMessage.getText().toString();

        if(TextUtils.isEmpty(addressEmail)){
            Toast.makeText(this, "Please enter email id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(subject)){
            Toast.makeText(this, "Please enter your Subject", Toast.LENGTH_SHORT).show();
            return;
        }
         if(TextUtils.isEmpty(message))
        {
            Toast.makeText(this, "Please enter your Message", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",addressEmail, null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }
}
