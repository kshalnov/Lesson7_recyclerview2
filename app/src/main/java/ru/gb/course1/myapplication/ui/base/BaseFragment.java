package ru.gb.course1.myapplication.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

abstract public class BaseFragment extends Fragment {
    private static final String TAG = "@@@_BaseFragment@";

    private String getLogTag() {
        return TAG + getClass().getSimpleName();
    }


    @Override
    public void onAttach(@NonNull Context context) {
        Log.d(getLogTag(), "onAttach() called with: context = [" + context + "]");
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onCreateView() called with: inflater = [" + inflater + "], container = [" + container + "], savedInstanceState = [" + savedInstanceState + "]");
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(getLogTag(), "onViewCreated() called with: view = [" + view + "], savedInstanceState = [" + savedInstanceState + "]");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Log.d(getLogTag(), "onDestroyView() called");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(getLogTag(), "onDestroy() called");
        super.onDestroy();
    }

    @Override
    public void onStart() {
        Log.d(getLogTag(), "onStart() called");
        super.onStart();
    }

    @Override
    public void onStop() {
        Log.d(getLogTag(), "onStop() called");
        super.onStop();
    }

    @Override
    public void onResume() {
        Log.d(getLogTag(), "onResume() called");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(getLogTag(), "onPause() called");
        super.onPause();
    }

    @Override
    public void onDetach() {
        Log.d(getLogTag(), "onDetach() called");
        super.onDetach();
    }

}
