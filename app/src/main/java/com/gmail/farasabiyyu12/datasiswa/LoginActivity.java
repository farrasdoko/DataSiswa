package com.gmail.farasabiyyu12.datasiswa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.ApiService;
import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.InstanceRetrofit;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.acusername)
    AutoCompleteTextView acusername;
    @BindView(R.id.edtpassword)
    EditText edtpassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.txtregister)
    TextView txtregister;

    String strUsername, strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLogin, R.id.txtregister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                strUsername = acusername.getText().toString();
                strPassword = edtpassword.getText().toString();

                if (TextUtils.isEmpty(strUsername)){
                    acusername.setError("Username Wajib Diisi");
                }else if (TextUtils.isEmpty(strPassword)){
                    edtpassword.setError("Username Wajib Diisi");
                }else if (strPassword.length() <= 6 ) {
                    edtpassword.setError("Password Harus Lebih Dari 6");
                }else {
                    loginUser();
                }
                break;
            case R.id.txtregister:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }

    private void loginUser() {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
//        dialog.setIndeterminate(true);
        dialog.show();

        ApiService api = InstanceRetrofit.getInstance();
        Call<ResponseLogin> call = api.response_login(strUsername, strPassword);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                dialog.dismiss();
                String result = response.body().getResult();
                String msg = response.body().getMsg();
                if (result.equals("1")){
                    Toast.makeText(LoginActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, ""+msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
