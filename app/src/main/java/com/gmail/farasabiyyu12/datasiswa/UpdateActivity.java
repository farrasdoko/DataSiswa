package com.gmail.farasabiyyu12.datasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.ApiService;
import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.InstanceRetrofit;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseUpdateData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    @BindView(R.id.etnamed)
    EditText etnamed;
    @BindView(R.id.etaddressed)
    EditText etaddressed;
    @BindView(R.id.btnupdated)
    Button btnupdated;
    @BindView(R.id.popup2)
    LinearLayout popup2;

    String straddress, strname, strid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        etnamed.setText(getIntent().getStringExtra("name"));
        etaddressed.setText(getIntent().getStringExtra("address"));

        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @OnClick(R.id.btnupdated)
    public void onViewClicked() {
        strid = getIntent().getStringExtra("id");
        strname = etnamed.getText().toString();
        straddress = etaddressed.getText().toString();

        final ProgressDialog progress = ProgressDialog.show(this, "Proses Update Data"
                , "Mohon Tunggu Sebentar");
        ApiService api = InstanceRetrofit.getInstance();
        Call<ResponseUpdateData> call = api.response_update_data(strid, strname, straddress);
        call.enqueue(new Callback<ResponseUpdateData>() {
            @Override
            public void onResponse(Call<ResponseUpdateData> call, Response<ResponseUpdateData> response) {
                if(response.body().isSukses()){
                    Toast.makeText(UpdateActivity.this, ""+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    loadingselesai();
                }else {
                    Toast.makeText(UpdateActivity.this, ""+response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    loadingselesai();
                }
            }

            private void loadingselesai() {
                startActivity(new Intent(UpdateActivity.this, MainActivity.class));
                progress.setCancelable(true);
            }

            @Override
            public void onFailure(Call<ResponseUpdateData> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });

    }
}
