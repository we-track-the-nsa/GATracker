package com.wetrackthensa.chimmy.gatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

        FirstName = (EditText)findViewById(R.id.etFirstName);
        LastName = (EditText)findViewById(R.id.etLastName);
        Email = (EditText)findViewById(R.id.etEmail);
        CreateBtn = (Button)findViewById(R.id.btnCreate);
        Password = (EditText)findViewById(R.id.caPassword);
        LoginLink = (TextView)findViewById(R.id.tvLoginLink);

        LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CreateAccount.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
