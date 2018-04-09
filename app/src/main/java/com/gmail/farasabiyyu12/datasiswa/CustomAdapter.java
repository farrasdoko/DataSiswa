package com.gmail.farasabiyyu12.datasiswa;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.ApiService;
import com.gmail.farasabiyyu12.datasiswa.ApiRetrofit.InstanceRetrofit;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.DataItem;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseDeleteData;
import com.gmail.farasabiyyu12.datasiswa.ResponseServer.ResponseUpdateData;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * Created by farasabiyyuhandoko on 27/03/2018.
 *
 */

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    Context context;
    List<DataItem> data;
    public static final String WebUrl = "http://192.168.20.83/latihancrud/";
    Dialog popUp;
    Button btnDelete, btnUpdate;


    public CustomAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.data = dataItems;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item , parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, final int position) {
    holder.id.setText(data.get(position).getId());
    final String strid = data.get(position).getId();
    holder.address.setText(data.get(position).getAddress());
    holder.name.setText(data.get(position).getName());
    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Log.d("RAM", ""+data.get(position).getAddress());
            popUp = new Dialog(context);
            popUp.setContentView(R.layout.editdelete);
            popUp.show();

            btnDelete = popUp.findViewById(R.id.btnDelete);
            btnUpdate = popUp.findViewById(R.id.btnUpdate);

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Update Data", Toast.LENGTH_SHORT).show();
                    context.startActivity(new Intent(context, UpdateActivity.class)
                    .putExtra("name", data.get(position).getName())
                    .putExtra("address", data.get(position).getAddress())
                    .putExtra("id", data.get(position).getId()));
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ProgressDialog dialog = ProgressDialog
                            .show(context, "Process Delete Data", "Mohon Tunggu Sebentar");
                    ApiService api = InstanceRetrofit.getInstance();
                    retrofit2.Call<ResponseDeleteData> call = api.response_delete_data(strid);
                    call.enqueue(new Callback<ResponseDeleteData>() {
                        @Override
                        public void onResponse(retrofit2.Call<ResponseDeleteData> call, Response<ResponseDeleteData> response) {
                            Toast.makeText(context, ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            popUp.dismiss();

                        }

                        @Override
                        public void onFailure(retrofit2.Call<ResponseDeleteData> call, Throwable t) {
                            Toast.makeText(context, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                }
            });
            return true;
        }
    });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, address;
//        EditText sid, sadd, sname;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.nama);
            address = itemView.findViewById(R.id.address);
        }
    }
}
