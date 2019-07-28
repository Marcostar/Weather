package com.example.weather.typeConverters;

import androidx.room.TypeConverter;
import com.example.weather.model.HourlyData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class HourlyTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<HourlyData> stringToHourlyDataList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<HourlyData>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String HourlyDataListToString(List<HourlyData> someObjects) {
        return gson.toJson(someObjects);
    }

}
