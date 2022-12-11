package com.example.monacco.helpclasses;

import android.graphics.Color;

public class MoneyCategory {

    private int color;
    private String label;
    private Integer value;

    public MoneyCategory(int color, String label, Integer value) {
        this.color = color;
        this.label = label;
        this.value = value;
    }

    public MoneyCategory(int color, String label) {
        this.color = color;
        this.label = label;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
