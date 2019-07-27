package com.example.weather.TypeConverters;

import androidx.room.TypeConverter;
import com.example.weather.Model.Daily;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DailyTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Daily.DailyData> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Daily.DailyData>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Daily.DailyData> someObjects) {
        return gson.toJson(someObjects);
    }

}
