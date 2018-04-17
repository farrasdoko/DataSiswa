package com.gmail.farasabiyyu12.datasiswa;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.ApiService;
import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.InstanceRetrofit;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.DataItem;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseCreateData;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseReadData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //TODO new Materi
    Dialog popUp;
    EditText etName, etAddress;
    Button btnInsert, btnDelete;

    SwipeRefreshLayout mSwipeRefreshLayout;

    String snama, saddress;

//    List<DataItem> dataItems;
    List<DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();
        //TODO new Materi
        FloatingActionButton floatingActionButton = findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            popUp = new Dialog(MainActivity.this);
            popUp.setContentView(R.layout.inputdata);
            popUp.show();

            etName = popUp.findViewById(R.id.etname);
            etAddress = popUp.findViewById(R.id.etaddress);

            btnInsert = popUp.findViewById(R.id.btninsert);
            btnInsert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    snama = etName.getText().toString();
                    saddress = etAddress.getText().toString();

                    if (TextUtils.isEmpty(snama) && TextUtils.isEmpty(saddress)){
                        etAddress.setError("Isi Dulu");
                        etName.setError("Isi Dulu");

                        etAddress.requestFocus();
                        etName.requestFocus();
                    }else{
                        insertData();
                        popUp.dismiss();
                    }
                }
            });

            btnDelete = popUp.findViewById(R.id.btndelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteData();
                }
            });

                // TODO Swipe Refresh Layout
                mSwipeRefreshLayout = findViewById(R.id.swifeRefresh);
                mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        getData();
                    }
                });



            }
        });
    }

    private void insertData() {
        final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "Proses Data", "Mohon Ditunggu");
        ApiService apiService = InstanceRetrofit.getInstance();
        Call<ResponseCreateData> createDataCall = apiService.response_create_data(
          snama, saddress
        );

        createDataCall.enqueue(new Callback<ResponseCreateData>() {
            @Override
            public void onResponse(Call<ResponseCreateData> call, Response<ResponseCreateData> response) {
                if (response.body().isSukses()){
                    Toast.makeText(MainActivity.this, ""+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateData> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteData() {
        
    }

    private void getData() {
        ApiService apiService = InstanceRetrofit.getInstance();
        Call<ResponseReadData> readDataCall = apiService.response_read_data();
        readDataCall.enqueue(new Callback<ResponseReadData>() {
            @Override
            public void onResponse(Call<ResponseReadData> call, Response<ResponseReadData> response) {
                Boolean status = response.body().isSuccess();
                if (status) {
                    mSwipeRefreshLayout.setRefreshing(false);
                    dataItems = response.body().getData();
                    CustomAdapter adapter = new CustomAdapter(MainActivity.this, dataItems);
                    recyclerView.setAdapter(adapter);
                }else {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onFailure(Call<ResponseReadData> call, Throwable t) {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
