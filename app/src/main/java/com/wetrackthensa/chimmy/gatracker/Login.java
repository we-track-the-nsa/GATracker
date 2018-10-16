package com.wetrackthensa.chimmy.gatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {
    private Intent intent,crIntent;
    private EditText Username;
    private EditText Password;
    private Button Login;
    private TextView CreateLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = (EditText)findViewById(R.id.etUserName);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btnLogin);
        CreateLink = (TextView)findViewById(R.id.tvCreateLink);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Username.getText().toString(),Password.getText().toString());
            }
        });
        CreateLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crIntent = new Intent(Login.this,CreateAccount.class);
                startActivity(crIntent);
            }
        });
    }

    private void validate(String username, String password){
        if(username.equals("admin") && password.equals("1234")){
            intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);

        }
    }
}
