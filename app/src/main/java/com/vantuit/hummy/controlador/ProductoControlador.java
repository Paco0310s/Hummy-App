package com.vantuit.hummy.controlador;

import android.content.Context;
import android.widget.Toast;
import com.vantuit.hummy.modelo.Producto;

public class ProductoControlador {
    private static ProductoDAO dao = new ProductoDAO("Productos");


    public static void insertar(Context context, Producto p) {
        try {
            dao.insertar(p);
            Toast.makeText(context, "Producto agregado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al agregar el producto", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void eliminar(Context context, Producto p) {
        try {
            dao.eliminar(p);
            Toast.makeText(context, "Producto eliminado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al eliminar el producto", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void modificar(Context context, Producto p) {
        try {
            dao.modificar(p);
            Toast.makeText(context, "Producto modificado", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(context, "Error al modificar el producto", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
