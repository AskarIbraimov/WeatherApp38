package com.example.weatherapp38.data.local.convertor;

import androidx.room.TypeConverter;

import com.example.weatherapp38.data.model.Clouds;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class CloudConvertor {

    @TypeConverter
    public String fromMainString(Clouds clouds) {
        if (clouds == null) {
            return null;
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {
        }.getType();
        return gson.toJson(clouds, type);
    }

    @TypeConverter
    public Clouds fromMainString(String cloudString) {
        if (cloudString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Clouds>() {
        }.getType();
        return gson.fromJson(cloudString, type);
    }


}
