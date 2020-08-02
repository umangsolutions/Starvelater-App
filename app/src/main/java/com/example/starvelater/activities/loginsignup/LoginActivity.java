package com.example.starvelater.activities.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.SharedPref.MyAppPrefsManager;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.api.ApiInterface;
import com.example.starvelater.api.RetrofitClient;
import com.example.starvelater.jsonmodels.UserLoginModel;
import com.google.gson.JsonObject;

import java.net.Inet4Address;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ImageView btnback;
    EditText edtEmail,edtPassword;
    Button btnSubmit,btnCreate;
    String emailID, password;

    LinearLayout progressBar;
    MyAppPrefsManager myAppPrefsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnback = findViewById(R.id.back_button);

        progressBar = findViewById(R.id.progressBar);

        edtEmail = findViewById(R.id.editEmail);
        edtPassword = findViewById(R.id.editPassword);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCreate = findViewById(R.id.btnCreate);

        myAppPrefsManager = new MyAppPrefsManager(LoginActivity.this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailID = edtEmail.getText().toString().trim();
                password = edtPassword.getText().toString().trim();

                if(emailID.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Email ID", Toast.LENGTH_SHORT).show();
                } else if(password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                } else {
                    next();
                }
            }
        });
    }

    public void next() {
        progressBar.setVisibility(View.VISIBLE);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("email",emailID);
        jsonObject.addProperty("password",password);

        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        apiInterface.processUserLogin(jsonObject).enqueue(new Callback<UserLoginModel>() {
            @Override
            public void onResponse(Call<UserLoginModel> call, Response<UserLoginModel> response) {
                if(response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    UserLoginModel userLoginModel = response.body();
                    assert userLoginModel!=null;

                    if(userLoginModel.isStatus()) {
                        progressBar.setVisibility(View.GONE);

                        myAppPrefsManager.setUserLoggedIn(true);
                        myAppPrefsManager.setUserName(emailID);

                        Toast.makeText(LoginActivity.this, "Logged in Successfully !", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this,UserDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(LoginActivity.this, ""+userLoginModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserLoginModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
