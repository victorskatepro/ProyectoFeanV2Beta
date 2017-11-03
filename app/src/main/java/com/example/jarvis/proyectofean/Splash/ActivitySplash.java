package com.example.jarvis.proyectofean.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.jarvis.proyectofean.IntroActivity;
import com.example.jarvis.proyectofean.LoginActivity;
import com.example.jarvis.proyectofean.R;

public class ActivitySplash extends AppCompatActivity {
    private static final String TAG = ActivitySplash.class.getSimpleName();
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        this.getApplication().registerActivityLifecycleCallbacks(new CurrentActivityListener());

        new Handler().postDelayed(new Runnable() {
            public void run() {
                verifyLogged();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }
    private void verifyLogged(){
        if(PreferencesManager.getInstance(this).get(PreferencesManager.PREF_ISLOGGED) != null){
            startActivity(new Intent(ActivitySplash.this, LoginActivity.class));
        }else{
            startActivity(new Intent(ActivitySplash.this, IntroActivity.class));
        }
        finish();
    }
}
