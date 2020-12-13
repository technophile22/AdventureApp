package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.adventureapp.HelperClasses.HomeAdapter.CartAdapter;
import com.example.adventureapp.HelperClasses.HomeAdapter.CartHelperClass;



import java.util.ArrayList;


public class cart extends AppCompatActivity {

    RecyclerView cartRecycler;
    TextView totalPrice;
    RecyclerView.Adapter adapter;
    Button button_payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        cartRecycler = findViewById(R.id.cart_recycler);
        cartRecycler();

        totalPrice = findViewById(R.id.totalPrice);
        totalPrice.setText("950 Rupees");

        button_payment = findViewById(R.id.button_payment);

        button_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cart.this, Checkout.class);
                startActivity(intent);
            }
        });

    }

    private void cartRecycler() {

        cartRecycler.setHasFixedSize(true);
        cartRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ArrayList<CartHelperClass> cartLocations = new ArrayList<>();
        cartLocations.add(new CartHelperClass(R.drawable.sajjan, "300 Rupees", "Sajjangarh", "3 Tickets"));
        cartLocations.add(new CartHelperClass(R.drawable.amber, "200 Rupees", "Amber Fort", "2 Tickets"));
        cartLocations.add(new CartHelperClass(R.drawable.badi, "450 Rupees", "Badi Lake", "3 Tickets"));

        adapter = new CartAdapter(cartLocations, getApplicationContext());
        cartRecycler.setAdapter(adapter);



    }
}