package com.example.adventureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class description_page extends AppCompatActivity {

    private static final String TAG = "description_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_page);
        getIncomingIntent();
    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image") && getIntent().hasExtra("title") && getIntent().hasExtra("description")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            Integer imageUrl = getIntent().getIntExtra("image", 0);
            String imageTitle = getIntent().getStringExtra("title");
            String imageDescription = getIntent().getStringExtra("description");
            setImage(imageUrl, imageTitle, imageDescription);
        }
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