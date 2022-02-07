package com.example.weatherapp38.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.weatherapp38.data.model.Weather;

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Weather weather);

    @Query("SELECT * FROM weather")
    Weather getWeather();

}
