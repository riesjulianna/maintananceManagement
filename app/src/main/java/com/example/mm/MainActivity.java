package com.example.mm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;


public class MainActivity extends AppCompatActivity {

    Button getBtn, sendBtn;
    TextView testText;
    EditText testData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testData = findViewById(R.id.editText);
        sendBtn = findViewById(R.id.buttonSend);
        getBtn = findViewById(R.id.buttonGet);
        testText = findViewById(R.id.textView2);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send to db
                Toast.makeText(MainActivity.this, "HAALLLOOOOO", Toast.LENGTH_SHORT).show();
                //teszt merge
                //hali dori
            }
        });

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //read from db
            }
        });
    }
}
//dori teszt
//az az új teszt remélem mostmár jó lesz :) dori
//abrakadabra update működj
//03.11.proba