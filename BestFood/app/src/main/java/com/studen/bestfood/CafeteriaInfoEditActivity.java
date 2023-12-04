package com.studen.bestfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CafeteriaInfoEditActivity extends AppCompatActivity {

    EditText nameET, ratingET, descriptionET, phoneET, tagsET, locationNameET, longitudeET, latitudeT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_info_edit);

        nameET = findViewById(R.id.editTextCafeteriaName);
        ratingET = findViewById(R.id.editTextCafeteriaRating);
        descriptionET = findViewById(R.id.editTextCafeteriaDescription);
        phoneET = findViewById(R.id.editTextCafeteriaPhone);
        tagsET = findViewById(R.id.editTextCafeteriaTags);
        locationNameET = findViewById(R.id.editTextCafeteriaLocationName);
        longitudeET = findViewById(R.id.editTextCafeteriaLongitude);
        latitudeT = findViewById(R.id.editTextCafeteriaLatitude);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        Button btnSave = findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(v -> {
            String imagePath = "Add photo function at the back";
            String name = nameET.getText().toString().trim();
            String rating = ratingET.getText().toString().trim();
            String description = descriptionET.getText().toString().trim();
            String phone = phoneET.getText().toString().trim();
            String tags = tagsET.getText().toString().trim();
            String locationName = locationNameET.getText().toString().trim();
            String longitude = longitudeET.getText().toString().trim();
            String latitude = latitudeT.getText().toString().trim();

            if (name.isEmpty() || rating.isEmpty() || description.isEmpty() ||
                    phone.isEmpty() || tags.isEmpty() || locationName.isEmpty() || longitude.isEmpty() || latitude.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            CafeteriaInfo cafeteriaInfo = new CafeteriaInfo(name, phone, tags,
                    rating, description, imagePath, locationName,
                    Double.parseDouble(latitude), Double.parseDouble(longitude));
            saveCafeteriaInfo(cafeteriaInfo);
            finish();
        });
    }

    private void saveCafeteriaInfo(CafeteriaInfo cafeteriaInfo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.cafeteriaInfoDao().insert(cafeteriaInfo);
            handler.post(() -> {
                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            });
        });
    }


}