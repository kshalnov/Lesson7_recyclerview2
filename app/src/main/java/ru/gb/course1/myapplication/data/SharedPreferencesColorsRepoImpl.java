package ru.gb.course1.myapplication.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class SharedPreferencesColorsRepoImpl implements ColorsRepo {
    private static final String SHARED_PREFS_NAME = "SharedPreferencesColorsRepoImpl";
    private static final String SHARED_PREFS_COLORS_KEY = "SHARED_PREFS_COLORS_KEY";

    private final SharedPreferences sharedPreferences;
    private final Gson gson = new Gson();

    public SharedPreferencesColorsRepoImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void addColor(ColorEntity colorEntity) {
        final List<ColorEntity> data = getColors();
        data.add(0, colorEntity);

        final String jsonString = gson.toJson(data);

        sharedPreferences
                .edit()
                .putString(SHARED_PREFS_COLORS_KEY, jsonString)
                .apply();
    }

    @Override
    public List<ColorEntity> getColors() {
        final String colorsJson = sharedPreferences.getString(SHARED_PREFS_COLORS_KEY, "");
        if (!TextUtils.isEmpty(colorsJson)) {
            Type type = new TypeToken<ArrayList<ColorEntity>>() {
            }.getType();
            return gson.fromJson(colorsJson, type);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void deleteItem(String id) {
        final List<ColorEntity> data = getColors();

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(id)) {
                data.remove(i);
                break;
            }
        }

        final String jsonString = gson.toJson(data);
        sharedPreferences
                .edit()
                .putString(SHARED_PREFS_COLORS_KEY, jsonString)
                .apply();
    }
}
