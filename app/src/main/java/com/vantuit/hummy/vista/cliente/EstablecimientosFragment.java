package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_establecimientos, container, false);

        init();

        return root;
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


    }
    public void adapter()
    {
        Adapter = new AdapterEstablecimientos(establecimientos, getActivity().getApplicationContext());
        recyclerView = root.findViewById(R.id.listaEstablecimientos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(
                getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(Adapter);
    }

}