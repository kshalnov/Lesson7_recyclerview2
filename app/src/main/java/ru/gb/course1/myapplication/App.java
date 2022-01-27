package ru.gb.course1.myapplication;

import android.app.Application;

import ru.gb.course1.myapplication.data.SharedPreferencesColorsRepoImpl;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class App extends Application {
    private static App sInstance = null;

    public ColorsRepo colorsRepo;

    public static App get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        colorsRepo = new SharedPreferencesColorsRepoImpl(this);
        sInstance = this;
    }
}
