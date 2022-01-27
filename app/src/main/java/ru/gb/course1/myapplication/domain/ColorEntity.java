package ru.gb.course1.myapplication.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ColorEntity implements Parcelable, Serializable {

    public static final Creator<ColorEntity> CREATOR = new Creator<ColorEntity>() {
        @Override
        public ColorEntity createFromParcel(Parcel in) {
            return new ColorEntity(in);
        }

        @Override
        public ColorEntity[] newArray(int size) {
            return new ColorEntity[size];
        }
    };
    private String id;
    private int color;

    public ColorEntity() {
    }

    public ColorEntity(String id, int color) {
        this.id = id;
        this.color = color;
    }

    protected ColorEntity(Parcel in) {
        id = in.readString();
        color = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeInt(color);
    }

    @Override
    public int describeContents() {
        return 0;
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
