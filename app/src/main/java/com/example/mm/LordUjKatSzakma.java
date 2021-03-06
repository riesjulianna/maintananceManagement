package com.example.mm;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LordUjKatSzakma extends AppCompatActivity {

    Button mentes, alkatment;
    EditText kategoria, szakma, karbantartas, normaido, alkategoria, normaidoinput2, karbantartas2;
    Spinner spinnerKat;

    List<String> list;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_uj_kat_szakma);

        mentes = findViewById(R.id.buttonMentes);
        kategoria = findViewById(R.id.kategoriaInput);
        szakma = findViewById(R.id.szakmaInput);
        karbantartas = findViewById(R.id.karbantartasET);
        normaido = findViewById(R.id.normaidoET);

        alkategoria=findViewById(R.id.kategoriaInput2);
        spinnerKat=findViewById(R.id.spinnerKat);
        alkatment=findViewById(R.id.buttonMentes2);
        normaidoinput2=findViewById(R.id.normaidoinput2);
        karbantartas2 = findViewById(R.id.karbantartasET2);
        list= new ArrayList<>();

        DatabaseReference ref = db.getReference().child("kategoriak");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    list.add(id);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LordUjKatSzakma.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                spinnerKat.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);

        mentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat, szak,kar,nor;
                kat=kategoria.getText().toString();
                szak =szakma.getText().toString();
                kar=karbantartas.getText().toString();
                nor=normaido.getText().toString();

                if (kat.isEmpty() || szak.isEmpty() || kar.isEmpty() || nor.isEmpty())
                {
                    Toast.makeText(LordUjKatSzakma.this, "Minden adatot ki kell t??lteni!", Toast.LENGTH_SHORT).show();
                }
                else {

                    DatabaseReference ref = db.getReference().child("kategoriak");
                    ref.child(kat).child("szakma").setValue(szak);
                    ref.child(kat).child("karbantartas").setValue(kar);
                    ref.child(kat).child("normaido").setValue(nor);

                    kategoria.setText("");
                    szakma.setText("");
                    karbantartas.setText("");
                    normaido.setText("");

                    list.clear();

                    DatabaseReference refi = db.getReference().child("kategoriak");
                    ValueEventListener eventListener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                String id = ds.getKey();
                                list.add(id);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(LordUjKatSzakma.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                            spinnerKat.setAdapter(adapter);


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    };
                    refi.addListenerForSingleValueEvent(eventListener);
                }







            }
        });

        alkatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat, alkat, nor, karban;
                kat = spinnerKat.getSelectedItem().toString();
                alkat = alkategoria.getText().toString();
                nor = normaidoinput2.getText().toString();
                karban = karbantartas2.getText().toString();

                DatabaseReference ref = db.getReference().child("kategoriak");
                //ures string legyen vagy inkabb k??rjem le az anyjatol?
                if(!nor.isEmpty()){
                    if(!karban.isEmpty()){
                        ref.child(kat).child("alkategoria").child(alkat).child("normaido").setValue(nor);
                        ref.child(kat).child("alkategoria").child(alkat).child("karbantartas").setValue(karban);
                    }
                    else{
                        ref.child(kat).child("alkategoria").child(alkat).child("normaido").setValue(nor);
                        ref.child(kat).child("alkategoria").child(alkat).child("karbantartas").setValue("");
                    }
                }
                else{
                    if(!karban.isEmpty()){
                        ref.child(kat).child("alkategoria").child(alkat).child("normaido").setValue("");
                        ref.child(kat).child("alkategoria").child(alkat).child("karbantartas").setValue(karban);
                    }
                    else {
                        ref.child(kat).child("alkategoria").child(alkat).child("normaido").setValue("");
                        ref.child(kat).child("alkategoria").child(alkat).child("karbantartas").setValue("");
                    }
                }

                alkategoria.setText("");
                normaidoinput2.setText("");
                karbantartas2.setText("");
            }
        });

    }


}