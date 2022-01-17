package ru.gb.course1.myapplication.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
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
    private static final String TAG = "@@@_MainActivity";

    private FrameLayout secondFragmentContainer;

    private boolean isTwoPaneMode() {
        return secondFragmentContainer != null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondFragmentContainer = findViewById(R.id.activity_main__second_fragment_container);

        if (isTwoPaneMode()) {
            // 1 узнать какой фрагмент лежит в контейнере
            Fragment mainFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main__main_fragment_container);
            if (mainFragment instanceof ColorsListFragment) {
                // всё хорошо
            } else if (mainFragment != null) {
                // переложить фрагмент в другой контейнер
                moveFragment(mainFragment);
                showListInMainContainer();
            } else {
                showListInMainContainer();
            }
        } else {
            // тоже нужно что-то придумать
            // todo @@@
            showListInMainContainer();
        }
    }

    private void moveFragment(Fragment oldFragment) {
        final Bundle args = oldFragment.getArguments();
        final Bundle savedState = new Bundle();
        oldFragment.onSaveInstanceState(savedState);

        Fragment newFragment = null;
        try {
            newFragment = oldFragment.getClass().newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        newFragment.setArguments(args);

        newFragment.onAttach((Context) this);
        FrameLayout container = findViewById(R.id.activity_main__second_fragment_container);
        final View fragmentView = newFragment.onCreateView(LayoutInflater.from(this), container, savedState);
        container.addView(fragmentView);
        newFragment.onViewCreated(fragmentView, savedState);
    }

    private void showListInMainContainer() {
        Fragment colorsListFragment = new ColorsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__main_fragment_container, colorsListFragment, TAG_LIST_FRAGMENT)
                .commit();
    }

    @Override
    public void showColorDetails(ColorEntity colorEntity) {
        Toast.makeText(this, colorEntity.getHexString(), Toast.LENGTH_SHORT).show();

        Fragment colorDetailsFragment = ColorDetailsFragment.newInstance(colorEntity);
        Log.d(TAG, "Begin ColorDetailsFragment transaction");

        int containerId;
        if (isTwoPaneMode()) {
            containerId = R.id.activity_main__second_fragment_container;
        } else {
            containerId = R.id.activity_main__main_fragment_container;
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerId, colorDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDeleteColor(String colorId) {
        Toast.makeText(this, "Delete " + colorId, Toast.LENGTH_SHORT).show();
        getSupportFragmentManager().popBackStack();
        ColorsListFragment colorsListFragment = (ColorsListFragment) getSupportFragmentManager().findFragmentByTag(TAG_LIST_FRAGMENT);
        if (colorsListFragment == null)
            throw new IllegalStateException("ColorsListFragment not on screen");
        colorsListFragment.onDeleteColor(colorId);
    }
}