package com.example.monacco;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class NewCategoryAddingFragment extends Fragment {

    private ImageView picker;
    private CardView cardView;
    private Bitmap bitmap;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_category_adding, container, false);

        picker = view.findViewById(R.id.fnca_color_picker);
        cardView = view.findViewById(R.id.fnca_cv_demo);

        picker.setDrawingCacheEnabled(true);
        picker.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                try {
                    bitmap = picker.getDrawingCache();
                    int pixels = bitmap.getPixel((int)event.getX(), (int)event.getY());
                    int r = Color.red(pixels);
                    int g = Color.green(pixels);
                    int b = Color.blue(pixels);

                    String hex = "#" + Integer.toHexString(pixels);
                    cardView.setCardBackgroundColor(Color.rgb(r, g, b));
                } catch (Exception e){
                    Log.d("FNCA", "out of pic");
                }
            }
            return true;
        });

        return view;
    }
}