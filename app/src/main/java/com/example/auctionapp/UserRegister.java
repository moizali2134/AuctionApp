package com.example.auctionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class UserRegister extends AppCompatActivity {

    EditText address, email, password, phoneNo, uName;
    private FirebaseAuth mAuth;

    Button gotoLogin,registerBtn,gotoAdmin;

    //loading screen
    ScrollView scrollView;
    LottieAnimationView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        address=findViewById(R.id.address);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        phoneNo=findViewById(R.id.phoneNo);
        uName=findViewById(R.id.uName);

        gotoLogin=findViewById(R.id.gotoLogin);
        registerBtn=findViewById(R.id.registerBtn);

        //screen loading
        loading=findViewById(R.id.loading);
        scrollView=findViewById(R.id.scrollable);
        loading.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);

        //init firebase
        mAuth = FirebaseAuth.getInstance();

        gotoLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(UserRegister.this, UserLogin.class);
                        startActivity(i);
                    }
                });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user info
                String saddress,semail,spassword,sphoneNo,suName;
                semail = email.getText().toString();
                suName = uName.getText().toString();
                spassword = password.getText().toString();
                sphoneNo = phoneNo.getText().toString();
                saddress = address.getText().toString();
                if (!Patterns.EMAIL_ADDRESS.matcher(semail).matches()){
                    email.setError("Invalided Email");
                    email.setFocusable(true);
                }
                else if(spassword.length()<6){
                    password.setError("Password length at least 6 characters");
                    password.setFocusable(true);
                }
                else if(suName.isEmpty()){
                    uName.setError("Name is empty");
                    uName.setFocusable(true);
                }
                else if(sphoneNo.length()<10){
                    phoneNo.setError("PhoneNo length at least 10 characters");
                    phoneNo.setFocusable(true);
                }
                else if(saddress.length()<4){
                    address.setError("RollNo length at least 4 characters");
                    address.setFocusable(true);
                }
                else {

                    mAuth.createUserWithEmailAndPassword(semail, spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                String uid = user.getUid();
                                final HashMap<Object,String> hashMap=new HashMap<>();
                                // storing data in realtime firebase database
                                hashMap.put("uName",suName);
                                hashMap.put("email",semail);
                                hashMap.put("phoneNo",sphoneNo);
                                hashMap.put("password",spassword);
                                hashMap.put("address",saddress);
                                hashMap.put("uId",uid);

                                final FirebaseDatabase database=FirebaseDatabase.getInstance();

                                DatabaseReference reference=database.getReference("Users");

                                reference.child(uid).setValue(hashMap);

                                Toast.makeText(UserRegister.this, "user registered successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(UserRegister.this, UserLogin.class);
                                startActivity(i);
                                finish();
                            }
                            else {
                                Toast.makeText(UserRegister.this, "failed to registered user please try again !", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });
    }
}