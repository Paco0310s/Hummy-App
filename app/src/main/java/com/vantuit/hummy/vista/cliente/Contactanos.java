package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.compartido.RolActivity;

public class Contactanos extends AppCompatActivity {

    Button enviar_problema, enviar_mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactanos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Contactanos");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        enviar_problema = findViewById(R.id.co_enviar_problema);
        enviar_problema.setOnClickListener(view -> {
            Intent i = new Intent(Contactanos.this, MensajeReclamo.class);
            startActivity(i);
        });
        enviar_mensaje = findViewById(R.id.co_enviar_mensaje);
        enviar_mensaje.setOnClickListener(view -> {
            Intent i = new Intent(Contactanos.this, EnviarMensaje.class);
            startActivity(i);
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}