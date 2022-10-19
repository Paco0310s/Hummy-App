package com.vantuit.hummy.vista.cliente;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Direccion;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.vista.cliente.adapters.AdapterDirecciones;

import java.util.ArrayList;
import java.util.List;

public class DireccionesActivity extends AppCompatActivity {
    List<Direccion> direcciones;
    AdapterDirecciones Adapter;
    RecyclerView recyclerView;
    Button bAgregarDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direcciones);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Lista Opiniones");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        bAgregarDireccion = findViewById(R.id.bAgregarDireccion);

        bAgregarDireccion.setOnClickListener(v -> {
            Intent i = new Intent(this, NuevaDireccionActivity.class);
            startActivity(i);
        });
        init();

    }

    public void init() {
        direcciones = new ArrayList<>();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Direccion/"+id);
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Direccion dir = ds.getValue(Direccion.class);
                    direcciones.add(dir);
                }
                adapter();
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
    public void adapter()
    {
        Adapter = new AdapterDirecciones(direcciones, this, new AdapterDirecciones.OnItemClickListener() {
            @Override
            public void onItemClick(Direccion item) {
                seleccionaDireccion(item);
            }
        });
        recyclerView = findViewById(R.id.listaDirecciones);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }
    public void seleccionaDireccion(Direccion item)//Guarda la direccion que seleccionó como la direccion predeterminada en Firebase
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        DatabaseReference myRefUsser = database.getReference("Clientes");
        myRefUsser.child(id).child("direccion").setValue(item.getCalle()+" #"+item.getNumeroExterior()+item.getNumeroInterior());


        //Con esto puedes pasar la info a otra clase o activity
        /*Intent intent = new Intent(this, DescriptionActivity.class);
        intent.putExtra("Direccion",item);
        startActivity(intent);*/
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}