package hu.ait.camdensikes.weatheroverground;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import hu.ait.camdensikes.weatheroverground.adapter.PagerAdapter;
import hu.ait.camdensikes.weatheroverground.api.WeatherAPI;
import hu.ait.camdensikes.weatheroverground.weatherdata.Main;
import hu.ait.camdensikes.weatheroverground.weatherdata.Weather;
import hu.ait.camdensikes.weatheroverground.weatherdata.WeatherResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherDetailActivity extends AppCompatActivity {

    private String city;
    ViewPager pager;
    Response<WeatherResult> response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        city = getIntent().getStringExtra(MainActivity.KEY_CITY);

        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://api.openweathermap.org").
                addConverterFactory(GsonConverterFactory.create()).
                build();
        final WeatherAPI weatherApi = retrofit.create(WeatherAPI.class);

        Map<String, String> options = new HashMap<String, String>();
        options.put("q", city);
        options.put("units", "metric");
        options.put("appid", "9477a91e8a4f7b3dfc89f331f8cbe27c");


        Call<WeatherResult> call = weatherApi.getWeather(options);
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                if (response.isSuccessful()) {
                    displayFragments(response);
                }
                else {
                    Toast.makeText(WeatherDetailActivity.this, "City not found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Toast.makeText(WeatherDetailActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void displayFragments(Response<WeatherResult> response) {
        this.response = response;

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        pager.setVisibility(View.VISIBLE);
    }

    public String getTemp(){
        String temp = String.format(getString(R.string.temp), response.body().getMain().getTemp());
        return temp;
    }

    public String getCity() {
        return response.body().getName();
    }

    public String getDesc() {
        return response.body().getWeather().get(0).getDescription();
    }

    public String getIcon() {
        return ("http://openweathermap.org/img/w/" + response.body().getWeather().get(0).getIcon() + ".png");
    }

    public String getMaxTemp() {
        return String.format(getString(R.string.max_temp), response.body().getMain().getTempMax());
    }

    public String getMinTemp() {
        return String.format(getString(R.string.min_temp), response.body().getMain().getTempMin());
    }


    public String getHumidity() {
        return String.format(getString(R.string.humidity), response.body().getMain().getHumidity());
    }

    public String getVis() {
        return String.format(getString(R.string.visibility), response.body().getVisibility());
    }

    public String getPressure() {
        return String.format(getString(R.string.pressure), response.body().getMain().getPressure());
    }

    public String getWind() {
        String direction;
        double degree = response.body().getWind().getDeg();
        if(degree>=337.5 || degree<22.5){
            direction = getString(R.string.north);
        }
        else if(degree<67.5){
            direction = getString(R.string.northeast);
        }
        else if(degree<112.5){
            direction = getString(R.string.east);
        }
        else if(degree<157.5){
            direction = getString(R.string.southeast);
        }
        else if(degree<202.5){
            direction = getString(R.string.south);
        }
        else if(degree<247.5){
            direction = getString(R.string.southwest);
        }
        else if(degree<292.5){
            direction = getString(R.string.west);
        }
        else{
            direction = getString(R.string.northwest);
        }
        return String.format(getString(R.string.wind), response.body().getWind().getSpeed(), direction);
    }
}
