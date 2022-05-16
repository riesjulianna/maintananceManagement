package com.example.mm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SzolgaIndoklas extends AppCompatActivity {
    Button elutasit;
    EditText indoklas;

    String indoklas_str, feladatID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_indoklas);

        elutasit = findViewById(R.id.bt_elutasit);
        indoklas = findViewById(R.id.et_indoklas);

    }
    public void onClick(View view) {

        indoklas_str = indoklas.getText().toString();
        //putextrából kiszedni a feladatID-t
        Intent i= getIntent();
        Bundle b = i.getExtras();
        if(b!=null)
        {
            feladatID =(String) b.get("id");
        }

        // Adatbázis hívás
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbFeladatokRef = db.getReference("feladatok");
        //Ellenőrzés hogy van-e indoklás ha van adatok feltöltése
        if(!indoklas_str.equals("")){
            dbFeladatokRef.child(feladatID).child("allapot").setValue("Elutasítva");
            dbFeladatokRef.child(feladatID).child("indoklas").setValue(indoklas_str);
            Toast.makeText(this, "Sikeresen Elutasítva a(z) "+feladatID+" sorszámú feladat!", Toast.LENGTH_LONG).show();
            finish();
        }else{
            Toast.makeText(this, "Írj egy indoklást!", Toast.LENGTH_LONG).show();
        }
    }
}