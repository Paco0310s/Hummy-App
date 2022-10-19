package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Opinion;
import com.vantuit.hummy.modelo.Producto;

import java.util.List;

public class AdapterOpinion extends RecyclerView.Adapter<AdapterOpinion.ViewHolder> {


    private List<Opinion> mData;
    private LayoutInflater inflater;
    private Context context;
    final AdapterOpinion.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Opinion item);
    }

    public AdapterOpinion(List<Opinion> itemList, Context context, AdapterOpinion.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @NonNull
    @Override
    public AdapterOpinion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_opinion, null, false);
        return new AdapterOpinion.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOpinion.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Opinion> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, opinion;
        ImageView imagen_producto;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.op_nombre);
            opinion = itemView.findViewById(R.id.op_opnion);
            imagen_producto = itemView.findViewById(R.id.op_imagen);
        }

        void bindData(final Opinion item){
            nombre.setText(item.getNombre());
            opinion.setText(item.getOinion());
            imagen_producto.setImageResource(item.getImagen());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });
        }
    }

}
