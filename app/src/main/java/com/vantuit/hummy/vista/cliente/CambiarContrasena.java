package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vantuit.hummy.R;

public class CambiarContrasena extends AppCompatActivity {

    Button btnCambiarcontra;
    EditText contraAntigua;
    EditText contraNueva;
    EditText contraRepetir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasena);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cambiar Contraseña");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        contraAntigua = findViewById(R.id.contraseña_antigua);
        contraNueva = findViewById(R.id.contraseña_nueva);
        contraRepetir = findViewById(R.id.contraseña_repetir);


        btnCambiarcontra = findViewById(R.id.btnGuardar);
        btnCambiarcontra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contraAntigua.length() == 0)contraAntigua.setError("Ingrese su contraseña antigua");
                if(contraNueva.length() == 0)contraNueva.setError("Ingrese su contraseña nueva");
                if(contraRepetir.length() == 0)contraRepetir.setError("Repita su nueva contraseña");
                Toast.makeText(CambiarContrasena.this, "Escriba correctamente su antigua contraseña", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}