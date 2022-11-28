package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.vista.compartido.LoginActivity;

public class DatosUsuario extends AppCompatActivity {
    EditText etUsuario, etNombre, etApellidos, etCorreo, etCurp, etTelefono;
    Button btnGuardar,btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_usuario);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tus Datos");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        etUsuario = findViewById(R.id.etUsuario);
        etNombre = findViewById(R.id.etNombre);
        etApellidos = findViewById(R.id.etApellido);
        etCorreo =findViewById(R.id.etCorreo);
        etCurp = findViewById(R.id.etCurp);
        etTelefono = findViewById(R.id.etContacto);
        btnGuardar = findViewById(R.id.btnGuardar);
        String nombreCompleto = Constantes.USUARIO.getNOMBRE();
        etUsuario.setText(nombreCompleto);
        String[] parts = nombreCompleto.split(" ");
        String nombre = "", apellido="";

        if(parts.length == 2)
        {
            nombre = parts[0];
            apellido = parts[1];

        }
        if(parts.length == 3)
        {
            nombre = parts[0]+"";
            apellido = parts[1]+parts[2];
        }
        if(parts.length == 4)
        {
            nombre = parts[0]+parts[1];
            apellido = parts[2]+parts[3];
        }
        etNombre.setText(nombre);
        etApellidos.setText(apellido);
        etCorreo.setText(Constantes.USUARIO.getCORREO());
        etTelefono.setText(Constantes.USUARIO.getTELEFONO());

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DatosUsuario.this, "Datos Guardados con éxito", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}