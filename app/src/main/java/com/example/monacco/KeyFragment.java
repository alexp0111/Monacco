package com.example.monacco;

import android.content.Context;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.monacco.adapters.CategoriesAdapter;
import com.example.monacco.helpclasses.MoneyCategory;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.ArrayList;

public class KeyFragment {

    private String header = "sww";
    private PieChart pieChart;
    private Context context;
    private int currentDot;


    public KeyFragment(String header, Context context) {
        this.header = header;
        this.context = context;
    }

    public void setUpPieChart(PieChart pieChart){
        this.pieChart = pieChart;
    }

    public void setUpPieChart(String value) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(ContextCompat.getColor(context, R.color.gray_800));
        pieChart.setDrawEntryLabels(false);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);

        pieChart.setCenterText(value + " ₽");
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setCenterTextSize(24);

        pieChart.setHoleRadius(56);
        pieChart.setTransparentCircleRadius(62);

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

    public CategoriesAdapter getPieData(String key) {
        switch (key) {
            case "D": {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(5000, "Job"));
                entries.add(new PieEntry(2000, "gift"));
                entries.add(new PieEntry(4000, "incognito"));
                setUpPieChart("1100");
                loadEarnData(entries);
                break;
            }
            case "W": {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(1000, "Job"));
                entries.add(new PieEntry(7000, "gift"));
                entries.add(new PieEntry(4000, "incognito"));
                setUpPieChart("9600");
                loadEarnData(entries);
                break;
            }
            case "M": {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(1000, "Job"));
                entries.add(new PieEntry(3000, "gift"));
                entries.add(new PieEntry(7000, "incognito"));
                setUpPieChart("69870");
                loadEarnData(entries);
                break;
            }
            default: {
                ArrayList<PieEntry> entries = new ArrayList<>();
                entries.add(new PieEntry(40000, "Job"));
                entries.add(new PieEntry(10000, "gift"));
                entries.add(new PieEntry(10000, "incognito"));
                setUpPieChart("547900");
                loadEarnData(entries);
                break;
            }
        }

        ArrayList<MoneyCategory> list = new ArrayList<>();
        list.add(new MoneyCategory(R.color.red_300, "Еда", 1000));
        list.add(new MoneyCategory(R.color.green_300, "Музыка", 4799));
        list.add(new MoneyCategory(R.color.light_blue_600, "Учеба", 9000));
        list.add(new MoneyCategory(R.color.orange_700, "Спорт", 4536));

        return new CategoriesAdapter(list, context.getResources());
    }

    public void openDatePicker(){
        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText(R.string.calendar_label);
        builder.setTheme(R.style.MaterialCalendarTheme);
        MaterialDatePicker<Long> materialDatePicker = builder.build();

        materialDatePicker.show(((AppCompatActivity) context).getSupportFragmentManager(), "DATE_PICKER");
    }

    public String getHeader() {
        return header;
    }

    public int getCurrentDot() {
        return currentDot;
    }

    public void setCurrentDot(int currentDot) {
        this.currentDot = currentDot;
    }
}
