package com.example.starvelater.common;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.starvelater.common.loginsignup.LoginActivity;
import com.example.starvelater.common.loginsignup.SignUpActivity;
import com.example.starvelater.helperClasses.SliderAdapter;
import com.example.starvelater.user.UserDashboard;
import com.example.starvelater.R;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button btnLogin,btnSignUp;
    Animation animation;
    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        btnLogin = findViewById(R.id.login);
        btnSignUp=findViewById(R.id.signup);


        //Call Adapter
        sliderAdapter = new SliderAdapter(OnBoarding.this);
        viewPager.setAdapter(sliderAdapter);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view) {
        Intent intent = new Intent(OnBoarding.this, UserDashboard.class);
        startActivity(intent);
        finish();
    }

    public void next(View view) {
        viewPager.setCurrentItem(currentPosition + 1);
    }

    private void addDots(int position) {
        dots = new TextView[3];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(OnBoarding.this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;
            if (position == 0) {
                btnLogin.setVisibility(View.INVISIBLE);
                btnSignUp.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                btnLogin.setVisibility(View.INVISIBLE);
                btnSignUp.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                btnLogin.setAnimation(animation);
                btnSignUp.setAnimation(animation);

                btnLogin.setVisibility(View.VISIBLE);
                btnSignUp.setVisibility(View.VISIBLE);

                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                        Pair[] pairs = new Pair[1];
                        pairs[0] = new Pair<View,String>(findViewById(R.id.login),"transition_login");

                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OnBoarding.this,pairs);
                        startActivity(intent,options.toBundle());
                    }
                });

                btnSignUp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                        Pair[] pairs = new Pair[1];
                        pairs[0] = new Pair<View,String>(findViewById(R.id.signup),"transition_signup");

                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OnBoarding.this,pairs);
                        startActivity(intent,options.toBundle());
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
