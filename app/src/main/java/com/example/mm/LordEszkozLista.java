package com.example.mm;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class LordEszkozLista extends AppCompatActivity {

    ListView eszkozListaLV;
    List<String> eszkozokID_list;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_eszkoz_lista);

        eszkozListaLV = findViewById(R.id.eszkozListaLV);
        eszkozokID_list = new ArrayList<>();

        //TODO: DB lekérdezést megcsinálni

        //TODO: ha rákattintok egy itemre a listában akkor jelenjen meg a szerkesztős oldala

    }
}