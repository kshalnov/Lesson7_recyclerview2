package ru.gb.course1.myapplication.domain;

import java.util.List;

public interface ColorsRepo {
    List<ColorEntity> getColors();

    void deleteItem(String id);
}
