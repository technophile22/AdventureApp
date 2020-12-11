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

import static android.content.ContentValues.TAG;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {


    Context context;
    ArrayList<FeaturedHelperClass> featuredLocations;

    public FeaturedAdapter(ArrayList<FeaturedHelperClass> featuredLocations, Context applicationContext) {
        this.featuredLocations = featuredLocations;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design, parent, false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);

        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        final FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);
        holder.image.setImageResource(featuredHelperClass.getImage());
        holder.title.setText(featuredHelperClass.getTitle());
        holder.desc.setText(featuredHelperClass.getDescription());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: " + featuredLocations.get(position));

                //Toast.makeText(context, (CharSequence) featuredLocations.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, description_page.class);
                intent.putExtra("image", featuredHelperClass.getImage());
                intent.putExtra("title", featuredHelperClass.getTitle());
                intent.putExtra("description", featuredHelperClass.getDescription());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {


        ImageView image;
        TextView title, desc;


        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks

            image = itemView.findViewById(R.id.desc_image);
            title = itemView.findViewById(R.id.desc_title);
            desc = itemView.findViewById(R.id.desc_description);
        }


    }
}
