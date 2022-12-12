package com.example.monacco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

// TODO: Settings
//  1. Валюта
//  2. Язык
//  3. Удалить данные
//  4. Конфигурация счетов -> Название + валюта

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Color of status bar
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.gray_900));

        ConstraintLayout add = findViewById(R.id.btn_main_add);
        ConstraintLayout sub = findViewById(R.id.btn_main_sub);

        ImageView img_sub, img_back, img_add, img_done;
        img_add = findViewById(R.id.img_main_right_add);
        img_sub = findViewById(R.id.img_main_left_sub);
        img_back = findViewById(R.id.img_main_left_back);
        img_done = findViewById(R.id.img_main_right_done);

        add.setClipToOutline(true);
        sub.setClipToOutline(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MainFragment()).commit();

        add.setOnClickListener(view -> {
            img_add.setVisibility(View.GONE);
            img_sub.setVisibility(View.GONE);
            img_back.setVisibility(View.VISIBLE);
            img_done.setVisibility(View.VISIBLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SubAddFragment()).commit();
        });
        sub.setOnClickListener(view -> {
            img_add.setVisibility(View.GONE);
            img_sub.setVisibility(View.GONE);
            img_back.setVisibility(View.VISIBLE);
            img_done.setVisibility(View.VISIBLE);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new SubAddFragment()).commit();
        });
    }
}