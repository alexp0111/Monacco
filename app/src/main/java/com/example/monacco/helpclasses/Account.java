package com.example.monacco.helpclasses;

public class Account {

    private String label;
    private int value;
    private String currency;

    public Account(String label, int value, String currency) {
        this.label = label;
        this.value = value;
        this.currency = currency;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
