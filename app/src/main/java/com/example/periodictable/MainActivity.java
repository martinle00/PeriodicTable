package com.example.periodictable;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;


    private void swapFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_slot, fragment);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Fragment fragment = new PeriodicTableFragment();

        swapFragment(fragment);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = findViewById(R.id.drawer_layout);
        nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(nvDrawer);
    }

    //The code for the following two methods (setupDrawerContent and onOptionsItemSelected)
    //were made by following along with the following tutorial: https://guides.codepath.com/android/fragment-navigation-drawer
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectMenuItem(menuItem);
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // Menu navigation of fragments
    public boolean selectMenuItem(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_periodic_table) {
            Fragment fragment = new PeriodicTableFragment();
            swapFragment(fragment);
            setTitle(menuItem.getTitle());
            mDrawer.closeDrawers();
            return true;
        } else if (menuItem.getItemId() == R.id.nav_quiz) {
            Fragment fragment = new QuizFragment();
            swapFragment(fragment);
            setTitle(menuItem.getTitle());
            mDrawer.closeDrawers();
            return true;
        }
        return false;
    }
}




