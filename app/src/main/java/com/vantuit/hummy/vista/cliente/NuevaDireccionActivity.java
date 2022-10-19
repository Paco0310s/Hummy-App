package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;

public class NuevaDireccionActivity extends AppCompatActivity {

    Button anadir;
    EditText etNombreEncargado,etCodigoPostal, etEstado, etMunicipio, etCalle, etNumeroExterior, etNumeroInterior,etEntreCalles1, etEntreCalles2, etNumeroTelefono, etRefDom;
    Spinner  spColonia;
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

        etNombreEncargado = findViewById(R.id.etNombreEncargado);
        etCodigoPostal = findViewById(R.id.etCodigoPostal);
        etEstado = findViewById(R.id.etEstado);
        etMunicipio = findViewById(R.id.etMunicipio);
        etCalle = findViewById(R.id.etCalle);
        etNumeroExterior = findViewById(R.id.etNumeroExterior);
        etNumeroInterior = findViewById(R.id.etNumeroInterior);
        etEntreCalles1 = findViewById(R.id.etEntreCalles1);
        etEntreCalles2 = findViewById(R.id.etEntreCalles2);
        etNumeroTelefono = findViewById(R.id.etNumeroTelefono);
        etRefDom = findViewById(R.id.etRefDom);

        spColonia = findViewById(R.id.spColonia);


        anadir = findViewById(R.id.btnAnadirDireccion);


        anadir.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), DireccionesActivity.class);
            startActivity(i);
        });

        etCodigoPostal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(etCodigoPostal.length() == 5)
                {
                    etEstado.setEnabled(false);
                    etMunicipio.setEnabled(false);
                    autocompletaDireccion();
                }
                else etCodigoPostal.setError("Pon un codigo válido");

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }

            // Metodos abstractos

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    public void autocompletaDireccion() //Guarda los datos del usuario en el TDA por si los necesitamos después
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("codigos_postales");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> codigoPostal = new ArrayList<>();
                ArrayList<String> estado = new ArrayList<>();
                ArrayList<String> municipio = new ArrayList<>();
                ArrayList<String> colonia = new ArrayList<>();
                //String colonia[] = new String[6];
                int i = 0;
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    if(etCodigoPostal.getText().toString().equals(ds.child("codigo").getValue().toString()))
                    {
                        etEstado.setText(ds.child("estado").getValue().toString());
                        etMunicipio.setText(ds.child("municipio").getValue().toString());
                        colonia.add(ds.child("asenta").getValue().toString());
                        i++;

                    }
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(NuevaDireccionActivity.this.getApplicationContext(), R.layout.spinner_item, colonia);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spColonia.setAdapter(adapter);
                spColonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

    }
}