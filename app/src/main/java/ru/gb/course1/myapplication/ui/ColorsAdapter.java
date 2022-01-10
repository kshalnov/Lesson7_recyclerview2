package ru.gb.course1.myapplication.ui;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gb.course1.myapplication.domain.ColorEntity;

public class ColorsAdapter extends RecyclerView.Adapter<ColorViewHolder> {
    private final List<ColorEntity> data = new ArrayList<>();
    private int holdersCounters = 0;

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ColorViewHolder(parent, holdersCounters++);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        ColorEntity colorEntity = getItem(position);
        holder.bind(colorEntity);
    }

    private ColorEntity getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<ColorEntity> colors) {
        data.clear();
        data.addAll(colors);
        notifyDataSetChanged();
    }
}
