package com.example.mm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HibaBejentes extends AppCompatActivity implements View.OnClickListener {

    Button leadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiba_bejentes);

        leadas = findViewById(R.id.buttonHibaBejelentes);

        leadas.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonHibaBejelentes:
                //Intent leadas = new Intent(this, SzolgaMainActivity.class);
                //startActivity(leadas);
                onBackPressed();
                break;
        }
    }
}