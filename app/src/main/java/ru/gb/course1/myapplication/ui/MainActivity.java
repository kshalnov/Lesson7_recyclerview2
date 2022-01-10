package ru.gb.course1.myapplication.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.myapplication.App;
import ru.gb.course1.myapplication.R;

public class MainActivity extends AppCompatActivity {
    private ColorsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.activity_main__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ColorsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(App.get().colorsRepo.getColors());
    }
}