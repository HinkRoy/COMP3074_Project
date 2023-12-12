package com.studen.bestfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private int cafeteriaInfoId;
    private CafeteriaInfo cafeteriaInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
        setTitle("Cafeteria Location");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        cafeteriaInfoId = getIntent().getIntExtra("CAFETERIA_INFO_ID", -1);
        if (cafeteriaInfoId != -1) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                cafeteriaInfo = db.cafeteriaInfoDao().findById(cafeteriaInfoId);
                handler.post(() -> {
                    LatLng toronto = new LatLng(cafeteriaInfo.getLatitude(), cafeteriaInfo.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(toronto).title("Name:"+cafeteriaInfo.getName()).snippet("Description:"+cafeteriaInfo.getDescription()));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(toronto, 15));

                });
            });
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapActivity.this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                    222);
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
}
