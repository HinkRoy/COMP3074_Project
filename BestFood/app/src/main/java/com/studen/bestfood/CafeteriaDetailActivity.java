package com.studen.bestfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
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

        String name = getIntent().getStringExtra("CAFETERIA_NAME");
        String rating = getIntent().getStringExtra("CAFETERIA_RATING");
        String comment = getIntent().getStringExtra("CAFETERIA_COMMENT");
        int imageResId = getIntent().getIntExtra("CAFETERIA_IMAGE_RES_ID", 0);

        TextView tvName = findViewById(R.id.tvCafeteriaName);
        tvName.setText(name);
        TextView tvRating = findViewById(R.id.tvCafeteriaRating);
        tvRating.setText(rating);
        TextView tvComment = findViewById(R.id.tvCafeteriaDescription);
        tvComment.setText(comment);
        ImageView imageView = findViewById(R.id.cafeteriaImage);
        if (imageResId != 0) {
            imageView.setImageResource(imageResId);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cafeteria_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_more) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}