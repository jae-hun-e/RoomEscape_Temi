package com.example.temisdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class RFIDActivity extends AppCompatActivity {

    TextView readHint;
    EditText writeHint;
    Button writeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfid);

        readHint = findViewById(R.id.read_hint);
        writeHint = findViewById(R.id.write_hint);
        writeBtn = findViewById(R.id.write_btn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("hint");
        //myRef.setValue(writeHint.getText().toString());


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String value = snapshot.child(writeHint.getText().toString()).getValue(String.class);
                String hint = snapshot.child("value").getValue(Long.class).toString();
                if(value == null) value = "";
                readHint.setText(value+"\n남은힌트 : "+hint);
            }
            @Override

            public void onCancelled(@NonNull DatabaseError error) {
                readHint.setText("error "+error.toException());
            }
        });
    }
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }


}