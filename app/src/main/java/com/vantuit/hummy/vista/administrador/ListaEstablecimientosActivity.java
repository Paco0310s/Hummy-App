package com.vantuit.hummy.vista.administrador;


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
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
        establecimientos.add(new Establecimiento(R.drawable.santinos,"Santino's Pizza",
                "", "", "","","00001"));
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

}