package com.wetrackthensa.chimmy.gatracker;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
public class test extends AppCompatActivity {
    private EditText agencyname;
    private Button search_b;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
       agencyname=findViewById(R.id.agency_name);
        search_b=findViewById(R.id.search_b);
       search_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = agencyname.getText().toString();
                intent = new Intent(test.this,agencypage.class);
                intent.putExtra("user",user);


                startActivity(intent);
            }
        });
    }



}
