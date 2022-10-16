package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Producto;

import java.util.List;

public class AdapterCarrito extends RecyclerView.Adapter<AdapterCarrito.ViewHolder>{
    private List<Producto> mData;
    private LayoutInflater inflater;
    private Context context;
    final AdapterCarrito.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Producto item);
    }

    public AdapterCarrito(List<Producto> itemList, Context context, AdapterCarrito.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
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

    public void setItems(List<Producto> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, descripcion, precio, calificacion;
        ImageView iconImage;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.ca_nombre);
            precio = itemView.findViewById(R.id.ca_precio);
            descripcion = itemView.findViewById(R.id.ca_descricion);
            iconImage = itemView.findViewById(R.id.ca_imagen);
        }

        void bindData(final Producto item){
            nombre.setText(item.getNombre());
            precio.setText(item.getPrecio());
            descripcion.setText(item.getVendedor());
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
