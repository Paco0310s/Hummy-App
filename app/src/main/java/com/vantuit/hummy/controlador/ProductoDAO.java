package com.vantuit.hummy.controlador;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vantuit.hummy.config.ConexionBD;
import com.vantuit.hummy.modelo.Producto;

public class ProductoDAO {

    FirebaseDatabase fbd;
    DatabaseReference dr;

    public ProductoDAO(String path) {
        try {
            ConexionBD con = new ConexionBD();
            fbd = con.getConexionFirebase();
            dr = fbd.getReference(path);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void insertar(Producto p) {
        dr.child(p.getVendedor()).child(p.getId()).setValue(p);
    }

    public void modificar(Producto p) {
        dr.child(p.getVendedor()).child(p.getId()).setValue(p);
    }

    public void eliminar(Producto p) {
        dr.child(p.getVendedor()).child(p.getId()).removeValue();
    }
}
