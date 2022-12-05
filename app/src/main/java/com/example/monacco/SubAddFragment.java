package com.example.monacco;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// TODO: Functionality
//  1. Ввод суммы
//  2. Выбор категории
//  3. Выбор даты
//  4. Комментарий


public class SubAddFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_add, container, false);

        return view;
    }
}