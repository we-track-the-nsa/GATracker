package com.wetrackthensa.chimmy.gatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Login extends AppCompatActivity {
    private Intent intent,crIntent;
    private EditText Username;
    private EditText Password;
    private Button Login;
    private TextView CreateLink;

    static final int READ_BLOCK_SIZE = 100;

    private void storeFile(String user, String pass){
        String fileName = "login";
        String fileContents = user + ":" + pass;

        try {
            FileOutputStream fileout = openFileOutput(fileName, MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(fileContents);
            outputWriter.close();

            //display file saved message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readFile(){
        //reading text from file
        try {
            FileInputStream fileIn=openFileInput("login");
            InputStreamReader InputRead= new InputStreamReader(fileIn);

            char[] inputBuffer= new char[READ_BLOCK_SIZE];
            String s="";
            int charRead;

            while ((charRead = InputRead.read(inputBuffer))>0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close();
            return s;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void validate(){
        String login = readFile();

        if(login != null){
            String[] values = login.split(":");
            intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        } else {
            //display file saved message
            Toast.makeText(getBaseContext(), "Could not validate account.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.etUserName);
        Password = findViewById(R.id.etPassword);
        Login = findViewById(R.id.btnLogin);
        CreateLink = findViewById(R.id.tvCreateLink);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = Username.getText().toString();
                String pass = Password.getText().toString();

                storeFile(user, pass);
                validate();
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
}
