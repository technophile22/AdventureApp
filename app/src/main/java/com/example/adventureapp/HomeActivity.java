package com.example.adventureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private static final String TAG = "Home Activity";
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
        ImageView cart_btn = (ImageView) findViewById(R.id.cart_btn);

        cart_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(HomeActivity.this,cart.class);
                startActivity(mainIntent);
            }
        });

        cart_btn.setOnClickListener(new View.OnClickListener() {
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

        switch(item.getItemId()) {
            case R.id.nav_cart:
                Intent intent = new Intent(this, cart.class);
                this.startActivity(intent);
                break;
            case R.id.nav_home:
                Intent intent1 = new Intent(this, HomeActivity.class);
                this.startActivity(intent1);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }


    //recyclers for recycler view cards
    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<FeaturedHelperClass> featuredLocations = new ArrayList<>();
        featuredLocations.add(new FeaturedHelperClass(R.drawable.sajjan, "100", "Sajjangarh", "The Monsoon Palace, also known as the Sajjan Garh Palace, is a hilltop palatial residence in the city of Udaipur, Rajasthan in India, overlooking the Fateh Sagar Lake. It is named Sajjangarh after Maharana Sajjan Singh of the Mewar Dynasty, who it was built for in 1884."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.badi, "150", "Badi Lake", "Lake Badi, situated in Udaipur city in the Indian state of Rajasthan, is an artificial fresh water lake. The lake was built in the village of Badi, about 12 km from the city of Udaipur, by Maharana Raj Singh I to counteract the devastating effects of a famine. He named it Jiyan Sagar after his mother Jana Devi."));
        featuredLocations.add(new FeaturedHelperClass(R.drawable.moti, "200", "Moti Magri", "Moti Magri is a hill in India. It overlooks the Fateh Sagar Lake in the city of Udaipur, Rajasthan. Atop the Moti Magri or Pearl Hill is the memorial of the Rajput hero Maharana Pratap, which has a bronze statue of the Maharana astride his favourite horse \"Chetak\""));

        adapter = new FeaturedAdapter(featuredLocations, getApplicationContext());
        featuredRecycler.setAdapter(adapter);



    }



    private void tourRecycler() {

        tourRecycler.setHasFixedSize(true);
        tourRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        ArrayList<TourHelperClass> tourLocations = new ArrayList<>();
        tourLocations.add(new TourHelperClass(R.drawable.amber, "100","Amber Fort", "Amer Fort or Amber Fort is a fort located in Amer, Rajasthan, India. Amer is a town with an area of 4 square kilometres located 11 kilometres from Jaipur, the capital of Rajasthan."));
        tourLocations.add(new TourHelperClass(R.drawable.hawa, "140", "Hawa Mahal", "Hawa Mahal is a palace in Jaipur, India approximately 300 kilometers from the capital city of Delhi. Built from red and pink sandstone, the palace sits on the edge of the City Palace, Jaipur, and extends to the Zenana, or women's chambers."));
        tourLocations.add(new TourHelperClass(R.drawable.jal, "200", "Jal Mahal", "Jal Mahal is a palace in the middle of the Man Sagar Lake in Jaipur city, the capital of the state of Rajasthan, India. The palace and the lake around it were renovated and enlarged in the 18th century by Maharaja Jai Singh II of Amber."));

        adapter = new TourAdapter(tourLocations, getApplicationContext());
        tourRecycler.setAdapter(adapter);



    }

}