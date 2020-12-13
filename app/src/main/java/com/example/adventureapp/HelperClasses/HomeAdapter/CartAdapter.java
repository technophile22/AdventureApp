package com.example.adventureapp.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adventureapp.R;
import com.example.adventureapp.description_page;

import java.util.ArrayList;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<CartHelperClass> cartLocations;

    public CartAdapter(ArrayList<CartHelperClass> cartLocations, Context applicationContext) {
        this.cartLocations = cartLocations;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        CartAdapter.CartViewHolder cartViewHolder = new CartAdapter.CartViewHolder(view);

        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {

        final CartHelperClass cartHelperClass = cartLocations.get(position);
        holder.image.setImageResource(cartHelperClass.getImage());
        holder.title.setText(cartHelperClass.getTitle());
        holder.quantity.setText(cartHelperClass.getQuantity());
        holder.price.setText(cartHelperClass.getPrice());

        /*
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: " + featuredLocations.get(position));

                //Toast.makeText(context, (CharSequence) featuredLocations.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, description_page.class);
                intent.putExtra("image", cartHelperClass.getImage());
                intent.putExtra("title", cartHelperClass.getTitle());
                intent.putExtra("description", cartHelperClass.getDescription());
                intent.putExtra("price", cartHelperClass.getPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

         */

    }

    @Override
    public int getItemCount() {
        return cartLocations.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView title, quantity, price;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks

            image = itemView.findViewById(R.id.cartImage);
            title = itemView.findViewById(R.id.fragranceName);
            quantity = itemView.findViewById(R.id.quantity);
            price = itemView.findViewById(R.id.price);

        }


    }
}
