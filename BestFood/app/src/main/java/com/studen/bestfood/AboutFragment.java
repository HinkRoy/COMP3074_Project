package com.studen.bestfood;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AboutFragment extends Fragment {
    public AboutFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        TextView personal_name = view.findViewById(R.id.personal_name);
        personal_name.setText("HengZhou");
        TextView personal_profile = view.findViewById(R.id.personal_profile);
        personal_profile.setText("I am a passionate and excellence-seeking Computer Science student looking to apply my skills and knowledge to solving real-world problems and constantly improving myself. I look forward to collaborating with like-minded individuals and exploring the endless possibilities of computer science.");
        TextView personal_name_1 = view.findViewById(R.id.personal_name_1);
        personal_name_1.setText("Bingye Liu");
        TextView personal_profile_1 = view.findViewById(R.id.personal_profile_1);
        personal_profile_1.setText("I am a passionate and excellence-seeking Computer Science student looking to apply my skills and knowledge to solving real-world problems and constantly improving myself. I look forward to collaborating with like-minded individuals and exploring the endless possibilities of computer science.");
        return view;
    }
}