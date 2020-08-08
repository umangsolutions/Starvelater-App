package com.example.starvelater.activities.helpandsupport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starvelater.R;
import com.example.starvelater.activities.user.UserDashboard;


public class Feedback extends AppCompatActivity {

    RatingBar simpleRatingBar;
    ImageView btnBack;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_form);


        btnBack=findViewById(R.id.back_button);
        simpleRatingBar = findViewById(R.id.rating);
        submit = findViewById(R.id.submit);




        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent back = new Intent(Feedback.this, UserDashboard.class);
//                back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(back);
                onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float rating = simpleRatingBar.getRating();
                Toast.makeText(Feedback.this, ""+rating, Toast.LENGTH_SHORT).show();
            }
        });

    }
}