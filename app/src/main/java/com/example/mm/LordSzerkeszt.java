package com.example.mm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class LordSzerkeszt extends AppCompatActivity {

    EditText kategoriaET, alkategoriaET, eszkoz_neveET,
            helyET, eszkozLeirasET, utolsoET;

    Button eszkozTorlesBTN, eszkozMentesBTN;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_szerkeszt);

        kategoriaET = findViewById(R.id.kategoriaET);
        alkategoriaET = findViewById(R.id.alkategoriaET);
        eszkoz_neveET = findViewById(R.id.eszkoz_neveET);
        helyET = findViewById(R.id.helyET);
        eszkozLeirasET = findViewById(R.id.eszkozLeirasET);
        utolsoET = findViewById(R.id.utolsoET);

        eszkozTorlesBTN = findViewById(R.id.eszkozTorlesBTN);
        eszkozMentesBTN = findViewById(R.id.eszkozMentesBTN);

        //TODO: lekérdezéseket megcsinálni hozzájuk

        eszkozTorlesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: törlés gomb leprogramozása

                Toast.makeText(LordSzerkeszt.this, "Sikeres törlés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        eszkozMentesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: mentés gomb leprogramozása

                Toast.makeText(LordSzerkeszt.this, "Sikeres mentés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }
}