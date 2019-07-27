package com.example.weather.TypeConverters;

import androidx.room.TypeConverter;
import com.example.weather.Model.Currently;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class CurrentlyTypeConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Currently> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<Currently>>() {}.getType();

        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<Currently> someObjects) {
        return gson.toJson(someObjects);
    }

}
