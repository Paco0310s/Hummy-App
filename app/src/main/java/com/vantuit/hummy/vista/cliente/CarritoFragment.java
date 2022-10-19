package com.vantuit.hummy.vista.cliente;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Carrito;
import com.vantuit.hummy.modelo.Categoria;
import com.vantuit.hummy.modelo.Comunicacion;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCarrito;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCategorias;

import java.util.ArrayList;
import java.util.List;

public class CarritoFragment extends Fragment implements Comunicacion {

    View root;
    List<Carrito> carrito;
    AdapterCarrito Adapter;
    RecyclerView recyclerView;
    TextView direccion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_carrito, container, false);

        direccion = root.findViewById(R.id.ca_direccion);

        direccion.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), DireccionesActivity.class);
            startActivity(intent);
        });

        init();

        return root;
    }

    public void init() {
        carrito = new ArrayList<>();
        carrito.add(new Carrito(R.drawable.logo, "Tacos",
                "Tacos el Paco","20",1));
        carrito.add(new Carrito(R.drawable.logo,"Tacos",
                "Tacos el Paco","20",1));
        carrito.add(new Carrito(R.drawable.logo,"Tacos",
                "Tacos el Paco","20",1));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterCarrito(carrito, getActivity().getApplicationContext() ,new AdapterCarrito.OnItemClickListener() {
            @Override
            public void onItemClick(Carrito item) {
                productoIndividual(item);
            }
        }, this);
        recyclerView = root.findViewById(R.id.lista_carrito);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        recyclerView.setAdapter(Adapter);
    }

    public void productoIndividual (Carrito carrito){
        Intent intent = new Intent(getActivity().getApplicationContext(), ProductoInduvidual.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("UnsafeOptInUsageError")
    @Override
    public void add(Carrito ad) {
        BadgeDrawable badgeDrawable= BadgeDrawable.create(this.getContext());
        badgeDrawable.setNumber(carrito.size());
        BadgeUtils.attachBadgeDrawable(badgeDrawable,getActivity().findViewById(R.id.menuprincipal),R.id.fragmentCarrito);
    }
}