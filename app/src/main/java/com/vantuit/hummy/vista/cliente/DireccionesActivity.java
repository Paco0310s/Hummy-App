package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Direccion;
import com.vantuit.hummy.vista.cliente.adapters.AdapterDirecciones;

import java.util.ArrayList;
import java.util.List;

public class DireccionesActivity extends AppCompatActivity {
    List<Direccion> direcciones;
    AdapterDirecciones Adapter;
    RecyclerView recyclerView;
    Button bAgregarDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);

        bAgregarDireccion = findViewById(R.id.bAgregarDireccion);

        bAgregarDireccion.setOnClickListener(v -> {
            Intent i = new Intent(this, NuevaDireccionActivity.class);
            startActivity(i);
        });

        init();

    }

    public void init() {
        direcciones = new ArrayList<>();
        direcciones.add(new Direccion("Oscar Medina", 47899,"Jalisco", "Ocotlan",
                "Nuevo Fuerte", "Azulejo", 0, 80, "Emiliano Zapata", "Cantera",
                "Hogar", "3319549202", "A lado del poste" ));
        direcciones.add(new Direccion("Hector Garcia", 47899,"Jalisco", "Ocotlan",
                "Soli", "Azulejo", 0, 80, "Emiliano Zapata", "Cantera",
                "Hogar", "3319549202", "A lado del poste" ));
        direcciones.add(new Direccion("Francisco Sotelo", 47899,"Jalisco", "Ocotlan",
                "Emanuel", "Azulejo", 0, 80, "Emiliano Zapata", "Cantera",
                "Hogar", "3319549202", "A lado del poste" ));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterDirecciones(direcciones, getApplicationContext());
        recyclerView = findViewById(R.id.listaDirecciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }
}