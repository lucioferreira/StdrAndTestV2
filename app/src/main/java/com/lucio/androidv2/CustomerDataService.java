package com.lucio.androidv2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Field;
import retrofit2.http.Query;

public interface CustomerDataService {

    @FormUrlEncoded
    @POST("login")
    Call<Customer> getAllCustomer(@Field("user") String user, @Field("password") String password);
}
