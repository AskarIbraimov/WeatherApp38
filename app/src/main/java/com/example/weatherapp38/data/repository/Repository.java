package com.example.weatherapp38.data.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp38.common.Resource;
import com.example.weatherapp38.data.local.WeatherDao;
import com.example.weatherapp38.data.model.Weather;
import com.example.weatherapp38.data.remote.WeatherApi;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private WeatherApi api;
    private String city;
    private WeatherDao dao;

    public void setCity(String city) {
        this.city = city;
    }

    @Inject
    public Repository(WeatherApi api, WeatherDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public MutableLiveData<Resource<Weather>> getTemp() {

        MutableLiveData<Resource<Weather>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getWeather(city, "c1ea15e8f360d97759f0b5a78fc32620", "metric").enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    liveData.setValue(Resource.success(response.body()));
                    dao.insert(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
            }
        });
        return liveData;
    }

}
