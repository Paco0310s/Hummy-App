package com.vantuit.hummy.config;

import com.google.firebase.database.FirebaseDatabase;

public class ConexionBD {

    public FirebaseDatabase getConexionFirebase() {
        return FirebaseDatabase.getInstance();
    }
}
