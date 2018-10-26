package EjerciciosFinal.Ejercicio1_1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coche implements Runnable {

    Transbordador transbordador;
    String id;
    Semaphore estaSubiendo = new Semaphore(1);

    public Coche(Transbordador t, String nombre) {
        this.transbordador = t;
        this.id = nombre;
    }

    public String getID() {
        return this.id;
    }

    @Override
    public void run() {
        transbordador.subir(this.id);
        transbordador.bajar(this.id);
    }

}
