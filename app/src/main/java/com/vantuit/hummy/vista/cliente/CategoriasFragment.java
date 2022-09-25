package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Categoria;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCategorias;

import java.util.ArrayList;
import java.util.List;

public class CategoriasFragment extends Fragment {

    View root;
    List<Categoria> categorias;
    AdapterCategorias Adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_categorias, container, false);

        init();

        return root;
    }

    public void init() {
        categorias = new ArrayList<>();
        categorias.add(new Categoria(R.drawable.logo));
        categorias.add(new Categoria(R.drawable.logo));
        categorias.add(new Categoria(R.drawable.logo));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterCategorias(categorias, getActivity().getApplicationContext() ,new AdapterCategorias.OnItemClickListener() {
            @Override
            public void onItemClick(Categoria item) {
                listaCategorias(item);
            }
        });
        recyclerView = root.findViewById(R.id.listaCategorias);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(Adapter);
    }

    public void listaCategorias (Categoria categoria){
        Intent intent = new Intent(getActivity().getApplicationContext(), ListaProductosActivity.class);
        startActivity(intent);
    }

}