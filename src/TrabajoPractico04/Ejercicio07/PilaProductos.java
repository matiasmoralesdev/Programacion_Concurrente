package TrabajoPractico04.Ejercicio07;

public class PilaProductos {

    int cantidad;

    public PilaProductos() {
        this.cantidad = 0;
    }

    public void apilar() {
        this.cantidad = this.cantidad + 1;
    }

    public int getApilados() {
        return this.cantidad;
    }

}
