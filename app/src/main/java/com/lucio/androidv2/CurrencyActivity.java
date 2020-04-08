package com.lucio.androidv2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class CurrencyActivity extends AppCompatActivity {

    TextView textConta;
    TextView textSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        textConta = (TextView) findViewById(R.id.text_conta);
        textSaldo = (TextView) findViewById(R.id.text_saldo);

        Toolbar mBar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(mBar);
        mBar.setTitle("Fulano de Tal");
//        mBar.setSubtitle("Android");
//        mBar.setNavigationIcon(R.drawable.logout2);
        mBar.inflateMenu(R.menu.menu_currency);


        mBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                if( menuItem.getItemId() == R.id.menu_logout){
                    Toast.makeText(CurrencyActivity.this, "logout", Toast.LENGTH_LONG).show();
                }

                return true;
            }
        });

        CurrencyDataset currencyDs = GetDatasetFilled();
        textConta.setText(currencyDs.getCustomer());
        textSaldo.setText(String.format("%.2f", currencyDs.getSum()));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id. recycler_currency);
        CurrencyAdapter adapter = new CurrencyAdapter(this, currencyDs);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    CurrencyDataset GetDatasetFilled() {
        CurrencyDataset cds = new CurrencyDataset("Lucio Ferreira", "123456789-0");
        cds.addBankRecordItem(new BankRecordItem("Conta de luz", 13.0, "01/01/2020"));
        cds.addBankRecordItem(new BankRecordItem("Conta de luz", 14.0, "02/01/2020"));
        cds.addBankRecordItem(new BankRecordItem("Conta de luz", 15.0, "03/01/2020"));
        cds.addBankRecordItem(new BankRecordItem("Conta de luz", 16.0, "04/01/2020"));
        cds.addBankRecordItem(new BankRecordItem("Conta de luz", 17.0, "05/01/2020"));
        cds.addBankRecordItem(new BankRecordItem("Conta de luz", 18.0, "06/01/2020"));
        return cds;
    }
}
