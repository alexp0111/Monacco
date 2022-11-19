package com.example.monacco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class KeyFragment extends Fragment {

    private String header = "sww";


    public KeyFragment(String header) {
        this.header = header;
    }

        @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_sub_add, container, false);

        String t = getHeader();

        return view;
    }

    public String getHeader() {
        return header;
    }
}
