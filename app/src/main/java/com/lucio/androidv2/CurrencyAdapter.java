package com.lucio.androidv2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>{


//    private CurrencyDataset customerDs;
    private ArrayList<BankRecordItem> customerDs;
    private LayoutInflater inflater;

    public CurrencyAdapter(Context context, ArrayList<BankRecordItem> ds) {
        inflater = LayoutInflater.from(context);
        this.customerDs = ds;
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        CurrencyViewHolder holder = new CurrencyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return customerDs.size();
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        BankRecordItem current = customerDs.get(position);
        holder.setData(current, position);
//        holder.setListeners();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView description;
        private TextView date;
        private TextView value;
        private int position;
        private BankRecordItem currentItem;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_title);
            description = (TextView)  itemView.findViewById(R.id.text_description);
            date = (TextView)  itemView.findViewById(R.id.text_date);
            value = (TextView)  itemView.findViewById(R.id.text_value);
        }

        public void setData(BankRecordItem currentObject, int position) {
            this.title.setText(currentObject.getTitle());
            this.description.setText(currentObject.getTitle());
            this.date.setText(currentObject.getDate());
            this.value.setText("R$ " + String.format("%.2f",currentObject.getValue()));
            this.position = position;
            this.currentItem = currentObject;
        }


    }



}
