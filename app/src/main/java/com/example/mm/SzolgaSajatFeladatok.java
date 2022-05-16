package com.example.mm;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

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
    String instrukcio = "";

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
            instrukcio = feladatokArrayList.get(position).instrukcio;
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
        // Adatbázis hívás
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbFeladatokRef = db.getReference("feladatok");

        switch (view.getId()) {
            case R.id.buttonElfogad:
                //Ellenőrizni kell hogy tényleg választottunk valamit
                if(feladatID != null){
                    dbFeladatokRef.child(feladatID).child("allapot").setValue("Elfogadva");
                    Toast.makeText(this, "Sikeresen Elfogadva a(z) "+feladatID+" sorszámú feladat!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Válassz ki egy feladatot!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonKezd:
                //Ellenőrizni kell hogy tényleg választottunk valamit
                if(feladatID != null){
                    dbFeladatokRef.child(feladatID).child("allapot").setValue("Megkezdve");
                    Toast.makeText(this, "Sikeresen Megkezdve a(z) "+feladatID+" sorszámú feladat!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "Válassz ki egy feladatot!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.buttonElutasit:
                //Ellenőrizni kell hogy tényleg választottunk valamit
                if(feladatID != null){
                    Intent i = new Intent(this, SzolgaIndoklas.class);
                    i.putExtra("id", feladatID);
                    startActivity(i);
                }else{
                    Toast.makeText(this, "Válassz ki egy feladatot!", Toast.LENGTH_LONG).show();
                }
                break;
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
                feladatokArrayList.add(new Feladat(feladatID, szolgaID, allapot, instrukcio));
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN)));
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
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN)));
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
                feladatokArrayList.removeIf(obj -> (!obj.szolgaID.equals(loggedIN)));
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

//LOGOLÁS