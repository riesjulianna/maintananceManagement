package com.example.mm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HnoFeladatSzerkeszt extends AppCompatActivity {

    EditText eszkoznevET, problemaET, datumET, prioritasET,
            idotartamET, instrukcioET, tipusET, allapotET;

    Button feladatTorlesBTN, feladatMentesBTN;

    String feladatID, szolgaID;

    Spinner szolganevET;
    DatabaseReference db;
    FirebaseDatabase z = FirebaseDatabase.getInstance();

    List<String> list,idlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hno_feladat_szerkeszt);

        eszkoznevET = findViewById(R.id.eszkoznevET);
        problemaET = findViewById(R.id.problemaET);
        datumET = findViewById(R.id.datumET);
        szolganevET = findViewById(R.id.szolganevET);
        prioritasET = findViewById(R.id.prioritasET);
        idotartamET = findViewById(R.id.idotartamET);
        instrukcioET = findViewById(R.id.instrukcioET);
        tipusET = findViewById(R.id.tipusET);
        allapotET = findViewById(R.id.allapotET);

        feladatTorlesBTN = findViewById(R.id.feladatTorlesBTN);
        feladatMentesBTN = findViewById(R.id.feladatMentesBTN);

        list= new ArrayList<>();
        idlist=new ArrayList<>();

        //szövegek átállítása read only-ra
        eszkoznevET.setEnabled(false);
        tipusET.setEnabled(false);
        allapotET.setEnabled(false);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            feladatID = extras.getString("feladatID");
            szolgaID = extras.getString("szolgaID");
        }

        //adatok betöltése
        loadFeladat();


        feladatTorlesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("feladatok").orderByKey().equalTo(feladatID);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("error on delete", "onCancelled", databaseError.toException());
                    }
                });

                Toast.makeText(HnoFeladatSzerkeszt.this, "Sikeres törlés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

        feladatMentesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: mentés gomb leprogramozása
                DatabaseReference refi = z.getReference().child("feladatok");

                if(!allapotET.getText().toString().equals("Befejezve") && !szolganevET.getSelectedItem().toString().equals("")){
                    refi.child(feladatID).child("allapot").setValue("Ütemezve");
                }
                refi.child(feladatID).child("idotartam").setValue(idotartamET.getText().toString());
                refi.child(feladatID).child("instrukcio").setValue(instrukcioET.getText().toString());
                refi.child(feladatID).child("prioritas").setValue(prioritasET.getText().toString());
                int pos = szolganevET.getSelectedItemPosition();
                refi.child(feladatID).child("szolgaID").setValue(idlist.get(pos));
                refi.child(feladatID).child("problema").setValue(problemaET.getText().toString());
                refi.child(feladatID).child("datum").setValue(datumET.getText().toString());


                Toast.makeText(HnoFeladatSzerkeszt.this, "Sikeres mentés!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }


    public void loadFeladat() {

        db = FirebaseDatabase.getInstance().getReference();
        DatabaseReference refke = z.getReference().child("users");
        ValueEventListener eventListenerke = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String b = snapshot.child(id).child("beosztas").getValue().toString();

                    if (b.equals("szolga"))
                    {
                        String n = snapshot.child(id).child("nev").getValue().toString();
                        String s = snapshot.child(id).child("szakma").getValue().toString();
                        idlist.add(id);
                        list.add(n + " | " + s);
                    }


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(HnoFeladatSzerkeszt.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
                adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                szolganevET.setAdapter(adapter);
                int pos=0;
                idlist.add("");
                list.add("");
                for(int i = 0; i < idlist.size(); ++i){
                    if(idlist.get(i).equals(szolgaID))
                        pos=i;
                }
                szolganevET.setSelection(pos);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        refke.addListenerForSingleValueEvent(eventListenerke);



        Query query = db.child("feladatok").orderByKey().equalTo(feladatID);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot s : snapshot.getChildren()) {
                    String id = s.getKey();
                    
                    //eszkoz,problema,datum,szolga,prioritas,idotartam,instrukcio,tipus,allapot
                    eszkoznevET.setText(snapshot.child(id).child("eszkozID").getValue().toString());
                    problemaET.setText(snapshot.child(id).child("problema").getValue().toString());
                    datumET.setText(snapshot.child(id).child("datum").getValue().toString());
                    prioritasET.setText(snapshot.child(id).child("prioritas").getValue().toString());
                    idotartamET.setText(snapshot.child(id).child("idotartam").getValue().toString());
                    instrukcioET.setText(snapshot.child(id).child("instrukcio").getValue().toString());
                    tipusET.setText(snapshot.child(id).child("tipus").getValue().toString());
                    allapotET.setText(snapshot.child(id).child("allapot").getValue().toString());//

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

//LOGOLÁS