package com.example.monacco;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;

import com.example.monacco.adapters.CategoriesAdapter;
import com.example.monacco.helpclasses.MoneyCategory;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

// TODO: Functionality
//  1. Ввод суммы
//  2. Выбор категории
//  3. Выбор даты
//  4. Комментарий


public class SubAddFragment extends Fragment {

    private StringBuilder value;

    private RecyclerView recyclerView;
    private CategoriesAdapter adapter;
    private ConstraintLayout addCategory;

    private TextInputEditText editText;
    private ArrayList<CardView> cards;
    private CardView cd_remove;
    private CardView cd_clear;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sub_add, container, false);

        initItems(view);

        editText.setInputType(InputType.TYPE_NULL);
        addCategory.setClipToOutline(true);

        // Click listeners
        for (int i = 0; i < cards.size(); i++) {
            int finalI = i;
            cards.get(i).setOnClickListener(view12 -> {
                editText.requestFocus();
                value.append(finalI);
                editText.setText(String.format("%s ₽", value.toString()));
            });
        }

        cd_remove.setOnClickListener(view13 -> {
            if (value.length() > 1) {
                value.deleteCharAt(value.length() - 1);
                editText.setText(String.format("%s ₽", value.toString()));
            } else if (value.length() == 1) {
                value.setLength(0);
                editText.setText(value.toString());
            }
        });
        cd_clear.setOnClickListener(view14 -> {
            value.setLength(0);
            editText.setText(value.toString());
        });

        addCategory.setOnClickListener(view1 -> getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewCategoryAddingFragment()).commit());

        //

        ArrayList<MoneyCategory> list = new ArrayList<>();
        list.add(new MoneyCategory(R.color.red_300, "Еда"));
        list.add(new MoneyCategory(R.color.green_300, "Музыка"));
        list.add(new MoneyCategory(R.color.light_blue_600, "Учеба"));
        list.add(new MoneyCategory(R.color.orange_700, "Спорт"));
        list.add(new MoneyCategory(R.color.white, "Фильмы"));

        adapter = new CategoriesAdapter(list, getContext().getResources());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public Integer getValue() {
        return Integer.valueOf(value.toString());
    }

    public ArrayList<MoneyCategory> getCategories() {
        return adapter.getCheckedItems();
    }

    private void initItems(View v) {
        value = new StringBuilder();
        cards = new ArrayList<>();

        editText = v.findViewById(R.id.fsa_et);

        cards.add(v.findViewById(R.id.fsa_cd_0));
        cards.add(v.findViewById(R.id.fsa_cd_1));
        cards.add(v.findViewById(R.id.fsa_cd_2));
        cards.add(v.findViewById(R.id.fsa_cd_3));
        cards.add(v.findViewById(R.id.fsa_cd_4));
        cards.add(v.findViewById(R.id.fsa_cd_5));
        cards.add(v.findViewById(R.id.fsa_cd_6));
        cards.add(v.findViewById(R.id.fsa_cd_7));
        cards.add(v.findViewById(R.id.fsa_cd_8));
        cards.add(v.findViewById(R.id.fsa_cd_9));
        cd_remove = v.findViewById(R.id.fsa_cd_remove);
        cd_clear = v.findViewById(R.id.fsa_cd_clear);

        addCategory = v.findViewById(R.id.fsa_add_category);

        recyclerView = v.findViewById(R.id.fsa_rv);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (value != null) {
            value.setLength(0);
            editText.setText(value.toString());
        }
    }
}