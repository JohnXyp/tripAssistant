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
import com.example.tripassistant.RoomData.SuggestedTrip;
import com.example.tripassistant.UpdateSuggestedTrip;

import java.util.List;

//for Suggested Trip:
public class Saved2Adapter extends RecyclerView.Adapter<Saved2Adapter.Saved2ViewHolder> {

    Context context;
    List<SuggestedTrip> suggestedTripList;
    DeleteItemClickListener2 deleteItemClickListener2;

    public Saved2Adapter(Context context, List<SuggestedTrip> suggestedTripList, DeleteItemClickListener2 deleteItemClickListener2) {
        this.context= context;
        this.suggestedTripList= suggestedTripList;
        this.deleteItemClickListener2= deleteItemClickListener2;
    }

    @NonNull
    @Override
    public Saved2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Saved2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_trip_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Saved2Adapter.Saved2ViewHolder holder, int position) {
        holder.SuggestedTripCode.setText(String.valueOf(suggestedTripList.get(position).getSuggestedTripCode()));
        holder.SuggestedTripCity.setText(suggestedTripList.get(position).getCity());
        holder.SuggestedTripCountry.setText(suggestedTripList.get(position).getCountry());
        holder.duration.setText(suggestedTripList.get(position).getDuration());
        holder.kind.setText(suggestedTripList.get(position).getKind());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), UpdateSuggestedTrip.class);
                intent.putExtra("id2",String.valueOf(suggestedTripList.get(position).getSuggestedTripCode()));
                intent.putExtra("city",String.valueOf(suggestedTripList.get(position).getCity()));
                intent.putExtra("country",String.valueOf(suggestedTripList.get(position).getCountry()));
                intent.putExtra("duration",String.valueOf(suggestedTripList.get(position).getDuration()));
                intent.putExtra("kind",String.valueOf(suggestedTripList.get(position).getKind()));
                view.getContext().startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItemClickListener2.onItemDelete2(position,suggestedTripList.get(position).getSuggestedTripCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestedTripList.size();
    }

    public static final class Saved2ViewHolder extends RecyclerView.ViewHolder{

        TextView SuggestedTripCode;
        TextView SuggestedTripCity;
        TextView SuggestedTripCountry;
        TextView duration;
        TextView kind;

        Button edit;
        Button delete;

        public Saved2ViewHolder(@NonNull View itemView) {
            super(itemView);

            SuggestedTripCode = itemView.findViewById(R.id.text_SuggestedTripCode);
            SuggestedTripCity = itemView.findViewById(R.id.text_city);
            SuggestedTripCountry = itemView.findViewById(R.id.text_country);
            duration = itemView.findViewById(R.id.text_duration);
            kind = itemView.findViewById(R.id.text_kind);

            edit = itemView.findViewById(R.id.Edit_ST);
            delete = itemView.findViewById(R.id.Delete_ST);
        }
    }

    public interface DeleteItemClickListener2 {
        void onItemDelete2(int position, int id);
    }
}
