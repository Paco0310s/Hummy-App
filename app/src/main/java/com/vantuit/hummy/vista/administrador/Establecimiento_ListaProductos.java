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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.administrador.adapters.AdapterAdminEstablecimiento_Productos;
import com.vantuit.hummy.vista.administrador.adapters.AdapterAdminEstablecimientos;

import java.util.ArrayList;
import java.util.List;

public class Establecimiento_ListaProductos extends AppCompatActivity {

    List<Producto> productos;
    Establecimiento establecimiento;
    AdapterAdminEstablecimiento_Productos Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_lista_productos);

        establecimiento = (Establecimiento) getIntent().getSerializableExtra("establecimiento");

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Productos de " + establecimiento.getNombre());
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
                i.putExtra("establecimiento", establecimiento);
                startActivity(i);
            }
        });

        init();
    }

    public void init() {
        productos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Productos/" + establecimiento.getId());

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
                Toast.makeText(Establecimiento_ListaProductos.this, "Falló", Toast.LENGTH_SHORT).show();
            }
        });
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