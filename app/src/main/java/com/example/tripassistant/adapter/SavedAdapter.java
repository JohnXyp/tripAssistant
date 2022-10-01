package com.example.tripassistant.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripassistant.R;
import com.example.tripassistant.RoomData.TravelAgency;
import com.example.tripassistant.UpdateTravelAgency;

import java.util.List;

//for Travel Agency:
public class SavedAdapter extends RecyclerView.Adapter<SavedAdapter.SavedViewHolder> {

    Context context;
    List<TravelAgency> travelAgencyList;
    DeleteItemClickListener deleteItemClickListener;

    public SavedAdapter(Context context, List<TravelAgency> travelAgencyList, DeleteItemClickListener deleteItemClickListener) {
        this.context= context;
        this.travelAgencyList= travelAgencyList;
        this.deleteItemClickListener= deleteItemClickListener;
    }

    @NonNull
    @Override
    public SavedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SavedViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_agency_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SavedAdapter.SavedViewHolder holder, int position) {
        holder.TravelAgencyCode.setText(String.valueOf(travelAgencyList.get(position).getTravelAgencyCode()));
        holder.TravelAgencyName.setText(travelAgencyList.get(position).getName());
        holder.TravelAgencyAddress.setText(travelAgencyList.get(position).getAddress());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  Intent intent = new Intent(view.getContext(), UpdateTravelAgency.class);
                  intent.putExtra("id",String.valueOf(travelAgencyList.get(position).getTravelAgencyCode()));
                  intent.putExtra("name",String.valueOf(travelAgencyList.get(position).getName()));
                  intent.putExtra("address",String.valueOf(travelAgencyList.get(position).getAddress()));
                  view.getContext().startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemClickListener.onItemDelete(position, travelAgencyList.get(position).getTravelAgencyCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return travelAgencyList.size();
    }

    public static final class SavedViewHolder extends RecyclerView.ViewHolder{

        TextView TravelAgencyCode;
        TextView TravelAgencyName;
        TextView TravelAgencyAddress;

        Button edit;
        Button delete;

        public SavedViewHolder(@NonNull View itemView) {
            super(itemView);

            TravelAgencyCode = itemView.findViewById(R.id.text_TravelAgencyCode);
            TravelAgencyName = itemView.findViewById(R.id.text_name);
            TravelAgencyAddress = itemView.findViewById(R.id.text_address);

            edit = itemView.findViewById(R.id.Edit_TA);
            delete = itemView.findViewById(R.id.Delete_TA);
        }
    }

    public interface DeleteItemClickListener {
        void onItemDelete(int position, int id);
    }
}

