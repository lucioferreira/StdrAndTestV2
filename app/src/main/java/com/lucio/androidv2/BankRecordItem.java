package com.lucio.androidv2;

public class BankRecordItem {
    private String description;
    private Double value;
    private String date;

    public BankRecordItem(String description, Double value, String date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public Double getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }
}
