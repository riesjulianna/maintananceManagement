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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button lord, hazvezetono, szolga;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    EditText fnev, jelszo;
    String beosztas, fhnv, jlsz;

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

    @Override
    public void onClick(View view) {
        if (fnev.getText().toString().length() > 0 && jelszo.getText().toString().length() > 0) {
            switch (view.getId()) {
                case R.id.buttonLord:
                    checkLogin("lord");
                    if (fnev.getText().toString().trim().equals(fhnv)
                            && jelszo.getText().toString().trim().equals(jlsz)) {
                        Intent lord = new Intent(this, LordMainActivity.class);
                        startActivity(lord);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Helytelen felhasználónév/jelszó/beosztás.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.buttonHazvezetono:
                    checkLogin("hazvezetono");
                    if (fnev.getText().toString().trim().equals(fhnv)
                            && jelszo.getText().toString().trim().equals(jlsz)) {
                        Intent hazvezetono = new Intent(this, HnoMainActivity.class);
                        startActivity(hazvezetono);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Helytelen felhasználónév/jelszó/beosztás.", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.buttonSzolga:
                    checkLogin("szolga");
                    if (fnev.getText().toString().trim().equals(fhnv)
                            && jelszo.getText().toString().trim().equals(jlsz)) {
                        Intent szolga = new Intent(this, SzolgaMainActivity.class);
                        startActivity(szolga);
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Helytelen felhasználónév/jelszó/beosztás.", Toast.LENGTH_LONG).show();
                    }
                    break;
                default:break;
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Írja be felhasználónevét és jelszavát!", Toast.LENGTH_LONG).show();
        }
    }

    public void checkLogin(String who) {
        db.collection("users")
                .whereEqualTo("beosztas", who)
                .whereEqualTo("felhasznalonev", fnev.getText().toString().trim())
                .whereEqualTo("jelszo", jelszo.getText().toString().trim())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            beosztas = document.getString("beosztas");
                            fhnv = document.getString("felhasznalonev");
                            jlsz = document.getString("jelszo");
                        }
                    } else {
                        //error?
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







