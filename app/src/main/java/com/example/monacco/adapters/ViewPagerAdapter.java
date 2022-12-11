package com.example.monacco.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monacco.KeyFragment;
import com.example.monacco.R;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {
    private ArrayList<KeyFragment> list;
    private Context mContext;

    public ViewPagerAdapter(ArrayList<KeyFragment> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sub_add, parent, false);
        return new ViewPagerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        KeyFragment k = list.get(position);

        k.setUpPieChart(holder.pieChart);
        holder.txt.setText(k.getHeader());
        k.setUpPieChart("1100");
        k.getPieData("D");
        k.setCurrentDot(0);

        for (int i = 0; i < holder.dots.size(); i++) {
            TextView currentTextView = holder.dots.get(i);
            int finalI = i;
            currentTextView.setOnClickListener(view -> {
                if (k.getCurrentDot() == finalI){
                    k.openDatePicker();
                } else {
                    // Menu correcting
                    currentTextView.setBackground(ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.dr_orange, null));
                    currentTextView.setTextColor(Color.WHITE);
                    currentTextView.setElevation(6f);
                    clearMenu(currentTextView.getText().toString(), holder);

                    // Data loading
                    k.getPieData(currentTextView.getText().toString());
                }
                k.setCurrentDot(finalI);
            });
        }

        holder.cl.setOnClickListener(view -> holder.cl.setVisibility(View.GONE));

        CategoriesAdapter adapter = k.getPieData(holder.dots.get(0).getText().toString());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        holder.recyclerView.setAdapter(adapter);
    }

    private void clearMenu(String key, MyViewHolder holder) {
        for (int i = 0; i < holder.dots.size(); i++) {
            if (!holder.dots.get(i).getText().toString().equals(key)) {
                holder.dots.get(i).setBackgroundColor(ResourcesCompat.getColor(mContext.getResources(), R.color.gray_900, null));
                holder.dots.get(i).setTextColor(ResourcesCompat.getColor(mContext.getResources(), R.color.gray_800, null));
                holder.dots.get(i).setElevation(0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout cl;
        public TextView txt;
        public PieChart pieChart;
        public ArrayList<TextView> dots = new ArrayList<>();
        public RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cl = itemView.findViewById(R.id.item_period_choice);

            txt = itemView.findViewById(R.id.item_sub_add_header_txt);
            pieChart = itemView.findViewById(R.id.pie_chart);
            dots.add(itemView.findViewById(R.id.menu_day));
            dots.add(itemView.findViewById(R.id.menu_week));
            dots.add(itemView.findViewById(R.id.menu_month));
            dots.add(itemView.findViewById(R.id.menu_year));

            recyclerView = itemView.findViewById(R.id.item_sub_add_rv);
        }
    }
}
