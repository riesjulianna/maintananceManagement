package com.example.mm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LordUjEszkoz extends AppCompatActivity {

    EditText ujKategoriaET, ujAlkategoriaET, ujEszkoz_neveET,
            ujHelyET, ujEszkozIdotartamET, ujInstrukcioET, ujProblemaET, ujUtolsoET, ujSorszamET;

    Button ujEszkozMentesBTN;

    String ujKategoriaStr, ujAlkategoriaStr, ujEszkoz_neveStr,
            ujHelyStr, ujEszkozIdotartamStr, ujInstrukcioStr, ujProblemaStr, ujUtolsoStr, ujSorszamStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lord_uj_eszkoz);

        ujKategoriaET = findViewById(R.id.ujKategoriaET);
        ujAlkategoriaET = findViewById(R.id.ujAlkategoriaET);
        ujEszkoz_neveET = findViewById(R.id.ujEszkoz_neveET);
        ujHelyET = findViewById(R.id.ujHelyET);
        ujEszkozIdotartamET = findViewById(R.id.ujEszkozIdotartamET);
        ujInstrukcioET = findViewById(R.id.ujInstrukcioET);
        ujProblemaET = findViewById(R.id.ujProblemaET);
        ujUtolsoET = findViewById(R.id.ujUtolsoET);
        ujSorszamET = findViewById(R.id.ujSorszamET);

        ujEszkozMentesBTN = findViewById(R.id.ujEszkozMentesBTN);

        //TODO: db updatet megcsinálni hozzájuk

        ujEszkozMentesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: mentés gomb leprogramozása
                ujKategoriaStr = ujKategoriaET.getText().toString();
                ujAlkategoriaStr = ujAlkategoriaET.getText().toString();
                ujEszkoz_neveStr = ujEszkoz_neveET.getText().toString();
                ujHelyStr = ujHelyET.getText().toString();
                ujEszkozIdotartamStr = ujEszkozIdotartamET.getText().toString();
                ujInstrukcioStr = ujInstrukcioET.getText().toString();
                ujProblemaStr = ujProblemaET.getText().toString();
                ujUtolsoStr = ujUtolsoET.getText().toString();
                ujSorszamStr = ujSorszamET.getText().toString();

                if(!ujSorszamStr.matches("")
                        &&!ujKategoriaStr.matches("")
                        &&!ujAlkategoriaStr.matches("")
                        &&!ujEszkoz_neveStr.matches("")
                        &&!ujHelyStr.matches("")
                        &&!ujEszkozIdotartamStr.matches("")
                        &&!ujInstrukcioStr.matches("")
                        &&!ujProblemaStr.matches("")
                        &&!ujUtolsoStr.matches("")) {

                    //egybe kell rakni a két kategóriát a konstruktornak adás elött mivel így tároljuk az adatbázisban
                    String kategoriaStr = ujKategoriaStr+"/"+ujAlkategoriaStr;
                    EszkozHelper EszkHelper = new EszkozHelper(kategoriaStr, ujEszkoz_neveStr, ujHelyStr);
                    EszkozHelper KarbanHelper = new EszkozHelper(ujEszkozIdotartamStr, ujInstrukcioStr, ujProblemaStr, ujUtolsoStr);

                    // Adatbázis hívás
                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbEszkozRef = db.getReference("eszkozok");
                    //Eszkozok adatbázisba írása
                    dbEszkozRef.child(ujSorszamStr).setValue(EszkHelper);

                    //Adatbázis hívás a megadott sorszám segítségével
                    DatabaseReference dbKarbanRef = db.getReference("eszkozok/"+ujSorszamStr);
                    //Karbantartások az elöbb létrehozott ágba írása
                    dbKarbanRef.child("karbantartas").setValue(KarbanHelper);

                    Toast.makeText(LordUjEszkoz.this, "Sikeres mentés!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(LordUjEszkoz.this, "Sikertelen mentés!\nMinden adatot meg kell adni!", Toast.LENGTH_SHORT).show();
                }
                onBackPressed();
            }
        });
    }
}