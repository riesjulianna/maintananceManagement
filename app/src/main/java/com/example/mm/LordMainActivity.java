package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class LordMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button eszkozok, ujEszkoz, hibabejelent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_main);

        eszkozok = findViewById(R.id.buttonEszkozok);
        ujEszkoz = findViewById(R.id.buttonUjEszkoz);
        hibabejelent = findViewById(R.id.buttonLordHibabejelent);

        eszkozok.setOnClickListener(this);
        ujEszkoz.setOnClickListener(this);
        hibabejelent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonEszkozok:
                Intent eszkozlista = new Intent(this, LordEszkozLista.class);
                startActivity(eszkozlista);
                break;
            case R.id.buttonUjEszkoz:
                Intent ujEszkoz = new Intent(this, LordUjEszkoz.class);
                startActivity(ujEszkoz);
                break;
            case R.id.buttonLordHibabejelent:
                Intent hiba = new Intent(this, Hibajelentes.class);
                startActivity(hiba);
                break;
        }
    }

}