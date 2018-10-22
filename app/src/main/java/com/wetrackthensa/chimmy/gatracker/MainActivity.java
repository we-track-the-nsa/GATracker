package com.wetrackthensa.chimmy.gatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    public static String readFileAsString(String fileName) {
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /*String login = readFileAsString("login");
        if(login.equals("")){
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
