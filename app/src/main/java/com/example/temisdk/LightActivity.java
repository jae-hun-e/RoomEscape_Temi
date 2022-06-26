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
    boolean light1 = false;
    boolean light2 = false;
    boolean light3 = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.light_main);

        nextStage = findViewById(R.id.nextStage);
        buttonFollow = findViewById(R.id.buttonFollow);
        buttonBack = findViewById(R.id.buttonBack);

        final RoboTemi roboTemi = new RoboTemi();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef1 = database.getReference("light_value_1");
        DatabaseReference myRef2 = database.getReference("light_value_2");
        DatabaseReference myRef3 = database.getReference("light_value_3");
        DatabaseReference switch_value = database.getReference("switch_value");


        switch_value.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean switch_value = dataSnapshot.getValue(boolean.class);
                light1 = switch_value;
                Log.d("tag", "light_value1" + switch_value);

                // door open
                if(switch_value == true){
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

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean light_value1 = dataSnapshot.getValue(boolean.class);
                light1 = light_value1;
                Log.d("tag", "light_value1" + light_value1);

//                // door open
//                if(light_value1 == true){
//                    Log.d("tag","true");
//
//                    // nextStage 숨김해제
//                    nextStage.setVisibility(View.VISIBLE);
//
//                    // nextStage누르면 open view이동
//                    nextStage.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            Intent intent = new Intent(getApplicationContext(),Open1.class);
//                            startActivity(intent);
//                        }
//                    });
//                }
//                // door close
//                else{
//                    Log.d("tag", "false");
//                    // nextStage 숨김
//                    nextStage.setVisibility(View.INVISIBLE);
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("error"+ databaseError.toException());
            }
        });

        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean light_value2 = dataSnapshot.getValue(boolean.class);
                light2 = light_value2;
                Log.d("tag", "light_value2" + light_value2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("error"+ databaseError.toException());
            }
        });

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                boolean light_value3 = dataSnapshot.getValue(boolean.class);
                light3 = light_value3;
                Log.d("tag", "light_value3" + light_value3);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("error"+ databaseError.toException());
            }
        });

//        light.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                boolean returnValse = dataSnapshot.getValue(child(users1));
//
//                Log.d("tag", "light" + light);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                System.out.println("error"+ databaseError.toException());
//            }
//        });

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
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
