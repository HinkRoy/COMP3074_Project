package com.studen.bestfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private MenuItem rightMenuItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_list){
                    setTitle("cafeteria");
                    switchFragment(new CafeteriaListFragment());
                    rightMenuItem.setVisible(true);
                    return true;
                } else if (item.getItemId() == R.id.navigation_about) {
                    setTitle("about");
                    switchFragment(new AboutFragment());
                    rightMenuItem.setVisible(false);
                    return true;
                }
                return false;
            }
        });

        setTitle("cafeteria");
        switchFragment(new CafeteriaListFragment());
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        rightMenuItem = menu.findItem(R.id.action_add_restaurant);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_restaurant) {
            startActivity(new Intent(MainActivity.this, CafeteriaInfoEditActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}