package ru.gb.course1.myapplication.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.ui.details.ColorDetailsFragment;

public class MainActivity extends AppCompatActivity implements ColorsListFragment.Controller {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment colorsListFragment = new ColorsListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__fragment_container, colorsListFragment)
                    .commit();
        }
    }

    @Override
    public void showColorDetails(ColorEntity colorEntity) {
        Toast.makeText(this, colorEntity.getHexString(), Toast.LENGTH_SHORT).show();

        Fragment colorDetailsFragment = ColorDetailsFragment.newInstance(colorEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__fragment_container, colorDetailsFragment)
                .addToBackStack(null)
                .commit();
    }
}