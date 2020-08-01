package com.example.starvelater.activities.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.starvelater.adapters.general_adapters.SliderAdapter;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.R;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Button letsgetstarted;
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
        letsgetstarted = findViewById(R.id.get_started_btn);


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
                letsgetstarted.setVisibility(View.INVISIBLE);
            } else if (position == 1) {
                letsgetstarted.setVisibility(View.INVISIBLE);
            } else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                letsgetstarted.setAnimation(animation);

                letsgetstarted.setVisibility(View.VISIBLE);


               /* btnLogin.setOnClickListener(new View.OnClickListener() {
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
                        Pair[] pairs1 = new Pair[1];
                        pairs1[0] = new Pair<View,String>(findViewById(R.id.signup),"transition_signup");

                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(OnBoarding.this,pairs1);
                        startActivity(intent,options.toBundle());
                    }
                });*/

                letsgetstarted.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), UserDashboard.class);
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
