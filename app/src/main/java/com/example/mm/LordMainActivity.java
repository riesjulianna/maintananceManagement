package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LordMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button eszkozok, szerkesztes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_main);

        eszkozok = findViewById(R.id.buttonEszkozok);
        szerkesztes = findViewById(R.id.buttonSzerkesztes);

        eszkozok.setOnClickListener(this);
        szerkesztes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonEszkozok:
                Intent eszkozlista = new Intent(this, LordEszkozLista.class);
                startActivity(eszkozlista);
                break;
            case R.id.buttonSzerkesztes:
                Intent szerkesztes = new Intent(this, LordSzerkeszt.class);
                startActivity(szerkesztes);
                break;
        }
    }

}