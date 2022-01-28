package ru.gb.course1.myapplication.data;

import java.util.ArrayList;
import java.util.List;

import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class SimpleColorsRepoImpl implements ColorsRepo {

    private final List<ColorEntity> data = new ArrayList<>();

    @Override
    public void addColor(ColorEntity colorEntity) {
        data.add(0, colorEntity);
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

}
