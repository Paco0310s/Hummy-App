package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Categoria;

import java.util.List;

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.ViewHolder>{

    private List<Categoria> mData;
    private LayoutInflater inflater;
    private Context context;
    final OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Categoria item);
    }

    public AdapterCategorias(List<Categoria> itemList, Context context, OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.card_categorias, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Categoria> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.imagen);
        }

        void bindData(final Categoria item){
            iconImage.setImageResource(item.getImagen());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });

        }
    }

}
