package com.example.weather.typeConverters;

import androidx.room.TypeConverter;
import com.example.weather.model.Currently;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;


public class CurrentlyTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static Currently stringToCurrently(String data) {
        if (data == null) {
            return null;
        }

        Type CurrentlyType = new TypeToken<Currently>() {}.getType();

        return gson.fromJson(data, CurrentlyType);
    }

    @TypeConverter
    public static String currentlyToString(Currently currently) {
        return gson.toJson(currently);
    }

}
