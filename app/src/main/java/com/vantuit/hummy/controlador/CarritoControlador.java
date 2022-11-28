package com.vantuit.hummy.controlador;

import android.content.Context;
import android.widget.Toast;

import com.vantuit.hummy.modelo.Carrito;
import com.vantuit.hummy.modelo.Pedido;
import com.vantuit.hummy.modelo.Producto;

public class CarritoControlador {
    private static CarritoDAO dao = new CarritoDAO("Carrito");

    public static void insertar(Context context, Producto p) {
        try {
            dao.insertar(p);
            Toast.makeText(context, "Se agregó al carrito", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al agregar al carrito", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
