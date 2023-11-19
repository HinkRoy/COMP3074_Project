package com.studen.bestfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class CafeteriaListFragment extends Fragment {

    public CafeteriaListFragment() {
    }

    private List<CafeteriaInfo> cafeteriaInfoList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafeteria_list, container, false);
        initializeRestaurantData();

        RecyclerView cafeteriaListView = view.findViewById(R.id.cafeteriaListView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        cafeteriaListView.setLayoutManager(layoutManager);

        CafeteriaListAdapter adapter = new CafeteriaListAdapter(cafeteriaInfoList);
        cafeteriaListView.setAdapter(adapter);

        return view;
    }

    private void initializeRestaurantData() {
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "4.5", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Toronto Seafood Bistro", "4.7", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "4.5", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Toronto Seafood Bistro", "4.7", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "4.5", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Toronto Seafood Bistro", "4.7", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "4.5", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Toronto Seafood Bistro", "4.7", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "4.5", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Toronto Seafood Bistro", "4.7", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "4.5", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
        cafeteriaInfoList.add(new CafeteriaInfo("Toronto Seafood Bistro", "4.7", "Traditional Canadian flavours with great maple syrup!",R.drawable.logo));
    }
}