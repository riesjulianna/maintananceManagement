package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Hibajelentes extends AppCompatActivity  {

    Button mentes;
    EditText problema;
    Spinner nev;
    List<String> list, listnev;

    FirebaseDatabase db = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hibajelentes);

        mentes = findViewById(R.id.buttonHibaBejelentes);
        problema = findViewById(R.id.problema);
        nev = findViewById(R.id.spinner);


        listnev = new ArrayList<>();
        list= new ArrayList<>();




        DatabaseReference ref = db.getReference().child("eszkozok");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String n = snapshot.child(id).child("nev").getValue().toString();
                    list.add(id);

                    listnev.add(n);
                }


                ArrayAdapter<String> adapter = new ArrayAdapter<>(Hibajelentes.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listnev);
                adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                nev.setAdapter(adapter);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);








        mentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nevid, problem, idnev = null, id;

                nevid = nev.getSelectedItem().toString();
                problem = problema.getText().toString();
                String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                for (int y=0; y< list.size(); y++){
                    if (listnev.get(y) == nevid)
                    {
                        idnev = list.get(y);
                    }

                }

                //unique ID gener??l??sa
                id = UUID.randomUUID().toString();
                //egyez?? id ellen??rz??s
                DatabaseReference ref = db.getReference().child("feladatok");
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

                if (problem.isEmpty())
                {
                    Toast.makeText(Hibajelentes.this, "K??rem ??rja le a probl??m??t.", Toast.LENGTH_SHORT).show();
                }else {

                    DatabaseReference refi = db.getReference().child("feladatok");
                    refi.child(id).child("allapot").setValue("??j");
                    refi.child(id).child("eszkozID").setValue(idnev);
                    refi.child(id).child("problema").setValue(problem);
                    refi.child(id).child("tipus").setValue("rendkivuli");
                    refi.child(id).child("datum").setValue(currentDate);
                    refi.child(id).child("idotartam").setValue("");
                    refi.child(id).child("instrukcio").setValue("");
                    refi.child(id).child("prioritas").setValue("");
                    refi.child(id).child("szolgaID").setValue("");
                    onBackPressed();
                }

            }
        });
    }

}