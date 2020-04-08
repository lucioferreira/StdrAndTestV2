package com.lucio.androidv2;

import com.google.gson.annotations.SerializedName;

public class BankRecordItem {

    @SerializedName("title")
    private String title;
    @SerializedName("desc")
    private String desc;
    @SerializedName("date")
    private String date;
    @SerializedName("value")
    private Double value;

    public BankRecordItem(String title, String desc, String date, Double value) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
