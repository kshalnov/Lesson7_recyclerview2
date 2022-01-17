package ru.gb.course1.myapplication.domain;

import java.util.List;

// CRUD interface - Create Read Update Delete
public interface ColorsRepo {
    List<ColorEntity> getColors();

    void deleteItem(String id);
}
