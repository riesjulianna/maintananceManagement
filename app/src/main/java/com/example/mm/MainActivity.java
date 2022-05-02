package com.example.mm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    Button lord, hazvezetono, szolga;
    DatabaseReference db;
    //FirebaseFirestore fdb = FirebaseFirestore.getInstance();
    EditText fnev, jelszo;
    String /*beosztas*/ fnInput, jInput, who;
    public static User user = new User();

    //karbantartas generalashoz cuccok
    FirebaseDatabase FDdb = FirebaseDatabase.getInstance();
    List<String> eszkozok,eszkozkat,generalando,instrukciok,normaidok;
    List<Date> utolsok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lord = findViewById(R.id.buttonLord);
        hazvezetono = findViewById(R.id.buttonHazvezetono);
        szolga = findViewById(R.id.buttonSzolga);
        fnev = findViewById(R.id.felhasznalonev);
        jelszo = findViewById(R.id.jelszo);

//        DatabaseReference ref = db.getReference().child("eszkozok");
//        ValueEventListener eventListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    String id = ds.getKey();
//                    Log.d("KEY---", id);
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        };
//        ref.addListenerForSingleValueEvent(eventListener);

        //karbantartas generalashoz cuccok
        eszkozok=new ArrayList<>();
        eszkozkat=new ArrayList<>();
        utolsok=new ArrayList<>();
        generalando=new ArrayList<>();
        instrukciok=new ArrayList<>();
        normaidok=new ArrayList<>();

        //utolso karbantartas idopontjanak a lecsekkolasa
        //ESZKOZOKBOL LEKERDEZES
        DatabaseReference ref = FDdb.getReference().child("eszkozok");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String n = snapshot.child(id).child("utolso").getValue().toString();
                    String kat= snapshot.child(id).child("kategoria").getValue().toString();
                    String[] szetszedve = kat.split("/");
                    eszkozok.add(id);
                    eszkozkat.add(szetszedve[0]);
                    Date d= null;
                    try {
                        d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).parse(n);
                        Log.e("TEST",d.toString());
                    } catch (ParseException e) {
                        Log.e("TEST", "Exception", e);
                    }
                    utolsok.add(d);
                }

                Log.e("TEST",eszkozok.toString()+eszkozkat.toString()+utolsok.toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);

        //KATEGORIABOL LEKERDEZES
        DatabaseReference ref0 = FDdb.getReference().child("kategoriak");
        ValueEventListener eventListener0 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Date currentTime = Calendar.getInstance().getTime();

                for (DataSnapshot ds : snapshot.getChildren()) {
                    String id = ds.getKey();
                    String ido = snapshot.child(id).child("karbantartas").getValue().toString();
                    String norma = snapshot.child(id).child("normaido").getValue().toString();

                    for (int i = 0; i < eszkozok.size(); ++i) {
                        if(id.equals(eszkozkat.get(i))){
                            long diff = currentTime.getTime()-utolsok.get(i).getTime();
                            Log.e("TEST",currentTime.getTime()+" - "+utolsok.get(i).getTime()+"= "+diff);
                            //ket orat hozza kell adni mert vmiert nem jo idozonaban van
                            diff = TimeUnit.MILLISECONDS.toMinutes(diff)+120;
                            Log.e("TEST", String.valueOf(diff));
                            if(diff>=Integer.parseInt(ido)){
                                generalando.add(eszkozok.get(i));
                                normaidok.add(norma);
                            }
                        }
                    }
                }

                Log.e("TEST",generalando.toString());

                //____________________________________________________

                //idoszakos karbantartas generalas

                //        DatabaseReference ref = db.getReference().child("eszkozok");
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()){
                            String id = ds.getKey();
                            for (int i = 0; i < generalando.size(); ++i) {
                                if(id.equals(generalando.get(i))){
                                    String instruk = snapshot.child(id).child("instrukcio").getValue().toString();
                                    instrukciok.add(instruk);
                                }
                            }
                        }
                        Log.e("TEST",instrukciok.toString());
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };
                ref.addListenerForSingleValueEvent(eventListener);

                //ellenorzes hogy van e mar generalva hozza
                DatabaseReference ref2 = FDdb.getReference().child("feladatok");
                ValueEventListener eventListener2 = new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String id = ds.getKey();
                            String eszkoz_id = snapshot.child(id).child("eszkozID").getValue().toString();
                            String tipus = snapshot.child(id).child("tipus").getValue().toString();

                            for (int i = 0; i < generalando.size(); ++i){

                                if(eszkoz_id.equals(generalando.get(i)) && tipus.equals("idoszakos")){
                                    generalando.remove(i);
                                }
                            }
                        }
                        Log.e("TEST",generalando.toString());

                        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());

                        //feladathoz: allapot = "Ütemezve", datum, eszkozID, idotartam, instrukcio,
                        //            prioritas = "9", problema = "", szolgaID = "", tipus = "idoszakos"

                        if(!generalando.isEmpty()){
                            for (int i = 0; i < generalando.size(); ++i){
                                //unique ID generálása
                                String random_id = UUID.randomUUID().toString();

                                DatabaseReference refi = FDdb.getReference().child("feladatok");
                                refi.child(random_id).child("allapot").setValue("Ütemezve");
                                refi.child(random_id).child("datum").setValue(currentDate);
                                refi.child(random_id).child("eszkozID").setValue(generalando.get(i));
                                refi.child(random_id).child("idotartam").setValue(normaidok.get(i));
                                refi.child(random_id).child("instrukcio").setValue(instrukciok.get(i));
                                refi.child(random_id).child("prioritas").setValue("9");
                                refi.child(random_id).child("problema").setValue("");
                                refi.child(random_id).child("szolgaID").setValue("");
                                refi.child(random_id).child("tipus").setValue("idoszakos");
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                };
                ref2.addListenerForSingleValueEvent(eventListener2);


                //____________________________________________________________________
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref0.addListenerForSingleValueEvent(eventListener0);
        //__________________________________________________

    }

    public void onClick(View view){

        switch (view.getId()) {
            case R.id.buttonLord:
                who="lord";
                break;
            case R.id.buttonHazvezetono:
                who="hazvezetono";
                break;
            case R.id.buttonSzolga:
                who="szolga";
                break;
        }

        db = FirebaseDatabase.getInstance().getReference();
        fnInput = fnev.getText().toString().trim();
        jInput = jelszo.getText().toString().trim();
        Query query = db.child("users").orderByChild("felhnev").equalTo(fnInput);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren())
                {
                    user.id=s.getKey();
                    user.felhnev=s.child("felhnev").getValue().toString();
                    user.jelszo=s.child("jelszo").getValue().toString();
                    user.beosztas=s.child("beosztas").getValue().toString();
                    user.nev=s.child("nev").getValue().toString();
                    user.szakma=s.child("szakma").getValue().toString();
                    if(user.getFelhnev().equals(fnInput) && user.getJelszo().equals(jInput) && user.getBeosztas().equals(who)){
                        switch (who) {
                                case "lord":
                                    Intent lord = new Intent(MainActivity.this, LordMainActivity.class);
                                    startActivity(lord);
                                    break;
                                case "hazvezetono":
                                    Intent hazvezetono = new Intent(MainActivity.this, HnoMainActivity.class);
                                    startActivity(hazvezetono);
                                    break;
                                case "szolga":
                                    Intent szolga = new Intent(MainActivity.this, SzolgaMainActivity.class);
                                    startActivity(szolga);
                                    break;
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Helytelen valami.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //who knows...
            }
        });

        //RÉGI ADATBÁZISOS BEJELENTKEZÉS
//        fdb.collection("users")
//                .whereEqualTo("beosztas", who)
//                .whereEqualTo("felhasznalonev", fnev.getText().toString().trim())
//                .whereEqualTo("jelszo", jelszo.getText().toString().trim())
//                .get()
//                .addOnCompleteListener(task -> {
//                    for (QueryDocumentSnapshot document : task.getResult()) {
//                        beosztas = document.getString("beosztas");
//                        fhnv = document.getString("felhasznalonev");
//                        jlsz = document.getString("jelszo");
//                    }
//                        if (!(beosztas==null)) { // itt mindegyik null ha nem stimmel, ezért csak egyet ellenőrzök
//                            switch (who) {
//                                case "lord":
//                                    Intent lord = new Intent(MainActivity.this, LordMainActivity.class);
//                                    startActivity(lord);
//                                    break;
//                                case "hazvezetono":
//                                    Intent hazvezetono = new Intent(MainActivity.this, HnoMainActivity.class);
//                                    startActivity(hazvezetono);
//                                    break;
//                                case "szolga":
//                                    Intent szolga = new Intent(MainActivity.this, SzolgaMainActivity.class);
//                                    startActivity(szolga);
//                                    break;
//                            }
//                        } else {
//                            Toast.makeText(MainActivity.this, "Helytelen valami.", Toast.LENGTH_SHORT).show();
//                        }
//                });
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        finish();
        startActivity(getIntent());
    }
}







