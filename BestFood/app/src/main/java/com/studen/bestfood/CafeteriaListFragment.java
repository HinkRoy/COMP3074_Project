package com.studen.bestfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
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
        cafeteriaInfoList.add(new CafeteriaInfo("Maple Delights", "+1-416-100-1000", Arrays.asList("Canadian", "Dessert"),
                "4.5", "Authentic Canadian maple treats.", "path/to/image_maple_delights", "Downtown Toronto", 43.651070, -79.347015));
        cafeteriaInfoList.add(new CafeteriaInfo("Harbor Seafood", "+1-416-200-2000", Arrays.asList("Seafood", "Fine Dining"),
                "4.7", "Fresh and delicious seafood by the lake.", "path/to/image_harbor_seafood", "Harbourfront", 43.638947, -79.380699));
        cafeteriaInfoList.add(new CafeteriaInfo("Bella Pasta", "+1-416-300-3000", Arrays.asList("Italian", "Pasta"),
                "4.6", "Traditional Italian pasta dishes.", "path/to/image_bella_pasta", "Little Italy", 43.655208, -79.414481));
        cafeteriaInfoList.add(new CafeteriaInfo("Dragon Dim Sum", "+1-416-400-4000", Arrays.asList("Chinese", "Dim Sum"),
                "4.8", "Delicious dim sum and Chinese cuisine.", "path/to/image_dragon_dim_sum", "Chinatown", 43.652925, -79.398262));
        cafeteriaInfoList.add(new CafeteriaInfo("Curry House", "+1-416-500-5000", Arrays.asList("Indian", "Curry"),
                "4.3", "Spicy and flavorful Indian curries.", "path/to/image_curry_house", "Brampton", 43.731548, -79.762418));
        cafeteriaInfoList.add(new CafeteriaInfo("Sushi Zen", "+1-416-600-6000", Arrays.asList("Japanese", "Sushi"),
                "4.9", "Fresh sushi and Japanese dishes.", "path/to/image_sushi_zen", "Yorkville", 43.670337, -79.391864));
        cafeteriaInfoList.add(new CafeteriaInfo("Veggie Delight", "+1-416-700-7000", Arrays.asList("Vegetarian", "Organic"),
                "4.4", "Healthy and organic vegetarian meals.", "path/to/image_veggie_delight", "Kensington Market", 43.655214, -79.402260));
        cafeteriaInfoList.add(new CafeteriaInfo("Taco Fiesta", "+1-416-800-8000", Arrays.asList("Mexican", "Tacos"),
                "4.5", "Authentic Mexican tacos and more.", "path/to/image_taco_fiesta", "St. Lawrence", 43.649675, -79.371484));
        cafeteriaInfoList.add(new CafeteriaInfo("BBQ Station", "+1-416-900-9000", Arrays.asList("Barbecue", "American"),
                "4.7", "Classic American BBQ flavors.", "path/to/image_bbq_station", "The Annex", 43.670526, -79.404280));
        cafeteriaInfoList.add(new CafeteriaInfo("French Bistro", "+1-416-101-1010", Arrays.asList("French", "Fine Dining"),
                "4.6", "Elegant French dining experience.", "path/to/image_french_bistro", "Etobicoke", 43.643467, -79.576708));

    }
}