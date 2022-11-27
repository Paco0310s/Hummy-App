package com.vantuit.hummy.vista.cliente;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.vista.administrador.adapters.AdapterAdminEstablecimientos;
import com.vantuit.hummy.vista.administrador.adapters.AdapterListaPedidos;

import java.util.ArrayList;
import java.util.List;

public class NotificacionesFragment extends Fragment {
    View root;
    List <Pedido> listaPedidos;
    AdapterListaPedidos Adapter;
    RecyclerView recyclerView;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_notificaciones, container, false);




        init();
        return root;
    }

    public void init() {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Pedidos");
        listaPedidos = new ArrayList<>();
        // lee de la base de datos
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listaPedidos.clear();

                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    //Lee de la base de datos todos los atributos de Establecimiento
                    Pedido pedido = ds.getValue(Pedido.class);
                    //Lo añade al arreglo para luego pasarlo al Adapter
                    listaPedidos.add(pedido);


                }
                adapter();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterListaPedidos(listaPedidos, getContext());
        recyclerView = root.findViewById(R.id.listaNotificaciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }


}