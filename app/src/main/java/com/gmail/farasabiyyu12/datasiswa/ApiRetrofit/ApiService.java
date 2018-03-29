package com.gmail.farasabiyyu12.datasiswa.ApiRetrofit;

import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseReadData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 *
 * Created by farasabiyyuhandoko on 27/03/2018.
 */

//TODO EndPoint

public interface ApiService {
    @GET("read_data.php")
    Call<ResponseReadData> response_read_data();
}
