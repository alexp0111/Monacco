package com.example.monacco.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monacco.KeyFragment;
import com.example.monacco.R;
import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.MyViewHolder> {
    private ArrayList<KeyFragment> list;

    public ViewPagerAdapter(ArrayList<KeyFragment> list) {
        this.list = list;
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

        holder.txt.setText(header);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public PieChart pieChart;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt = itemView.findViewById(R.id.item_sub_add_header_txt);
            pieChart = itemView.findViewById(R.id.pie_chart);
        }
    }
}
