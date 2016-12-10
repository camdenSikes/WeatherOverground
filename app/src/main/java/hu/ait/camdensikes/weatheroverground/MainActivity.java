package hu.ait.camdensikes.weatheroverground;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import hu.ait.camdensikes.weatheroverground.adapter.CityAdapter;
import hu.ait.camdensikes.weatheroverground.data.ListItem;
import hu.ait.camdensikes.weatheroverground.fragment.AddCityDialogFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AddCityDialogFragment.AddCityDialogListener{

    private CityAdapter cityAdapter;
    public static final String KEY_CITY = "KEY_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(getResources().getString(R.string.app_name_full));

        List<ListItem> itemList = ListItem.listAll(ListItem.class);

        cityAdapter = new CityAdapter(itemList, this);
        RecyclerView recyclerViewItems = (RecyclerView) findViewById(
                R.id.recyclerViewCities);
        recyclerViewItems.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewItems.setAdapter(cityAdapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton btnAddCity = (FloatingActionButton) findViewById(R.id.btnAddCity);
        btnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAddCityFragment();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void startAddCityFragment() {
        DialogFragment dialog = new AddCityDialogFragment();
        dialog.show(getSupportFragmentManager(), "NoticeDialogFragment");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.addCity) {
            startAddCityFragment();
        } else if (id == R.id.about) {
            Snackbar.make(findViewById(R.id.drawer_layout), getString(R.string.about_text),Snackbar.LENGTH_SHORT);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onDialogPositiveClick(String city) {
        ListItem item = new ListItem(city);
        cityAdapter.addCity(item);
    }

    public void openWeatherDetails(String cityName) {
        Intent startIntent = new Intent(MainActivity.this, WeatherDetailActivity.class);
        startIntent.putExtra(KEY_CITY, cityName);
        startActivity(startIntent);
    }
}
