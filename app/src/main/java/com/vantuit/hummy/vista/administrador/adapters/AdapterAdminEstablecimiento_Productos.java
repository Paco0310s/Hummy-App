package com.vantuit.hummy.vista.administrador.adapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;

import java.util.List;

public class AdapterAdminEstablecimiento_Productos extends RecyclerView.Adapter<AdapterAdminEstablecimiento_Productos.ViewHolder> {

    private List<Producto> mData;
    private LayoutInflater inflater;
    private Context context;

    public AdapterAdminEstablecimiento_Productos(List<Producto> itemList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public AdapterAdminEstablecimiento_Productos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_admin_establecimiento_productos, null, false);
        return new AdapterAdminEstablecimiento_Productos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAdminEstablecimiento_Productos.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Producto> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, descripcion, precio;
        ImageView iconImage;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.es_nombre);
            descripcion = itemView.findViewById(R.id.es_descripcion);
            precio = itemView.findViewById(R.id.es_precio);
            iconImage = itemView.findViewById(R.id.imagen);
        }

        void bindData(final Producto item){
            nombre.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            precio.setText(item.getPrecio());
            iconImage.setImageResource(item.getImagen());

        }
    }
}