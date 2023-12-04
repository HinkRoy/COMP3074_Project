package com.studen.bestfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CafeteriaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        setTitle("Cafeteria Detail");

        ImageButton mapButton = findViewById(R.id.btnMap);
        mapButton.setOnClickListener(v -> {
            Intent intent = new Intent(CafeteriaDetailActivity.this, MapActivity.class);
            startActivity(intent);
        });

        CafeteriaInfo cafeteriaInfo = getIntent().getParcelableExtra("CAFETERIA_INFO");
        ImageView ivImage = findViewById(R.id.cafeteriaImage);
//        if (cafeteriaInfo.getImagePath() == null || cafeteriaInfo.getImagePath().isEmpty()) {
            ivImage.setImageResource(R.drawable.logo);
//        } else {
//            Bitmap bitmap = BitmapFactory.decodeFile(cafeteriaInfo.getImagePath());
//            ivImage.setImageBitmap(bitmap);
//        }

        TextView tvName = findViewById(R.id.tvCafeteriaName);
        tvName.setText(cafeteriaInfo.getName());
        TextView tvRating = findViewById(R.id.tvCafeteriaRating);
        tvRating.setText(cafeteriaInfo.getRating());
        TextView tvDescription = findViewById(R.id.tvCafeteriaDescription);
        tvDescription.setText(cafeteriaInfo.getDescription());
        TextView tvPhone = findViewById(R.id.tvCafeteriaPhone);
        tvPhone.setText(cafeteriaInfo.getPhone());
        TextView tvLocation = findViewById(R.id.tvCafeteriaLocation);
        tvLocation.setText(cafeteriaInfo.getLocation());
        TextView tvTags = findViewById(R.id.tvCafeteriaTags);
        tvTags.setText(cafeteriaInfo.getTags());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cafeteria_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_edit) {
            // editor
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            // delete
            return true;
        } else if (item.getItemId() == R.id.action_share_email) {
            // share email
            return true;
        } else if (item.getItemId() == R.id.action_share_facebook) {
            // share Facebook
            return true;
        } else if (item.getItemId() == R.id.action_share_twitter) {
            // share Twitter
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}