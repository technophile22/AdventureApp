package com.example.adventureapp.HelperClasses.HomeAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.R;
import com.example.adventureapp.description_page;

import java.util.ArrayList;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {


    Context context;
    ArrayList<TourHelperClass> tourLocations;

    public TourAdapter(ArrayList<TourHelperClass> tourLocations, Context applicationContext) {
        this.tourLocations = tourLocations;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tour_packages_card_design, parent, false);
        TourViewHolder TourViewHolder = new TourViewHolder(view);

        return TourViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {

        final TourHelperClass TourHelperClass = tourLocations.get(position);
        holder.image.setImageResource(TourHelperClass.getImage());
        holder.title.setText(TourHelperClass.getTitle());
        holder.desc.setText(TourHelperClass.getDescription());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: " + featuredLocations.get(position));

                //Toast.makeText(context, (CharSequence) featuredLocations.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, description_page.class);
                intent.putExtra("image", TourHelperClass.getImage());
                intent.putExtra("title", TourHelperClass.getTitle());
                intent.putExtra("description", TourHelperClass.getDescription());
                intent.putExtra("price", TourHelperClass.getPrice());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tourLocations.size();
    }

    public static class TourViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, desc;

        public TourViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks

            image = itemView.findViewById(R.id.tour_image);
            title = itemView.findViewById(R.id.tour_title);
            desc = itemView.findViewById(R.id.tour_description);
        }
    }
}
