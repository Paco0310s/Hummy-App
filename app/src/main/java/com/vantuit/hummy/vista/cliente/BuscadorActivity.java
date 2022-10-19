package com.vantuit.hummy.vista.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.compartido.ViewPageAdapter;

import java.util.Objects;

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
        buscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                EstablecimientosFragment ef = new EstablecimientosFragment();
                ef.setBuscador(buscador.getText().toString());
                ef.nel();
                //Toast.makeText(BuscadorActivity.this, buscador.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            // Metodos abstractos

        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new ViewPageAdapter(fragmentManager, getLifecycle());

        EstablecimientosFragment ef = new EstablecimientosFragment();

        //Añadir los fragmentos al adapter
        adapter.addFragment(ef);
        adapter.addFragment(new CategoriasFragment());

        viewPager2.setAdapter(adapter);

        TabLayout tableLayout =  findViewById(R.id.tablelayout_buscador);

        new TabLayoutMediator(tableLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0: tab.setIcon(R.drawable.ic_carrito);
                        tab.setText("Establecimientos");
                        //int selectedTabPosition = tableLayout.getSelectedTabPosition();
                        //TabLayout.Tab tabAt = tableLayout.getTabAt(selectedTabPosition);
                        //assert tabAt != null;
                        //View tabView = tabAt.getCustomView();
                        //Toast.makeText(BuscadorActivity.this, tabView.toString(), Toast.LENGTH_SHORT).show();

                        //int wantedTabIndex = 0;
                        //RecyclerView rv = (RecyclerView)(((LinearLayout)((LinearLayout)tableLayout.getChildAt(0)).getChildAt(wantedTabIndex)).getChildAt(1));
                        break;
                    case 1: tab.setIcon(R.drawable.ic_usuario);
                        tab.setText("Categorias");
                        break;
                }
            }
        }).attach();
    }
}