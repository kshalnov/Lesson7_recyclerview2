package ru.gb.course1.myapplication.ui.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;

public class ColorDetailsActivity extends AppCompatActivity {
    public static final String COLOR_EXTRA_KEY = "COLOR_EXTRA_KEY";

    private LinearLayout rootLayout;
    private TextView colorNameTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_details);

        rootLayout = findViewById(R.id.activity_color_details__root_linear_layout);
        colorNameTextView = findViewById(R.id.activity_color_details__color_name_text_view);

        final Intent intent = getIntent();
        if (intent != null && intent.hasExtra(COLOR_EXTRA_KEY)) {
            ColorEntity colorEntity = intent.getParcelableExtra(COLOR_EXTRA_KEY);

            rootLayout.setBackgroundColor(colorEntity.getColor());
            colorNameTextView.setText(colorEntity.getHexString());
        }
    }
}
