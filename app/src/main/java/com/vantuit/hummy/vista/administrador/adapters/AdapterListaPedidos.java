package com.vantuit.hummy.vista.administrador.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.List;

public class AdapterListaPedidos extends RecyclerView.Adapter<AdapterListaPedidos.ViewHolder> {

    private List<Pedido> mData;
    private LayoutInflater inflater;
    private Context context;

    public AdapterListaPedidos(List<Pedido> itemList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public AdapterListaPedidos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.cardview_lista_pedidos, null, false);
        return new AdapterListaPedidos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterListaPedidos.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Pedido> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView direccion, cliente, producto, estado;

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
                    /*Intent i = new Intent(context, Establecimiento_ListaProductos.class);
                    i.putExtra("establecimiento",item);
                    context.startActivity(i);*/
                }
            });
        }

        void bindData(final Pedido item){
            this.item = item;
            direccion.setText(item.getCliente().getDIRECCION());
            cliente.setText(item.getCliente().getNOMBRE());
            producto.setText(item.getProducto().getNombre());
            estado.setText(item.getEstado());


        }
    }
}

