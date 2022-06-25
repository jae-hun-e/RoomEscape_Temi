package com.example.temisdk;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.temisdk.temi.RoboTemi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LightActivity extends AppCompatActivity {

    Button nextStage;
    Button buttonFollow;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_main);

        nextStage = findViewById(R.id.nextStage);
        buttonFollow = findViewById(R.id.buttonFollow);
        buttonBack = findViewById(R.id.buttonBack);

        final RoboTemi roboTemi = new RoboTemi();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("light_value1");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean light_value1 = dataSnapshot.getValue(boolean.class);
                Log.d("tag", "light_value1" + light_value1);
                // door open
                if(light_value1 != true){
                    Log.d("tag","true");

                    // nextStage 숨김해제
                    nextStage.setVisibility(View.VISIBLE);

                    // nextStage누르면 open view이동
                    nextStage.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(),Open1.class);
                            startActivity(intent);
                        }
                    });
                }
                // door close
                else{
                    Log.d("tag", "false");
                    // nextStage 숨김
                    nextStage.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("error"+ databaseError.toException());
            }
        });

        buttonFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonFollow.getText().equals("따라가기")) {
                    roboTemi.followMe();
                    buttonFollow.setText("중지");
                } else if (buttonFollow.getText().equals("중지")) {
                    roboTemi.stopMovement();
                    buttonFollow.setText("따라가기");
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

}
