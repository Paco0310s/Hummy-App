package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.vantuit.hummy.R;

import java.util.Objects;

public class NuevaDireccionActivity extends AppCompatActivity {

    Button anadir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_direccion);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Nueva Dirección");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        anadir = findViewById(R.id.anadirDireccion);

        anadir.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), DireccionesActivity.class);
            startActivity(i);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}