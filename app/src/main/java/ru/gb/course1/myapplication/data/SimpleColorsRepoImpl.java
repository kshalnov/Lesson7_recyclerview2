package ru.gb.course1.myapplication.data;

import androidx.core.graphics.ColorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class SimpleColorsRepoImpl implements ColorsRepo {
    private static final int COLORS_LIST_SIZE = 100;

    private final List<ColorEntity> data = new ArrayList<>();

    public SimpleColorsRepoImpl() {
        regenerateColors(COLORS_LIST_SIZE);
    }

    @Override
    public List<ColorEntity> getColors() {
        return new ArrayList<>(data);
    }

    @Override
    public void deleteItem(String id) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId().equals(id)) {
                data.remove(i);
                return;
            }
        }
    }

    public void regenerateColors(int size) {
        for (int i = 0; i < size; i++) {
            ColorEntity colorEntity = new ColorEntity(
                    UUID.randomUUID().toString(),
                    ColorUtils.setAlphaComponent(new Random().nextInt(), 255)
            );
            data.add(colorEntity);
        }
    }
}
