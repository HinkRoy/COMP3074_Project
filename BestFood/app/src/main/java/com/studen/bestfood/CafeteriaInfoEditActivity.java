package com.studen.bestfood;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CafeteriaInfoEditActivity extends AppCompatActivity {

    private ImageView cafeteriaImage;
    private Uri selectedImageUri = null;
    EditText nameET, ratingET, descriptionET, phoneET, tagsET, locationNameET, longitudeET, latitudeT;
    private int cafeteriaInfoId;

    private LocationManager locationManager;
    private boolean shouldUpdateLocation = true;
    private CafeteriaInfo cafeteriaInfo;
    private static final int GALLERY_REQUEST_CODE = 1;
    private static final int LOCATION_REQUEST_CODE = 2;
    private ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cafeteria_info_edit);

        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        if (uri != null) {
                            cafeteriaImage.setImageURI(uri);
                            selectedImageUri = uri;
                        }
                    }
                });
        cafeteriaImage = findViewById(R.id.cafeteriaImage);
        cafeteriaImage.setOnClickListener(v -> {
            mGetContent.launch("image/*");
        });
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
            String imagePath = selectedImageUri != null ? copyImageToAppStorage(selectedImageUri) : cafeteriaInfo.getImagePath().toString();
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

            if (cafeteriaInfoId != -1) {
                cafeteriaInfo.setName(name);
                cafeteriaInfo.setImagePath(imagePath);
                cafeteriaInfo.setRating(rating);
                cafeteriaInfo.setDescription(description);
                cafeteriaInfo.setPhone(phone);
                cafeteriaInfo.setTags(tags);
                cafeteriaInfo.setLocation(locationName);
                cafeteriaInfo.setLongitude(Double.parseDouble(longitude));
                cafeteriaInfo.setLatitude(Double.parseDouble(latitude));
            } else {
                cafeteriaInfo = new CafeteriaInfo(name, phone, tags,
                        rating, description, imagePath, locationName,
                        Double.parseDouble(latitude), Double.parseDouble(longitude));
            }
            saveCafeteriaInfo(cafeteriaInfo);
            finish();
        });

        cafeteriaInfoId = getIntent().getIntExtra("CAFETERIA_INFO_ID", -1);
        if (cafeteriaInfoId != -1) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                cafeteriaInfo = db.cafeteriaInfoDao().findById(cafeteriaInfoId);
                handler.post(() -> {
                    File imgFile = new File(cafeteriaInfo.getImagePath());
                    if (imgFile.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        cafeteriaImage.setImageBitmap(bitmap);
                    } else {
                        cafeteriaImage.setImageResource(R.drawable.logo);
                    }
                    nameET.setText(cafeteriaInfo.getName());
                    ratingET.setText(cafeteriaInfo.getRating());
                    descriptionET.setText(cafeteriaInfo.getDescription());
                    phoneET.setText(cafeteriaInfo.getPhone());
                    tagsET.setText(cafeteriaInfo.getTags());
                    locationNameET.setText(cafeteriaInfo.getLocation());
                    longitudeET.setText(String.valueOf(cafeteriaInfo.getLongitude()));
                    latitudeT.setText(String.valueOf(cafeteriaInfo.getLatitude()));
                });
            });
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
            }
        };

        Button btnLocation = findViewById(R.id.getLocationButton);
        btnLocation.setOnClickListener(v -> {
            if (ActivityCompat.checkSelfPermission(CafeteriaInfoEditActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(CafeteriaInfoEditActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(CafeteriaInfoEditActivity.this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION},
                        LOCATION_REQUEST_CODE);
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                longitudeET.setText(String.valueOf(longitude));
                latitudeT.setText(String.valueOf(latitude));
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                try {
                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    if (addresses != null && !addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String addressName = address.getAddressLine(0);
                        locationNameET.setText(addressName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

    }

    private void saveCafeteriaInfo(CafeteriaInfo cafeteriaInfo) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            if (cafeteriaInfoId != -1) {
                db.cafeteriaInfoDao().update(cafeteriaInfo);
            } else {
                db.cafeteriaInfoDao().insert(cafeteriaInfo);
            }
            handler.post(() -> {
                Toast.makeText(getApplicationContext(), "Data saved successfully", Toast.LENGTH_SHORT).show();
            });
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(getApplicationContext(), "Please authorise location information", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String copyImageToAppStorage(Uri imageUri) {
        try {
            File storageDir = new File(getFilesDir(), "images");
            if (!storageDir.exists()) storageDir.mkdirs();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String imageFileName = "JPEG_" + timeStamp + ".jpg";
            File imageFile = new File(storageDir, imageFileName);
            try (InputStream in = getContentResolver().openInputStream(imageUri);
                 OutputStream out = new FileOutputStream(imageFile)) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) > 0) {
                    out.write(buffer, 0, len);
                }
            }
            return imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}