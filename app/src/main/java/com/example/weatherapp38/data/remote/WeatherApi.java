package com.example.weatherapp38.data.remote;

import com.example.weatherapp38.data.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("weather?")
    Call<Weather> getWeather(
            @Query("q") String city,
            @Query("appId") String appId,
            @Query("units") String metric
    );

}
