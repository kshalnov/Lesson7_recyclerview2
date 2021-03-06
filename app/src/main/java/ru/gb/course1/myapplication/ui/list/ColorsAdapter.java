package ru.gb.course1.myapplication.ui.list;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ru.gb.course1.myapplication.domain.ColorEntity;

public class ColorsAdapter extends RecyclerView.Adapter<ColorViewHolder> {
    private final List<ColorEntity> data = new ArrayList<>();
    private int holdersCounters = 0;
    private ColorViewHolder.OnItemClickListener onItemClickListener = null;

    ColorsAdapter() {
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ColorViewHolder(parent, holdersCounters++, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        ColorEntity colorEntity = getItem(position);
        holder.bind(colorEntity);
    }

    @Override
    public long getItemId(int position) {
        return UUID.fromString(getItem(position).getId()).getLeastSignificantBits();
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

    public void setOnItemClickListener(ColorViewHolder.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
