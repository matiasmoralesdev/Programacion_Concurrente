package PracticasFinal.Semaforos.LectorEscritor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Escritor implements Runnable {

    Libro libro;
    String nombre;

    public Escritor(Libro unLibro, String name) {
        libro = unLibro;
        nombre = name;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void run() {
        while (!(libro.finalizado())) {
            empezarEscribir(libro, this);
            escribir();
            terminarEscribir(libro,this);
        }
    }

    private void empezarEscribir(Libro unLibro, Escritor writer) {
        libro.empezarEscribir(writer);
    }
    
    private void terminarEscribir(Libro libro, Escritor writer){
        libro.terminarEscribir(writer);
    }

    private void escribir() {
        try {
            System.out.println("Escribiendo...");
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Escritor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
