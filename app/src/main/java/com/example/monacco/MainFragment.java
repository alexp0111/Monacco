package com.example.monacco;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.monacco.adapters.ViewPagerAdapter;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;


public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private ViewPager2 viewPager2;
    private ImageView dot1, dot2;
    private ImageView img_sub, img_back, img_add, img_done;
    private ConstraintLayout add, sub, hat, valueLandscape;
    private FragmentContainerView operations;
    private LinearLayout dots;
    private ArrayList<KeyFragment> list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        initItems(view);
        loadMainViewPager();

        Log.d(TAG, "main");


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                changeDotColor();
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                changeDotColor();
            }
        });

        add.setOnClickListener(view12 -> {
            if (img_add.getVisibility() == View.VISIBLE){
                hat.setVisibility(View.GONE);
                dots.setVisibility(View.GONE);
                viewPager2.setVisibility(View.GONE);
                valueLandscape.setVisibility(View.GONE);

                operations.setVisibility(View.VISIBLE);

                img_add.setVisibility(View.GONE);
                img_sub.setVisibility(View.GONE);
                img_done.setVisibility(View.VISIBLE);
                img_back.setVisibility(View.VISIBLE);
            } else {
                Snackbar.make(getView(), "Sending data to DB ...", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        sub.setOnClickListener(view1 -> {
            if (img_sub.getVisibility() == View.VISIBLE){
                hat.setVisibility(View.GONE);
                dots.setVisibility(View.GONE);
                viewPager2.setVisibility(View.GONE);
                valueLandscape.setVisibility(View.GONE);

                operations.setVisibility(View.VISIBLE);

                img_add.setVisibility(View.GONE);
                img_sub.setVisibility(View.GONE);
                img_done.setVisibility(View.VISIBLE);
                img_back.setVisibility(View.VISIBLE);
            } else {
                // Going back
                img_add.setVisibility(View.VISIBLE);
                img_sub.setVisibility(View.VISIBLE);
                img_done.setVisibility(View.GONE);
                img_back.setVisibility(View.GONE);

                hat.setVisibility(View.VISIBLE);
                dots.setVisibility(View.VISIBLE);
                viewPager2.setVisibility(View.VISIBLE);
                valueLandscape.setVisibility(View.VISIBLE);


                operations.setVisibility(View.GONE);
            }
        });

        return view;
    }

    private void changeDotColor() {
        Log.d(TAG, "in func");
        switch (viewPager2.getCurrentItem()) {
            case 0: {
                dot1.setImageResource(R.color.orange_700);
                dot2.setImageResource(R.color.gray_800);
                Log.d(TAG, "changeDotColor: 0");
                break;
            }
            case 1: {
                dot2.setImageResource(R.color.orange_700);
                dot1.setImageResource(R.color.gray_800);
                Log.d(TAG, "changeDotColor: 0");
                break;
            }
            default: {
                break;
            }
        }
    }

    private void loadMainViewPager() {
        KeyFragment f1 = new KeyFragment(getString(R.string.header_consumptions), getContext());
        KeyFragment f2 = new KeyFragment(getString(R.string.header_earnings), getContext());

        list = new ArrayList<>();
        list.add(f1);
        list.add(f2);

        ViewPagerAdapter adapter = new ViewPagerAdapter(list, getContext());
        viewPager2.setAdapter(adapter);
    }

    private void initItems(View v) {
        viewPager2 = v.findViewById(R.id.view_pager_main);
        dot1 = v.findViewById(R.id.dot1);
        dot2 = v.findViewById(R.id.dot2);

        add = v.findViewById(R.id.btn_main_add);
        sub = v.findViewById(R.id.btn_main_sub);

        img_add = v.findViewById(R.id.img_main_right_add);
        img_sub = v.findViewById(R.id.img_main_left_sub);
        img_back = v.findViewById(R.id.img_main_left_back);
        img_done = v.findViewById(R.id.img_main_right_done);

        hat = v.findViewById(R.id.toolbar_in_main);
        dots = v.findViewById(R.id.ll_main_dots);

        operations = v.findViewById(R.id.fragment_container);
        valueLandscape = v.findViewById(R.id.toolbar_in_main_value);
    }
}