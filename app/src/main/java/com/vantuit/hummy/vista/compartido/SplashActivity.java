package com.vantuit.hummy.vista.compartido;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.administrador.ListaPedidos;
import com.vantuit.hummy.vista.cliente.InicioActivity;
import com.vantuit.hummy.vista.cliente.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("Clientes");
            Query query = ref.orderByChild("id").equalTo(user.getUid());
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        for(DataSnapshot data : snapshot.getChildren())
                        {
                            if(data.child("tipoUsuario").getValue().toString().equals("Cliente"))abreActivity(MainActivity.class);
                            if(data.child("tipoUsuario").getValue().toString().equals("Establecimiento"))abreActivity(ListaPedidos.class);
                            if(data.child("tipoUsuario").getValue().toString().equals("Vendedor"))abreActivity(RolActivity.class);
                        }
                    } else
                    {
                        FirebaseAuth.getInstance().signOut();
                        abreActivity(LoginActivity.class);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }
        if (user == null)
            abreActivity(LoginActivity.class);
    }
    private void abreActivity(Class activity)
    {
        Intent intent = new Intent(SplashActivity.this, activity);
        startActivity(intent);
        finish();
    }

    public void splash()
    {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
            }
        };
        Timer timer = new Timer();


    }
}