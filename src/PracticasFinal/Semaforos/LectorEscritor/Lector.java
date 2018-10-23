package PracticasFinal.Semaforos.LectorEscritor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lector implements Runnable {

    private Libro libro;
    private String nombre;

    public Lector(Libro unLibro, String name) {
        this.libro = unLibro;
        this.nombre = name;
    }

    public String getNombre() {
        return this.nombre;
    }

    @Override
    public void run() {
        boolean terminaLectura = false;
        int contador = 0;
        while (!terminaLectura) {
            try {
                if (libro.hayEscrito()) {
                    empezarLeer(libro, this);
                    leer();
                    terminarLeer(libro, this);
                    if (contador == 100) {
                        terminaLectura = true;
                    }
                } else {
                    System.out.println("No hay nada escrito en el libro");
                    sleep(1000);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void empezarLeer(Libro unLibro, Lector reader) {
        unLibro.empezarLeer(reader);
    }

    private void leer() {
        try {
            System.out.println("Leyendo....");
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void terminarLeer(Libro unLibro, Lector reader) {
        unLibro.terminarLeer(reader);
    }

}
