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
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.administrador.Establecimiento_ListaProductos;
import com.vantuit.hummy.vista.cliente.adapters.AdapterEstablecimientos;
import com.vantuit.hummy.vista.cliente.adapters.AdapterProducto;

import java.util.ArrayList;
import java.util.List;

public class ListaProductosActivity extends AppCompatActivity {

    List<Producto> productos;
    ImageButton refresar;
    AdapterProducto Adapter;
    RecyclerView recyclerView;
    //Establecimiento establecimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_productos);

        //establecimiento = (Establecimiento) getIntent().getSerializableExtra("establecimiento");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista Productos");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        init();
    }

    public void init() {
        productos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Productos");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productos.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Producto p = ds.getValue(Producto.class);
                    productos.add(p);

                }
                adapter();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ListaProductosActivity.this, "Falló", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void adapter()
    {
        Adapter = new AdapterProducto(productos, ListaProductosActivity.this,new AdapterProducto.OnItemClickListener() {
            @Override
            public void onItemClick(Producto item) {
                individualProducto(item);
            }
        });
        recyclerView = findViewById(R.id.listaProductos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(Adapter);
    }

    public void individualProducto (Producto categoria){
        Intent i = new Intent(ListaProductosActivity.this, ProductoInduvidual.class);
        startActivity(i);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}