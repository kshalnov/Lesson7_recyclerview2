package ru.gb.course1.myapplication.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gb.course1.myapplication.App;
import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;

public class ColorDetailsFragment extends Fragment {

    private static final String COLOR_ARG_KEY = "COLOR_ARG_KEY";

    private Controller controller;
    private LinearLayout rootLayout;
    private TextView colorNameTextView;
    private Button deleteButton;
    private ColorEntity colorEntity;

    public static ColorDetailsFragment newInstance(ColorEntity colorEntity) {
        ColorDetailsFragment fragment = new ColorDetailsFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(COLOR_ARG_KEY, colorEntity);

        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement ColorDetailsFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_color_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rootLayout = view.findViewById(R.id.activity_color_details__root_linear_layout);
        colorNameTextView = view.findViewById(R.id.fragment_color_details__color_name_text_view);
        deleteButton = view.findViewById(R.id.fragment_color__details_delete_button);

        colorEntity = getArguments().getParcelable(COLOR_ARG_KEY);


        deleteButton.setOnClickListener(v -> {
            App.get().colorsRepo.deleteItem(colorEntity.getId());
            controller.onDeleteColor(colorEntity.getId());
        });
        rootLayout.setBackgroundColor(colorEntity.getColor());
        colorNameTextView.setText(colorEntity.getHexString());
    }

    public interface Controller {
        void onDeleteColor(String colorId);
    }
}
