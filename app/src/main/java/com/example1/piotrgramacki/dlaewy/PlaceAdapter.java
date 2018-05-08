package com.example1.piotrgramacki.dlaewy;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Piotrek on 2018-05-07.
 */

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    private ArrayList<Place> places;
    private OnItemClickListener listener;

    public PlaceAdapter(Context context) {
        places = PlacesManager.getPlaces();
        listener = (OnItemClickListener) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.image.setImageResource(places.get(position).getOldPhoto());
        holder.name.setText(places.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemView;
        ImageView image;
        TextView name;

        public ViewHolder(LinearLayout itemView) {
            super(itemView);
            this.itemView = itemView;
            this.image = itemView.findViewById(R.id.list_image);
            this.name = itemView.findViewById(R.id.list_name);
        }
    }
}
