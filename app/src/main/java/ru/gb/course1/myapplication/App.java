package ru.gb.course1.myapplication;

import android.app.Application;

import ru.gb.course1.myapplication.data.SimpleColorsRepoImpl;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class App extends Application {
    private static App sInstance = null;

    public final ColorsRepo colorsRepo = new SimpleColorsRepoImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static App get() {
        return sInstance;
    }
}
