package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class description_page extends AppCompatActivity {

    TextView value, price, total;
    String imagePrice;
    Button addToCart;
    ImageView cart_btn, back;
    int count = 1;


    private static final String TAG = "description_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);
        getIncomingIntent();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        value = (TextView) findViewById(R.id.quantity_text_view);
        price = (TextView) findViewById(R.id.price);
        price.setText(imagePrice);
        total = (TextView) findViewById((R.id.cost_text_view));
        addToCart =  findViewById(R.id.cart_button);
        cart_btn = findViewById(R.id.cart_btn);
        back = findViewById(R.id.back);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(description_page.this, "Tickets added to cart successfully", Toast.LENGTH_SHORT).show();
            }
        });

        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(description_page. this,cart.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(description_page. this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }



    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image") && getIntent().hasExtra("title") && getIntent().hasExtra("description") && getIntent().hasExtra("price")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            Integer imageUrl = getIntent().getIntExtra("image", 0);
            String imageTitle = getIntent().getStringExtra("title");
            String imageDescription = getIntent().getStringExtra("description");
            imagePrice = getIntent().getStringExtra("price");


            setImage(imageUrl, imageTitle, imageDescription);

        }
    }

    public void increment(View v){
        count++;
        value.setText("" + count);
        int i=Integer.parseInt(imagePrice);
        total.setText("" + String.valueOf(i * count));
    }

    public void decrement(View v){
        if (count <= 1) count = 1;
        else
            count--;
        value.setText("" + count);
        int i=Integer.parseInt(imagePrice);
        total.setText("" + String.valueOf(i * count));

    }

    private void setImage(Integer imageUrl, String imageTitle, String imageDescription){
        Log.d(TAG, "setImage: setting te image and name to widgets.");



        TextView title = findViewById(R.id.desc_title);
        title.setText(imageTitle);

        TextView description = findViewById(R.id.desc_description);
        description.setText(imageDescription);

        ImageView image = findViewById(R.id.desc_image);
        image.setImageResource(imageUrl);





    }






}