package PracticasFinal.Concurrencia;

public class Datos {

    private int dato = 0;

    public Datos(int nro) {
        dato = nro;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int valor) {
        dato = valor;
    }

    public boolean verificar(int valor) {
        return dato > valor;
    }

}
