package com.example.mm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class HnoFeladatSzerkeszt extends AppCompatActivity {

    EditText eszkoznevET, problemaET, datumET, szolganevET, prioritasET,
            idotartamET, instrukcioET, tipusET, allapotET;

    Button feladatTorlesBTN, feladatMentesBTN;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

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

        //TODO: lekérdezéseket megcsinálni hozzájuk

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
}

//LOGOLÁS