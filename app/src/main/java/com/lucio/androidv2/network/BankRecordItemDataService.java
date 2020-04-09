package com.lucio.androidv2.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BankRecordItemDataService {

    @GET("statements/{id}")
    Call<UserStatement> getAllStatements(@Path("id") int id);

}
