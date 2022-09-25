package com.vantuit.hummy.vista.compartido;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.vantuit.hummy.R;

public class LoginActivity extends AppCompatActivity {
    ViewPageAdapter adapter;
    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewPager2 = findViewById(R.id.visualisacion);

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new ViewPageAdapter(fragmentManager, getLifecycle());

        //Añadir los fragmentos al adapter
        adapter.addFragment(new IniciarSesionFragment());
        adapter.addFragment(new RegistrarseFragment());

        viewPager2.setAdapter(adapter);

        TabLayout tableLayout =  findViewById(R.id.tablelayout);

        new TabLayoutMediator(tableLayout, viewPager2, (tab, position) -> {

            switch (position){
                case 0: tab.setText("INICIAR SESIÓN");
                    break;
                case 1: tab.setText("INSCRIBIRSE");
                    break;
            }
        }).attach();
    }
}