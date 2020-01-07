package com.team10.lifeorientedlist;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout drawer;
    ManageData manageData;
    FirebaseAuth auth;
    private static Context context;
    public static Fragment fragment = null;

    public static Context getContext() {
        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manageData = new ManageData(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       if(manageData.getPrimaryColor() != 0) {
           ActionBar actionBar = getSupportActionBar();
           actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(manageData.getPrimaryColor())));

           ChangeColors.darkenStatusBar(this, manageData.getPrimaryColor());
       }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new CreateTask();
                if (fragment != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                }
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        if(manageData.getAccentColor() != 0) {
            View header = navigationView.getHeaderView(0);
        /*GradientDrawable drawable = new GradientDrawable();
        drawable.setColors(new int[]{
                R.color.Orange,
                R.color.Red
        });
        drawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
        drawable.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);*/
            header.setBackgroundColor(getResources().getColor(manageData.getAccentColor()));
        }
        navigationView.setNavigationItemSelectedListener(this);


        auth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        if(auth.getCurrentUser() != null){
            displaySelectedScreen(R.id.home);
        }
        else {
            startActivity(new Intent(this, Login.class));
        }
    }

    @Override
    public void onBackPressed() {
        drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displaySelectedScreen(int id) {
        if (id == R.id.create_task) {
            fragment = new CreateTask();
        } else if (id == R.id.create_reward) {
            fragment = new CreateReward();
        } else if (id == R.id.create_notification) {
            fragment = new NotificationFragment();
        } else if (id == R.id.action_settings) {
            fragment = new Settings();
        }else if (id == R.id.goals) {
            fragment = new GoalView();
        } else if (id == R.id.ToDo) {
            fragment = new ToDoView();
        } else if (id == R.id.rewards) {
            fragment = new RewardView();
        } else if (id == R.id.home) {
            fragment = new MainContent();
        } else if (id == R.id.pointsOverview) {
            fragment = new PointsOverviewFragment();
        } else if (id == R.id.signOut) {
            auth.getInstance().signOut();
            manageData.setLoggedIn(false);
            startActivity(new Intent(this, Login.class));

        }
        if(fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }
        drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;
    }
}