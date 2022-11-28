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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.vantuit.hummy.R;
import com.vantuit.hummy.controlador.CarritoControlador;
import com.vantuit.hummy.controlador.PedidoControlador;
import com.vantuit.hummy.databinding.ActivityMainBinding;
import com.vantuit.hummy.modelo.Carrito;
import com.vantuit.hummy.modelo.Comunicacion;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.vista.RealidadVirtual.RA;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ProductoInduvidual extends AppCompatActivity  {

    private Menu menu;
    CollapsingToolbarLayout collapsingToolbar;
    int numero = 1;
    Carrito cantidad = new Carrito();
    MainActivity aumnetar = new MainActivity();
    Producto producto;
    Establecimiento establecimiento;
    ImageView ivImagen;
    TextView tvPrecio, tvCal, tvDescripcion;
    ImageButton bMenos, bMas;
    EditText etNumero;
    Button bOrdernar;
    CheckBox[] cbIngre = new CheckBox[10];
    FloatingActionButton fab;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_induvidual);

        producto = (Producto) getIntent().getSerializableExtra("producto");
        establecimiento = (Establecimiento) getIntent().getSerializableExtra("establecimiento");

        //Toast.makeText(ProductoInduvidual.this, producto.getVendedor(), Toast.LENGTH_SHORT).show();
        final Toolbar mToolbar = findViewById(R.id.toolbar_producto);
        mToolbar.setTitle(producto.getNombre());
        setSupportActionBar(mToolbar);

        FrameLayout includedLayout = findViewById(R.id.contenido);

        ImageButton ordenar = includedLayout.findViewById(R.id.ver_opiniones);
        ImageButton realidadvirtual = includedLayout.findViewById(R.id.ver_RA);
        ivImagen = findViewById(R.id.ivImagen);
        if(!producto.getImagen().isEmpty()) Picasso.get().load(producto.getImagen()).into(ivImagen);

        tvPrecio = includedLayout.findViewById(R.id.tvPrecio);
        tvCal = includedLayout.findViewById(R.id.cal_producto);
        tvDescripcion = includedLayout.findViewById(R.id.tvDescripción);
        etNumero = includedLayout.findViewById(R.id.etNumero);
        bMas = includedLayout.findViewById(R.id.bMas);
        bMenos = includedLayout.findViewById(R.id.bMenos);
        bOrdernar = includedLayout.findViewById(R.id.ordenar);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(v -> {
            //CarritoControlador.insertar();
        });

        //Se crea el vector para los id´s
        int ckbDigits[]={R.id.cbIngrediente1,R.id.cbIngrediente2,R.id.cbIngrediente3,R.id.cbIngrediente4,R.id.cbIngrediente5,
                R.id.cbIngrediente6,R.id.cbIngrediente7,R.id.cbIngrediente8,R.id.cbIngrediente9,R.id.cbIngrediente10};
        //Enlaza el arreglo de checkbox con cada uno de los del xml
        for (int k =0; k <= 9; k++){
            cbIngre[k] = includedLayout.findViewById(ckbDigits[k]);
        }
        muestraIngredientes();

        tvPrecio.setText("$" + producto.getPrecio());
        tvCal.setText(producto.getCalificacion());
        tvDescripcion.setText(producto.getDescripcion());

        bMas.setOnClickListener(v -> {
            if(numero<99) numero++;
            etNumero.setText(String.valueOf(numero));
        });

        bMenos.setOnClickListener(v -> {
            if(numero>1) numero--;
            etNumero.setText(String.valueOf(numero));
        });

        bOrdernar.setOnClickListener(v -> {
            ordernar();
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        ordenar.setOnClickListener(view -> {
            Intent i = new Intent(ProductoInduvidual.this, ListaOpiniones.class);
            startActivity(i);
        });

        realidadvirtual.setOnClickListener(view -> {
            Intent i = new Intent(ProductoInduvidual.this, RA.class);
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

    private void ordernar() {
        String fecha = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss", Locale.getDefault()).format(new Date());

        Pedido p = new Pedido();
        p.setCliente(Constantes.USUARIO);
        p.setEstablecimiento(establecimiento);
        p.setProducto(producto);
        p.setFecha(fecha);
        p.setCantidad(numero);
        p.setMonto(Float.parseFloat(producto.getPrecio()));
        p.setEstado("Pendiente");
        p.setIngredientes(ingredientesSeleccionados());


        if(p.getCliente().getDIRECCION() == null || p.getCliente().getDIRECCION().isEmpty()) {
            Intent i = new Intent(ProductoInduvidual.this, DireccionesActivity.class);
            startActivity(i);
        } else {
            PedidoControlador.insertar(this, p);
        }
    }
    public ArrayList<String> ingredientesSeleccionados()
    {
        ArrayList<String> ingredientes = new ArrayList<>();
        for (int x = 0; x< cbIngre.length; x++)
            if(cbIngre[x].isChecked()) ingredientes.add(cbIngre[x].getText().toString());

        return ingredientes;
    }
    public void muestraIngredientes()
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Productos/"+producto.getVendedor()+"/"+producto.getId()+"/ingredientes");
        //fToast.makeText(ProductoInduvidual.this,"Productos/"+producto.getVendedor()+"/"+producto.getId() , Toast.LENGTH_SHORT).show();
        myRef.addValueEventListener(new ValueEventListener() {
            ArrayList <String> arrayList = new ArrayList<>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    arrayList.add(ds.getValue().toString());
                }
                for (int x = 0; x < arrayList.size(); x++)
                {
                    cbIngre[x].setVisibility(View.VISIBLE);
                    cbIngre[x].setText(arrayList.get(x));
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

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
