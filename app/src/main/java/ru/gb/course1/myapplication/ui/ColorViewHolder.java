package ru.gb.course1.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;

public class ColorViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView = itemView.findViewById(R.id.item_color__name_text_view);
    private final CardView rootCardView = itemView.findViewById(R.id.item_color__root_card_view);

    public ColorViewHolder(@NonNull ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false));
    }

    public void bind(ColorEntity item) {
        nameTextView.setText("#" + item.getHexString());
        rootCardView.setCardBackgroundColor(item.getColor());
    }

    private Context getContext() {
        return itemView.getContext();
    }
}
