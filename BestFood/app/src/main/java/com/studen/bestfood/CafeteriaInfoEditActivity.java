package com.studen.bestfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CafeteriaInfoEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_info_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        Button btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(v -> {
            String name = ((EditText) findViewById(R.id.editTextCafeteriaName)).getText().toString();
            String rating = ((EditText) findViewById(R.id.editTextCafeteriaRating)).getText().toString();
            String description = ((EditText) findViewById(R.id.editTextCafeteriaDescription)).getText().toString();
            finish();
        });
    }
}