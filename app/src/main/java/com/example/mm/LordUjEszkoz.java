package com.example.mm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

public class LordUjEszkoz extends AppCompatActivity {

    EditText ujKategoriaET, ujAlkategoriaET, ujEszkoz_neveET,
            ujHelyET, ujEszkozLeirasET;

    Button ujEszkozMentesBTN;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_uj_eszkoz);

        ujKategoriaET = findViewById(R.id.ujKategoriaET);
        ujAlkategoriaET = findViewById(R.id.ujAlkategoriaET);
        ujEszkoz_neveET = findViewById(R.id.ujEszkoz_neveET);
        ujHelyET = findViewById(R.id.ujHelyET);
        ujEszkozLeirasET = findViewById(R.id.ujEszkozLeirasET);

        ujEszkozMentesBTN = findViewById(R.id.ujEszkozMentesBTN);

        //TODO: db updatet megcsinálni hozzájuk

        ujEszkozMentesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: mentés gomb leprogramozása

                Toast.makeText(LordUjEszkoz.this, "Sikeres mentés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}