package com.example.weather.typeConverters;

import androidx.room.TypeConverter;
import com.example.weather.model.DailyData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class DailyTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<DailyData> stringToDailyDataList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<DailyData>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String DailyDataListToString(List<DailyData> someObjects) {
        return gson.toJson(someObjects);
    }

}
