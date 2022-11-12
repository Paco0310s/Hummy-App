package com.vantuit.hummy.vista.administrador;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.vantuit.hummy.R;

public class EstablecimientoProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_producto);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Agregar nuevo producto a Santino's Pizza");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button bIngredientes = findViewById(R.id.bIngredientes);
        bIngredientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Ingredientes_Producto_Activity.class);
                startActivity(i);
            }
        });

        Button bExtras = findViewById(R.id.bExtras);
        bExtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Extras_Producto_Activity.class);
                startActivity(i);
            }
        });

        Button bTamanos = findViewById(R.id.bTamaños);
        bTamanos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Tamanos_Producto_Activity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}