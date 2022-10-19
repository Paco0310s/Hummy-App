package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.modelo.Producto;
import com.vantuit.hummy.modelo.Usuario;
import com.vantuit.hummy.vista.cliente.adapters.AdapterEstablecimientos;

import java.util.ArrayList;
import java.util.List;

public class PrincipalFragment extends Fragment {
    View root;
    ImageSlider imagen;
    ImageButton filtrar;
    Button buscar;
    LinearLayout llDireccion;
    List<Establecimiento> establecimientos;
    AdapterEstablecimientos Adapter;
    RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_principal, container, false);

        //Toolbar toolbar = root.findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        imagen = root.findViewById(R.id.image_slider);
        buscar = root.findViewById(R.id.pri_buscar);
        filtrar = root.findViewById(R.id.Filtrar);
        llDireccion = root.findViewById(R.id.llDireccion);

        ArrayList<SlideModel> images = new ArrayList<>();

        images.add(new SlideModel(R.drawable.promo, null));
        images.add(new SlideModel(R.drawable.promo2, null));

        imagen.startSliding(3000);
        imagen.setImageList(images, ScaleTypes.CENTER_CROP);
        
        filtrar.setOnClickListener(view -> {
            //Intent i = new Intent(getActivity().getApplicationContext(), ProductoInduvidual.class);
            //startActivity(i);
        });

        buscar.setOnClickListener(view -> {
            /*Fragment fragment = new FragmentBuscardor();
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.cambiar, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();*/
            Intent i = new Intent(getActivity().getApplicationContext(), BuscadorActivity.class);
            startActivity(i);
        });

        llDireccion.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), DireccionesActivity.class);
            startActivity(i);

        });

        View est = root.findViewById(R.id.fragment_establecimientos);
        init(est);
        return root;
    }

    public void init(View est) {

        establecimientos = new ArrayList<>();

        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas Ahogadas el Pepe",
                "Tortas ahogadas estilo sinaloa", "Abierto", "2.1"));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Pizza Gerardos",
                "Pizzería", "Abierto", "4.9"));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Café la esquina",
                "Sweets and Cofee", "Abierto", "4.0"));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Delmich",
                "Heladería", "Abierto", "3.7"));
        establecimientos.add(new Establecimiento(R.drawable.logo,"El Padrino",
                "Restaurant/Bar ", "Abierto", "4.5"));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Establecimientos");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        myRef.setValue(establecimientos);*/

        //Conexion con Firebase para insertar datos

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Establecimientos");

        // lee de la base de datos
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    //Lee de la base de datos todos los atributos de Establecimiento
                    Establecimiento e = ds.getValue(Establecimiento.class);
                    //Lo añade al arreglo para luego pasarlo al Adapter
                    establecimientos.add(e);
                }
                adapter(est);//Le envía la vista al adapter
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Falló", Toast.LENGTH_SHORT).show();
            }
        });
        guardaDatosTDA();
    }
    public void adapter(View est)
    {
        Adapter = new AdapterEstablecimientos(establecimientos, getActivity().getApplicationContext(),new AdapterEstablecimientos.OnItemClickListener() {
            @Override
            public void onItemClick(Establecimiento item) {
                establecimientoIndividual(item);
            }
        });
        recyclerView = est.findViewById(R.id.listaEstablecimientos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }
    
    public void guardaDatosTDA() //Guarda los datos del usuario en el TDA por si los necesitamos después
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Clientes");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();

                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    if(id.equals(ds.child("id").getValue().toString()))
                    {
                        Usuario usuario = new Usuario();
                        usuario.setCORREO(ds.child("correo").getValue().toString());
                        usuario.setDIRECCION(ds.child("nombre").getValue().toString());
                        usuario.setID(ds.child("id").getValue().toString());
                        usuario.setTELEFONO(ds.child("telefono").getValue().toString());
                        usuario.setTIPOUSUARIO(ds.child("tipoUsuario").getValue().toString());
                        Constantes.USUARIO = usuario;
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Falló", Toast.LENGTH_SHORT).show();
            }
        });
    }
}