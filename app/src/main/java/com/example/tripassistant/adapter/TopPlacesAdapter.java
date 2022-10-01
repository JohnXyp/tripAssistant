package com.example.tripassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripassistant.BarcelonaActivity;
import com.example.tripassistant.ParisActivity;
import com.example.tripassistant.R;
import com.example.tripassistant.VeniceActivity;
import com.example.tripassistant.model.TopPlacesData;

import java.util.List;

public class TopPlacesAdapter extends RecyclerView.Adapter<TopPlacesAdapter.TopPlacesViewHolder> {

    Context context;
    List<TopPlacesData> topPlacesData;

    public TopPlacesAdapter(Context context, List<TopPlacesData> recentsDataList) {
        this.context = context;
        this.topPlacesData = recentsDataList;
    }

    @NonNull
    @Override
    public TopPlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.top_places_row_item, parent, false);
        return new TopPlacesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopPlacesViewHolder holder, int position) {
        holder.countryName.setText(topPlacesData.get(position).getCountryName());
        holder.placeName.setText(topPlacesData.get(position).getPlaceName());
        holder.price.setText(topPlacesData.get(position).getPrice());
        holder.placeImage.setImageResource(topPlacesData.get(position).getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.placeName.getText() == "Paris") {
                    Intent i = new Intent(context, ParisActivity.class);
                    context.startActivity(i);
                }
                if (holder.placeName.getText() == "Venice") {
                    Intent i = new Intent(context, VeniceActivity.class);
                    context.startActivity(i);
                }
                if (holder.placeName.getText() == "Barcelona") {
                    Intent i = new Intent(context, BarcelonaActivity.class);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return topPlacesData.size();
    }

    public static final class TopPlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView placeImage;
        TextView placeName, countryName, price;

        public TopPlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName= itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}

