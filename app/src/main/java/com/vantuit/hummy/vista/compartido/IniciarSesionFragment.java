package com.vantuit.hummy.vista.compartido;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.vantuit.hummy.R;

public class IniciarSesionFragment extends Fragment {
    Button iniciaSesion;
    View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);

        iniciaSesion = root.findViewById(R.id.bIniciaSesion);
        iniciaSesion.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), RolActivity.class);
            startActivity(i);
        });

        return root;
    }
}