package com.studen.bestfood;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class CafeteriaListAdapter extends RecyclerView.Adapter<CafeteriaListAdapter.ViewHolder> {
    private static List<CafeteriaInfo> cafeteriaInfoList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView cafeteriaImage;
        public TextView cafeteriaName, cafeteriaRating, cafeteriaDescription, cafeteriaTags;

        public ViewHolder(View itemView) {
            super(itemView);
            cafeteriaImage = itemView.findViewById(R.id.cafeteriaImage);
            cafeteriaName = itemView.findViewById(R.id.cafeteriaName);
            cafeteriaRating = itemView.findViewById(R.id.cafeteriaRating);
            cafeteriaDescription = itemView.findViewById(R.id.cafeteriaDescription);
            cafeteriaTags = itemView.findViewById(R.id.cafeteriaTags);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    CafeteriaInfo cafeteriaInfo = cafeteriaInfoList.get(position);
                    Intent intent = new Intent(itemView.getContext(), CafeteriaDetailActivity.class);
                    intent.putExtra("CAFETERIA_INFO_ID", cafeteriaInfo.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
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
        File imgFile = new File(cafeteriaInfo.getImagePath());
        if (imgFile.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.cafeteriaImage.setImageBitmap(bitmap);
        } else {
            holder.cafeteriaImage.setImageResource(R.drawable.logo);
        }
        holder.cafeteriaName.setText(cafeteriaInfo.getName());
        holder.cafeteriaRating.setText(cafeteriaInfo.getRating());
        holder.cafeteriaDescription.setText(cafeteriaInfo.getDescription());
        holder.cafeteriaTags.setText(cafeteriaInfo.getTags());
    }

    @Override
    public int getItemCount() {
        return cafeteriaInfoList.size();
    }

    public void updateData(List<CafeteriaInfo> newData) {
        cafeteriaInfoList.clear();
        cafeteriaInfoList.addAll(newData);
        notifyDataSetChanged();
    }
}


