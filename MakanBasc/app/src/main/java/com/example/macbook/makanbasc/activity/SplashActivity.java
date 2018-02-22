package com.example.macbook.makanbasc.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.macbook.makanbasc.R;
import com.example.macbook.makanbasc.helper.SessionManager;

public class SplashActivity extends SessionManager {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler tunda = new Handler();
        tunda.postDelayed(new Runnable() {
            @Override
            public void run() {
                sessionManager.checkLogin();
                finish();
            }
        },3000);
    }
}
