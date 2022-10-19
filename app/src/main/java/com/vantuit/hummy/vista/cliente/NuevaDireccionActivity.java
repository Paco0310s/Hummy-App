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
import android.widget.RadioButton;
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
    RadioButton rbVivienda, rbLugarTrabajo, rbEdificio;

    String Colonia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_direccion);
        autocompletaDireccion("47862");

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

        rbVivienda = findViewById(R.id.rbVivienda);
        rbEdificio = findViewById(R.id.rbEdificio);
        rbLugarTrabajo = findViewById(R.id.rbLugarTrabajo);

        spColonia = findViewById(R.id.spColonia);
        anadir = findViewById(R.id.btnAnadirDireccion);

        etNumeroTelefono.setText(Constantes.USUARIO.getTELEFONO());

        anadir.setOnClickListener(view -> {
            guardaDireccion();
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
                    autocompletaDireccion(etCodigoPostal.getText().toString());
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

    public void autocompletaDireccion(String CodPos) //Guarda los datos del usuario en el TDA por si los necesitamos después
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();//Conexion con firebase
        DatabaseReference myRef = database.getReference("codigos_postales");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> colonia = new ArrayList<>(); //Array list donde se almacenarán los datos del Spinner
                int i = 0; //Variable de contador de indice
                for(DataSnapshot ds: dataSnapshot.getChildren()) {//For each para buscar en Firebase
                    if(CodPos.equals(ds.child("codigo").getValue().toString()))//Si el CP que ingresó == al que está en firebase
                    {   //Lo imprime en los Edit Text
                        etEstado.setText(ds.child("estado").getValue().toString());
                        etMunicipio.setText(ds.child("municipio").getValue().toString());
                        colonia.add(ds.child("asenta").getValue().toString());//lo añade al ArrayList
                        i++;
                    }
                } //Adapter del Spinner
                if(colonia.size() == 0)
                {
                    Toast.makeText(NuevaDireccionActivity.this, "Ingrese un Codigo Postal válido", Toast.LENGTH_SHORT).show();
                    etEstado.setText("");
                    etMunicipio.setText("");
                    etEstado.setEnabled(true);
                    etMunicipio.setEnabled(true);
                }
                //Hago Visibles los campos
                ArrayAdapter adapter = new ArrayAdapter<String>(NuevaDireccionActivity.this.getApplicationContext(), R.layout.spinner_item, colonia);
                adapter.setDropDownViewResource(R.layout.spinner_item);
                spColonia.setAdapter(adapter);
                spColonia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        Colonia = colonia.get(position);
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
    public void guardaDireccion()//Guarda la dirección en Firebase
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Direccion");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        String calle = etCalle.getText().toString();
        String numero = etNumeroExterior.getText().toString();
        myRef.child(id).child(calle+numero).child("nombreEncargado").setValue(etNombreEncargado.getText().toString());
        myRef.child(id).child(calle+numero).child("codigoPostal").setValue(etCodigoPostal.getText().toString());
        myRef.child(id).child(calle+numero).child("estado").setValue(etEstado.getText().toString());
        myRef.child(id).child(calle+numero).child("municipio").setValue(etMunicipio.getText().toString());
        myRef.child(id).child(calle+numero).child("colonia").setValue(Colonia);
        myRef.child(id).child(calle+numero).child("calle").setValue(calle);
        myRef.child(id).child(calle+numero).child("numeroExterior").setValue(numero);
        myRef.child(id).child(calle+numero).child("numeroInterior").setValue(etNumeroInterior.getText().toString());
        myRef.child(id).child(calle+numero).child("entreCalle1").setValue(etEntreCalles1.getText().toString());
        myRef.child(id).child(calle+numero).child("entreCalle2").setValue(etEntreCalles2.getText().toString());
        if(rbLugarTrabajo.isChecked()) myRef.child(id).child(calle+numero).child("tipoDomicilio").setValue("Lugar de trabajo");
        if(rbEdificio.isChecked()) myRef.child(id).child(calle+numero).child("tipoDomicilio").setValue("Edificio");
        if(rbVivienda.isChecked()) myRef.child(id).child(calle+numero).child("tipoDomicilio").setValue("Vivienda");
        myRef.child(id).child(calle+numero).child("numeroTelefono").setValue(etNumeroTelefono.getText().toString());
        myRef.child(id).child(calle+numero).child("referencia").setValue(etRefDom.getText().toString());

        // Guardamos la direccion predeterminada en firebase

        DatabaseReference myRefUsser = database.getReference("Clientes");

        myRefUsser.child(id).child("direccion").setValue(calle+" #"+numero+etNumeroInterior.getText().toString());

        Toast.makeText(this, "Datos Guardados con éxito", Toast.LENGTH_SHORT).show();


        Intent i = new Intent(getApplicationContext(), InicioActivity.class);
        startActivity(i);
        finish();

    }
}