package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.vantuit.hummy.R;
import com.vantuit.hummy.databinding.ActivityMainBinding;
import com.vantuit.hummy.modelo.Carrito;
import com.vantuit.hummy.modelo.Comunicacion;

public class ProductoInduvidual extends AppCompatActivity  {

    private Menu menu;
    CollapsingToolbarLayout collapsingToolbar;
    int numero;
    Carrito cantidad = new Carrito();
    MainActivity aumnetar = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_induvidual);
        final Toolbar mToolbar = findViewById(R.id.toolbar_producto);
        setSupportActionBar(mToolbar);

        FrameLayout includedLayout = findViewById(R.id.contenido);

        ImageButton ordenar = includedLayout.findViewById(R.id.ver_opiniones);

        ordenar.setOnClickListener(view -> {
            Intent i = new Intent(ProductoInduvidual.this, ListaOpiniones.class);
            startActivity(i);
        });

        collapsingToolbar = findViewById(R.id.collapsingToolBar);

        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.Toolbar_TitleText2);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.Toolbar_TitleText);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //aumnetar.badgeDrawable.setNumber(numero++);
                add(4);
                Snackbar.make(view, "Agregado al carrito", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar_producto);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_producto, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       if (id == R.id.action_info) {
           Toast.makeText(this, "Agregado al carrito", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    @SuppressLint("UnsafeOptInUsageError")
    public void add(int numero) {

        BadgeDrawable badgeDrawable= BadgeDrawable.create(ProductoInduvidual.this);
        badgeDrawable.setNumber(numero);
        badgeDrawable.setVisible(true);
        BadgeUtils.attachBadgeDrawable(badgeDrawable, findViewById(R.id.toolbar_producto), R.id.action_info);
    }

}
