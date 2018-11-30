package com.wetrackthensa.chimmy.gatracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import io.opencensus.tags.Tag;

public class settings extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mFirestore;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        mFirestore = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prioritieslist);
        // TODO reorder to not skip no spinner 1
        final Spinner JDspinner = (Spinner) findViewById(R.id.spinner4);
        final Spinner DODspinner = (Spinner) findViewById(R.id.spinner2);
        final Spinner CBPspinner = (Spinner) findViewById(R.id.spinner5);
        final Spinner FBIspinner = (Spinner) findViewById(R.id.spinner6);
        final Spinner NSAspinner = (Spinner) findViewById(R.id.spinner7);
        final Spinner ICEspinner = (Spinner) findViewById(R.id.spinner3);
        final Spinner CIAspinner = (Spinner) findViewById(R.id.spinner8);
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

    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            // bool to store if the spinners on set to regular
            Boolean JD = JDspinner.getSelectedItem().toString().compareTo("Reg") == 0;
            Boolean DOD = DODspinner.getSelectedItem().toString().compareTo("Reg") == 0;
            Boolean CBP = CBPspinner.getSelectedItem().toString().compareTo("Reg") == 0;
            Boolean FBI = FBIspinner.getSelectedItem().toString().compareTo("Reg") == 0;
            Boolean NSA = NSAspinner.getSelectedItem().toString().compareTo("Reg") == 0;
            Boolean ICE = ICEspinner.getSelectedItem().toString().compareTo("Reg") == 0;
            Boolean CIA = CIAspinner.getSelectedItem().toString().compareTo("Reg") == 0;

            Map<String, Object> agencies = new HashMap<>();
            agencies.put("TheJusticeDept", JD);
            agencies.put("DeptofDefense", DOD);
            agencies.put("CBP", CBP);
            agencies.put("FBI", FBI);
            agencies.put("NSAGov", NSA);
            agencies.put("ICE", ICE);
            agencies.put("CIA", CIA);

            mAuth = FirebaseAuth.getInstance();

            if(mAuth.getCurrentUser() != null) {
                mFirestore.collection("Users").document(mAuth.getCurrentUser().getEmail())
                        .set(agencies)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("priority list", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("priority list", "Error writing document", e);
                            }
                        });
            }
        }
    });

    }
}

