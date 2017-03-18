package dev.paytrack.paytrack;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {

        DrawerLayout drawerLayout = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = (FrameLayout) drawerLayout.findViewById(R.id.main_frame);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        super.setContentView(drawerLayout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
