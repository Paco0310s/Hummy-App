package com.vantuit.hummy.vista.administrador;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.vantuit.hummy.R;
import com.vantuit.hummy.controlador.ProductoControlador;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.UUID;

public class EstablecimientoModificarProductoActivity extends AppCompatActivity {
    Producto producto;
    ImageView ivProducto;
    TextInputEditText etNombre, etDescripcion;
    Button bCambiarFoto, bGuardar;
    Spinner sCategorias, sDisponible;
    EditText etPrecio;
    TextInputLayout[] ingredientes = new TextInputLayout[9];
    TextInputEditText[] etIngredientes = new TextInputEditText[10];
    TextInputLayout ing_2, ing_3, ing_4, ing_5, ing_6, ing_7, ing_8, ing_9, ing_10;
    static int x;
    ImageButton b_ingrediente;
    ArrayList<String> lista_ingredientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_producto);

        producto = (Producto) getIntent().getSerializableExtra("producto");

        if(producto.getIngredientes() == null) {
            lista_ingredientes = new ArrayList<>();
            x = 0;
        } else {
            lista_ingredientes = producto.getIngredientes();
            x = lista_ingredientes.size() - 1;
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Modificar " + producto.getNombre());
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);


        ivProducto = findViewById(R.id.imagelProducto);
        bCambiarFoto = findViewById(R.id.cambiarFoto_producto);
        bGuardar = findViewById(R.id.bGuardarProducto);
        etNombre = findViewById(R.id.nombreProducto);
        etDescripcion = findViewById(R.id.descProducto);
        sCategorias = findViewById(R.id.spinnerCategorias);
        sDisponible = findViewById(R.id.spinner2);
        etPrecio = findViewById(R.id.t_Precio);

        bGuardar.setText("Modificar");

        if(!producto.getImagen().isEmpty()) Picasso.get().load(producto.getImagen()).into(ivProducto);

        etNombre.setText(producto.getNombre());
        etDescripcion.setText(producto.getDescripcion());
        etPrecio.setText(producto.getPrecio());

        setValoresSpinnerCategorias();
        setValoresSpinnerDisponible();
        setIngredientes();

        bCambiarFoto.setOnClickListener(v -> {
            final PopupMenu popupMenu = new PopupMenu(EstablecimientoModificarProductoActivity.this, bCambiarFoto);
            popupMenu.getMenuInflater().inflate(R.menu.menu_foto, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.agregar_foto:
                        if(ActivityCompat.checkSelfPermission(EstablecimientoModificarProductoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED){
                            abrirGaleria();
                        }else{
                            ActivityCompat.requestPermissions(EstablecimientoModificarProductoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                        return true;
                    case R.id.tomar_foto:
                        if(ActivityCompat.checkSelfPermission(EstablecimientoModificarProductoActivity.this, Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_GRANTED){
                            abrirCamara();
                        }else{
                            ActivityCompat.requestPermissions(EstablecimientoModificarProductoActivity.this, new String[]{Manifest.permission.CAMERA}, 2);
                        }
                        return true;
                    default:
                        return false;
                }
            });
            popupMenu.show();
        });



        bGuardar.setOnClickListener(v -> guardar());
    }

    private void setValoresSpinnerCategorias() {
        ArrayList<String> lista = new ArrayList<String>();

        lista.add("Bebidas");
        lista.add("Antojitos");
        lista.add("Comida Rapida");
        lista.add("Desayunos");
        lista.add("Ensaladas");
        lista.add("Helados");
        lista.add("Mariscos");
        lista.add("Reposteria");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategorias.setAdapter(adapter);

        for (int i = 0; i < lista.size(); i++) {
            if(producto.getCategoria().equals(lista.get(i))){
                sCategorias.setSelection(i);
            }
        }
    }

    private void setValoresSpinnerDisponible() {
        ArrayList<String> lista = new ArrayList<String>();

        lista.add("SI");
        lista.add("NO");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDisponible.setAdapter(adapter);

        for (int i = 0; i < lista.size(); i++) {
            if(producto.getDisponible().equals(lista.get(i))){
                sDisponible.setSelection(i);
            }
        }
    }

    private void setIngredientes() {
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
                    Toast.makeText(EstablecimientoModificarProductoActivity.this, "Sólo puedes agregar 10 ingredientes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //etIngredientes[0].setText(lista_ingredientes.get(0));

        for (int i = 0; i < lista_ingredientes.size(); i++) {
            etIngredientes[i].setText(lista_ingredientes.get(i));

            if(i < lista_ingredientes.size() - 1) {
                ingredientes[i].setVisibility(View.VISIBLE);
            }
        }
    }

    private void guardar() {
        lista_ingredientes.clear();

        for (TextInputEditText etIngrediente : etIngredientes) {
            String ingrediente = etIngrediente.getText().toString();
            if (!ingrediente.isEmpty()) lista_ingredientes.add(ingrediente);
        }

        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        String categoria = sCategorias.getSelectedItem().toString();
        String disponible = sDisponible.getSelectedItem().toString();
        String precio = etPrecio.getText().toString();

        if(nombre.isEmpty()) {
            etNombre.setError("Ingrese el nombre del producto");
            return;
        }

        if(descripcion.isEmpty()) {
            etDescripcion.setError("Ingrese la descripción del producto");
            return;
        }

        if(precio.isEmpty()) {
            etPrecio.setError("Ingrese el precio del producto");
            return;
        }

        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setIngredientes(lista_ingredientes);
        producto.setCategoria(categoria);
        producto.setDisponible(disponible);

        ProductoControlador.modificar(this, producto);
        finish();
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void abrirCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 2);
        }
    }

    @SuppressLint("IntentReset")
    private void abrirGaleria(){
        @SuppressLint("IntentReset") Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Seleccione la Aplicación"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 2){
            if (resultCode == Activity.RESULT_OK){
                assert data != null;
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                ivProducto.setImageBitmap(bitmap);
                subirImagen(getImageUri(this,bitmap));
            }
        }
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                assert data != null;
                Uri path = data.getData();
                ivProducto.setImageURI(path);
                subirImagen(path);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    private void subirImagen(Uri FileUri){
        StorageReference Folder = FirebaseStorage.getInstance().getReference().child("Images/" + producto.getVendedor() + "/Productos");

        final StorageReference file_name = Folder.child("IMG-" + producto.getId());

        file_name.putFile(FileUri).addOnSuccessListener(taskSnapshot -> file_name.getDownloadUrl().addOnSuccessListener(uri -> {
            producto.setImagen(String.valueOf(uri));
            Toast.makeText(this, "Se ha subido la imagen", Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}