package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SzolgaMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button osszesFeladat, sajatFeladatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_main);

        osszesFeladat = findViewById(R.id.buttonOsszesFeladat);
        sajatFeladatok = findViewById(R.id.buttonSajatFeladatok);

        osszesFeladat.setOnClickListener(this);
        sajatFeladatok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonOsszesFeladat:
                Intent osszesFeladat = new Intent(this, SzolgaFeladatok.class);
                startActivity(osszesFeladat);
                break;
            case R.id.buttonSajatFeladatok:
                Intent sajatFeladatok = new Intent(this, SzolgaSajatFeladatok.class);
                startActivity(sajatFeladatok);
                break;
        }
    }
}