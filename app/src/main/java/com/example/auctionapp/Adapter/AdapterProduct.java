package com.example.auctionapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.auctionapp.ForgetPassword;
import com.example.auctionapp.Models.Product;
import com.example.auctionapp.R;
import com.example.auctionapp.Seller.AddProducts;
import com.example.auctionapp.Seller.DeleteProducts;
import com.example.auctionapp.Seller.EditProducts;
import com.example.auctionapp.Seller.SellerProductDetails;
import com.example.auctionapp.User.ProductDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.MyHolder>  {

    Context context;
    List<Product> productsList;
    boolean isUser;
    int action;//0 user 1 seller 2 admin //
    public AdapterProduct(Context context, List<Product> productsList,int action) {
        this.context = context;
        this.productsList = productsList;
        this.action=action;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_product,parent,false);
        return new AdapterProduct.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, @SuppressLint("RecyclerView") final int position) {
        final String sId= productsList.get(position).getsId();
        final String name= productsList.get(position).getpName();
        final String endTime= String.valueOf(productsList.get(position).getEndTime());
        final String price= productsList.get(position).getMaxPrice();
        final String imageUrl= productsList.get(position).getpImageUrl();

        //setdata
        Picasso.get().load(imageUrl).into(holder.img);
        holder.pName.setText(""+name);
        holder.totalPrice.setText("Max Price : $"+ price);
        holder.vendorId.setText("Seller Id : "+sId);
        holder.endTime.setText("End Time : "+endTime);
        //handle click

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action==0){
                    Intent yourIntent = new Intent(context, ProductDetails.class);
                    yourIntent.putExtra("pId", productsList.get(position).getpId());
                    context.startActivity(yourIntent);
                }else if(action==1){
                    Intent yourIntent = new Intent(context, SellerProductDetails.class);
                    yourIntent.putExtra("pId", productsList.get(position).getpId());
                    context.startActivity(yourIntent);
                }else if(action==2){
                    Intent yourIntent = new Intent(context, DeleteProducts.class);
                    yourIntent.putExtra("pId", productsList.get(position).getpId());
                    context.startActivity(yourIntent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return productsList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView pName,vendorId,totalPrice,endTime;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            pName=itemView.findViewById(R.id.pName);
            vendorId=itemView.findViewById(R.id.selllerId);
            totalPrice=itemView.findViewById(R.id.maxPrice);
            endTime=itemView.findViewById(R.id.endTime);
        }
    }

}