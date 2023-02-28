package com.vantuit.hummy.vista.compartido;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vantuit.hummy.R;


public class IniciarSesionFragment extends Fragment {

    private View root;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);

        return root;
    }//Fin del oncreate



}