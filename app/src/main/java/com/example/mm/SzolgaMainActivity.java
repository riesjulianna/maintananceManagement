package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SzolgaMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button osszesFeladat, sajatFeladatok, hiba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_main);

        osszesFeladat = findViewById(R.id.buttonSajatFeladataim);
        sajatFeladatok = findViewById(R.id.buttonMegkezdettFeladataim);
        hiba  = findViewById(R.id.buttonHiba);

        osszesFeladat.setOnClickListener(this);
        sajatFeladatok.setOnClickListener(this);
        hiba.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSajatFeladataim:
                Intent osszesFeladat = new Intent(this, SzolgaSajatFeladatok.class);
                startActivity(osszesFeladat);
                break;
            case R.id.buttonMegkezdettFeladataim:
                Intent sajatFeladatok = new Intent(this, SzolgaMegkezdett.class);
                startActivity(sajatFeladatok);
                break;
            case R.id.buttonHiba:
                Intent  hiba = new Intent(this, Hibajelentes.class);
                startActivity(hiba);
                break;
        }
    }
    
}