package com.vantuit.hummy.vista.compartido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.administrador.ListaEstablecimientosActivity;
import com.vantuit.hummy.vista.cliente.InicioActivity;
import com.vantuit.hummy.vista.cliente.MainActivity;

public class RolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);

        Button bCliente = findViewById(R.id.bCliente);
        bCliente.setOnClickListener(v -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        Button bAdministrador = findViewById(R.id.bAdministrador);
        bAdministrador.setOnClickListener(v -> {
            Intent i = new Intent(this, ListaEstablecimientosActivity.class);
            startActivity(i);
        });
    }
}