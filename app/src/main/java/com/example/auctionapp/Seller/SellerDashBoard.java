package com.example.auctionapp.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.auctionapp.MainActivity;
import com.example.auctionapp.Models.User;
import com.example.auctionapp.R;
import com.example.auctionapp.User.UserAccount;
import com.example.auctionapp.User.ui.slideshow.SlideshowFragment;

public class SellerDashBoard extends AppCompatActivity {
    Button addProduct, viewProducts, placedProducts, seller_acc, back_btn, btn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dash_board);
        addProduct = findViewById(R.id.addProduct);
        viewProducts = findViewById(R.id.viewProducts);
        placedProducts = findViewById(R.id.placedProducts);
        seller_acc = findViewById(R.id.seller_acc);
        back_btn = findViewById(R.id.backBtnSeller);

        // testing
        btn_edit = findViewById(R.id.editProducts);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerDashBoard.this, AddProducts.class));
            }
        });

        viewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerDashBoard.this, ViewProducts.class));
            }
        });

        placedProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellerDashBoard.this, PlacedOrders.class));
            }
        });

        seller_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yourIntent = new Intent(SellerDashBoard.this, UserAccount.class);
                yourIntent.putExtra("refrenceId","Users");
                startActivity(yourIntent);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SellerDashBoard.this, MainActivity.class);
                startActivity(i);
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SellerDashBoard.this, EditProducts.class);
                startActivity(i);
            }
        });
    }
}