package com.lucio.androidv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        loadUserPreferences();

        buttonLogin = findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String username = editUser.getText().toString();
                String password = editPassword.getText().toString();

                if( (!isValidUsername(username)) || (!isValidPassword(password)) ){
                    Toast.makeText(MainActivity.this, "Invalid user name or password", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Authenticating....");
                progressDialog.show();

                getUserData();

                saveUserPreferences();
            }
        });
    }

    private boolean isValidUsername(String user){
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

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("(?=.*[A-Z])(?=.*[@$%&#])(?=.*[0-9a-z])");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    private void getUserData() {
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

        Intent intent = new Intent(MainActivity.this, CurrencyActivity.class);

        intent.putExtra("id", customer.userAccount.getUserId());
        intent.putExtra("name", customer.userAccount.getName());
        intent.putExtra("account", customer.userAccount.getBankAccount());
        intent.putExtra("agency", customer.userAccount.getAgency());
        intent.putExtra("balance", String.format("%.2f", customer.userAccount.getBalance()));

        startActivity(intent);
    }

    private void loadUserPreferences() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        if (sharedPref.contains(getString(R.string.username_preference_key))) {
            editUser.setText(sharedPref.getString(getString(R.string.username_preference_key), ""));
        }
        if (sharedPref.contains(getString(R.string.password_preference_key))) {
            String password = sharedPref.getString(getString(R.string.password_preference_key), "");
            editPassword.setText(encryptPassword(password));
        }
    }

    private void saveUserPreferences() {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.username_preference_key), editUser.getText().toString());
        String password = editPassword.getText().toString();
        editor.putString(getString(R.string.password_preference_key), decryptPassword(password));
        editor.apply();;
    }

    private String encryptPassword(String message){
        CryptHelper ch = new CryptHelper();
        String messageEncrypted = "";
        try {
            messageEncrypted = ch.encrypt(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messageEncrypted;
    }

    private String decryptPassword(String message){
        CryptHelper ch = new CryptHelper();
        String messageDecrypted = "";
        try {
            messageDecrypted = ch.decrypt(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return messageDecrypted;
    }

}
