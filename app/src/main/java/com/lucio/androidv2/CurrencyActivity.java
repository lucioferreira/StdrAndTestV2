package com.lucio.androidv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyActivity extends AppCompatActivity {

    TextView textAccount;
    TextView textBalance;
    ProgressDialog progressDialog;
    int userId;
    ArrayList<BankRecordItem> recItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency);

        textAccount = (TextView) findViewById(R.id.text_conta);
        textBalance = (TextView) findViewById(R.id.text_saldo);
        Toolbar mBar = (Toolbar) findViewById(R.id.toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mBar.setTitle(extras.getString("name"));
            textAccount.setText(extras.getString("account") + " / " + extras.getString("agency"));
            textBalance.setText("R$ " + extras.getString("balance"));
            userId = extras.getInt("id");
        }

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

        getUserData(userId);

    }

    void getUserData(int id){

        progressDialog = new ProgressDialog(CurrencyActivity.this);
        progressDialog.setMessage("Fetching statements....");
        progressDialog.show();

        BankRecordItemDataService service = RfClient.getRetrofitInstance().create(BankRecordItemDataService.class);

        Call<UserStatement> call = service.getAllStatements(userId);
        call.enqueue(new Callback<UserStatement>() {

            @Override
            public void onResponse(Call<UserStatement> call, Response<UserStatement> response) {
                progressDialog.dismiss();
                fillStatementData(response.body());
            }

            @Override
            public void onFailure(Call<UserStatement> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(CurrencyActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private CurrencyDataset GetDatasetFilled() {
        CurrencyDataset cds = new CurrencyDataset("Lucio Ferreira", "123456789-0");
        cds.addBankRecordItem(new BankRecordItem("Luz", "Conta de luz", "01/01/2020", 13.0));
        cds.addBankRecordItem(new BankRecordItem("Agua", "Conta de água", "02/01/2020", 14.0));
        return cds;
    }

    private void fillStatementData(UserStatement item){

        recItems = item.getItems();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id. recycler_currency);
        CurrencyAdapter adapter = new CurrencyAdapter(this, recItems);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}
