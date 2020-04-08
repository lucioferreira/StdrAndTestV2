package com.lucio.androidv2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText editUser;
    EditText editPassword;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = (EditText) findViewById(R.id.edittext_user);
        editPassword = (EditText) findViewById(R.id.edittext_password);

        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Finding ....");
                progressDialog.show();
                getUserData();


//                String username = editUser.getText().toString();
//                if(!isUsernameValid(username)){
//                    Toast.makeText(MainActivity.this, "username inválido", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);
//                startActivity(intent);
            }
        });
    }

    private boolean isUsernameValid(String user){
        if(user.isEmpty()){
            return false;
        }
        return (isValidCpf(user) || isValidEmail(user));
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean isValidCpf(String cpf){
        Pattern pattern = Pattern.compile("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}");
        Matcher matcher = pattern.matcher(cpf);
        return matcher.find();
    }

    private void getUserData() {
        /*Create handle for the RetrofitInstance interface*/
        CustomerDataService service = RfClient.getRetrofitInstance().create(CustomerDataService.class);

        Call<Customer> call = service.getAllCustomer("test_user", "Test@1");
        call.enqueue(new Callback<Customer>() {

            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                progressDialog.dismiss();
                fillCustomerData(response.body());
            }

            @Override
            public void onFailure(Call<Customer> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fillCustomerData(Customer customer){

        // TODO: objeto chegando nulo aqui
        CurrencyDataset cds = new CurrencyDataset(customer.getName(), customer.getBankAccount());

    }

}
