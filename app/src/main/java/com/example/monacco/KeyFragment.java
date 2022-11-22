package com.example.monacco;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class KeyFragment {

    private String header = "sww";
    private PieChart pieChart;
    private Context context;


    public KeyFragment(String header, Context context) {
        this.header = header;
        this.context = context;
    }

    public void setUpPieChart(PieChart pieChart){
        this.pieChart = pieChart;
    }

    public void setUpPieChart() {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(ContextCompat.getColor(context, R.color.gray_800));
        pieChart.setDrawEntryLabels(false);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);

        pieChart.setCenterText("1100 ₽");
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setCenterTextSize(32);

        pieChart.setHoleRadius(60);
        pieChart.setTransparentCircleRadius(66);

        Legend l = pieChart.getLegend();
        //l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //l.setOrientation(Legend.LegendOrientation.VERTICAL);
        //l.setDrawInside(false);
        l.setEnabled(false);
    }

    public void loadEarnData(ArrayList<PieEntry> entries) {
        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }
        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "label_cons");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(10);
        data.setValueTextColor(Color.WHITE);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }

    public void getPieData(String key) {
        switch (key) {
            case "D": {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(5000, "Job"));
                entries.add(new PieEntry(2000, "gift"));
                entries.add(new PieEntry(4000, "incognito"));
                loadEarnData(entries);
                break;
            }
            case "W": {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(1000, "Job"));
                entries.add(new PieEntry(7000, "gift"));
                entries.add(new PieEntry(4000, "incognito"));
                loadEarnData(entries);
                break;
            }
            case "M": {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(1000, "Job"));
                entries.add(new PieEntry(3000, "gift"));
                entries.add(new PieEntry(7000, "incognito"));
                loadEarnData(entries);
                break;
            }
            default: {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(40000, "Job"));
                entries.add(new PieEntry(10000, "gift"));
                entries.add(new PieEntry(10000, "incognito"));
                loadEarnData(entries);
                break;
            }
        }

    }

    public String getHeader() {
        return header;
    }
}
