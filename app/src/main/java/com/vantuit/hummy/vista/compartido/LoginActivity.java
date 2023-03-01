package com.vantuit.hummy.vista.compartido;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vantuit.hummy.R;
import com.vantuit.hummy.vista.cliente.MainActivity;


public class LoginActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 1;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;



    SignInButton mSignInButtonGoogle;
    TextView mTextViewRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mSignInButtonGoogle = findViewById(R.id.btnGoogle);
        mTextViewRespuesta = findViewById(R.id.textViewRespuesta);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mSignInButtonGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Revisa si el usuario esta logueado (!= null) y actualiza el UI
        FirebaseUser User = mAuth.getCurrentUser();
       // updateUI(User);
    }

    private void signIn() { //Intenta loguearse con google
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Resultado retornado del lanzamiento del intent
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Error al iniciar sesion"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(LoginActivity.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {//Revisa si el usuario esta logueado (!= null)
        user = mAuth.getCurrentUser();
        if(user != null)userExists(user);
    }

    private void irMainActivity() { //pos va a main activity xD
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void userExists(FirebaseUser user)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();
        Query query = ref.child("Clientes").orderByChild("id").equalTo(user.getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    for(DataSnapshot data : snapshot.getChildren())
                    {
                        String key = data.getKey();
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference("Clientes").child(key);
                        Toast.makeText(LoginActivity.this, "Bienvenido "+data.child("nombre").getValue().toString(), Toast.LENGTH_LONG).show();
                        irMainActivity();
                    }
                 else
                    creaUsuario();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void creaUsuario()
    {
        Toast.makeText(this, "Primero necesitamos algunos de sus datos", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        RegistrarseFragment fragment = new RegistrarseFragment();
        fragmentTransaction.add(R.id.LoginActivity, fragment);
        fragmentTransaction.commit();
    }


}