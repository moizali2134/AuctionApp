package com.example.auctionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    Button submit;
    EditText name, email, phone, msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        submit = findViewById(R.id.contact_submit);

        name = findViewById(R.id.contact_name);
        email = findViewById(R.id.contact_email);
        phone = findViewById(R.id.contact_phone);
        msg = findViewById(R.id.contact_message);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getName, getEmail, getPhone, getMsg;

                getName = name.getText().toString();
                getEmail = email.getText().toString();
                getPhone = phone.getText().toString();
                getMsg = msg.getText().toString();

                if (TextUtils.isEmpty(getName) && TextUtils.isEmpty(getEmail) && TextUtils.isEmpty(getPhone) && TextUtils.isEmpty(getMsg)) {
                    Toast.makeText(ContactUs.this, "Please enter required fields", Toast.LENGTH_SHORT).show();

                } else {
                    Intent i = new Intent(ContactUs.this, ContactResponse.class);
                    startActivity(i);

                }

            }
        });
    }
}