package com.example.starvelater.common.loginsignup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.example.starvelater.R;
import com.google.android.material.badge.BadgeUtils;

public class StartUpScreen extends AppCompatActivity {

    Button btnlogin,btnsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up_screen);

        btnlogin=(Button)findViewById(R.id.login);
        btnsignup=(Button)findViewById(R.id.signup);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View,String>(findViewById(R.id.login),"transition_login");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartUpScreen.this,pairs);
                startActivity(intent,options.toBundle());
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                Pair[] pairs1 = new Pair[1];
                pairs1[0] = new Pair<View,String>(findViewById(R.id.signup),"transition_signup");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartUpScreen.this,pairs1);
                startActivity(intent,options.toBundle());
            }
        });
    }
}
