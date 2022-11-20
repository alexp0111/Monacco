package com.example.monacco;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.monacco.adapters.ViewPagerAdapter;

import java.util.ArrayList;

import javax.security.auth.callback.Callback;


public class MainFragment extends Fragment {

    private static final String TAG = "MainFragment";

    private ViewPager2 viewPager2;
    private ImageView dot1, dot2;
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

        ViewPagerAdapter adapter = new ViewPagerAdapter(list);
        viewPager2.setAdapter(adapter);
    }

    private void initItems(View v) {
        viewPager2 = v.findViewById(R.id.view_pager_main);
        dot1 = v.findViewById(R.id.dot1);
        dot2 = v.findViewById(R.id.dot2);
    }
}