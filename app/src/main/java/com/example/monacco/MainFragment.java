package com.example.monacco;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.monacco.adapters.ViewPagerAdapter;
import com.example.monacco.helpclasses.MoneyCategory;
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
        AppCompatActivity main_activity = (MainActivity) getActivity();

        initItems(view);
        loadMainViewPager();

        Log.d(TAG, "main");

        View child = viewPager2.getChildAt(0);
        if (child instanceof RecyclerView) {
            child.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

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

        ConstraintLayout add = main_activity.findViewById(R.id.btn_main_add);
        ConstraintLayout sub = main_activity.findViewById(R.id.btn_main_sub);

        ImageView img_sub, img_back, img_add, img_done;
        img_add = main_activity.findViewById(R.id.img_main_right_add);
        img_sub = main_activity.findViewById(R.id.img_main_left_sub);
        img_back = main_activity.findViewById(R.id.img_main_left_back);
        img_done = main_activity.findViewById(R.id.img_main_right_done);


        add.setOnClickListener(view2 -> {
            img_add.setVisibility(View.GONE);
            img_sub.setVisibility(View.GONE);
            img_back.setVisibility(View.VISIBLE);
            img_done.setVisibility(View.VISIBLE);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SubAddFragment()).commit();
        });
        sub.setOnClickListener(view3 -> {
            img_add.setVisibility(View.GONE);
            img_sub.setVisibility(View.GONE);
            img_back.setVisibility(View.VISIBLE);
            img_done.setVisibility(View.VISIBLE);

            getParentFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SubAddFragment()).commit();
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

        hat = v.findViewById(R.id.toolbar_in_main);
        dots = v.findViewById(R.id.ll_main_dots);

        //operations = v.findViewById(R.id.fragment_container);
        valueLandscape = v.findViewById(R.id.toolbar_in_main_value);
    }
}