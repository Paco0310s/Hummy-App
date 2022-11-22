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
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_establicimientos, null, false);
        return new AdapterAdminEstablecimientos.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AdapterAdminEstablecimientos.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }

    public void setItems(List<Establecimiento> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre, descripcion, estado, calificacion;
        ImageView iconImage;
        Establecimiento item;

        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.es_nombre);
            descripcion = itemView.findViewById(R.id.es_id);
            estado = itemView.findViewById(R.id.estadoTienda);
            calificacion = itemView.findViewById(R.id.cal_establecimiento);
            iconImage = itemView.findViewById(R.id.imagen);

            nombre.setSelected(true);
            descripcion.setSelected(true);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, Establecimiento_ListaProductos.class);
                    i.putExtra("establecimiento",item);
                    context.startActivity(i);
                }
            });
        }

        void bindData(final Establecimiento item){
            this.item = item;

            nombre.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            estado.setText(item.getEstado());
            calificacion.setText(item.getCalificacion());

            if(item.getFoto()==null || item.getFoto().isEmpty()) iconImage.setImageResource(R.drawable.logo);
            else {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                DatabaseReference databaseReference = firebaseDatabase.getReference();

                DatabaseReference getImage = databaseReference.child("Establecimientos/" + item.getId() + "/foto");

                getImage.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String link = dataSnapshot.getValue(String.class);

                        assert link != null;
                        if(!link.isEmpty()) Picasso.get().load(link).into(iconImage);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        }
    }
}
