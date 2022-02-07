package com.example.weatherapp38.data.local;

import android.content.Context;

import androidx.room.Room;

public class RoomClient {

    public AppDatabase provideDatabase(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "database")
                .allowMainThreadQueries().build();
    }
}
