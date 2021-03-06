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

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import io.opencensus.tags.Tag;

public class agencypage extends AppCompatActivity {
    private static final String TAG = "Firelog";
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;
    private UpdateListAdapter updateListAdapter;
    private List<updates> updatesList;
    private Intent intent, crIntent;
    private Button search;
    private String agency_name;
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            agency_name=extras.getString("user");

        }
        updatesList = new ArrayList<>();
        updateListAdapter = new UpdateListAdapter(updatesList);
        mMainList = (RecyclerView) findViewById(R.id.main_list);
        mMainList.setHasFixedSize(true);
        mMainList.setLayoutManager(new LinearLayoutManager(this));
        mMainList.setAdapter(updateListAdapter);
        mFirestore = FirebaseFirestore.getInstance();

        mFirestore.collection(agency_name).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d(TAG, "Error: " + e.getMessage());

                }
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        updates ups = doc.getDocument().toObject(updates.class);
                        updatesList.add(ups);
                        updateListAdapter.notifyDataSetChanged();
                    }
                }
                search = findViewById(R.id.button3);
                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        crIntent = new Intent(agencypage.this, test.class);
                        startActivity(crIntent);
                    }
                });


            }
        });
    }
}

