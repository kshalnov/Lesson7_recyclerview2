package ru.gb.course1.myapplication;

import android.app.Application;

import ru.gb.course1.myapplication.data.SharedPreferencesColorsRepoImpl;
import ru.gb.course1.myapplication.data.SnappyDbColorsRepoImpl;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class App extends Application {
    private static App sInstance = null;

    private SnappyDbColorsRepoImpl snappyRepo = new SnappyDbColorsRepoImpl();
    public ColorsRepo colorsRepo;

    public static App get() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        snappyRepo.init(this);
        colorsRepo = snappyRepo;

        sInstance = this;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        snappyRepo.destroy();
    }
}
