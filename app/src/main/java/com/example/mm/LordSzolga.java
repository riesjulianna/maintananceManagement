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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LordSzolga extends AppCompatActivity {

    Button mentes, vmentes;
    EditText nev, felhnev, jelszo, szakma, ujszakma;
    Spinner szolga;

    List<String> list,lista;

    FirebaseDatabase db = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_szolga);

        mentes = findViewById(R.id.buttonSzolgaHozzadas);
        vmentes = findViewById(R.id.buttonSzolgaVegzetseg);
        nev = findViewById(R.id.nevT);
        felhnev = findViewById(R.id.fnevT);
        jelszo = findViewById(R.id.jelszoT);
        szakma = findViewById(R.id.szakmaT);
        ujszakma = findViewById(R.id.ujSzakma);
        szolga = findViewById(R.id.spinnerSzolga);

        list= new ArrayList<>();
        lista = new ArrayList<>();


        DatabaseReference ref = db.getReference().child("users");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String b = snapshot.child(id).child("beosztas").getValue().toString();
                    if (b.equals("szolga"))
                    {
                        String n = snapshot.child(id).child("nev").getValue().toString();
                        list.add(n);
                        lista.add(id);
                    }


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(LordSzolga.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                szolga.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);



        mentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id, nevm, felh,jelszom,szakmae;

                nevm = nev.getText().toString();
                felh = felhnev.getText().toString();
                jelszom = jelszo.getText().toString();
                szakmae = szakma.getText().toString();

                //unique ID generálása
                id = UUID.randomUUID().toString();
                //egyező id ellenőrzés
                DatabaseReference ref = db.getReference().child("users");
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

                if (nevm.isEmpty())
                {
                    if (felh.isEmpty())
                    {
                        Toast.makeText(LordSzolga.this, "Adjon meg egy nevet és egy felhasználó nevet.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    if (jelszom.isEmpty())
                    {
                        jelszom = "jelszo";
                    }
                    DatabaseReference  refi= db.getReference().child("users");
                    refi.child(id).child("beosztas").setValue("szolga");
                    refi.child(id).child("nev").setValue(nevm);
                    refi.child(id).child("felhnev").setValue(felh);
                    refi.child(id).child("jelszo").setValue(jelszom);
                    refi.child(id).child("szakma").setValue(szakmae);
                }

                nev.setText("");
                jelszo.setText("");
                felhnev.setText("");
                szakma.setText("");

                list.clear();
                lista.clear();

                DatabaseReference refke = db.getReference().child("users");
                ValueEventListener eventListenerke = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String id = ds.getKey();
                            String b = snapshot.child(id).child("beosztas").getValue().toString();
                            if (b.equals("szolga"))
                            {
                                String n = snapshot.child(id).child("nev").getValue().toString();
                                list.add(n);
                                lista.add(id);
                            }


                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(LordSzolga.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                        szolga.setAdapter(adapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                };
                refke.addListenerForSingleValueEvent(eventListenerke);

            }
        });



        vmentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kiv, mszakma, id = null;

                kiv = szolga.getSelectedItem().toString();
                mszakma = ujszakma.getText().toString();


                for (int y=0; y< list.size(); y++){
                    if (list.get(y) == kiv)
                    {
                        id = lista.get(y);
                    }
                }

                if (mszakma.isEmpty())
                {
                    Toast.makeText(LordSzolga.this, "Adjon meg egy szakmát", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseReference  refi= db.getReference().child("users");
                    refi.child(id).child("szakma").setValue(mszakma);
                }


                ujszakma.setText("");
            }
        });


    }
}