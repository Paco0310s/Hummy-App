package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.telephony.PreciseDataConnectionState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;

import java.util.List;

public class AdapterProducto extends RecyclerView.Adapter<AdapterProducto.ViewHolder>{

    private List<Producto> mData;
    private LayoutInflater inflater;
    private Context context;
    final AdapterProducto.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Producto item);
    }

    public AdapterProducto(List<Producto> itemList, Context context, AdapterProducto.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_producto, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Producto> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, vendedor, precio, calificacion;
        ImageView iconImage;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.pro_nombre);
            vendedor = itemView.findViewById(R.id.pro_vendedor);
            precio = itemView.findViewById(R.id.pro_precio);
            calificacion = itemView.findViewById(R.id.cal_producto);
            iconImage = itemView.findViewById(R.id.imagen);
        }

        void bindData(final Producto item){
            nombre.setText(item.getNombre());
            vendedor.setText(item.getVendedor());
            calificacion.setText(item.getCalificacion());
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

