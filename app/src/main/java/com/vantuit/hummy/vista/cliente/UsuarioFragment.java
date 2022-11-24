package com.vantuit.hummy.vista.cliente;

import static com.vantuit.hummy.R.id.toolbar;
import static com.vantuit.hummy.R.id.us_cambiar_rol;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
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
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.vista.compartido.RolActivity;

import java.time.LocalDateTime;
import java.util.Calendar;


public class UsuarioFragment extends Fragment {

    View root;
    TextView nombre;
    String usuario;
    Button direcciones, datos, contactanos, cambiar_contraseña, ser_vendedor, aviso_privacidad, cambiar_rol;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_usuario, container, false);

        nombre = root.findViewById(R.id.ue_nombre_usuario);
        direcciones = root.findViewById(R.id.us_direcciones);
        direcciones.setOnClickListener(view ->{
            Intent i = new Intent(getActivity().getApplicationContext(), DireccionesActivity.class);
            startActivity(i);
        });
        datos = root.findViewById(R.id.us_datos);
        datos.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), DatosUsuario.class);
            startActivity(i);
        });
        contactanos = root.findViewById(R.id.us_contactanos);
        contactanos.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), Contactanos.class);
            startActivity(i);
        });
        cambiar_contraseña = root.findViewById(R.id.us_cambiar_contraseña);
        cambiar_contraseña.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), CambiarContrasena.class);
            startActivity(i);
        });
        ser_vendedor = root.findViewById(R.id.us_ser_vendedor);
        ser_vendedor.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), SerVendedor.class);
            startActivity(i);
        });
        aviso_privacidad = root.findViewById(R.id.us_aviso_privacidad);
        aviso_privacidad.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), AvisoPrivacidad.class);
            startActivity(i);
        });
        cambiar_rol = root.findViewById(R.id.us_cambiar_rol);
        cambiar_rol.setOnClickListener(view -> {
            Intent i = new Intent(getActivity().getApplicationContext(), RolActivity.class);
            startActivity(i);
        });

        String name = Constantes.USUARIO.getNOMBRE();
        String[] lista = name.split(" ");

        usuario = lista[0];

        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        System.out.println(hora + ":" + minutos + ":" + segundos);

        if ( hora >= 0 && hora <=12 ){
            setTitle("Buenos días, " + usuario);
        }
        if (hora>12 && hora<=20){
            setTitle("Buenas tardes, " + usuario);
        }
        if (hora > 20 && hora <= 24){
            setTitle("Buenas noches, " + usuario);
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

    public void notificacionesProductos(){
        // Get the layouts to use in the custom notification
        //RemoteViews notificationLayout = new RemoteViews(getActivity(), R.layout.notification_small);
        //RemoteViews notificationLayoutExpanded = new RemoteViews(getActivity(), R.layout.notification_large);

        // Apply the layouts to the notification
        Notification customNotification = new NotificationCompat.Builder(getActivity().getApplicationContext())
                .setSmallIcon(R.drawable.logo)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                //.setCustomContentView(notificationLayout)
                //.setCustomBigContentView(notificationLayoutExpanded)
                .build();
    }

}