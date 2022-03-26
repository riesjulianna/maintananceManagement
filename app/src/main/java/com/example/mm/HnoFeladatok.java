package com.example.mm;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HnoFeladatok extends AppCompatActivity {

    ListView hnoFeladatokLV;
    List<String> feladatokID_list;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hno_feladatok);

        hnoFeladatokLV = findViewById(R.id.hnoFeladatokLV);
        feladatokID_list = new ArrayList<>();

        db.collection("feladatok").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        feladatokID_list.add(document.getId());
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HnoFeladatok.this,
                            android.R.layout.simple_list_item_1,
                            feladatokID_list);
                    hnoFeladatokLV.setAdapter(arrayAdapter);
                    Log.d("List log: ",feladatokID_list.toString());
                } else {
                    Log.d("ERROR log: ", "Error getting documents: ", task.getException());
                }
            }
        });

        //TODO: ha rákattintok egy itemre a listában akkor jelenjen meg a szerkesztős oldala

    }
}