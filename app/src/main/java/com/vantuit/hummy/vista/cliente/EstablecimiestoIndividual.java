package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Categoria;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCategorias;
import com.vantuit.hummy.vista.cliente.adapters.AdapterProducto;

import java.util.ArrayList;
import java.util.List;

public class EstablecimiestoIndividual extends AppCompatActivity {

    List<Categoria> categorias;
    AdapterCategorias AdapterCategorias;
    RecyclerView recyclerView;
    List<Producto> productos;
    ImageButton refresar;
    AdapterProducto Adapterproducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiesto_individual);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Establecimiento");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        initCategorias();
        initProducto();

    }

    public void initCategorias() {
        categorias = new ArrayList<>();
        categorias.add(new Categoria(R.drawable.bebidas));
        categorias.add(new Categoria(R.drawable.antojitos));
        categorias.add(new Categoria(R.drawable.comidarapida));
        categorias.add(new Categoria(R.drawable.desayunos));
        categorias.add(new Categoria(R.drawable.ensaladas));
        categorias.add(new Categoria(R.drawable.helados));
        categorias.add(new Categoria(R.drawable.mariscos));
        categorias.add(new Categoria(R.drawable.reposteria));
        adapterCategorias();

    }
    public void adapterCategorias()
    {
        AdapterCategorias = new AdapterCategorias(categorias, this ,new AdapterCategorias.OnItemClickListener() {
            @Override
            public void onItemClick(Categoria item) {

            }
        },R.layout.card_categoria_establecimineto);
        recyclerView = findViewById(R.id.es_categoria);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(EstablecimiestoIndividual.this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(AdapterCategorias);
    }

    public void initProducto() {
        productos = new ArrayList<>();
        productos.add(new Producto(R.drawable.pizzapepperonijulio, "Pizza Pepperoni",
                "Julio Pizza","200","4.9"));
        productos.add(new Producto(R.drawable.pizzaquesojulio,"Pizza Queso",
                "Julio Pizza","230","4.9"));
        productos.add(new Producto(R.drawable.pizzaespecialjulio,"Pizza Especial",
                "Julio Pizza","270","4.9"));
        adapterProducto();

    }
    public void adapterProducto()
    {
        Adapterproducto = new AdapterProducto(productos, EstablecimiestoIndividual.this,new AdapterProducto.OnItemClickListener() {
            @Override
            public void onItemClick(Producto item) {
                individualProducto(item);
            }
        });
        recyclerView = findViewById(R.id.es_producto);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(Adapterproducto);
    }

    public void individualProducto (Producto categoria){
        Intent i = new Intent(EstablecimiestoIndividual.this, ProductoInduvidual.class);
        startActivity(i);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}