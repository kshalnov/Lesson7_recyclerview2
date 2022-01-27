package ru.gb.course1.myapplication.data;

import android.content.Context;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class SnappyDbColorsRepoImpl implements ColorsRepo {
    private static final String SNAPPY_ARRAY_KEY = "colors_array";
    private DB db;

    public void init(Context context) {
        try {
            db = DBFactory.open(context);
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            db.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
        db = null;
    }

    @Override
    public void addColor(ColorEntity colorEntity) {
        try {
            List<ColorEntity> data = getColors();
            data.add(0, colorEntity);
            db.put(SNAPPY_ARRAY_KEY, data.toArray());
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ColorEntity> getColors() {
        final ArrayList<ColorEntity> colorsList = new ArrayList<>();
        try {
            ColorEntity[] array = db.getArray(SNAPPY_ARRAY_KEY, ColorEntity.class);
            colorsList.addAll(Arrays.asList(array));
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        return colorsList;
    }

    @Override
    public void deleteItem(String id) {
        try {
            List<ColorEntity> data = getColors();

            for (int i = 0; i < data.size(); i++) {
                if (data.get(i).getId().equals(id)) {
                    data.remove(i);
                    break;
                }
            }

            db.put(SNAPPY_ARRAY_KEY, data.toArray());
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }
}
