package com.vantuit.hummy.controlador;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vantuit.hummy.config.ConexionBD;
import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.modelo.Producto;

public class PedidoDAO {

    FirebaseDatabase fbd;
    DatabaseReference dr;

    public PedidoDAO(String path) {
        try {
            ConexionBD con = new ConexionBD();
            fbd = con.getConexionFirebase();
            dr = fbd.getReference(path);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void insertar(Pedido p) {
        dr.child(p.getCliente().getID()).child(p.getFecha()).setValue(p);
    }

    public void modificar(Pedido p) {
        dr.child(p.getCliente().getID()).child(p.getFecha()).setValue(p);
    }

    public void eliminar(Pedido p) {
        dr.child(p.getCliente().getID()).child(p.getFecha()).removeValue();
    }
}
