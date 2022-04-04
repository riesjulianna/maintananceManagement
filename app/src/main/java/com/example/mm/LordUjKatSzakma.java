package com.example.mm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class LordUjKatSzakma extends AppCompatActivity {

    Button mentes;
    EditText kategoria, szakma;
    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_uj_kat_szakma);

        mentes = findViewById(R.id.buttonMentes);
        kategoria = findViewById(R.id.kategoriaInput);
        szakma = findViewById(R.id.szakmaInput);
    }

    public void Save(View view){
        Map<String, Object> kat = new HashMap<>();
        DatabaseReference ref = db.getReference().child("kategoriak");

    }

}