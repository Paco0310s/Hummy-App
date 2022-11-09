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
import com.vantuit.hummy.vista.administrador.adapters.AdapterAdminEstablecimientos;

import java.util.ArrayList;
import java.util.List;

public class ListaEstablecimientosActivity extends AppCompatActivity {

    List<Establecimiento> establecimientos;
    AdapterAdminEstablecimientos Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_establecimientos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista de Establecimientos");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        FloatingActionButton bNuevoEst = findViewById(R.id.bAgregarEstablecimiento);
        bNuevoEst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NuevoEstablecimientoActivity.class);
                startActivity(i);
            }
        });


        init();
    }

    public void init() {
        establecimientos = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Establecimientos");

        // lee de la base de datos
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    //Lee de la base de datos todos los atributos de Establecimiento
                    Establecimiento e = ds.getValue(Establecimiento.class);
                    //Lo añade al arreglo para luego pasarlo al Adapter
                    establecimientos.add(e);

                }
                adapter();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ListaEstablecimientosActivity.this, "Falló", Toast.LENGTH_SHORT).show();
            }
        });
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterAdminEstablecimientos(establecimientos, this);
        recyclerView = findViewById(R.id.listaEstablecimientos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }

    public void nuevoEstablecimiento(View v){

    }

}