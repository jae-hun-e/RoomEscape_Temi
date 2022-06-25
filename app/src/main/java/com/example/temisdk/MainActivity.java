package com.example.temisdk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.temisdk.temi.CustomTtsListener;
import com.example.temisdk.temi.RoboTemi;
import com.example.temisdk.temi.RoboTemiListeners;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.robotemi.sdk.Robot;
import com.robotemi.sdk.TtsRequest;
import com.robotemi.sdk.listeners.OnRobotReadyListener;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity implements
        OnRobotReadyListener,
        View.OnClickListener {


    Button room1;
    Button room2;
    Button room3;
    Button room4;
    Button hint;
    Button button6;
//    Button button6;
//    Button button7;
//    Button button8;
    Robot robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        room1 = findViewById(R.id.button1);
        room2 = findViewById(R.id.button2);
        room3 = findViewById(R.id.button3);
        room4 = findViewById(R.id.button4);
        hint = findViewById(R.id.button5);
//        button6 = findViewById(R.id.button6);
//        button6 = findViewById(R.id.button6);
//        button7 = findViewById(R.id.button7);
//        button8 = findViewById(R.id.button8);
        robot = Robot.getInstance();

        room1.setOnClickListener(this);
        room2.setOnClickListener(this);
        room3.setOnClickListener(this);
        room4.setOnClickListener(this);
        hint.setOnClickListener(this);
//        button6.setOnClickListener(this);
//        button7.setOnClickListener(this);
//        button8.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        robot.addOnRobotReadyListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        robot.removeOnRobotReadyListener(this);
    }

    @Override
    public void onRobotReady(boolean isReady) {
        if (isReady) {
            try {
                final ActivityInfo activityInfo = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
                robot.onStart(activityInfo);
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void onClick(View view) {
        Class exampleContext = null;
        switch (view.getId()) {
            case R.id.button1:
                exampleContext = LightActivity.class;
                break;
            case R.id.button2:
                exampleContext = PressActivity.class;
                break;
            case R.id.button3:
                exampleContext = SoundActivity.class;
                break;
            case R.id.button4:
                exampleContext = WaterActivity.class;
                break;
            case R.id.button5:
                exampleContext = RFIDActivity.class;
                break;
        }
        Intent intent = new Intent(getApplicationContext(),exampleContext);
        startActivity(intent);
    }
}
