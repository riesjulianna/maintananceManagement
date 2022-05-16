package com.example.mm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

public class SzolgaMegkezdett extends AppCompatActivity  {

    Button befejez;
    ListView listview;
    DatabaseReference db;
    ArrayList<Feladat> feladatokArrayList = new ArrayList<>();
    ArrayAdapter<Feladat> arrayAdapter;
    String loggedIN="";
    String feladatID = "";
    String szolgaID = "";
    String allapot = "";
    String instrukcio = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_megkezdettfeladatok);

        User user=MainActivity.user;
        loggedIN=user.id;

        befejez=findViewById(R.id.buttonBefejez);

        listview = (ListView) findViewById(R.id.listviewFeladatok);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, feladatokArrayList);
        listview.setAdapter(arrayAdapter);
        listview.setOnItemClickListener((parent, view, position, id) -> {
            feladatID = feladatokArrayList.get(position).feladatID;
            szolgaID = feladatokArrayList.get(position).szolgaID;
            allapot = feladatokArrayList.get(position).allapot;
            instrukcio = feladatokArrayList.get(position).instrukcio;
            arrayAdapter.notifyDataSetChanged();
        });

        load();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void BefejezOnClick(View v)
    {
        // Adatbázis hívás
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbFeladatokRef = db.getReference("feladatok");

        if(feladatID != null){
            dbFeladatokRef.child(feladatID).child("allapot").setValue("Befejezve");
            Toast.makeText(this, "Sikeresen Befejezve a(z) "+feladatID+" sorszámú feladat!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Válassz ki egy feladatot!", Toast.LENGTH_LONG).show();
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
                if((snapshot.child("instrukcio").getValue()) != null) {
                    instrukcio = snapshot.child("instrukcio").getValue().toString();
                }else{
                    instrukcio="nem található";
                }
                feladatokArrayList.removeIf(obj -> (obj.feladatID.equals(feladatID)));
                feladatokArrayList.add(new Feladat(feladatID, szolgaID, allapot, instrukcio));
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN) || !obj.allapot.equals("Megkezdve")));
                arrayAdapter.notifyDataSetChanged();
                feladatID = null;
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                feladatID = snapshot.getKey();
                allapot = snapshot.child("allapot").getValue().toString();
                if ((snapshot.child("szolgaID").getValue()) != null) {
                    szolgaID = snapshot.child("szolgaID").getValue().toString();
                } else {
                    szolgaID = "nincs";
                }
                if((snapshot.child("instrukcio").getValue()) != null) {
                    instrukcio = snapshot.child("instrukcio").getValue().toString();
                }else{
                    instrukcio="nem található";
                }
                feladatokArrayList.removeIf(obj -> (obj.feladatID.equals(feladatID)));
                feladatokArrayList.add(new Feladat(feladatID, szolgaID, allapot, instrukcio));
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN) || !obj.allapot.equals("Megkezdve")));
                arrayAdapter.notifyDataSetChanged();
                feladatID = null;
            }
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                feladatID = snapshot.getKey();
                allapot = snapshot.child("allapot").getValue().toString();
                if ((snapshot.child("szolgaID").getValue()) != null) {
                    szolgaID = snapshot.child("szolgaID").getValue().toString();
                } else {
                    szolgaID = "nincs";
                }
                if((snapshot.child("instrukcio").getValue()) != null) {
                    instrukcio = snapshot.child("instrukcio").getValue().toString();
                }else{
                    instrukcio="nem található";
                }
                feladatokArrayList.removeIf(obj -> (obj.feladatID.equals(feladatID)));
                feladatokArrayList.add(new Feladat(feladatID, szolgaID, allapot, instrukcio));
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN) || !obj.allapot.equals("Megkezdve")));
                arrayAdapter.notifyDataSetChanged();
                feladatID = null;
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

