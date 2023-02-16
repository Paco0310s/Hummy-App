package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.vantuit.hummy.modelo.Carrito;
import com.vantuit.hummy.modelo.Comunicacion;

import java.util.List;

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.ViewHolder>{
    private List<Carrito> mData;
    private LayoutInflater inflater;
    private Context context;
    final AdapterCarrito.OnItemClickListener listener;
    private final Comunicacion comunicacion;

    public interface OnItemClickListener{
        void onItemClick(Carrito item);
    }

    public AdapterCarrito(List<Carrito> itemList, Context context, AdapterCarrito.OnItemClickListener listener, Comunicacion comunicacion){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
        this.comunicacion = comunicacion;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public AdapterCarrito.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_producto_carrito, null, false);
        return new AdapterCarrito.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterCarrito.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Carrito> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, descripcion, precio, calificacion;
        ImageView iconImage;
        EditText etCantidad;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ca_nombre);
            precio = itemView.findViewById(R.id.ca_precio);
            descripcion = itemView.findViewById(R.id.ca_descricion);
            iconImage = itemView.findViewById(R.id.ca_imagen);
            etCantidad = itemView.findViewById(R.id.etCantidad);
        }

        void bindData(final Carrito item){
            nombre.setText(item.getNombre());
            precio.setText(item.getPrecio());
            etCantidad.setText(String.valueOf(item.getCantidad()));
            descripcion.setText(item.getVendedor());
            Picasso.get().load(item.getImagen()).into(iconImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //listener.onItemClick(item);
                }
            });

        }
    }

}
