package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.cliente.adapters.AdapterProducto;

import java.util.ArrayList;
import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {

    List<Producto> productos;
    ImageButton refresar;
    AdapterProducto Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        refresar = findViewById(R.id.btRegresar);
        refresar.setOnClickListener(view -> {
            onBackPressed();
        });

        init();
    }

    public void init() {
        productos = new ArrayList<>();
        productos.add(new Producto(R.drawable.logo, "Tacos",
                "Tacos el Paco","20","4.9"));
        productos.add(new Producto(R.drawable.logo,"Tacos",
                "Tacos el Paco","20","4.9"));
        productos.add(new Producto(R.drawable.logo,"Tacos",
                "Tacos el Paco","20","4.9"));
        productos.add(new Producto(R.drawable.logo,"Tacos",
                "Tacos el Paco","20","4.9"));
        productos.add(new Producto(R.drawable.logo,"Tacos",
                "Tacos el Paco","20","4.9"));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterProducto(productos, ListaProductosActivity.this);
        recyclerView = findViewById(R.id.listaProductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(Adapter);
    }

}