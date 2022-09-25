package com.vantuit.hummy.vista.compartido;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vantuit.hummy.R;


public class RegistrarseFragment extends Fragment {
    private View root;
    public TextView tvIniciarSesion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_registrarse, container, false);

        tvIniciarSesion = root.findViewById(R.id.tvIniciaSesion);
        return root;
    }
}