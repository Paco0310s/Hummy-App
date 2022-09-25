package com.vantuit.hummy.vista.cliente;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.vantuit.hummy.R;
import java.util.ArrayList;

public class PrincipalFragment extends Fragment {
    View root;
    ImageSlider imagen;
    ImageButton filtrar;
    Button buscar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_principal, container, false);

        //Toolbar toolbar = root.findViewById(R.id.toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        imagen = root.findViewById(R.id.image_slider);
        buscar = root.findViewById(R.id.pri_buscar);

        ArrayList<SlideModel> images = new ArrayList<>();

        images.add(new SlideModel(R.drawable.promo, null));
        images.add(new SlideModel(R.drawable.promo2, null));

        imagen.startSliding(3000);
        imagen.setImageList(images, ScaleTypes.CENTER_CROP);

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

        return root;
    }
}