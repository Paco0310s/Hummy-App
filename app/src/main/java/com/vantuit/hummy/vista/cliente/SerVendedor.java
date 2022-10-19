package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import com.vantuit.hummy.R;

public class SerVendedor extends AppCompatActivity {

    Button ser_vendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ser_vendedor);

        ser_vendedor = findViewById(R.id.ser_vendedor);
        ser_vendedor.setOnClickListener(view -> {
            createSimpleDialog();
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ser Vendedor");
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

    public AlertDialog createSimpleDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SerVendedor.this);
        builder.setTitle("Mucho exito proximo vendedor")
                .setMessage("Te enviaremos un correo pronto, gracias!")
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SerVendedor.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }).setIcon(R.drawable.logo);
        builder.create();

        return builder.show();
    }

}