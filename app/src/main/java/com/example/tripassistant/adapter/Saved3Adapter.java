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
import com.example.tripassistant.RoomData.TripPackage;
import com.example.tripassistant.UpdateTripPackage;

import java.util.List;

//for Trip Package:
public class Saved3Adapter extends RecyclerView.Adapter<Saved3Adapter.Saved3ViewHolder> {

    Context context;
    List<TripPackage> tripPackageList;
    DeleteItemClickListener3 deleteItemClickListener3;

    public Saved3Adapter(Context context, List<TripPackage> tripPackageList , DeleteItemClickListener3 deleteItemClickListener3) {
        this.context= context;
        this.tripPackageList= tripPackageList;
        this.deleteItemClickListener3 = deleteItemClickListener3;
    }

    @NonNull
    @Override
    public Saved3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Saved3ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_package_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Saved3ViewHolder holder, int position) {
        holder.PackageCode.setText(String.valueOf(tripPackageList.get(position).getPackageCode()));
        holder.Package_TACode.setText(String.valueOf(tripPackageList.get(position).getPackage_TACode()));
        holder.Package_STCode.setText(String.valueOf(tripPackageList.get(position).getPackage_STCode()));
        holder.departureDate.setText(tripPackageList.get(position).getDepartureDate());
        holder.price.setText(tripPackageList.get(position).getPrice());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateTripPackage.class);
                intent.putExtra("id3",String.valueOf(tripPackageList.get(position).getPackageCode()));
                intent.putExtra("ta_id",String.valueOf(tripPackageList.get(position).getPackage_TACode()));
                intent.putExtra("st_id",String.valueOf(tripPackageList.get(position).getPackage_STCode()));
                intent.putExtra("departureDate",String.valueOf(tripPackageList.get(position).getDepartureDate()));
                intent.putExtra("price",String.valueOf(tripPackageList.get(position).getPrice()));
                view.getContext().startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemClickListener3.onItemDelete3(position,tripPackageList.get(position).getPackageCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tripPackageList.size();
    }

    public static final class Saved3ViewHolder extends RecyclerView.ViewHolder{

        TextView PackageCode;
        TextView Package_TACode;
        TextView Package_STCode;
        TextView departureDate;
        TextView price;

        Button edit;
        Button delete;

        public Saved3ViewHolder(@NonNull View itemView) {
            super(itemView);

            PackageCode = itemView.findViewById(R.id.text_PackageCode);
            Package_TACode = itemView.findViewById(R.id.text_Package_TACode);
            Package_STCode = itemView.findViewById(R.id.text_Package_STCode);
            departureDate = itemView.findViewById(R.id.text_departureDate);
            price = itemView.findViewById(R.id.text_price);

            edit = itemView.findViewById(R.id.Edit_TP);
            delete = itemView.findViewById(R.id.Delete_TP);
        }
    }

    public interface DeleteItemClickListener3 {
        void onItemDelete3(int position, int id);
    }
}

