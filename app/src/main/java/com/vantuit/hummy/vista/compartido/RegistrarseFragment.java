package com.vantuit.hummy.vista.compartido;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.modelo.Constantes;
import com.vantuit.hummy.modelo.Usuario;
import com.vantuit.hummy.vista.cliente.InicioActivity;


public class RegistrarseFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Clientes");

    private FirebaseAuth auth;

    private View root;
    public TextView tvIniciarSesion;
    public EditText etNombre, etCorreo, etTelefono,etContra,etCContra;
    public CheckBox cbTerminos;
    public Button bInscribirse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_registrarse, container, false);
        auth = FirebaseAuth.getInstance();

        bInscribirse = root.findViewById(R.id.bInscribirse);
        etNombre = root.findViewById(R.id.etNombre);
        etCorreo = root.findViewById(R.id.etCorreo);
        etTelefono = root.findViewById(R.id.etContacto);
        etContra = root.findViewById(R.id.etContra);
        etCContra =  root.findViewById(R.id.etConfirmarContra);
        tvIniciarSesion = root.findViewById(R.id.tvIniciaSesion);
        cbTerminos =  root.findViewById(R.id.cbTerminos);
        bInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNombre.length() == 0) {
                    etNombre.setError("Ingrese su Nombre");
                    return;
                }
                if (etCorreo.length() == 0) {
                    etCorreo.setError("Ingrese su correo");
                    return;
                }
                if (etTelefono.length() == 0) {
                    etTelefono.setError("Ingrese su correo");
                    return;
                }
                if (etContra.length() == 0) {
                    etContra.setError("Ingrese su contraseña");
                    return;
                }
                if (etContra.length() <= 6) {
                    etContra.setError("La contraseña es muy corta");
                    return;
                }
                if (etCContra.length() == 0) {
                    etCContra.setError("Vuelva a escribir su contraseña");
                    return;
                }
                if (!etCContra.getText().toString().equals(etContra.getText().toString())) {
                    etCContra.setError("Las contraseñas no coinciden");
                    return;
                }
                if (!cbTerminos.isChecked()) {
                    Toast.makeText(getActivity(), "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*----------------Existe Usuario------------------*/

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Clientes");
                myRef.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String email = etCorreo.getText().toString().trim();
                        Boolean flag = false;
                        for(DataSnapshot ds: dataSnapshot.getChildren()) {
                            if(email.equals(ds.child("correo").getValue().toString()))
                            {
                                Toast.makeText(getActivity(), "Ya existe el usuario!\nInicie Sesion", Toast.LENGTH_SHORT).show();
                                flag = true;
                            }
                        }
                        if(!flag) {
                            creaUsuario();
                            return;
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(getActivity(), "Falló", Toast.LENGTH_SHORT).show();
                    }
                });
                /*----------Fin Exste Usuario-------------------*/
            }
        });
        return root;
    }
    public void Registra(FirebaseUser user)
    {
        String nombre, correo, telefono;
        nombre = etNombre.getText().toString();
        correo = etCorreo.getText().toString();
        telefono = etTelefono.getText().toString();
        try {
            String id = user.getUid();
            myRef.child(id).child("correo").setValue(correo);
            myRef.child(id).child("nombre").setValue(nombre);
            myRef.child(id).child("telefono").setValue(telefono);
            myRef.child(id).child("direccion").setValue("");
            myRef.child(id).child("tipoUsuario").setValue("Cliente");
            myRef.child(id).child("id").setValue(id);
        }catch (Exception e)
        {
            Toast.makeText(getActivity(), "Algo Salió Mal "+user, Toast.LENGTH_SHORT).show();
        }

    }

    public void creaUsuario() {
        //Crear usuario en Firebase
        String email = etCorreo.getText().toString().trim();
        String password = etContra.getText().toString().trim();

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                    Toast.makeText(getActivity(), "Usuario Creado con exito", Toast.LENGTH_SHORT).show();
                    Registra(auth.getCurrentUser());
                    Intent intent = new Intent(getActivity(), InicioActivity.class);
                    startActivity(intent);
            }

        });
    }


    public boolean existeUsuario() //Verifica si el usuario no esiste antes de loguearlo
    {
        return false;
    }
}