package com.lucio.androidv2.network;

import com.google.gson.annotations.SerializedName;
import com.lucio.androidv2.network.BankRecordItem;
import com.lucio.androidv2.network.Error;

import java.util.ArrayList;

public class UserStatement {
    @SerializedName("statementList")
    private ArrayList<BankRecordItem> items;

    public ArrayList<BankRecordItem> getItems() {
        return items;
    }

    public Error error;
}
