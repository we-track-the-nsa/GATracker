package com.wetrackthensa.chimmy.gatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import io.opencensus.tags.Tag;

public class settings extends AppCompatActivity {
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prioritieslist);
        // TODO reorder to not skip no spinner 1
        Spinner JDspinner = (Spinner) findViewById(R.id.spinner4);
        Spinner DODspinner = (Spinner) findViewById(R.id.spinner2);
        Spinner CBPspinner = (Spinner) findViewById(R.id.spinner5);
        Spinner FBIspinner = (Spinner) findViewById(R.id.spinner6);
        Spinner NSAspinner = (Spinner) findViewById(R.id.spinner7);
        Spinner ICEspinner = (Spinner) findViewById(R.id.spinner3);
        Spinner CIAspinner = (Spinner) findViewById(R.id.spinner8);
        Button submit = (Button) findViewById(R.id.submit_button);


    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    JDspinner.setAdapter(adapter);
    DODspinner.setAdapter(adapter);
    CBPspinner.setAdapter(adapter);
    FBIspinner.setAdapter(adapter);
    NSAspinner.setAdapter(adapter);
    ICEspinner.setAdapter(adapter);
    CIAspinner.setAdapter(adapter);

    }
}

