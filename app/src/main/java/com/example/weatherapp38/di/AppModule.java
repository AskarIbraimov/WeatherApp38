package com.example.weatherapp38.di;

import android.content.Context;

import com.example.weatherapp38.data.local.AppDatabase;
import com.example.weatherapp38.data.local.RoomClient;
import com.example.weatherapp38.data.local.WeatherDao;
import com.example.weatherapp38.data.remote.RetrofitClient;
import com.example.weatherapp38.data.remote.WeatherApi;
import com.example.weatherapp38.data.repository.Repository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public abstract class AppModule {

    @Provides
    public static WeatherApi provideApi() {
        return new RetrofitClient().provideApi();
    }

    @Provides
    public static Repository provideRepository(WeatherApi api, WeatherDao dao) {
        return new Repository(api, dao);
    }

    @Provides
    public static AppDatabase provideDatabase(@ApplicationContext Context context) {
        return new RoomClient().provideDatabase(context);
    }

    @Provides
    public static WeatherDao provideWeatherDao(AppDatabase database) {
        return database.provideDao();
    }
}
