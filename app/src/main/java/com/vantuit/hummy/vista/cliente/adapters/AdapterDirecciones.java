package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Direccion;

import java.util.List;

public class AdapterDirecciones extends RecyclerView.Adapter<AdapterDirecciones.ViewHolder>{

    private List<Direccion> mData;
    private LayoutInflater inflater;
    private Context context;

    public AdapterDirecciones(List<Direccion> itemList, Context context){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_domicilio, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Direccion> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView codigopostal;
        TextView estado;
        TextView municipio;
        TextView colonia;
        TextView numeroexterior;
        TextView numerodetelefono;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.Nombre);
            codigopostal = itemView.findViewById(R.id.codigoPostal);
            estado = itemView.findViewById(R.id.estado);
            municipio = itemView.findViewById(R.id.municipio);
            colonia = itemView.findViewById(R.id.colonia);
            numeroexterior = itemView.findViewById(R.id.numero);
            numerodetelefono = itemView.findViewById(R.id.telefono);
        }

        void bindData(final Direccion item){
            nombre.setText(item.getNombre());
            codigopostal.setText(String.valueOf(item.getCodigopostal()));
            estado.setText(item.getEstado());
            municipio.setText(item.getMunicipio());
            colonia.setText(item.getColonia());
            numeroexterior.setText(String.valueOf(item.getNumeroexterior()));
            numerodetelefono.setText(item.getNumerodetelefono());
        }
    }

}
