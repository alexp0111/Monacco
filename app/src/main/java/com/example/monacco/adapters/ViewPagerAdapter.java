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

        k.setUpPieChart(holder.pieChart);
        String header = k.getHeader();
        k.setUpPieChart();
        k.getPieData("D");

        for (int i = 0; i < holder.dots.size(); i++) {
            TextView currentTextView = holder.dots.get(i);
            currentTextView.setOnClickListener(view -> {
                // Menu correcting
                currentTextView.setBackground(ResourcesCompat.getDrawable(res, R.drawable.dr_orange, null));
                currentTextView.setTextColor(Color.WHITE);
                currentTextView.setElevation(6f);
                clearMenu(currentTextView.getText().toString(), holder);

                // Data loading
                k.getPieData(currentTextView.getText().toString());
            });
        }

        holder.txt.setText(header);
    }

    private void clearMenu(String key, MyViewHolder holder) {
        for (int i = 0; i < holder.dots.size(); i++) {
            if (!holder.dots.get(i).getText().toString().equals(key)) {
                holder.dots.get(i).setBackgroundColor(ResourcesCompat.getColor(res, R.color.gray_900, null));
                holder.dots.get(i).setTextColor(ResourcesCompat.getColor(res, R.color.gray_800, null));
                holder.dots.get(i).setElevation(0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public PieChart pieChart;
        public ArrayList<TextView> dots = new ArrayList<>();

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.item_sub_add_header_txt);
            pieChart = itemView.findViewById(R.id.pie_chart);
            dots.add(itemView.findViewById(R.id.menu_day));
            dots.add(itemView.findViewById(R.id.menu_week));
            dots.add(itemView.findViewById(R.id.menu_month));
            dots.add(itemView.findViewById(R.id.menu_year));
        }
    }
}
