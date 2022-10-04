package com.vantuit.hummy.vista.compartido;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.cliente.InicioActivity;

import java.util.Objects;

public class IniciarSesionFragment extends Fragment {
    private Button iniciarSesion;
    private View root;
    private FirebaseAuth auth;
    private ImageView google_img;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;
    private EditText etCorreo, etPassword;
    public TextView tvRegistrarse;

    // Constantes para iniciar sesión con Google
    private static final String TAG_GOOGLE = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_iniciar_sesion, container, false);
        iniciarSesion = root.findViewById(R.id.bIniciaSesion);
        etCorreo = root.findViewById(R.id.etCorreo);
        etPassword = root.findViewById(R.id.etPassword);
        google_img = root.findViewById(R.id.iniciarGoogle);
        tvRegistrarse = root.findViewById(R.id.tvInscribirse);



        // Creamos nuestro GoogleSignInOptions para poder obtener el cliente
        gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // Obtenemos el cliente de google
        gsc = GoogleSignIn.getClient(requireActivity(), gso);

        // Obtenemos la instancia de FirebaseAuth
        auth = FirebaseAuth.getInstance();

        google_img.setOnClickListener(view -> SignIn());

        // On Click del método del boton inicia Sesion, el cual inicia sesion con correo y contraseña
        iniciarSesion.setOnClickListener(view -> {
            // Obtiene el correo y contraseña de los Edit Text
            String email = etCorreo.getText().toString();
            String password = etPassword.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getActivity(),"Ingrese el correo",Toast.LENGTH_LONG).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getActivity(),"Ingrese la contraseña",Toast.LENGTH_LONG).show();
                return;
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity(), task -> {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    FirebaseUser user = auth.getCurrentUser();
                    iniciarSesion(user);
                } else
                    // Si algo sale mal muestra mensage de error
                    Toast.makeText(getActivity(), "El correo o contraseña no coinciden", Toast.LENGTH_SHORT).show();
            });
        });

        return root;
    }

    private void SignIn() {

        Intent intent  = gsc.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Si queremos iniciar con Google
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // Obtenemos la cuenta
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Y mandamos el token para autenticarse con google
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(requireActivity(), "Error al iniciar con Google", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Metodo que autenticará la cuenta de google
    private void firebaseAuthWithGoogle(String idToken) {
        // Obtenemos las credenciales de Googla
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        // E iniciamos sesión con esas credenciales
        auth.signInWithCredential(credential).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Si se pudo iniciar sesión
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            /*AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

                            Toast.makeText(requireActivity(), credential + "", Toast.LENGTH_SHORT).show();
                            assert user != null;
                            Toast.makeText(requireActivity(), user.getProviderId(), Toast.LENGTH_SHORT).show();*/

                            /*assert user != null;
                            if(user.isEmailVerified()) {
                                Toast.makeText(requireActivity(), "Ya está en uso", Toast.LENGTH_SHORT).show();
                            } else {

                            }*/

                            assert user != null;
                            iniciarSesion(user);
                        } else {
                            // Si no, mandamos un error
                            Toast.makeText(requireActivity(), "Error con las credenciales de Google", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
    }

    public void iniciarSesion(FirebaseUser user){
        String name;
        if(user.getDisplayName() == null) name = user.getEmail();
        else name = user.getDisplayName();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Clientes");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();

                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    if(id.equals(ds.child("id").getValue().toString()))
                    {
                        if(ds.child("tipoUsuario").getValue().toString().equals("Cliente"))
                        {
                            Intent i = new Intent(getActivity(), InicioActivity.class);
                            startActivity(i);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(getActivity(), "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }
}