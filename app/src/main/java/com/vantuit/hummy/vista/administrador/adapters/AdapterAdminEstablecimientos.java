package com.vantuit.hummy.vista.administrador.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.vista.administrador.EstablecimientoProductoActivity;
import com.vantuit.hummy.vista.administrador.Establecimiento_ListaProductos;

import java.util.List;

public class AdapterAdminEstablecimientos extends RecyclerView.Adapter<AdapterAdminEstablecimientos.ViewHolder> {

    private List<Establecimiento> mData;
    private LayoutInflater inflater;
    private Context context;

    public AdapterAdminEstablecimientos(List<Establecimiento> itemList, Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public AdapterAdminEstablecimientos.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_admin_establecimientos, null, false);
        return new AdapterAdminEstablecimientos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAdminEstablecimientos.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Establecimiento> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, id;
        ImageView iconImage;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.es_nombre);
            id = itemView.findViewById(R.id.es_id);
            iconImage = itemView.findViewById(R.id.imagen);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Establecimiento_ListaProductos.class);
                    context.startActivity(i);
                }
            });
        }

        void bindData(final Establecimiento item){
            nombre.setText(item.getNombre());
            id.setText(item.getId());
            iconImage.setImageResource(item.getImagen());

        }
    }
}
