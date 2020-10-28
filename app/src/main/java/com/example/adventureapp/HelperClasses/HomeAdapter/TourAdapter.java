package com.example.adventureapp.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.R;

import java.util.ArrayList;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder> {



    ArrayList<TourHelperClass> tourLocations;

    public TourAdapter(ArrayList<TourHelperClass> tourLocations) {
        this.tourLocations = tourLocations;
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

        TourHelperClass TourHelperClass = tourLocations.get(position);
        holder.image.setImageResource(TourHelperClass.getImage());
        holder.title.setText(TourHelperClass.getTitle());
        holder.desc.setText(TourHelperClass.getDescription());
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
