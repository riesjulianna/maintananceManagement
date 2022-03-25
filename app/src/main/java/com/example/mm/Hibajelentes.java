package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hibajelentes extends AppCompatActivity implements View.OnClickListener {

    Button leadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hibajelentes);

        leadas = findViewById(R.id.buttonHibaBejelentes);
        leadas.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {//sztem itt is dupla klikkes lesz
        switch (view.getId()) {
            case R.id.buttonHibaBejelentes:
                //hiba leadás, feladat generálás
                Intent i = new Intent(this, SzolgaMainActivity.class);
                startActivity(i);
                onBackPressed();
                break;
        }
    }
}