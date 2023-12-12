package com.studen.bestfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CafeteriaDetailActivity extends AppCompatActivity {

    private CafeteriaInfo cafeteriaInfo;
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
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        int cafeteriaInfoId = getIntent().getIntExtra("CAFETERIA_INFO_ID", -1);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            cafeteriaInfo = db.cafeteriaInfoDao().findById(cafeteriaInfoId);
            handler.post(() -> {
                ImageView ivImage = findViewById(R.id.cafeteriaImage);
//        if (cafeteriaInfo.getImagePath() == null || cafeteriaInfo.getImagePath().isEmpty()) {
                ivImage.setImageResource(R.drawable.logo);
//        } else {
//            Bitmap bitmap = BitmapFactory.decodeFile(cafeteriaInfo.getImagePath());
//            ivImage.setImageBitmap(bitmap);
//        }
                TextView tvName = findViewById(R.id.tvCafeteriaName);
                tvName.setText("Name:\n"+cafeteriaInfo.getName());
                TextView tvRating = findViewById(R.id.tvCafeteriaRating);
                tvRating.setText("Rating:\n"+cafeteriaInfo.getRating());
                TextView tvDescription = findViewById(R.id.tvCafeteriaDescription);
                tvDescription.setText("Description:\n"+cafeteriaInfo.getDescription());
                TextView tvPhone = findViewById(R.id.tvCafeteriaPhone);
                tvPhone.setText("Phone:\n"+cafeteriaInfo.getPhone());
                TextView tvLocation = findViewById(R.id.tvCafeteriaLocation);
                tvLocation.setText("Location:\n"+cafeteriaInfo.getLocation());
                TextView tvTags = findViewById(R.id.tvCafeteriaTags);
                tvTags.setText("Tags:\n"+cafeteriaInfo.getTags());
            });
        });
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
            Intent intent = new Intent(CafeteriaDetailActivity.this, CafeteriaInfoEditActivity.class);
            intent.putExtra("CAFETERIA_INFO_ID", cafeteriaInfo.getId());
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            // delete
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(() -> {
                AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                db.cafeteriaInfoDao().delete(cafeteriaInfo);
                handler.post(() -> {
                    finish();
                });
            });

            return true;
        } else if (item.getItemId() == R.id.action_share_email) {
            // share email
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.setType("message/rfc822");
//            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"Please enter the recipient's information"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Gourmet Shop Sharing");
            String content = "Name: " + cafeteriaInfo.getName() + "\n" +
                    "Rating: " + cafeteriaInfo.getRating() + "\n" +
                    "Description: " + cafeteriaInfo.getDescription() + "\n" +
                    "Phone: " + cafeteriaInfo.getPhone() + "\n" +
                    "Location: " + cafeteriaInfo.getLocation() + "\n" +
                    "Tags: " + cafeteriaInfo.getTags() + "\n";
            emailIntent.putExtra(Intent.EXTRA_TEXT, content);
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(Intent.createChooser(emailIntent, "Please select mail client"));
            } else {
                Toast.makeText(this, "No mail client found", Toast.LENGTH_SHORT).show();
            }

            return true;
        } else if (item.getItemId() == R.id.action_share_facebook) {
//    
            String content = "Name: " + cafeteriaInfo.getName() + "\n" +
                    "Rating: " + cafeteriaInfo.getRating() + "\n" +
                    "Description: " + cafeteriaInfo.getDescription() + "\n" +
                    "Phone: " + cafeteriaInfo.getPhone() + "\n" +
                    "Location: " + cafeteriaInfo.getLocation() + "\n" +
                    "Tags: " + cafeteriaInfo.getTags() + "\n";

            Intent facebookIntent = new Intent(Intent.ACTION_SEND);
            facebookIntent.setType("text/plain");
            facebookIntent.putExtra(Intent.EXTRA_TEXT, content);

            boolean facebookAppFound = false;
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> list = packageManager.queryIntentActivities(facebookIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : list) {
                if (resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")) {
                    facebookIntent.setPackage(resolveInfo.activityInfo.packageName);
                    facebookAppFound = true;
                    break;
                }
            }

            if (facebookAppFound) {
                startActivity(facebookIntent);
            } else {
                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + Uri.encode("https://www.facebook.com");
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
                startActivity(webIntent);
            }

            return true;
        } else if (item.getItemId() == R.id.action_share_twitter) {
            String content = "Name: " + cafeteriaInfo.getName() + "\n" +
                    "Rating: " + cafeteriaInfo.getRating() + "\n" +
                    "Description: " + cafeteriaInfo.getDescription() + "\n" +
                    "Phone: " + cafeteriaInfo.getPhone() + "\n" +
                    "Location: " + cafeteriaInfo.getLocation() + "\n" +
                    "Tags: " + cafeteriaInfo.getTags() + "\n";
            Intent tweetIntent = new Intent(Intent.ACTION_SEND);
            tweetIntent.putExtra(Intent.EXTRA_TEXT, content);
            tweetIntent.setType("text/plain");

            PackageManager packManager = getPackageManager();
            List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

            boolean resolved = false;
            for (ResolveInfo resolveInfo : resolvedInfoList) {
                if (resolveInfo.activityInfo.packageName.startsWith("com.twitter.android")) {
                    tweetIntent.setClassName(
                            resolveInfo.activityInfo.packageName,
                            resolveInfo.activityInfo.name);
                    resolved = true;
                    break;
                }
            }
            if (resolved) {
                startActivity(tweetIntent);
            } else {
                String tweetUrl = "https://twitter.com/intent/tweet?text=" + Uri.encode(content);
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl));
                startActivity(webIntent);

            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}