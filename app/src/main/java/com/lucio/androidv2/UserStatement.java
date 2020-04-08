package com.lucio.androidv2;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserStatement {
    @SerializedName("statementList")
    private ArrayList<BankRecordItem> items;

    public ArrayList<BankRecordItem> getItems() {
        return items;
    }
}
