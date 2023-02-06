package comunicacion;

import java.io.Serializable;

public class Mensaje implements Serializable {

    private final String fecha;
    private final String nombre;
    private final int cantidad;

    public Mensaje(String fecha, String nombre, int cantidad) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "fecha='" + fecha + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
