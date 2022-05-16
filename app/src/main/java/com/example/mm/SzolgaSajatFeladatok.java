package com.example.mm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SzolgaSajatFeladatok extends AppCompatActivity implements View.OnClickListener {

    Button elfogad, elutasit, kezd;
    ListView listview;
    DatabaseReference db;
    ArrayList<Feladat> feladatokArrayList = new ArrayList<>();
    ArrayAdapter<Feladat> arrayAdapter;
    String loggedIN="";
    String feladatID = "";
    String szolgaID = "";
    String allapot = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_sajatfeladatok);

        User user=MainActivity.user;
        loggedIN=user.id;

        listview = (ListView) findViewById(R.id.listviewFeladatok);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feladatokArrayList);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            feladatID = feladatokArrayList.get(position).feladatID;
            szolgaID = feladatokArrayList.get(position).szolgaID;
            allapot = feladatokArrayList.get(position).allapot;
            arrayAdapter.notifyDataSetChanged();
        });

        elfogad = findViewById(R.id.buttonElfogad);
        elutasit = findViewById(R.id.buttonElutasit);
        kezd = findViewById(R.id.buttonKezd);
        elfogad.setOnClickListener(this);
        elutasit.setOnClickListener(this);
        kezd.setOnClickListener(this);

        load();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonElfogad:
                //TODO: feladat elfogadásának rögzitése adatbázisba
                break;
            case R.id.buttonElutasit:
                Intent elutasit = new Intent(this, SzolgaIndoklas.class);
                startActivity(elutasit);
                break;
            case R.id.buttonKezd:
                //TODO: feladat megkezdésének beállítása
        }
    }

    public void load() {
        db = FirebaseDatabase.getInstance().getReference("feladatok");
        db.addChildEventListener(new ChildEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                feladatID = snapshot.getKey();
                allapot = snapshot.child("allapot").getValue().toString();
                if ((snapshot.child("szolgaID").getValue()) != null) {
                    szolgaID = snapshot.child("szolgaID").getValue().toString();
                } else {
                    szolgaID = "nincs";
                }
                feladatokArrayList.add(new Feladat(feladatID, szolgaID, allapot));
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN)));
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

//LOGOLÁS