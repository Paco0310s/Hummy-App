package com.vantuit.hummy.vista.cliente.adapters;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Direccion;
import com.vantuit.hummy.vista.cliente.DireccionesActivity;
import com.vantuit.hummy.vista.cliente.InicioActivity;
import com.vantuit.hummy.vista.cliente.NuevaDireccionActivity;

import java.util.List;

public class AdapterDirecciones extends RecyclerView.Adapter<AdapterDirecciones.ViewHolder>{

    private List<Direccion> mData;
    private LayoutInflater inflater;
    private Context context;
    final AdapterDirecciones.OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(Direccion item);
    }

    public AdapterDirecciones(List<Direccion> itemList, Context context, AdapterDirecciones.OnItemClickListener listener){
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
        this.listener = listener;
    }

    @Override
    public int getItemCount(){return mData.size();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.from(parent.getContext()).inflate(R.layout.card_domicilio, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position){
        holder.bindData(mData.get(position));

    }


    public void setItems(List<Direccion> items){mData=items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        TextView codigopostal;
        TextView estado;
        TextView municipio;
        TextView colonia;
        TextView numeroexterior;
        TextView numerodetelefono;
        ImageButton btnEliminar;


        ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.Nombre);
            codigopostal = itemView.findViewById(R.id.codigoPostal);
            estado = itemView.findViewById(R.id.estado);
            municipio = itemView.findViewById(R.id.municipio);
            colonia = itemView.findViewById(R.id.calle);
            numeroexterior = itemView.findViewById(R.id.numero);
            numerodetelefono = itemView.findViewById(R.id.telefono);

            colonia.setSelected(true);
            nombre.setSelected(true);
            numerodetelefono.setSelected(true);
            numeroexterior.setSelected(true);
            btnEliminar = itemView.findViewById(R.id.eliminar);
        }

        void bindData(final Direccion item){
            nombre.setText(item.getNombreEncargado());
            codigopostal.setText(String.valueOf(item.getCodigoPostal()));
            estado.setText(item.getEstado());
            municipio.setText(item.getMunicipio());
            colonia.setText(item.getColonia());
            numeroexterior.setText(String.valueOf(item.getNumeroExterior()));
            numerodetelefono.setText(item.getNumeroTelefono());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(item);
                }
            });


            btnEliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    eliminarPedido(item);
                }
            });
            
        }
        public void eliminarPedido(Direccion item) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String id = user.getUid();
            FirebaseDatabase database = FirebaseDatabase.getInstance();//Conexion con firebase
            DatabaseReference myRef = database.getReference("Direccion");
            myRef.child(id).child(item.getCalle()+item.getNumeroExterior()).removeValue();
        }

    }

}
