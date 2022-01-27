package ru.gb.course1.myapplication.util;

import androidx.core.graphics.ColorUtils;

import java.util.Random;
import java.util.UUID;

import ru.gb.course1.myapplication.domain.ColorEntity;

public class Utils {
    public static ColorEntity randomColor() {
        return new ColorEntity(
                UUID.randomUUID().toString(),
                ColorUtils.setAlphaComponent(new Random().nextInt(), 255)
        );
    }
}
