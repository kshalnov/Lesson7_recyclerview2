package ru.gb.course1.myapplication.domain;

public class ColorEntity {
    private String id;
    private int color;

    public ColorEntity(String id, int color) {
        this.id = id;
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getHexString() {
        return Integer.toHexString(color);
    }

}
