package com.example.monacco.adapters;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monacco.KeyFragment;
import com.example.monacco.R;
import com.github.mikephil.charting.charts.PieChart;
import com.google.android.material.transition.Hold;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {
    private ArrayList<KeyFragment> list;
    private Resources res;

    public ViewPagerAdapter(ArrayList<KeyFragment> list, Resources res) {
        this.list = list;
        this.res = res;
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

        String header = k.getHeader();
        k.setUpPieChart(holder.pieChart);
        k.loadEarnData(holder.pieChart);

        holder.pointer_day.setOnClickListener(view -> {
            holder.pointer_day.setBackground(ResourcesCompat.getDrawable(res, R.drawable.dr_orange, null));
            holder.pointer_day.setTextColor(Color.WHITE);
            holder.pointer_day.setElevation(6f);
            clearMenu("day", holder);
        });
        holder.pointer_week.setOnClickListener(view -> {
            holder.pointer_week.setBackground(ResourcesCompat.getDrawable(res, R.drawable.dr_orange, null));
            holder.pointer_week.setTextColor(Color.WHITE);
            holder.pointer_week.setElevation(6f);
            clearMenu("week", holder);
        });
        holder.pointer_month.setOnClickListener(view -> {
            holder.pointer_month.setBackground(ResourcesCompat.getDrawable(res, R.drawable.dr_orange, null));
            holder.pointer_month.setTextColor(Color.WHITE);
            holder.pointer_month.setElevation(6f);
            clearMenu("month", holder);
        });
        holder.pointer_year.setOnClickListener(view -> {
            holder.pointer_year.setBackground(ResourcesCompat.getDrawable(res, R.drawable.dr_orange, null));
            holder.pointer_year.setTextColor(Color.WHITE);
            holder.pointer_year.setElevation(6f);
            clearMenu("year", holder);
        });

        holder.txt.setText(header);
    }

    private void clearMenu(String key, MyViewHolder holder) {
        switch (key){
            case "day":{
                holder.pointer_week.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_week.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_week.setElevation(0);
                holder.pointer_month.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_month.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_month.setElevation(0);
                holder.pointer_year.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_year.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_year.setElevation(0);
                break;
            }
            case "week":{
                holder.pointer_day.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_day.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_day.setElevation(0);
                holder.pointer_month.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_month.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_month.setElevation(0);
                holder.pointer_year.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_year.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_year.setElevation(0);
                break;
            }
            case "month":{
                holder.pointer_day.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_day.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_day.setElevation(0);
                holder.pointer_week.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_week.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_week.setElevation(0);
                holder.pointer_year.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_year.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_year.setElevation(0);
                break;
            }
            case "year":{
                holder.pointer_day.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_day.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_day.setElevation(0);
                holder.pointer_week.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_week.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_week.setElevation(0);
                holder.pointer_month.setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.pointer_month.setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.pointer_month.setElevation(0);
                break;
            }
            default: break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public PieChart pieChart;
        public TextView pointer_day;
        public TextView pointer_week;
        public TextView pointer_month;
        public TextView pointer_year;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.item_sub_add_header_txt);
            pieChart = itemView.findViewById(R.id.pie_chart);
            pointer_day = itemView.findViewById(R.id.menu_day);
            pointer_week = itemView.findViewById(R.id.menu_week);
            pointer_month = itemView.findViewById(R.id.menu_month);
            pointer_year = itemView.findViewById(R.id.menu_year);
        }
    }
}
