package com.example.mm;

import android.content.Intent;
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


public class MainActivity extends AppCompatActivity {

    Button lord, hazvezetono, szolga;
    DatabaseReference db;
    //FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    EditText fnev, jelszo;
    String /*beosztas*/ fnInput, jInput, who;
    public static User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lord = findViewById(R.id.buttonLord);
        hazvezetono = findViewById(R.id.buttonHazvezetono);
        szolga = findViewById(R.id.buttonSzolga);
        fnev = findViewById(R.id.felhasznalonev);
        jelszo = findViewById(R.id.jelszo);

//        DatabaseReference ref = db.getReference().child("eszkozok");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String id = ds.getKey();
//                    Log.d("KEY---", id);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        };
//        ref.addListenerForSingleValueEvent(eventListener);

    }

    public void onClick(View view){

        switch (view.getId()) {
            case R.id.buttonLord:
                who="lord";
                break;
            case R.id.buttonHazvezetono:
                who="hazvezetono";
                break;
            case R.id.buttonSzolga:
                who="szolga";
                break;
        }

        db = FirebaseDatabase.getInstance().getReference();
        fnInput = fnev.getText().toString().trim();
        jInput = jelszo.getText().toString().trim();
        Query query = db.child("users").orderByChild("felhnev").equalTo(fnInput);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren())
                {
                    user.id=s.getKey();
                    user.felhnev=s.child("felhnev").getValue().toString();
                    user.jelszo=s.child("jelszo").getValue().toString();
                    user.beosztas=s.child("beosztas").getValue().toString();
                    user.nev=s.child("nev").getValue().toString();
                    user.szakma=s.child("szakma").getValue().toString();
                    if(user.getFelhnev().equals(fnInput) && user.getJelszo().equals(jInput) && user.getBeosztas().equals(who)){
                        switch (who) {
                                case "lord":
                                    Intent lord = new Intent(MainActivity.this, LordMainActivity.class);
                                    startActivity(lord);
                                    break;
                                case "hazvezetono":
                                    Intent hazvezetono = new Intent(MainActivity.this, HnoMainActivity.class);
                                    startActivity(hazvezetono);
                                    break;
                                case "szolga":
                                    Intent szolga = new Intent(MainActivity.this, SzolgaMainActivity.class);
                                    startActivity(szolga);
                                    break;
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Helytelen valami.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //who knows...
            }
        });

        //RÉGI ADATBÁZISOS BEJELENTKEZÉS
//        fdb.collection("users")
//                .whereEqualTo("beosztas", who)
//                .whereEqualTo("felhasznalonev", fnev.getText().toString().trim())
//                .whereEqualTo("jelszo", jelszo.getText().toString().trim())
//                .get()
//                .addOnCompleteListener(task -> {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        beosztas = document.getString("beosztas");
//                        fhnv = document.getString("felhasznalonev");
//                        jlsz = document.getString("jelszo");
//                    }
//                        if (!(beosztas==null)) { // itt mindegyik null ha nem stimmel, ezért csak egyet ellenőrzök
//                            switch (who) {
//                                case "lord":
//                                    Intent lord = new Intent(MainActivity.this, LordMainActivity.class);
//                                    startActivity(lord);
//                                    break;
//                                case "hazvezetono":
//                                    Intent hazvezetono = new Intent(MainActivity.this, HnoMainActivity.class);
//                                    startActivity(hazvezetono);
//                                    break;
//                                case "szolga":
//                                    Intent szolga = new Intent(MainActivity.this, SzolgaMainActivity.class);
//                                    startActivity(szolga);
//                                    break;
//                            }
//                        } else {
//                            Toast.makeText(MainActivity.this, "Helytelen valami.", Toast.LENGTH_SHORT).show();
//                        }
//                });
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}







