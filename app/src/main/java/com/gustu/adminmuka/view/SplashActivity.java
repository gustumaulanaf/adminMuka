package com.gustu.adminmuka.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.gustu.adminmuka.R;
import com.gustu.adminmuka.model.Petugas;
import com.gustu.adminmuka.sharePreferences.SharedPrefUtil;

public class SplashActivity extends AppCompatActivity {
    int waktuloading = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefUtil.getBoolean("login")){
                    startActivity(new Intent(SplashActivity.this, PetugasActivity.class));
                    finish();
                }
                else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        },waktuloading);
    }
}
