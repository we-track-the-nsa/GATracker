package com.wetrackthensa.chimmy.gatracker;

import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import io.opencensus.tags.Tag;
import com.google.firebase.firestore.Query.Direction;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="Firelog";
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;
    private UpdateListAdapter updateListAdapter;
    private List<updates> updatesList;
    private Intent intent,crIntent;
    private Button search,set;
    private FirebaseAuth mAuth;
    private priority mypriority = new priority();

    protected void onCreate(Bundle savedInstanceState)
{
    mAuth = FirebaseAuth.getInstance();


    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    updatesList=new ArrayList<>();
    updateListAdapter=new UpdateListAdapter(updatesList);
    mMainList= (RecyclerView) findViewById(R.id.main_list);
    mMainList.setHasFixedSize(true);
    mMainList.setLayoutManager(new LinearLayoutManager(this));
    mMainList.setAdapter(updateListAdapter);
    mFirestore=FirebaseFirestore.getInstance();

    // get user info

    DocumentReference docRef = mFirestore.collection("Users").document(mAuth.getCurrentUser().getEmail());
    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    priority temp = document.toObject(priority.class);
                    mypriority.copyPriorities(temp);
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        }
    });
    //Log.d(TAG, "data check: CBP is " + mypriority.isCBP());
    // pull collections into one collection
    // push collection into UpdateListAdapter

   final String myCollection = "CIA Updates";

   mFirestore.collection(myCollection).orderBy("time", Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
       @Override
       public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
           if(e !=null)
           {
               Log.d(TAG,"Error: "+e.getMessage());

           }
           for(DocumentChange doc : documentSnapshots.getDocumentChanges())
           {
               if(doc.getType()==DocumentChange.Type.ADDED)
               {
                   updates ups=doc.getDocument().toObject(updates.class);
                   ups.setagency(myCollection);
                   updatesList.add(ups);
                   updateListAdapter.notifyDataSetChanged();
               }
           }
           search=findViewById(R.id.button3);
           search.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   crIntent = new Intent(MainActivity.this,test.class);
                   startActivity(crIntent);
               }
           });
           set=findViewById(R.id.sett);
           set.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   intent = new Intent(MainActivity.this,settings.class);
                   startActivity(intent);
               }
           });



       }
   });
}


}