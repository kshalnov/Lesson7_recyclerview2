package ru.gb.course1.myapplication.ui.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;

public class ColorViewHolder extends RecyclerView.ViewHolder {
    private final TextView nameTextView = itemView.findViewById(R.id.item_color__name_text_view);
    private final CardView rootCardView = itemView.findViewById(R.id.item_color__root_card_view);
    private final TextView numberTextView = itemView.findViewById(R.id.item_color__number_text_view);
    private final AppCompatImageView refreshImageView = itemView.findViewById(R.id.item_color__refresh_image_view);
    private final AppCompatImageView deleteImageView = itemView.findViewById(R.id.item_color__delete_image_view);

    @NonNull
    private ColorEntity colorEntity;

    public ColorViewHolder(
            @NonNull ViewGroup parent,
            int holderNumber,
            OnItemClickListener onItemClickListener
    ) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false));
        numberTextView.setText(String.valueOf(holderNumber));
        deleteImageView.setOnClickListener(v -> {
            onItemClickListener.onDeleteItem(colorEntity);
        });
        refreshImageView.setOnClickListener(v -> {
            onItemClickListener.onRefreshItem(colorEntity);
        });
        rootCardView.setOnClickListener(v -> {
            onItemClickListener.onClickItem(colorEntity);
        });
        rootCardView.setOnLongClickListener(v -> {
            onItemClickListener.onDeleteItem(colorEntity);
            return true;
        });
    }

    public void bind(ColorEntity item) {
        nameTextView.setText("#" + item.getHexString());
        rootCardView.setCardBackgroundColor(item.getColor());

        colorEntity = item;
    }
    
    public interface OnItemClickListener {
        void onDeleteItem(ColorEntity item);

        void onRefreshItem(ColorEntity item);

        void onClickItem(ColorEntity item);
    }
}
