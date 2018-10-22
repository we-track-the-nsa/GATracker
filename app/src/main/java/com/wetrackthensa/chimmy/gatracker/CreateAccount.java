package com.wetrackthensa.chimmy.gatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    private EditText FirstName;
    private EditText LastName;
    private EditText Email;
    private EditText Password;
    private Button CreateBtn;
    private TextView LoginLink;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        FirstName = findViewById(R.id.etFirstName);
        LastName = findViewById(R.id.etLastName);
        Email = findViewById(R.id.etEmail);
        CreateBtn = findViewById(R.id.btnCreate);
        Password = findViewById(R.id.caPassword);
        LoginLink = findViewById(R.id.tvLoginLink);

        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString();
                String pass = Password.getText().toString();

                intent = new Intent(CreateAccount.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
