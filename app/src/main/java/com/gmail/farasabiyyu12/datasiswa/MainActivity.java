package com.gmail.farasabiyyu12.datasiswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.ApiService;
import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.InstanceRetrofit;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.DataItem;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseReadData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
    }

    private void getData() {
        ApiService apiService = InstanceRetrofit.getInstance();
        Call<ResponseReadData> call = apiService.response_read_data();
        call.enqueue(new Callback<ResponseReadData>() {
            @Override
            public void onResponse(Call<ResponseReadData> call, Response<ResponseReadData> response) {
                Boolean status = response.body().isSuccess();
                if (status){
                    List<DataItem> dataItems = response.body().getData();
                    CustomAdapter adapter = new CustomAdapter(MainActivity.this, dataItems);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseReadData> call, Throwable t) {

            }
        });
    }
}
