package com.studen.bestfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CafeteriaListFragment extends Fragment {

    private EditText searchET;

    public CafeteriaListFragment() {
    }

    private CafeteriaListAdapter adapter;
    private List<CafeteriaInfo> cafeteriaInfoList = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafeteria_list, container, false);

        searchET = view.findViewById(R.id.searchBox);
        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()){
                    loadData();
                }else {
                    filter(s.toString());
                }
            }
        });


        RecyclerView cafeteriaListView = view.findViewById(R.id.cafeteriaListView);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        cafeteriaListView.setLayoutManager(layoutManager);

        adapter = new CafeteriaListAdapter(cafeteriaInfoList);
        cafeteriaListView.setAdapter(adapter);

        return view;
    }

    private void loadData() {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getContext());
            List<CafeteriaInfo> data = db.cafeteriaInfoDao().getAll();

            handler.post(() -> {
                cafeteriaInfoList.clear();
                cafeteriaInfoList.addAll(data);
                adapter.notifyDataSetChanged();
            });
        });
    }

    private void filter(String text) {
        List<CafeteriaInfo> filteredList = new ArrayList<>();

        for (CafeteriaInfo info : cafeteriaInfoList) {
            if (info.getName().toLowerCase().contains(text.toLowerCase()) ||
                    info.getTags().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(info);
            }
        }
        adapter.updateData(filteredList);
    }

}