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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Categoria;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.cliente.adapters.AdapterCategorias;
import com.vantuit.hummy.vista.cliente.adapters.AdapterProducto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstablecimiestoIndividual extends AppCompatActivity {

    List<Categoria> categorias;
    AdapterCategorias AdapterCategorias;
    RecyclerView recyclerView;
    List<Producto> productos;
    ImageButton refresar;
    ImageView ivEstablecimiento;
    AdapterProducto Adapterproducto;
    Establecimiento establecimiento;
    TextView tvNombre, tvCalProducto;

    Map<String, Categoria> mapCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiesto_individual);

        establecimiento = (Establecimiento) getIntent().getSerializableExtra("establecimiento");
        mapCategorias = new HashMap<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Establecimiento");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ivEstablecimiento = findViewById(R.id.ivEstablecimiento);
        if(!establecimiento.getFoto().isEmpty()) Picasso.get().load(establecimiento.getFoto()).into(ivEstablecimiento);
        tvNombre = findViewById(R.id.es_nombre_establecimiento);
        tvNombre.setText(establecimiento.getNombre());
        tvCalProducto = findViewById(R.id.cal_producto);
        tvCalProducto.setText(establecimiento.getCalificacion());

        initCategorias();
        initProducto();

    }

    public void initCategorias() {
        categorias = new ArrayList<>();

        mapCategorias.put("Bebidas", new Categoria("Bebidas", R.drawable.bebidas));
        mapCategorias.put("Antojitos", new Categoria("Antojitos", R.drawable.antojitos));
        mapCategorias.put("Comida Rapida", new Categoria("Comida Rapida", R.drawable.comidarapida));
        mapCategorias.put("Desayunos", new Categoria("Desayunos", R.drawable.desayunos));
        mapCategorias.put("Ensaladas", new Categoria("Ensaladas", R.drawable.ensaladas));
        mapCategorias.put("Helados", new Categoria("Helados", R.drawable.helados));
        mapCategorias.put("Mariscos", new Categoria("Mariscos", R.drawable.mariscos));
        mapCategorias.put("Reposteria", new Categoria("Reposteria", R.drawable.reposteria));

        categorias.add(mapCategorias.get("Bebidas"));
        categorias.add(mapCategorias.get("Antojitos"));
        categorias.add(mapCategorias.get("Comida Rapida"));
        categorias.add(mapCategorias.get("Desayunos"));
        categorias.add(mapCategorias.get("Ensaladas"));
        categorias.add(mapCategorias.get("Helados"));
        categorias.add(mapCategorias.get("Mariscos"));
        categorias.add(mapCategorias.get("Reposteria"));

        //productos = new ArrayList<>();

        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Productos/" + establecimiento.getId());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productos.clear();

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Producto p = ds.getValue(Producto.class);

                    assert p != null;
                    categorias.add(mapCategorias.get(p.getCategoria()));
                }

                adapterCategorias();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(EstablecimiestoIndividual.this, "Falló", Toast.LENGTH_SHORT).show();
            }
        });*/

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
                adapterProducto();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(EstablecimiestoIndividual.this, "Falló", Toast.LENGTH_SHORT).show();
            }
        });
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

    public void individualProducto (Producto p){
        Intent i = new Intent(EstablecimiestoIndividual.this, ProductoInduvidual.class);
        i.putExtra("producto", p);
        startActivity(i);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}