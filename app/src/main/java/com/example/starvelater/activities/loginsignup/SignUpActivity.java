package com.example.starvelater.activities.loginsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.starvelater.R;
import com.example.starvelater.activities.user.UserDashboard;
import com.example.starvelater.api.ApiInterface;
import com.example.starvelater.api.RetrofitClient;
import com.example.starvelater.jsonmodels.UserRegistrationModel;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ImageView btnback;
    EditText edtName,edtEmail,edtPass,edtConfPass,edtPhone;
    Button btnSubmit;
    LinearLayout progressBar;
    String name,email,pass,conPass,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnback = findViewById(R.id.back_button);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboard.class));
            }
        });

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        progressBar = findViewById(R.id.progressBar);

        btnSubmit = findViewById(R.id.btnSubmit);
        edtName = findViewById(R.id.editName);
        edtEmail = findViewById(R.id.editMail);
        edtPass = findViewById(R.id.editPassword);
        edtConfPass = findViewById(R.id.editConfPassword);
        edtPhone = findViewById(R.id.editPhone);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edtName.getText().toString().trim();
                email = edtEmail.getText().toString().trim();
                pass= edtPass.getText().toString().trim();
                conPass = edtConfPass.getText().toString().trim();
                phone = edtPhone.getText().toString().trim();

                if(name.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter Name !", Toast.LENGTH_SHORT).show();
                } else  if(email.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter Email ID !", Toast.LENGTH_SHORT).show();
                } else  if(!email.matches(emailPattern)) {
                    Toast.makeText(SignUpActivity.this, "Please enter Valid Email ID !", Toast.LENGTH_SHORT).show();
                } else  if(pass.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter Password !", Toast.LENGTH_SHORT).show();
                }  else  if(conPass.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter Confirm Password !", Toast.LENGTH_SHORT).show();
                } else if(!pass.equals(conPass)) {
                    Toast.makeText(SignUpActivity.this, "Password and Confirm Password should be same !", Toast.LENGTH_SHORT).show();
                } else  if(phone.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please enter Phone Number !", Toast.LENGTH_SHORT).show();
                } else if(phone.length() !=10) {
                    Toast.makeText(SignUpActivity.this, "Invalid Phone Number !", Toast.LENGTH_SHORT).show();
                } else {
                    next();
                }

            }
        });
    }

    public void next() {
         progressBar.setVisibility(View.VISIBLE);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name",name);
        jsonObject.addProperty("email",email);
        jsonObject.addProperty("password",pass);
        jsonObject.addProperty("phone",phone);


        ApiInterface apiInterface = RetrofitClient.getClient(this).create(ApiInterface.class);

        apiInterface.processUserRegistration(jsonObject).enqueue(new Callback<UserRegistrationModel>() {
            @Override
            public void onResponse(Call<UserRegistrationModel> call, Response<UserRegistrationModel> response) {
                if(response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);

                    UserRegistrationModel userRegistrationModel = response.body();
                    assert userRegistrationModel!=null;

                    if(userRegistrationModel.isStatus()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, "Registered Successfully !", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(SignUpActivity.this, ""+userRegistrationModel.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserRegistrationModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
