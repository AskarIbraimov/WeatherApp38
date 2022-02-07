package com.example.weatherapp38.data.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private OkHttpClient provideClient() {
        return new OkHttpClient.Builder().
                writeTimeout(10, TimeUnit.SECONDS).
                connectTimeout(10, TimeUnit.SECONDS).
                readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(provideInterceptor())
                .build();
    }

    private Interceptor provideInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    private Retrofit retrofit = new Retrofit.Builder().client(provideClient())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build();

    public WeatherApi provideApi() {
        return retrofit.create(WeatherApi.class);
    }

}
