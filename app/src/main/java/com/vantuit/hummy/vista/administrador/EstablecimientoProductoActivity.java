package com.vantuit.hummy.vista.administrador;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;

import com.vantuit.hummy.R;

public class EstablecimientoProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_producto);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Agregar nuevo producto a Santino's Pizza");
//        toolbar.setTitleTextColor(Color.WHITE);
//        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}