package com.vantuit.hummy.vista.cliente.adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.vista.administrador.EstablecimientoProductoActivity;
import com.vantuit.hummy.vista.administrador.Establecimiento_ListaProductos;
import com.vantuit.hummy.vista.administrador.ListaPedidos;

import java.util.ArrayList;
import java.util.List;

public class AdapterNotificaciones extends RecyclerView.Adapter<AdapterNotificaciones.ViewHolder> {

    private List<Pedido> mData;
    private LayoutInflater inflater;
    private Context context;

    public AdapterNotificaciones(List<Pedido> itemList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public AdapterNotificaciones.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.cardview_lista_pedidos, null, false);
        return new AdapterNotificaciones.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterNotificaciones.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Pedido> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView direccion, cliente, producto, estado;
        String[] estados = {"Pendiente","Cocinando","En camino","Recibido"};
        Pedido item;

        ViewHolder(View itemView) {
            super(itemView);
            direccion = itemView.findViewById(R.id.tvDireccion);
            cliente = itemView.findViewById(R.id.tvCliente);
            producto = itemView.findViewById(R.id.tvProducto);
            estado = itemView.findViewById(R.id.tvEstado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(), "Card View", Toast.LENGTH_SHORT).show();
                }
            });
            estado.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for (int x = 0; x < (estados.length); x++)
                    {
                        if(estados[x].equals(estado.getText()))
                        {
                            if(x < (estados.length-1)) estado.setText(estados[x+1]);
                            else estado.setText(estados[0]);
                            x = estados.length;
                        }
                    }
                    //colorEstado();
                    cambiaEstadoFB();
                }
            });
        }
        public void cambiaEstadoFB()
        {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Pedidos");

            myRef.child(item.getFecha()).child("estado").setValue(estado.getText());

        }

        void bindData(final Pedido item){
            this.item = item;
            direccion.setText(item.getCliente().getDIRECCION());
            cliente.setText(item.getCliente().getNOMBRE());
            producto.setText(item.getProducto().getNombre());
            estado.setText(item.getEstado());
            colorEstado();

        }
        public void colorEstado()
        {

            int [] color = {Color.RED,Color.rgb(255,128,0),Color.rgb(182,170,91),Color.rgb(1,204,4)};
            for (int x = 0; x < (estados.length); x++)
            {
                if(estados[x].equals(estado.getText()))
                {
                    estado.setTextColor(color[x]);
                    x = estados.length;
                }
            }
        }
    }
}