package com.vantuit.hummy.vista.administrador;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.administrador.adapters.AdapterAdminEstablecimiento_Productos;
import com.vantuit.hummy.vista.administrador.adapters.AdapterAdminEstablecimientos;

import java.util.ArrayList;
import java.util.List;

public class Establecimiento_ListaProductos extends AppCompatActivity {

    List<Producto> productos;
    AdapterAdminEstablecimiento_Productos Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_lista_productos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de Productos de Santino's Pizza");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton bNuevoEst = findViewById(R.id.bAgregarProducto);
        bNuevoEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EstablecimientoProductoActivity.class);
                startActivity(i);
            }
        });

        init();
    }

    public void init() {
        productos = new ArrayList<>();
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizzade Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        productos.add(new Producto(R.drawable.pizza,"Pizza de Peperonni",
                "", "$200", "", "Pizza de Peperonni de tamaños chica, mediana y grande"));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterAdminEstablecimiento_Productos(productos, this);
        recyclerView = findViewById(R.id.listaProductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}