package com.vantuit.hummy.vista.cliente;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vantuit.hummy.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = findViewById(R.id.toolbar);

        BottomNavigationView bottomNavigationview = findViewById(R.id.menuprincipal);
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationview, navController);;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder
                (R.id.fragmentPrincipal, R.id.fragmentUsuario, R.id.fragmentCarrito,R.id.fragmentConfiguracion).build();
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
    }
}