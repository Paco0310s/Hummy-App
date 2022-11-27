package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toolbar;

import com.google.android.material.badge.BadgeDrawable;
import com.vantuit.hummy.R;
import com.vantuit.hummy.databinding.ActivityMainBinding;
import com.vantuit.hummy.modelo.Carrito;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    BadgeDrawable badgeDrawable;
    Carrito numero = new Carrito();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //BottomNavigationView bottomNavigationview = findViewById(R.id.menuprincipal);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder
                (R.id.fragmentPrincipal, R.id.fragmentUsuario, R.id.fragmentCarrito,R.id.fragmentNotificaciones).build();
        NavHostFragment navHostFragment = (NavHostFragment)getSupportFragmentManager().findFragmentById(R.id.nav_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(binding.menuprincipal, navController);

        badgeDrawable = binding.menuprincipal.getOrCreateBadge(R.id.fragmentCarrito);
        badgeDrawable.setBackgroundColor(Color.WHITE);
        badgeDrawable.setBadgeTextColor(Color.BLUE);
        badgeDrawable.setNumber(numero.getCantidad());
        badgeDrawable.setMaxCharacterCount(5);
        badgeDrawable.setVisible(true);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding=null;
    }

    public void carritoMas(int numero){


    }

}