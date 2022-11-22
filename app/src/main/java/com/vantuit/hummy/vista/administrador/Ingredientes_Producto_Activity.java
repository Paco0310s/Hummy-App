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

        etIngredientes[0] = findViewById(R.id.ingrediente1);
        etIngredientes[1] = findViewById(R.id.ingrediente2);
        etIngredientes[2] = findViewById(R.id.ingrediente3);
        etIngredientes[3] = findViewById(R.id.ingrediente4);
        etIngredientes[4] = findViewById(R.id.ingrediente5);
        etIngredientes[5] = findViewById(R.id.ingrediente6);
        etIngredientes[6] = findViewById(R.id.ingrediente7);
        etIngredientes[7] = findViewById(R.id.ingrediente8);
        etIngredientes[8] = findViewById(R.id.ingrediente9);
        etIngredientes[9] = findViewById(R.id.ingrediente10);

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
                ArrayList<String> listaIngredientes = new ArrayList<>();

                for (TextInputEditText etIngrediente : etIngredientes) {
                    String ingrediente = etIngrediente.getText().toString();
                    if (!ingrediente.isEmpty()) listaIngredientes.add(ingrediente);
                }

                Constantes.INGREDIENTES = listaIngredientes;
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