package com.gmail.farasabiyyu12.datasiswa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gmail.farasabiyyu12.datasiswa.ResponseServer.DataItem;

import java.util.List;

/**
 *
 * Created by farasabiyyuhandoko on 27/03/2018.
 *
 */

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    Context context;
    List<DataItem> data;
    public static final String WebUrl = "http://192.168.20.2/latihancrud/";

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
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
    holder.id.setText(data.get(position).getId());
    holder.address.setText(data.get(position).getAddress());
    holder.name.setText(data.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView id, name, address;

        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.nama);
            address = itemView.findViewById(R.id.address);
        }
    }
}
