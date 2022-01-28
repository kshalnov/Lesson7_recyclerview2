package ru.gb.course1.myapplication.domain;

import java.util.List;

// Create Read Update Delete
public interface ColorsRepo {
    // Create
    void addColor(ColorEntity colorEntity);

    // Read
    List<ColorEntity> getColors();

    // Update
    // todo

    // Delete
    void deleteItem(String id);
}
