package com.techease.debtreductioncalculator.view.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.techease.debtreductioncalculator.R;

public class SplashScreenActivity extends AppCompatActivity {

    boolean splash = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                if (splash) {
                    startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                }
            }
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        splash = false;
    }
}
