package com.studen.bestfood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CafeteriaListAdapter extends RecyclerView.Adapter<CafeteriaListAdapter.ViewHolder> {
    private List<CafeteriaInfo> cafeteriaInfoList;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cafeteriaImage;
        public TextView cafeteriaName, cafeteriaRating;

        public ViewHolder(View itemView) {
            super(itemView);
            cafeteriaImage = itemView.findViewById(R.id.cafeteriaImage);
            cafeteriaName = itemView.findViewById(R.id.cafeteriaName);
            cafeteriaRating = itemView.findViewById(R.id.cafeteriaRating);
        }
    }
    public CafeteriaListAdapter(List<CafeteriaInfo> cafeteriaInfoList) {
        this.cafeteriaInfoList = cafeteriaInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cafeteria_list_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CafeteriaInfo cafeteriaInfo = cafeteriaInfoList.get(position);
        holder.cafeteriaName.setText(cafeteriaInfo.getName());
        holder.cafeteriaRating.setText(cafeteriaInfo.getRating());
        holder.cafeteriaImage.setImageResource(cafeteriaInfo.getImageResId());
    }

    @Override
    public int getItemCount() {
        return cafeteriaInfoList.size();
    }
}

