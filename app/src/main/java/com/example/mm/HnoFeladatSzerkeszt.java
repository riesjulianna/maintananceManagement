package com.example.mm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HnoFeladatSzerkeszt extends AppCompatActivity {

    EditText eszkoznevET, problemaET, datumET, szolganevET, prioritasET,
            idotartamET, instrukcioET, tipusET, allapotET;

    Button feladatTorlesBTN, feladatMentesBTN;

    String feladatID;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hno_feladat_szerkeszt);

        eszkoznevET = findViewById(R.id.eszkoznevET);
        problemaET = findViewById(R.id.problemaET);
        datumET = findViewById(R.id.datumET);
        szolganevET = findViewById(R.id.szolganevET);
        prioritasET = findViewById(R.id.prioritasET);
        idotartamET = findViewById(R.id.idotartamET);
        instrukcioET = findViewById(R.id.instrukcioET);
        tipusET = findViewById(R.id.tipusET);
        allapotET = findViewById(R.id.allapotET);

        feladatTorlesBTN = findViewById(R.id.feladatTorlesBTN);
        feladatMentesBTN = findViewById(R.id.feladatMentesBTN);


        //TODO: betölteni az adott feladatID-jú feladat adatait(elkezdve)
        loadFeladat();


        feladatTorlesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: törlés gomb leprogramozása

                Toast.makeText(HnoFeladatSzerkeszt.this, "Sikeres törlés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        feladatMentesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: mentés gomb leprogramozása

                Toast.makeText(HnoFeladatSzerkeszt.this, "Sikeres mentés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    public void loadFeladat() {
        //TODO: EZ MÉG NEM JÓ, MINDIG 1-ES AZ ESZKÖZID VALAMIÉRT !!!
        Feladat feladat = HnoFeladatok.feladat;
        db = FirebaseDatabase.getInstance().getReference();
        Query query = db.child("feladatok").orderByKey().equalTo(feladat.getFeladatID());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    feladat.eszkozID = s.child("eszkozID").getValue().toString();
                    eszkoznevET.setText(feladat.getEszkozID());//TODO: nemjó
                    allapotET.setText(feladat.getAllapot());//TODO: nemjó
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

//LOGOLÁS