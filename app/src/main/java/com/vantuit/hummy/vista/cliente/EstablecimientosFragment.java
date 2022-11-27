package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    static String Buscador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_establecimientos, container, false);


        init();

        return root;
    }
    public static void setBuscador(String buscador)
    {
        Buscador = buscador;
    }
    public void nel()
    {
        try {

            Toast.makeText(getActivity(), Buscador+"s", Toast.LENGTH_SHORT).show();

        }catch (NullPointerException e)
        {

        }

    }



    public void init() {
        establecimientos = new ArrayList<>();

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
                adapter();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Falló", Toast.LENGTH_SHORT).show();
            }
        });

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

    public void establecimientoIndividual (Establecimiento e){
        Intent i = new Intent(getActivity().getApplicationContext(), EstablecimiestoIndividual.class);
        i.putExtra("establecimiento",e);
        startActivity(i);
    }

}