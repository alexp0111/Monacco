package com.example.monacco;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.monacco.adapters.AccountAdapter;
import com.example.monacco.adapters.CategoriesAdapter;
import com.example.monacco.helpclasses.Account;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private MaterialAlertDialogBuilder mdBuilder;
    private View rl;
    private TextInputEditText et;

    private RecyclerView recyclerView;
    private ArrayList<Account> list;
    private AccountAdapter adapter;

    private ConstraintLayout addAcc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        rl = inflater.inflate(R.layout.dialog_new_acc, container, false);
        AppCompatActivity main_activity = (MainActivity) getActivity();

        initItems(view);
        et = rl.findViewById(R.id.et_corrected_name);

        ConstraintLayout add = main_activity.findViewById(R.id.btn_main_add);
        ConstraintLayout sub = main_activity.findViewById(R.id.btn_main_sub);

        ImageView img_sub, img_back, img_add, img_done, img_arrow;
        img_add = main_activity.findViewById(R.id.img_main_right_add);
        img_sub = main_activity.findViewById(R.id.img_main_left_sub);
        img_back = main_activity.findViewById(R.id.img_main_left_back);
        img_done = main_activity.findViewById(R.id.img_main_right_done);
        img_arrow = main_activity.findViewById(R.id.img_main_left_arrow);

        recyclerView = view.findViewById(R.id.fst_rv);

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

        list = new ArrayList<>();
        list.add(new Account(getResources().getString(R.string.main_acc), 999, "₽"));

        adapter = new AccountAdapter(list, getContext().getResources());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        addAcc.setClipToOutline(true);

        addAcc.setOnClickListener(view1 -> {
            mdBuilder = new MaterialAlertDialogBuilder(getContext());

            mdBuilder.setTitle("Account name");
            mdBuilder.setBackground(getResources().getDrawable(R.drawable.dr_add, null));

            if (rl.getParent() != null) {
                ((ViewGroup) rl.getParent()).removeView(rl);
            }
            mdBuilder.setView(rl);

            mdBuilder.setPositiveButton("Apply", (dialogInterface, i) -> {

            });

            mdBuilder.show();
        });

        return view;
    }

    private void initItems(View v) {
        recyclerView = v.findViewById(R.id.fst_rv);
        addAcc = v.findViewById(R.id.fst_add_acc);
    }
}