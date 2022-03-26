package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HnoMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button feladatok, hibabejelent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hno_main);

        feladatok = findViewById(R.id.buttonUjEszkoz);
        hibabejelent = findViewById(R.id.buttonHnoHibabejelent);

        feladatok.setOnClickListener(this);
        hibabejelent.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonUjEszkoz:
                Intent feladatok = new Intent(this, HnoFeladatok.class);
                startActivity(feladatok);
                break;
            case R.id.buttonHnoHibabejelent:
                Intent hibabejelent = new Intent(this, Hibajelentes.class);
                startActivity(hibabejelent);
                break;
        }
    }

}