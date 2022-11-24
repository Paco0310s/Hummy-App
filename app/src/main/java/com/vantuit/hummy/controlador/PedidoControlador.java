package com.vantuit.hummy.controlador;

import android.content.Context;
import android.widget.Toast;

import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.modelo.Producto;

public class PedidoControlador {
    private static PedidoDAO dao = new PedidoDAO("Pedidos");


    public static void insertar(Context context, Pedido p) {
        try {
            dao.insertar(p);
            Toast.makeText(context, "Se realizó el pedido", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error en el pedido", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void eliminar(Context context, Pedido p) {
        try {
            dao.eliminar(p);
            Toast.makeText(context, "Pedido eliminado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar el pedido", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void modificar(Context context, Pedido p) {
        try {
            dao.modificar(p);
            Toast.makeText(context, "Pedido modificado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al modificar el pedido", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
