package com.thrq.bitebox;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriViewHolder>{

    public KategoriAdapter(Context context, List<KategoriClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    private Context context;
    private List<KategoriClass> dataList;

    @NonNull
    @Override
    public KategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kategori_list_item, parent, false);
        return new KategoriViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KategoriViewHolder holder, int position) {
        holder.recNama.setText(dataList.get(position).getDataNama());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailKategoriActivity.class);
                intent.putExtra("Nama", dataList.get(holder.getAdapterPosition()).getDataNama());

                intent.putExtra("Key",dataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<KategoriClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class KategoriViewHolder extends RecyclerView.ViewHolder{

    TextView recNama;
    CardView recCard;

    public KategoriViewHolder(@NonNull View itemView) {
        super(itemView);

        recNama = itemView.findViewById(R.id.recNama);
        recCard = itemView.findViewById(R.id.recCard);

    }
}