package com.example.monacco;

import static android.content.Context.SENSOR_SERVICE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

import kotlin.Suppress;


public class NewCategoryAddingFragment extends Fragment {

    private TextView consumptionsDot, earningsDot;

    private ImageView picker;
    private CardView cardView;
    private Bitmap bitmap;

    private SensorManager sensorManager;
    private boolean bul = false;
    private long lastUpdate; // mls; DELETION
    private SensorEventListener listener;

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

        ////


        accelerometerListener();


        ////

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

        ImageView img_back, img_arrow;
        img_back = main_activity.findViewById(R.id.img_main_left_back);
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

    private void accelerometerListener() {
        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    float[] values = event.values;
                    float x = values[0];
                    float y = values[1];
                    float z = values[2];
                    float accelerationSquareRoot = (x * x + y * y + z * z)
                            / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
                    long actualTime = System.currentTimeMillis();

                    if (accelerationSquareRoot >= 3.4) //Если тряска сильная
                    {
                        if (actualTime - lastUpdate < 250) {
                            return;
                        }
                        lastUpdate = actualTime;
                        if (bul) {
                            try {
                                Vibrator vibrator = (Vibrator) getContext().getSystemService(Context.VIBRATOR_SERVICE);
                                if (vibrator != null) {
                                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));

                                    // Picking random color
                                    String hex = "#0";
                                    while (hex.equals("#0")) {
                                        bitmap = picker.getDrawingCache();

                                        Random random = new Random();
                                        int bit_x = random.nextInt(bitmap.getWidth());
                                        int bit_y = random.nextInt(bitmap.getHeight());

                                        int pixels = bitmap.getPixel(bit_x, bit_y);
                                        int r = Color.red(pixels);
                                        int g = Color.green(pixels);
                                        int b = Color.blue(pixels);

                                        hex = "#" + Integer.toHexString(pixels);
                                        if (!hex.equals("#0")) {
                                            cardView.setCardBackgroundColor(Color.rgb(r, g, b));
                                            //break;
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                Log.d("FNCA", "out of pic");
                            }
                        }
                        bul = !bul;
                    }
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        //
        sensorManager = (SensorManager) getContext().getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void initItems(View v) {
        picker = v.findViewById(R.id.fnca_color_picker);
        cardView = v.findViewById(R.id.fnca_cv_demo);

        consumptionsDot = v.findViewById(R.id.menu_cons);
        earningsDot = v.findViewById(R.id.menu_earn);
    }
}