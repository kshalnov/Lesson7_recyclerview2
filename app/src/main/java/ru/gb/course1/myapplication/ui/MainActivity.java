package ru.gb.course1.myapplication.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.ui.details.ColorDetailsFragment;
import ru.gb.course1.myapplication.ui.list.ColorsListFragment;

public class MainActivity
        extends AppCompatActivity
        implements ColorsListFragment.Controller, ColorDetailsFragment.Controller {

    private static final String TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            Fragment colorsListFragment = new ColorsListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__fragment_container, colorsListFragment, TAG_LIST_FRAGMENT)
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

    @Override
    public void onDeleteColor(ColorEntity colorEntity) {
        Toast.makeText(this, "Delete " + colorEntity.getHexString(), Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().popBackStack();
        ColorsListFragment colorsListFragment = (ColorsListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        if (colorsListFragment == null) throw new IllegalStateException("ColorsListFragment not on screen");
        colorsListFragment.onDeleteColor(colorEntity);
    }
}