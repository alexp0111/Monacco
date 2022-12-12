package com.example.monacco;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SettingsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        AppCompatActivity main_activity = (MainActivity) getActivity();

        ConstraintLayout add = main_activity.findViewById(R.id.btn_main_add);
        ConstraintLayout sub = main_activity.findViewById(R.id.btn_main_sub);

        ImageView img_sub, img_back, img_add, img_done, img_arrow;
        img_add = main_activity.findViewById(R.id.img_main_right_add);
        img_sub = main_activity.findViewById(R.id.img_main_left_sub);
        img_back = main_activity.findViewById(R.id.img_main_left_back);
        img_done = main_activity.findViewById(R.id.img_main_right_done);
        img_arrow = main_activity.findViewById(R.id.img_main_left_arrow);

        add.setOnClickListener(view2 -> {
            img_add.setVisibility(View.VISIBLE);
            img_sub.setVisibility(View.VISIBLE);
            img_back.setVisibility(View.GONE);
            img_done.setVisibility(View.GONE);
            img_arrow.setVisibility(View.GONE);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainFragment()).commit();
        });
        sub.setOnClickListener(view3 -> {
            img_add.setVisibility(View.VISIBLE);
            img_sub.setVisibility(View.VISIBLE);
            img_back.setVisibility(View.GONE);
            img_done.setVisibility(View.GONE);
            img_arrow.setVisibility(View.GONE);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new MainFragment()).commit();
        });

        return view;
    }
}