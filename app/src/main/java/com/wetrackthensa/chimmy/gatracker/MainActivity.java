package com.wetrackthensa.chimmy.gatracker;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import javax.annotation.Nullable;

import io.opencensus.tags.Tag;
import com.google.firebase.firestore.Query.Direction;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG="Firelog";
    private RecyclerView mMainList;
    private FirebaseFirestore mFirestore;
    private UpdateListAdapter updateListAdapter;
    private List<updates> updatesList, finalUpdatesList;
    private Intent intent,crIntent;
    private Button search,set;
    private FirebaseAuth mAuth;
    private priority mypriority = new priority();
    private boolean gotCBP,gotCIA,gotDoD,gotFBI,gotICE, gotNSA, gotJD = false;

    protected void onCreate(Bundle savedInstanceState)
{
    mAuth = FirebaseAuth.getInstance();


    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    updatesList=new ArrayList<>();
    finalUpdatesList=new ArrayList<>();
    updateListAdapter=new UpdateListAdapter(updatesList);
    mMainList= (RecyclerView) findViewById(R.id.main_list);
    mMainList.setHasFixedSize(true);
    mMainList.setLayoutManager(new LinearLayoutManager(this));
    mMainList.setAdapter(updateListAdapter);
    mFirestore=FirebaseFirestore.getInstance();

    // get user info

    Log.d("AUTH", "Email data: " + mAuth.getCurrentUser().getEmail());
    DocumentReference docRef = mFirestore.collection("Users").document(mAuth.getCurrentUser().getEmail());
    docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    try {
                        JSONObject jObject = new JSONObject(document.getData());
                        mypriority.setCBP(jObject.getBoolean("CBP"));
                        mypriority.setCIA(jObject.getBoolean("CIA"));
                        mypriority.setDeptofDefense(jObject.getBoolean("DeptofDefense"));
                        mypriority.setFBI(jObject.getBoolean("FBI"));
                        mypriority.setICE(jObject.getBoolean("ICE"));
                        mypriority.setNSAGov(jObject.getBoolean("NSAGov"));
                        mypriority.setTheJusticeDept(jObject.getBoolean("TheJusticeDept"));

                        Log.d("data check", "CBP is " + mypriority.isCBP());
                        Log.d("data check", "CIA is " + mypriority.isCIA());
                        Log.d("data check", "DeptofDefense is " + mypriority.isDeptofDefense());
                        Log.d("data check", "FBI is " + mypriority.isFBI());
                        Log.d("data check", "ICE is " + mypriority.isICE());
                        Log.d("data check", "NSAGov is " + mypriority.isNSAGov());
                        Log.d("data check", "TheJusticeDept is " + mypriority.isTheJusticeDept());

                        List<String> agencyList = new ArrayList<String>(mypriority.whatsTrue());
                        Log.d("agencyList", "list len: " + agencyList.size());

                        for(String agency : agencyList){
                            Log.d("agencyList", "whats true: " + agency);
                        }

                        final String myCollection = "All Updates";

                        Query cbpQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "CBP").orderBy("time", Direction.DESCENDING);
                        final Task cbpTask = cbpQuery.get();

                        Query ciaQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "CIA").orderBy("time", Direction.DESCENDING);
                        final Task ciaTask = ciaQuery.get();

                        Query dodQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "DeptofDefense").orderBy("time", Direction.DESCENDING);
                        final Task dodTask = dodQuery.get();

                        Query fbiQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "FBI").orderBy("time", Direction.DESCENDING);
                        final Task fbiTask = fbiQuery.get();

                        Query iceQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "ICEgov").orderBy("time", Direction.DESCENDING);
                        final Task iceTask = iceQuery.get();

                        Query nsaQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "NSAGov").orderBy("time", Direction.DESCENDING);
                        final Task nsaTask = nsaQuery.get();

                        Query jdQuery = mFirestore.collection(myCollection).whereEqualTo("agency", "TheJusticeDept").orderBy("time", Direction.DESCENDING);
                        final Task jdTask = jdQuery.get();


                        cbpTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if(mypriority.isCBP()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.d("cbp listener", document.getData().toString());
                                            updates ups = document.toObject(updates.class);
                                            updatesList.add(ups);

                                        }
                                        updateListAdapter.notifyDataSetChanged();
                                    }
                                    ciaTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                            if (task.isSuccessful()) {
                                                if(mypriority.isCIA()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        Log.d("cia listener", document.getData().toString());
                                                        updates ups = document.toObject(updates.class);
                                                        updatesList.add(ups);
                                                    }
                                                    updateListAdapter.notifyDataSetChanged();
                                                }
                                                dodTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()) {
                                                            if(mypriority.isDeptofDefense()) {
                                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                                    Log.d("dod listener", document.getData().toString());
                                                                    updates ups = document.toObject(updates.class);
                                                                    updatesList.add(ups);
                                                                }
                                                                updateListAdapter.notifyDataSetChanged();
                                                            }
                                                            fbiTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                    if (task.isSuccessful()) {
                                                                        if(mypriority.isFBI()) {
                                                                            for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                Log.d("fbi listener", document.getData().toString());
                                                                                updates ups = document.toObject(updates.class);
                                                                                updatesList.add(ups);
                                                                            }
                                                                            updateListAdapter.notifyDataSetChanged();
                                                                        }
                                                                        iceTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                            @Override
                                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                Log.d("ice listener", "ice is:" + mypriority.isICE());
                                                                                if (task.isSuccessful()) {
                                                                                    if(mypriority.isICE()) {
                                                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                            Log.d("ice listener", document.getData().toString());
                                                                                            updates ups = document.toObject(updates.class);
                                                                                            updatesList.add(ups);
                                                                                        }
                                                                                        updateListAdapter.notifyDataSetChanged();
                                                                                    }
                                                                                    nsaTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                        @Override
                                                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                            if (task.isSuccessful()) {
                                                                                                if(mypriority.isNSAGov()) {
                                                                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                                        Log.d("nsa listener", document.getData().toString());
                                                                                                        updates ups = document.toObject(updates.class);
                                                                                                        updatesList.add(ups);
                                                                                                    }
                                                                                                    updateListAdapter.notifyDataSetChanged();
                                                                                                }
                                                                                                jdTask.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                                                    @Override
                                                                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                                                        if (task.isSuccessful()) {
                                                                                                            if(mypriority.isTheJusticeDept()) {
                                                                                                                for (QueryDocumentSnapshot document : task.getResult()) {
                                                                                                                    Log.d("jd listener", document.getData().toString());
                                                                                                                    updates ups = document.toObject(updates.class);
                                                                                                                    updatesList.add(ups);
                                                                                                                }
                                                                                                                updateListAdapter.notifyDataSetChanged();
                                                                                                            }

                                                                                                            //sort updateList
                                                                                                            Collections.sort(updatesList, new Comparator<updates>() {
                                                                                                                @Override
                                                                                                                public int compare(updates u1, updates u2) {
                                                                                                                    return u2.gettimeh().compareTo(u1.gettimeh());
                                                                                                                }
                                                                                                            });
                                                                                                            updateListAdapter.notifyDataSetChanged();

                                                                                                        } else {
                                                                                                            Log.d("jd listener", "Error getting documents: ", task.getException());
                                                                                                        }
                                                                                                    }
                                                                                                });
                                                                                            } else {
                                                                                                Log.d("nsa listener", "Error getting documents: ", task.getException());
                                                                                            }
                                                                                        }
                                                                                    });
                                                                                } else {
                                                                                    Log.d("ice listener", "Error getting documents: ", task.getException());
                                                                                }
                                                                            }
                                                                        });
                                                                    } else {
                                                                        Log.d("fbi listener", "Error getting documents: ", task.getException());
                                                                    }
                                                                }
                                                            });
                                                        } else {
                                                            Log.d("dod listener", "Error getting documents: ", task.getException());
                                                        }
                                                    }
                                                });
                                            } else {
                                                Log.d("cia listener", "Error getting documents: ", task.getException());
                                            }
                                        }
                                    });
                                } else {
                                    Log.d("cbp listener", "Error getting documents: ", task.getException());
                                }
                            }
                        });

                        search = findViewById(R.id.button3);
                        search.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                crIntent = new Intent(MainActivity.this, test.class);
                                startActivity(crIntent);
                            }
                        });
                        set = findViewById(R.id.sett);
                        set.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                intent = new Intent(MainActivity.this, settings.class);
                                startActivity(intent);
                            }
                        });


//                        mFirestore.collection(myCollection).orderBy("time", Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
//                            @Override
//                            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException e) {
//                                if (e != null) {
//                                    Log.d(TAG, "Error: " + e.getMessage());
//
//                                }
//                                try {
//                                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
//                                        if (doc.getType() == DocumentChange.Type.ADDED) {
//                                            updates ups = doc.getDocument().toObject(updates.class);
//                                            updatesList.add(ups);
//                                            updateListAdapter.notifyDataSetChanged();
//                                        }
//                                    }
//                                } catch (NullPointerException nullptrEx) {
//
//                                }
//                                search = findViewById(R.id.button3);
//                                search.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        crIntent = new Intent(MainActivity.this, test.class);
//                                        startActivity(crIntent);
//                                    }
//                                });
//                                set = findViewById(R.id.sett);
//                                set.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        intent = new Intent(MainActivity.this, settings.class);
//                                        startActivity(intent);
//                                    }
//                                });
//
//
//                            }
//                        });


                    } catch (JSONException e){
                        Log.d("JSON", "ERROR:" + e);
                    }
                } else {
                    Log.d(TAG, "No such document");
                }
            } else {
                Log.d(TAG, "get failed with ", task.getException());
            }
        }
    });

}


}