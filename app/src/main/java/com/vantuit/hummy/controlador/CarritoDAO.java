package com.vantuit.hummy.controlador;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vantuit.hummy.config.ConexionBD;
import com.vantuit.hummy.modelo.Carrito;
import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.modelo.Producto;

public class CarritoDAO {

    FirebaseDatabase fbd;
    DatabaseReference dr;

    public CarritoDAO(String path) {
        try {
            ConexionBD con = new ConexionBD();
            fbd = con.getConexionFirebase();
            dr = fbd.getReference(path);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void insertar(Producto p) {
        //dr.child(c.getId()).setValue(c);
    }
}
