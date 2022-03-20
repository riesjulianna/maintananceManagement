package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SzolgaSajatFeladatok extends AppCompatActivity implements View.OnClickListener {

    Button kezdes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_szolga_sajat_feladatok);

        kezdes=findViewById(R.id.buttonKezdes);

        kezdes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent kezdes = new Intent(this, SzolgaInstrukciokBefejezes.class);
        startActivity(kezdes);
    }
}