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

import com.google.android.material.textfield.TextInputLayout;
import com.vantuit.hummy.R;

public class Ingredientes_Producto_Activity extends AppCompatActivity {

    Button b_guardar;
    ImageButton b_ingrediente;
    TextInputLayout[] ingredientes = new TextInputLayout[9];
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

        ing_2 = findViewById(R.id.ing_2);
        ing_3 = findViewById(R.id.ing_3);
        ing_4 = findViewById(R.id.ing_4);
        ing_5 = findViewById(R.id.ing_5);
        ing_6 = findViewById(R.id.ing_6);
        ing_7 = findViewById(R.id.ing_7);
        ing_8 = findViewById(R.id.ing_8);
        ing_9 = findViewById(R.id.ing_9);
        ing_10 = findViewById(R.id.ing_10);
        TextInputLayout[] ingredientes = {ing_2, ing_3, ing_4, ing_5, ing_6, ing_7, ing_8, ing_9, ing_10};

        b_ingrediente = findViewById(R.id.bNuevo_Ingrediente);
        b_ingrediente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ing_2.getVisibility() == View.GONE) x = 0;
                if (ing_10.getVisibility() == View.GONE){
                    ingredientes[x].setVisibility(View.VISIBLE);
                    x++;
                } else{
                    Toast.makeText(Ingredientes_Producto_Activity.this, "Sólo puedes agregar 10 ingredientes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_guardar = findViewById(R.id.bGuardar_Ingredientes);
        b_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Ingredientes_Producto_Activity.this, "Ingredientes agregados con éxito", Toast.LENGTH_SHORT).show();
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