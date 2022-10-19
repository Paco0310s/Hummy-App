package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Establecimiento;
import com.vantuit.hummy.vista.cliente.adapters.AdapterEstablecimientos;

import java.util.ArrayList;
import java.util.List;

public class EstablecimientosFragment extends Fragment {

    View root;
    List<Establecimiento> establecimientos;
    AdapterEstablecimientos Adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_establecimientos, container, false);

        init();

        return root;
    }

    public void init() {
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
                
        adapter();

    }
    public void adapter()
    {
        Adapter = new AdapterEstablecimientos(establecimientos, getActivity().getApplicationContext(),new AdapterEstablecimientos.OnItemClickListener() {
            @Override
            public void onItemClick(Establecimiento item) {
                establecimientoIndividual(item);
            }
        });
        recyclerView = root.findViewById(R.id.listaEstablecimientos);
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