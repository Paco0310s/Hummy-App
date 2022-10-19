package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;

import java.util.List;

public class AdapterEstablecimientos extends RecyclerView.Adapter<AdapterEstablecimientos.ViewHolder>{

    private List<Establecimiento> mData;
    private LayoutInflater inflater;
    private Context context;
    final AdapterEstablecimientos.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Establecimiento item);
    }

    public AdapterEstablecimientos(List<Establecimiento> itemList, Context context, AdapterEstablecimientos.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_establicimientos, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Establecimiento> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, descripcion, estado, calificacion;
        ImageView iconImage;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.es_nombre);
            descripcion = itemView.findViewById(R.id.es_id);
            estado = itemView.findViewById(R.id.estadoTienda);
            calificacion = itemView.findViewById(R.id.cal_establecimiento);
            iconImage = itemView.findViewById(R.id.imagen);
        }

        public void bindData(final Establecimiento item){
            nombre.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            estado.setText(item.getEstado());
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
