package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HnoFeladatok extends AppCompatActivity {

    ListView listView;
    ArrayList<Feladat> feladatokArrayList = new ArrayList<>();
    ArrayAdapter<Feladat> arrayAdapter;
    DatabaseReference db;
    String szolgaID, feladatID, allapot;
     public static Feladat feladat = new Feladat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hno_feladatok);

        listView = (ListView) findViewById(R.id.hnoFeladatokLV);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feladatokArrayList);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            feladatID = feladatokArrayList.get(position).feladatID;
            szolgaID = feladatokArrayList.get(position).szolgaID;
            allapot = feladatokArrayList.get(position).allapot;
            arrayAdapter.notifyDataSetChanged();

            //szerkesztos oldal megnyitasa
            Intent szerkeszt = new Intent(this,HnoFeladatSzerkeszt.class);
            szerkeszt.putExtra("feladatID", feladatID);
            szerkeszt.putExtra("szolgaID", szolgaID);
            startActivity(szerkeszt);

        });
        loadData();

    }
    @Override
    public void onRestart() {
        super.onRestart();
        arrayAdapter.clear();
        loadData();
    }

    public void loadData() {
        db = FirebaseDatabase.getInstance().getReference("feladatok");
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                feladatID = snapshot.getKey();
                feladat.setFeladatID(feladatID);
                allapot = snapshot.child("allapot").getValue().toString();
                feladat.setAllapot(allapot);
                if ((snapshot.child("szolgaID").getValue()) != null) {
                    szolgaID = snapshot.child("szolgaID").getValue().toString();
                } else {
                    szolgaID = "még nincs";
                }
                feladatokArrayList.add(new Feladat(feladatID, szolgaID, allapot));
                arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}