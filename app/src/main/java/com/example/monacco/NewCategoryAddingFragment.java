package com.example.monacco;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;


public class NewCategoryAddingFragment extends Fragment {

    private TextView consumptionsDot, earningsDot;

    private ImageView picker;
    private CardView cardView;
    private Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_category_adding, container, false);
        AppCompatActivity main_activity = (MainActivity) getActivity();

        initItems(view);

        picker.setDrawingCacheEnabled(true);
        picker.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
                try {
                    bitmap = picker.getDrawingCache();
                    int pixels = bitmap.getPixel((int) event.getX(), (int) event.getY());
                    int r = Color.red(pixels);
                    int g = Color.green(pixels);
                    int b = Color.blue(pixels);

                    String hex = "#" + Integer.toHexString(pixels);
                    cardView.setCardBackgroundColor(Color.rgb(r, g, b));
                } catch (Exception e) {
                    Log.d("FNCA", "out of pic");
                }
            }
            return true;
        });

        consumptionsDot.setOnClickListener(view12 -> {
            consumptionsDot.setBackground(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.dr_orange, null));
            consumptionsDot.setTextColor(Color.WHITE);
            consumptionsDot.setElevation(6f);

            earningsDot.setBackgroundColor(ResourcesCompat.getColor(getContext().getResources(), R.color.gray_900, null));
            earningsDot.setTextColor(ResourcesCompat.getColor(getContext().getResources(), R.color.gray_800, null));
            earningsDot.setElevation(0);
        });

        earningsDot.setOnClickListener(view1 -> {
            earningsDot.setBackground(ResourcesCompat.getDrawable(getContext().getResources(), R.drawable.dr_orange, null));
            earningsDot.setTextColor(Color.WHITE);
            earningsDot.setElevation(6f);

            consumptionsDot.setBackgroundColor(ResourcesCompat.getColor(getContext().getResources(), R.color.gray_900, null));
            consumptionsDot.setTextColor(ResourcesCompat.getColor(getContext().getResources(), R.color.gray_800, null));
            consumptionsDot.setElevation(0);
        });

        ConstraintLayout add = main_activity.findViewById(R.id.btn_main_add);
        ConstraintLayout sub = main_activity.findViewById(R.id.btn_main_sub);

        ImageView img_sub, img_back, img_add, img_done, img_arrow;
        img_add = main_activity.findViewById(R.id.img_main_right_add);
        img_sub = main_activity.findViewById(R.id.img_main_left_sub);
        img_back = main_activity.findViewById(R.id.img_main_left_back);
        img_done = main_activity.findViewById(R.id.img_main_right_done);
        img_arrow = main_activity.findViewById(R.id.img_main_left_arrow);
        add.setOnClickListener(view13 -> {
            img_back.setVisibility(View.VISIBLE);
            img_arrow.setVisibility(View.GONE);
            // Ignore and back
            getParentFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new SubAddFragment()).commit();
        });

        sub.setOnClickListener(view13 -> {
            img_back.setVisibility(View.VISIBLE);
            img_arrow.setVisibility(View.GONE);
            // Adding new category to DB
            getParentFragmentManager().beginTransaction().
                    replace(R.id.fragment_container, new SubAddFragment()).commit();
        });

        return view;
    }

    private void initItems(View v) {
        picker = v.findViewById(R.id.fnca_color_picker);
        cardView = v.findViewById(R.id.fnca_cv_demo);

        consumptionsDot = v.findViewById(R.id.menu_cons);
        earningsDot = v.findViewById(R.id.menu_earn);
    }
}