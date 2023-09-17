package com.example.auctionapp.Seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.auctionapp.Adapter.AdapterAuction;
import com.example.auctionapp.Models.Auction;
import com.example.auctionapp.Models.Product;
import com.example.auctionapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DeleteProducts extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private Button btnDelete;
    String pId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_products);

        Intent intent = getIntent();
        pId = intent.getExtras().getString("pId");
        //set

        btnDelete =findViewById(R.id.DelBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseDatabase.getReference("Products").child(pId).removeValue();
            }
        });

    }
}