package com.vantuit.hummy.vista.compartido;

import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.cliente.InicioActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Intent intent;
        //Comprueba si ya inició Sesión
        //GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        //if (acct != null) intent = new Intent(SplashActivity.this, RolActivity.class);
        //else intent = new Intent(SplashActivity.this, LoginActivity.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Clientes");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Boolean Flag = false;
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String id = user.getUid();
                    for(DataSnapshot ds: dataSnapshot.getChildren()) {
                        if(id.equals(ds.child("id").getValue().toString()))
                        {
                            if(ds.child("tipoUsuario").getValue().toString().equals("Cliente"))
                            {
                                Intent i = new Intent(SplashActivity.this, InicioActivity.class);
                                startActivity(i);
                                finish();
                                Flag = true;
                            }
                        }
                    }
                    if(!Flag)
                    {
                        Intent i = new Intent(SplashActivity.this, RolActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Toast.makeText(SplashActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                }
            });
        }
        if (user == null)
        {
            intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
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