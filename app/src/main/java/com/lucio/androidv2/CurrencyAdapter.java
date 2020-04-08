package com.lucio.androidv2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>{


    private CurrencyDataset customerDs;
    private LayoutInflater inflater;

    public CurrencyAdapter(Context context, CurrencyDataset ds) {
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
        return customerDs.getRecItems().size();
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
        BankRecordItem current = customerDs.getRecItems().get(position);
        holder.setData(current, position);
//        holder.setListeners();
    }

    class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView description;
        private TextView date;
        private TextView value;
        private int position;
        private BankRecordItem currentItem;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            description = (TextView)  itemView.findViewById(R.id.text_description);
            date = (TextView)  itemView.findViewById(R.id.text_date);
            value = (TextView)  itemView.findViewById(R.id.text_value);
        }

        public void setData(BankRecordItem currentObject, int position) {
            this.description.setText(currentObject.getDescription());
            this.date.setText(currentObject.getDate());
            this.value.setText("R$ " + String.format("%.2f",currentObject.getValue()));
            this.position = position;
            this.currentItem = currentObject;
        }


    }



}
