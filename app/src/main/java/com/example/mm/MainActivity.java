package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button lord, hazvezetono, szolga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lord = findViewById(R.id.buttonLord);
        hazvezetono = findViewById(R.id.buttonHazvezetono);
        szolga = findViewById(R.id.buttonSzolga);

        lord.setOnClickListener(this);
        hazvezetono.setOnClickListener(this);
        szolga.setOnClickListener(this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonLord:
                Intent lord = new Intent(this, LordMainActivity.class);
                startActivity(lord);
                break;
            case R.id.buttonHazvezetono:
                Intent hno = new Intent(this, HnoMainActivity.class);
                startActivity(hno);
                break;
            case R.id.buttonSzolga:
                Intent szolga = new Intent(this, SzolgaMainActivity.class);
                startActivity(szolga);
                break;
        }
    }
}
