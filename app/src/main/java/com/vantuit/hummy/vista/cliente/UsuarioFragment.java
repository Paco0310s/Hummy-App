package com.vantuit.hummy.vista.cliente;

import static com.vantuit.hummy.R.id.toolbar;

import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.vantuit.hummy.R;

import java.time.LocalDateTime;
import java.util.Calendar;


public class UsuarioFragment extends Fragment {

    View root;
    TextView nombre;
    String usuario;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_usuario, container, false);

        nombre = root.findViewById(R.id.ue_nombre_usuario);

        usuario = "May";

        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        System.out.println(hora + ":" + minutos + ":" + segundos);

        if ( hora >= 0 && hora <=12){
            setTitle("Buenas Dias, " + usuario);
        }
        if (hora>12 && hora<=20){
            setTitle("Buenas Tardes, " + usuario);
        }
        if (hora > 20 && hora <= 24){
            setTitle("Buenas Noches, " + usuario);
        }

        return root;
    }

    public void setTitle (CharSequence title){
        nombre.setText(title);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.purple_500));
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_producto, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:  {
                // navigate to settings screen
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}