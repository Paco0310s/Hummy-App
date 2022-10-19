package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Opinion;
import com.vantuit.hummy.vista.cliente.adapters.AdapterEstablecimientos;
import com.vantuit.hummy.vista.cliente.adapters.AdapterOpinion;

import java.util.ArrayList;
import java.util.List;

public class ListaOpiniones extends AppCompatActivity {

    Button anadir_opiniones;
    List<Opinion> opiniones;
    AdapterOpinion Adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_opiniones);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista Opiniones");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        anadir_opiniones = findViewById(R.id.anadir_opinion);

        anadir_opiniones.setOnClickListener(view -> {
            Intent i = new Intent(this, AgregarOpinionProducto.class);
            startActivity(i);
        });

        init();

    }

    public void init() {
        opiniones = new ArrayList<>();
        opiniones.add(new Opinion("Oscar Medina", "Muy buena la pizza", R.drawable.oscar));
        opiniones.add(new Opinion("Efrain Venegas", "Una cagada de pizza", R.drawable.efra));
        opiniones.add(new Opinion("Hector Villegas", "Esta mejor pizza famili", R.drawable.hector));
        opiniones.add(new Opinion("Ismael Cordoba", "Mucho mejor que el caldo de bagre", R.drawable.may));
        opiniones.add(new Opinion("Paco Sotelo", "Le robaron al repartidor mi pizza :(", R.drawable.paco));
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterOpinion(opiniones, ListaOpiniones.this,new AdapterOpinion.OnItemClickListener() {
            @Override
            public void onItemClick(Opinion item) {
                //listaCategorias(item);
            }
        });
        recyclerView = findViewById(R.id.lista_opiniones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                ListaOpiniones.this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }

    public void listaCategorias (Establecimiento categoria){
        //Intent intent = new Intent(getActivity().getApplicationContext(), ActivityEstablecimiento.class);
        //startActivity(intent);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}