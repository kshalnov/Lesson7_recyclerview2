package ru.gb.course1.myapplication.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.myapplication.App;
import ru.gb.course1.myapplication.R;
import ru.gb.course1.myapplication.domain.ColorEntity;
import ru.gb.course1.myapplication.domain.ColorsRepo;
import ru.gb.course1.myapplication.util.Utils;

public class ColorsListFragment extends Fragment {
    private ColorsAdapter adapter;
    private RecyclerView recyclerView;
    private Button generateColorButton;

    private ColorsRepo colorsRepo;

    private Controller controller;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement ColorsListFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_colors_list, container, false); // todo переименовать в fragment_colors_list
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        colorsRepo = App.get().colorsRepo;

        generateColorButton = view.findViewById(R.id.fragment_colors_list__generate_color_button);
        generateColorButton.setOnClickListener(v -> {
            final ColorEntity color = Utils.randomColor();
            colorsRepo.addColor(color);
            adapter.setData(colorsRepo.getColors());
        });
        initRecycler(view);
    }

    private void initRecycler(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_colors_list__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ColorsAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(colorsRepo.getColors());
        adapter.setOnItemClickListener(new ColorViewHolder.OnItemClickListener() {
            @Override
            public void onDeleteItem(ColorEntity item) {
                Toast.makeText(getContext(), "Delete " + item.getHexString(), Toast.LENGTH_SHORT).show();
                colorsRepo.deleteItem(item.getId());
                adapter.deleteItem(item.getId());
            }

            @Override
            public void onRefreshItem(ColorEntity item) {
                // todo
                Toast.makeText(getContext(), "Refresh " + item.getHexString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickItem(ColorEntity item) {
                controller.showColorDetails(item);
            }
        });
    }

    public void onDeleteColor(String colorId) {
        adapter.setData(colorsRepo.getColors());
    }

    public interface Controller {
        void showColorDetails(ColorEntity colorEntity);
    }

}
