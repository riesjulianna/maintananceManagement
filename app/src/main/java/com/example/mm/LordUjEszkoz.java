package com.example.mm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LordUjEszkoz extends AppCompatActivity {

    EditText  ujEszkoz_neveET,
            ujHelyET, ujUtolsoET, ujInstrukcioET;

    Button ujEszkozMentesBTN, alkat;

    String ujKategoriaStr, ujAlkategoriaStr, ujEszkoz_neveStr,
            ujHelyStr, ujUtolsoStr, ujInstrukcioStr;

    Spinner ujKategoriaET, ujAlkategoriaET;

    List<String> list,lista;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_uj_eszkoz);

        ujKategoriaET = findViewById(R.id.ujKategoriaET);
        ujAlkategoriaET = findViewById(R.id.ujAlkategoriaET);
        ujEszkoz_neveET = findViewById(R.id.ujEszkoz_neveET);
        ujHelyET = findViewById(R.id.ujHelyET);
        ujUtolsoET = findViewById(R.id.ujUtolsoET);
        ujInstrukcioET = findViewById(R.id.ujInstrukcioET);

        alkat = findViewById(R.id.alkat);
        ujEszkozMentesBTN = findViewById(R.id.ujEszkozMentesBTN);

        list= new ArrayList<>();
        lista = new ArrayList<>();

        DatabaseReference ref = db.getReference().child("kategoriak");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    list.add(id);


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LordUjEszkoz.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                ujKategoriaET.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);

        alkat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat;

                kat = ujKategoriaET.getSelectedItem().toString();

                DatabaseReference refi = db.getReference().child("kategoriak").child(kat).child("alkategoria");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String id = ds.getKey();
                            lista.add(id);


                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(LordUjEszkoz.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, lista);
                        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                        ujAlkategoriaET.setAdapter(adapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                refi.addListenerForSingleValueEvent(eventListener);

                lista.clear();
            }



        });

        //TODO: db updatet megcsinálni hozzájuk

        ujEszkozMentesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: mentés gomb leprogramozása
                ujKategoriaStr = ujKategoriaET.getSelectedItem().toString();
                ujAlkategoriaStr = ujAlkategoriaET.getSelectedItem().toString();
                ujEszkoz_neveStr = ujEszkoz_neveET.getText().toString();
                ujHelyStr = ujHelyET.getText().toString();
                ujUtolsoStr = ujUtolsoET.getText().toString();
                ujInstrukcioStr = ujInstrukcioET.getText().toString();

                if(!ujKategoriaStr.matches("")
                        &&!ujAlkategoriaStr.matches("")
                        &&!ujEszkoz_neveStr.matches("")
                        &&!ujHelyStr.matches("")
                        &&!ujUtolsoStr.matches("")
                        &&!ujInstrukcioStr.matches("")) {

                    //egybe kell rakni a két kategóriát a konstruktornak adás elött mivel így tároljuk az adatbázisban
                    String kategoriaStr = ujKategoriaStr+"/"+ujAlkategoriaStr;
                    EszkozHelper EszkHelper = new EszkozHelper(ujHelyStr, kategoriaStr, ujEszkoz_neveStr, ujUtolsoStr, ujInstrukcioStr);

                    // Adatbázis hívás
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbEszkozRef = db.getReference("eszkozok");
                    //unique ID generálása
                    String uniqueID = UUID.randomUUID().toString();
                    //egyező id ellenőrzés
                    DatabaseReference ref = db.getReference().child("eszkozok");
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String id = ds.getKey();
                                Log.d("KEY---", id);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    };
                    ref.addListenerForSingleValueEvent(eventListener);

                    //Eszkozok adatbázisba írása
                    dbEszkozRef.child(uniqueID).setValue(EszkHelper);

                    Toast.makeText(LordUjEszkoz.this, "Sikeres mentés!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LordUjEszkoz.this, "Sikertelen mentés!\nMinden adatot meg kell adni!", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });
    }
}