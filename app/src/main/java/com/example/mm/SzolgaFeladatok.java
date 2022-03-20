package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SzolgaFeladatok extends AppCompatActivity implements View.OnClickListener {

    Button elfogad, elutasit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_feladatok);

        elfogad=findViewById(R.id.buttonElfogad);
        elutasit=findViewById(R.id.buttonElutasit);

        elfogad.setOnClickListener(this);
        elutasit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonElfogad:
                //feladat elfogadásának rögzitése adatbázisba
                break;
            case R.id.buttonElutasit:
                Intent elutasit = new Intent(this, SzolgaIndoklas.class);
                startActivity(elutasit);
                break;
        }
    }
}