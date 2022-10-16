package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Categoria;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCarrito;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCategorias;

import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends Fragment {

    View root;
    List<Producto> carrito;
    AdapterCarrito Adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_carrito, container, false);


        init();

        return root;
    }

    public void init() {
        carrito = new ArrayList<>();
        carrito.add(new Producto(R.drawable.logo, "Tacos",
                "Tacos el Paco","20","4.9"));
        carrito.add(new Producto(R.drawable.logo,"Tacos",
                "Tacos el Paco","20","4.9"));
        carrito.add(new Producto(R.drawable.logo,"Tacos",
                "Tacos el Paco","20","4.9"));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterCarrito(carrito, getActivity().getApplicationContext() ,new AdapterCarrito.OnItemClickListener() {
            @Override
            public void onItemClick(Producto item) {
                //listaCategorias(item);
            }
        });
        recyclerView = root.findViewById(R.id.lista_carrito);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(Adapter);
    }

    public void listaCategorias (Categoria categoria){
        Intent intent = new Intent(getActivity().getApplicationContext(), ListaProductosActivity.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}