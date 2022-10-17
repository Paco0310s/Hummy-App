package com.vantuit.hummy.vista.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.compartido.ViewPageAdapter;

public class BuscadorActivity extends AppCompatActivity {
    EditText buscador;
    ImageButton refresar;
    ViewPageAdapter adapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_buscador);

        buscador = findViewById(R.id.etBuscador);
        viewPager2 = findViewById(R.id.visualisacion_buscador);
        refresar = findViewById(R.id.btRegresar);
        refresar.setOnClickListener(view -> {
            onBackPressed();
        });

        buscador.requestFocus();

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new ViewPageAdapter(fragmentManager, getLifecycle());

        //Añadir los fragmentos al adapter
        adapter.addFragment(new EstablecimientosFragment());
        adapter.addFragment(new CategoriasFragment());

        viewPager2.setAdapter(adapter);

        TabLayout tableLayout =  findViewById(R.id.tablelayout_buscador);

        new TabLayoutMediator(tableLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0: tab.setIcon(R.drawable.ic_carrito);
                        tab.setText("Establecimientos");
                        break;
                    case 1: tab.setIcon(R.drawable.ic_usuario);
                        tab.setText("Categorias");
                        break;
                }
            }
        }).attach();

    }
}