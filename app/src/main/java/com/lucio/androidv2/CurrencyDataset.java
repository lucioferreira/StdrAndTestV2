package com.lucio.androidv2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrencyDataset {

    private String customer;
    private String AccountNo;
    private ArrayList<BankRecordItem> recItems;

    public CurrencyDataset(String customer, String accountNo) {
        this.customer = customer;
        AccountNo = accountNo;
        recItems = new ArrayList<BankRecordItem>();
    }

    public String getCustomer() {
        return customer;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public ArrayList<BankRecordItem> getRecItems() {
        return recItems;
    }

    public void addBankRecordItem(BankRecordItem item){
        recItems.add(item);
    }

    public Double getSum(){
        Double sum = 0.0;
        for(BankRecordItem item : recItems) {
            sum += item.getValue();
        }
        return sum;
    }
}
