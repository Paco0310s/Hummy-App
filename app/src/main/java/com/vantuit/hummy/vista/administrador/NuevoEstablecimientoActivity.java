package com.vantuit.hummy.vista.administrador;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.vista.RealidadVirtual.TimePickerFragment;
import com.vantuit.hummy.vista.cliente.InicioActivity;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

public class NuevoEstablecimientoActivity extends AppCompatActivity {

    Button guardar, cambiarlogo;
    EditText abierto, cerrado, nombre, descripcion, abiertosabado, cerradosabado, abiertodomingo, cerradodomingo;
    RadioButton si, no;
    LinearLayout horario_sabado, horario_domingo;
    TextView texto_lunes_viernes;
    ImageView imagenlogo;
    final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);
    Establecimiento e;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_establecimiento);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Nuevo Establecimiento");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        abierto = findViewById(R.id.es_abierto);
        cerrado = findViewById(R.id.es_cerrado);
        nombre = findViewById(R.id.es_nombre_establecimiento);
        descripcion = findViewById(R.id.es_descripcion_establecimiento);
        guardar = findViewById(R.id.bGuardarEst);
        cambiarlogo = findViewById(R.id.bCambiarLogo_est);
        si = findViewById(R.id.ra_si);
        no = findViewById(R.id.ra_no);
        horario_sabado = findViewById(R.id.horario_sabado);
        horario_domingo = findViewById(R.id.horario_domingo);
        texto_lunes_viernes = findViewById(R.id.lunes_viernes);
        abiertosabado = findViewById(R.id.es_abierto_sabado);
        cerradosabado = findViewById(R.id.es_cerrado_sabado);
        abiertodomingo = findViewById(R.id.es_abierto_domingo);
        cerradodomingo = findViewById(R.id.es_cerrado_domingo);
        imagenlogo = findViewById(R.id.imagelogo);

        e = new Establecimiento();
        e.setId(UUID.randomUUID().toString());

        abierto.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int ii) {
                    abierto.setText(String.valueOf(i)+":"+ String.valueOf(ii));
                }
        },hour,minute, false);
            timePickerDialog.show();
        });
        cerrado.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int ii) {
                    cerrado.setText(String.valueOf(i)+":"+ String.valueOf(ii));
                }
            },hour,minute, false);
            timePickerDialog.show();
        });
        abiertosabado.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int ii) {
                    abiertosabado.setText(String.valueOf(i)+":"+ String.valueOf(ii));
                }
            },hour,minute, false);
            timePickerDialog.show();
        });
        cerradosabado.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int ii) {
                    cerradosabado.setText(String.valueOf(i)+":"+ String.valueOf(ii));
                }
            },hour,minute, false);
            timePickerDialog.show();
        });
        abiertodomingo.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int ii) {
                    abiertodomingo.setText(String.valueOf(i)+":"+ String.valueOf(ii));
                }
            },hour,minute, false);
            timePickerDialog.show();
        });
        cerradodomingo.setOnClickListener(view -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){

                @Override
                public void onTimeSet(TimePicker timePicker, int i, int ii) {
                    cerradodomingo.setText(String.valueOf(i)+":"+ String.valueOf(ii));
                }
            },hour,minute, false);
            timePickerDialog.show();
        });

        si.setOnClickListener(view -> {
            horario_sabado.setVisibility(View.VISIBLE);
            horario_domingo.setVisibility(View.VISIBLE);
            texto_lunes_viernes.setText("Horario Lunes a Viernes");
        });
        no.setOnClickListener(view -> {
            horario_sabado.setVisibility(View.GONE);
            horario_domingo.setVisibility(View.GONE);
            texto_lunes_viernes.setText("Seleccione su horario de ventas");
            abiertosabado.setText("none");
            cerradosabado.setText("none");
            abiertodomingo.setText("none");
            cerradodomingo.setText("none");
        });

        cambiarlogo.setOnClickListener(view -> {
            final PopupMenu popupMenu = new PopupMenu(NuevoEstablecimientoActivity.this, cambiarlogo);
            popupMenu.getMenuInflater().inflate(R.menu.menu_foto, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.agregar_foto:
                        if(ActivityCompat.checkSelfPermission(NuevoEstablecimientoActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                == PackageManager.PERMISSION_GRANTED){
                            abrirGaleria();
                        }else{
                            ActivityCompat.requestPermissions(NuevoEstablecimientoActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                        return true;
                    case R.id.tomar_foto:
                        if(ActivityCompat.checkSelfPermission(NuevoEstablecimientoActivity.this, Manifest.permission.CAMERA)
                                == PackageManager.PERMISSION_GRANTED){
                            abrirCamara();
                        }else{
                            ActivityCompat.requestPermissions(NuevoEstablecimientoActivity.this, new String[]{Manifest.permission.CAMERA}, 2);
                        }
                        return true;
                    default:
                        return false;
                }
            });
            popupMenu.show();
        });

        guardar.setOnClickListener(view -> {
            if (nombre.length() == 0){
                Toast.makeText(this, "No escribiste el nombre", Toast.LENGTH_SHORT).show();
                nombre.setFocusable(View.FOCUSABLE);
            }else if(descripcion.length() == 0){
                Toast.makeText(this, "No escribiste la descripción", Toast.LENGTH_SHORT).show();
                descripcion.setFocusable(View.FOCUSABLE);
            }else if(abierto.length() == 0){
                Toast.makeText(this, "No escribiste el horario de abierto", Toast.LENGTH_SHORT).show();
                abierto.setFocusable(View.FOCUSABLE);
            }else if(cerrado.length() == 0){
                Toast.makeText(this, "No escribiste el horario de cerrado", Toast.LENGTH_SHORT).show();
                cerrado.setFocusable(View.FOCUSABLE);
            }else if(abiertosabado.length() == 0){
                Toast.makeText(this, "No escribiste el horario de abierto sabado", Toast.LENGTH_SHORT).show();
                abiertosabado.setFocusable(View.FOCUSABLE);
            }else if(cerradosabado.length() == 0){
                Toast.makeText(this, "No escribiste el horario de cerrado sabado", Toast.LENGTH_SHORT).show();
                cerradosabado.setFocusable(View.FOCUSABLE);
            }else if(abiertodomingo.length() == 0){
                Toast.makeText(this, "No escribiste el horario de abierto domingo", Toast.LENGTH_SHORT).show();
                abiertodomingo.setFocusable(View.FOCUSABLE);
            }else if(cerradodomingo.length() == 0){
                Toast.makeText(this, "No escribiste el horario de cerrado domingo", Toast.LENGTH_SHORT).show();
                cerradodomingo.setFocusable(View.FOCUSABLE);
            } else {
                guardaEstablecimiento();
            }

        });


    }

    public void guardaEstablecimiento()//Guarda el establecimiento en Firebase
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Establecimientos");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        e.setNombre(nombre.getText().toString());
        e.setDescripcion(descripcion.getText().toString());
        e.setHora_abierto(abierto.getText().toString());
        e.setHora_cerrado(cerrado.getText().toString());
        e.setCalificacion("");
        e.setId_usuario(id);

        myRef.child(e.getId()).setValue(e);

        Toast.makeText(this, "Datos Guardados con éxito", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), ListaEstablecimientosActivity.class);
        startActivity(i);
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
                imagenlogo.setImageBitmap(bitmap);
                subirImagen(getImageUri(this,bitmap));
            }
        }
        if (requestCode == 1){
            if (resultCode == Activity.RESULT_OK){
                assert data != null;
                Uri path = data.getData();
                imagenlogo.setImageURI(path);
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
        StorageReference Folder = FirebaseStorage.getInstance().getReference().child("Images");

        final StorageReference file_name = Folder.child("IMG-" + e.getId());

        file_name.putFile(FileUri).addOnSuccessListener(taskSnapshot -> file_name.getDownloadUrl().addOnSuccessListener(uri -> {
            e.setFoto(String.valueOf(uri));
            Toast.makeText(this, "Se ha subido la imagen", Toast.LENGTH_SHORT).show();
        }));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}