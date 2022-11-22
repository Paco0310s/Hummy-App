package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.telephony.PreciseDataConnectionState;
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
            vendedor.setText(item.getDescripcion());
            calificacion.setText(item.getCalificacion());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });

            if(item.getImagen()==null || item.getImagen().isEmpty()) iconImage.setImageResource(R.drawable.logo);
            else {
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

                DatabaseReference databaseReference = firebaseDatabase.getReference();

                DatabaseReference getImage = databaseReference.child("Productos/" + item.getVendedor() +
                        "/" + item.getId() + "/imagen");

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

