package com.example.adventureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.adventureapp.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.adventureapp.HelperClasses.HomeAdapter.FeaturedHelperClass;
import com.example.adventureapp.HelperClasses.HomeAdapter.TourHelperClass;
import com.example.adventureapp.HelperClasses.HomeAdapter.TourAdapter;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //variables
     static final float END_SCALE = 0.7f;
    RecyclerView featuredRecycler;
    RecyclerView tourRecycler;
    RecyclerView.Adapter adapter;

    //Drawer menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView menuIcon;
    LinearLayout contentView;




    //logout firebase
    Button button_logout;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //cart button

        ImageView cart_image = (ImageView) findViewById(R.id.cart);

        cart_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(HomeActivity.this,cart.class);
                startActivity(mainIntent);
            }
        });

        //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        tourRecycler = findViewById(R.id.tour_recycler);
        menuIcon = findViewById(R.id.menu_icon);
        featuredRecycler();
        tourRecycler();

        //Menu Hooks
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);

        navigationDrawer();

        menuIcon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.content);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        button_logout = findViewById(R.id.button_logout);
        mAuth = FirebaseAuth.getInstance();

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(HomeActivity.this, login.class);
                //Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                HomeActivity.this.startActivity(intent);
            }
        });

    }

    //Navigation Drawer functions
    private void navigationDrawer() {

        //Navigation Drawer
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener( this);
        navigationView.setCheckedItem(R.id.nav_home);

        menuIcon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(drawerLayout.isDrawerVisible(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        //Add any color or remove it to use the default one!
        //To make it transparent use Color.Transparent in side setScrimColor();
        //drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return true;
    }


    //recyclers for recycler view cards
    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.sajjan, "Sajjangarh", "The Monsoon Palace, also known as the Sajjan Garh Palace, is a hilltop palatial residence in the city of Udaipur, Rajasthan in India, overlooking the Fateh Sagar Lake. It is named Sajjangarh after Maharana Sajjan Singh of the Mewar Dynasty, who it was built for in 1884."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.badi, "Badi Lake", "Lake Badi, situated in Udaipur city in the Indian state of Rajasthan, is an artificial fresh water lake. The lake was built in the village of Badi, about 12 km from the city of Udaipur, by Maharana Raj Singh I to counteract the devastating effects of a famine. He named it Jiyan Sagar after his mother Jana Devi."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.moti, "Moti Magri", "Moti Magri is a hill in India. It overlooks the Fateh Sagar Lake in the city of Udaipur, Rajasthan. Atop the Moti Magri or Pearl Hill is the memorial of the Rajput hero Maharana Pratap, which has a bronze statue of the Maharana astride his favourite horse \"Chetak\""));

        adapter = new FeaturedAdapter(featuredLocations, getApplicationContext());
        featuredRecycler.setAdapter(adapter);



    }



    private void tourRecycler() {

        tourRecycler.setHasFixedSize(true);
        tourRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<TourHelperClass> tourLocations = new ArrayList<>();
        tourLocations.add(new TourHelperClass(R.drawable.sajjan, "Sajjangarh", "The Monsoon Palace, also known as the Sajjan Garh Palace, is a hilltop palatial residence in the city of Udaipur, Rajasthan in India, overlooking the Fateh Sagar Lake. It is named Sajjangarh after Maharana Sajjan Singh of the Mewar Dynasty, who it was built for in 1884."));
        tourLocations.add(new TourHelperClass(R.drawable.badi, "Badi Lake", "Lake Badi, situated in Udaipur city in the Indian state of Rajasthan, is an artificial fresh water lake. The lake was built in the village of Badi, about 12 km from the city of Udaipur, by Maharana Raj Singh I to counteract the devastating effects of a famine. He named it Jiyan Sagar after his mother Jana Devi."));
        tourLocations.add(new TourHelperClass(R.drawable.moti, "Moti Magri", "Moti Magri is a hill in India. It overlooks the Fateh Sagar Lake in the city of Udaipur, Rajasthan. Atop the Moti Magri or Pearl Hill is the memorial of the Rajput hero Maharana Pratap, which has a bronze statue of the Maharana astride his favourite horse \"Chetak\"."));

        adapter = new TourAdapter(tourLocations);
        tourRecycler.setAdapter(adapter);



    }

}