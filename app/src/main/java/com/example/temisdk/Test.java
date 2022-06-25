package com.example.temisdk;

import android.util.Log;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.robotemi.sdk.Robot;
import com.robotemi.sdk.UserInfo;
import com.robotemi.sdk.model.CallEventModel;

import org.jetbrains.annotations.NotNull;


public class Test extends AppCompatActivity {

    TextView textState;
    Button buttonBack;
    Button buttonCall;
    Robot robot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_main);
        textState = findViewById(R.id.textState);
        buttonBack = findViewById(R.id.buttonBack);


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void callOwner(View view) {
        UserInfo admin = robot.getAdminInfo();
        if (admin == null) {
            Log.d("callOwner()", "adminInfo is null.");
            return;
        }
        robot.startTelepresence(admin.getName(), admin.getUserId());
    }

//    @Override
//    public void onTelepresenceEventChanged(@NotNull CallEventModel callEventModel) {
//        printLog("onTelepresenceEvent", callEventModel.toString());
//    }

}
