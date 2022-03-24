package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HnoMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button hibaLista, feladatok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hno_main);

        hibaLista = findViewById(R.id.buttonHibalista);
        feladatok = findViewById(R.id.buttonSzerkesztes);

        hibaLista.setOnClickListener(this);
        feladatok.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonHibalista:
                Intent hibalista = new Intent(this, HnoHibalista.class);
                startActivity(hibalista);
                break;
            case R.id.buttonSzerkesztes:
                Intent feladatok = new Intent(this, HnoFeladatok.class);
                startActivity(feladatok);
                break;
        }
    }

}