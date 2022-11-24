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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establecimiento_producto);

        producto = (Producto) getIntent().getSerializableExtra("producto");

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

    private void guardar() {
        ArrayList<String> ingredientes = Constantes.INGREDIENTES;

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
        producto.setIngredientes(ingredientes);
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