package hu.ait.camdensikes.weatheroverground.api;

import java.util.Map;

import hu.ait.camdensikes.weatheroverground.weatherdata.Weather;
import hu.ait.camdensikes.weatheroverground.weatherdata.WeatherResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Camden Sikes on 11/29/2016.
 */

public interface WeatherAPI {
    @GET("data/2.5/weather")
    Call<WeatherResult> getWeather(@QueryMap Map<String, String> options);
}
