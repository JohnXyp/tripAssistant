package com.example.tripassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripassistant.BarcelonaActivity;
import com.example.tripassistant.BerlinActivity;
import com.example.tripassistant.ParisActivity;
import com.example.tripassistant.PragueActivity;
import com.example.tripassistant.R;
import com.example.tripassistant.VeniceActivity;
import com.example.tripassistant.ViennaActivity;
import com.example.tripassistant.model.RecentsData;

public class RecentsAdapter extends RecyclerView.Adapter<RecentsAdapter.RecentsViewHolder> {

    Context context;
    List<RecentsData> recentsDataList;

    public RecentsAdapter(Context context, List<RecentsData> recentsDataList) {
        this.context = context;
        this.recentsDataList = recentsDataList;
    }

    @NonNull
    @Override
    public RecentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recents_row_item, parent, false);
        return new RecentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentsViewHolder holder, int position) {
        holder.countryName.setText(recentsDataList.get(position).getCountryName());
        holder.placeName.setText(recentsDataList.get(position).getPlaceName());
        holder.price.setText(recentsDataList.get(position).getPrice());
        holder.placeImage.setImageResource(recentsDataList.get(position).getImageUrl());

        //Opening the Activities from places.
       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.getAdapterPosition() == 0) {
                    Intent i = new Intent(context, VeniceActivity.class);
                    context.startActivity(i);
                }
                if (holder.getAdapterPosition() == 1) {
                    Intent i = new Intent(context, ViennaActivity.class);
                    context.startActivity(i);
                }
                if (holder.getAdapterPosition() == 2) {
                    Intent i = new Intent(context, PragueActivity.class);
                    context.startActivity(i);
                }
                if (holder.getAdapterPosition() == 3) {
                    Intent i = new Intent(context, BerlinActivity.class);
                    context.startActivity(i);
                }
                if (holder.getAdapterPosition() == 4) {
                    Intent i = new Intent(context, BarcelonaActivity.class);
                    context.startActivity(i);
                }
                if (holder.getAdapterPosition() == 5) {
                    Intent i = new Intent(context, ParisActivity.class);
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return recentsDataList.size();
    }

    public static final class RecentsViewHolder extends RecyclerView.ViewHolder {

        ImageView placeImage;
        TextView placeName, countryName, price;

        public Context context;

        public RecentsViewHolder(@NonNull View itemView) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.place_image);
            placeName = itemView.findViewById(R.id.place_name);
            countryName = itemView.findViewById(R.id.country_name);
            price = itemView.findViewById(R.id.price);
        }
    }
}

