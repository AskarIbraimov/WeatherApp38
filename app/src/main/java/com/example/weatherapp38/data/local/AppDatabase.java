package com.example.weatherapp38.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherapp38.data.local.convertor.MainConvertor;
import com.example.weatherapp38.data.model.Weather;

@Database(entities = {Weather.class}, version = 1)
@TypeConverters({MainConvertor.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract WeatherDao provideDao();
}
