package com.vantuit.hummy.vista.administrador;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Constantes;

import java.util.ArrayList;

public class Ingredientes_Producto_Activity extends AppCompatActivity {

    Button b_guardar;
    ImageButton b_ingrediente;
    TextInputLayout[] ingredientes = new TextInputLayout[9];
    TextInputEditText[] etIngredientes = new TextInputEditText[10];
    TextInputLayout ing_2, ing_3, ing_4, ing_5, ing_6, ing_7, ing_8, ing_9, ing_10;
    static int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredientes_producto);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Agregar Ingredientes");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

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