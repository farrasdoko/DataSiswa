package com.gmail.farasabiyyu12.datasiswa.ApiRetrofit;

import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseCreateData;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseDeleteData;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseReadData;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseUpdateData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 *
 * Created by farasabiyyuhandoko on 27/03/2018.
 */

//TODO EndPoint

public interface ApiService {
    @GET("read_data.php")
    Call<ResponseReadData> response_read_data();

    @FormUrlEncoded
    @POST("create_data.php")
    Call<ResponseCreateData> response_create_data(
            @Field("name") String nama,
            @Field("address") String address
    );

    @FormUrlEncoded
    @POST("delete_data.php")
    Call<ResponseDeleteData> response_delete_data(
      @Field("vsid") String id
    );

    @FormUrlEncoded
    @POST("update_data.php")
    Call<ResponseUpdateData> response_update_data(
            @Field("vsid") String id,
            @Field("vsname") String name,
            @Field("vsaddress") String address
    );
}
