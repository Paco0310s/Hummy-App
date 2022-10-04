package com.vantuit.hummy.vista.compartido;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.modelo.Usuario;
import com.vantuit.hummy.vista.administrador.ListaEstablecimientosActivity;
import com.vantuit.hummy.vista.cliente.InicioActivity;

public class RolActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rol);

        Button bCliente = findViewById(R.id.bCliente);
        Button bAdministrador = findViewById(R.id.bAdministrador);
        Button bEstablecimiento = findViewById(R.id.bEstablecimiento);



        bCliente.setOnClickListener(v -> {
            Intent i = new Intent(this, InicioActivity.class);
            startActivity(i);
        });


        bAdministrador.setOnClickListener(v -> {
            Intent i = new Intent(this, ListaEstablecimientosActivity.class);
            startActivity(i);
        });
    }

}