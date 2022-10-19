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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
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
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));
        establecimientos.add(new Establecimiento(R.drawable.logo,"Tortas",
                "Local establecido", "Abierto", "4.5","",""));

        adapter(est);
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

    public void establecimientoIndividual (Establecimiento categoria){
        Intent i = new Intent(getActivity().getApplicationContext(), EstablecimiestoIndividual.class);
        startActivity(i);
    }

}