package com.example.weather.TypeConverters;

import androidx.room.TypeConverter;
import com.example.weather.Model.Hourly;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class HourlyTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Hourly.HourlyData> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Hourly.HourlyData>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Hourly.HourlyData> someObjects) {
        return gson.toJson(someObjects);
    }

}
