package ru.gb.course1.myapplication.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ru.gb.course1.myapplication.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment colorsListFragment = new ColorsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__fragment_container, colorsListFragment)
                .commit();
    }

}