package ru.gb.course1.myapplication.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.myapplication.App;
import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.domain.ColorsRepo;

public class MainActivity extends AppCompatActivity {
    private ColorsAdapter adapter;
    private RecyclerView recyclerView;
    private LinearLayout rootLinearLayout;
    private Button scrollButton;

    private ColorsRepo colorsRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorsRepo = App.get().colorsRepo;

        scrollButton = findViewById(R.id.activity_main__scroll_button);
        scrollButton.setOnClickListener(v -> {
            recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
        });
        rootLinearLayout = findViewById(R.id.activity_main__root_linear_layout);
        initRecycler();
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.activity_main__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ColorsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(colorsRepo.getColors());
        adapter.setOnItemClickListener(new ColorViewHolder.OnItemClickListener() {
            @Override
            public void onDeleteItem(ColorEntity item) {
                Toast.makeText(MainActivity.this, "Delete " + item.getHexString(), Toast.LENGTH_SHORT).show();
                colorsRepo.deleteItem(item.getId());
                adapter.deleteItem(item.getId());
            }

            @Override
            public void onRefreshItem(ColorEntity item) {
                Toast.makeText(MainActivity.this, "Refresh " + item.getHexString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickItem(ColorEntity item) {
                rootLinearLayout.setBackgroundColor(item.getColor());
            }
        });
    }

}