package com.example.aplikasimoviecatalogue4;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.aplikasimoviecatalogue4.Fragment.FavoriteFragment;
import com.example.aplikasimoviecatalogue4.Fragment.MoviesFragment;
import com.example.aplikasimoviecatalogue4.Fragment.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    final Fragment moviesFragment = new MoviesFragment();
    final Fragment tvShowFragment = new TvShowFragment();
    final Fragment favoriteFragment = new FavoriteFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = moviesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Catalogue Movie");

        // fragment initialize
        fm.beginTransaction().add(R.id.container, favoriteFragment, "3").hide(favoriteFragment).commit();
        fm.beginTransaction().add(R.id.container, tvShowFragment, "2").hide(tvShowFragment).commit();
        fm.beginTransaction().add(R.id.container,moviesFragment, "1").commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bn_main);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.movies:
                fm.beginTransaction().hide(active).show(moviesFragment).commit();
                active = moviesFragment;
                return true;

            case R.id.tv_show:
                fm.beginTransaction().hide(active).show(tvShowFragment).commit();
                active = tvShowFragment;
                return true;

            case R.id.favorite:
                fm.beginTransaction().hide(active).show(favoriteFragment).commit();
                active = favoriteFragment;
                return true;
        }
        return false;
    }
}
