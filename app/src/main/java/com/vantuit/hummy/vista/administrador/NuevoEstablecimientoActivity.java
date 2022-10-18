package com.vantuit.hummy.vista.administrador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.vantuit.hummy.R;

public class NuevoEstablecimientoActivity extends AppCompatActivity {

    Spinner spEstado;
    String[] valores = {"Abierto", "Cerrado"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_establecimiento);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Nuevo Establecimiento");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        spEstado = findViewById(R.id.sp_estado);
        spEstado.setAdapter(new ArrayAdapter<String>(this, R.layout.spinner_estado_establecimiento, valores));
        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}