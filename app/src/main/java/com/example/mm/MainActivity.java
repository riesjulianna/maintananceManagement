package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class MainActivity extends AppCompatActivity {

    Button lord, hazvezetono, szolga;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText fnev, jelszo;
    String beosztas, fhnv, jlsz, who;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lord = findViewById(R.id.buttonLord);
        hazvezetono = findViewById(R.id.buttonHazvezetono);
        szolga = findViewById(R.id.buttonSzolga);
        fnev = findViewById(R.id.felhasznalonev);
        jelszo = findViewById(R.id.jelszo);

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

        db.collection("users")
                .whereEqualTo("beosztas", who)
                .whereEqualTo("felhasznalonev", fnev.getText().toString().trim())
                .whereEqualTo("jelszo", jelszo.getText().toString().trim())
                .get()
                .addOnCompleteListener(task -> {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        beosztas = document.getString("beosztas");
                        fhnv = document.getString("felhasznalonev");
                        jlsz = document.getString("jelszo");
                    }
                        if (!(beosztas==null)) { // itt mindegyik null ha nem stimmel, ezért csak egyet ellenőrzök
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
                });
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}







