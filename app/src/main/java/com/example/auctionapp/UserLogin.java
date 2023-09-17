package com.example.auctionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.auctionapp.Models.User;
import com.example.auctionapp.User.HomePage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLogin extends AppCompatActivity {

    Button user_login_signIn, gotoRegister, forgetPass;
    EditText user_login_password,user_login_email;
    private FirebaseAuth mAuth;

    LinearLayout linearLayout;
    LottieAnimationView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        user_login_signIn = findViewById(R.id.user_login_signIn);
        user_login_password = findViewById(R.id.user_login_password);
        user_login_email = findViewById(R.id.user_login_email);
        gotoRegister = findViewById(R.id.gotoRegister);
        forgetPass = findViewById(R.id.user_forget_pass);
        loading = findViewById(R.id.loading);
        linearLayout = findViewById(R.id.linearLayout);

        loading.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

        mAuth = FirebaseAuth.getInstance();


        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLogin.this, UserRegister.class));
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserLogin.this, ForgetPassword.class));
            }
        });
        user_login_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sign
                String email,password;
                email = user_login_email.getText().toString();
                password = user_login_password.getText().toString();

                if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    Toast.makeText(UserLogin.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                if(user != null) {
                                    Toast.makeText(UserLogin.this, "User login successfully", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(UserLogin.this, MainActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                            else {
                                Toast.makeText(UserLogin.this, "login failed, please try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}