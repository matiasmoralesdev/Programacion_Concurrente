package EjerciciosFinal.Ejercicio1;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transbordador implements Runnable {

    private final int CANTMAXIMA = 10;

    private Coche[] espacio;
    private int cantSubidos;

    private Lock lock = new ReentrantLock();
    private final Condition noEstaLleno = lock.newCondition();
    private final Condition estaLleno = lock.newCondition();

    Semaphore ladoIzquierdo = new Semaphore(1);
    Semaphore ladoDerecho = new Semaphore(1);
    Semaphore cargando = new Semaphore(0);

    public Transbordador() {
        this.espacio = new Coche[CANTMAXIMA];
        this.cantSubidos = 0;
    }

    public void subir(Coche auto) {
        try {
            lock.lock();
            while (cantSubidos >= CANTMAXIMA) {
                estaLleno.await();
            }
            espacio[cantSubidos] = auto;
            cantSubidos++;

            if (cantSubidos == CANTMAXIMA) {
                noEstaLleno.signal();
            }
            lock.unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ir() {
        try {
            lock.lock();
            while (cantSubidos < CANTMAXIMA) {
                noEstaLleno.await();
            }
            System.out.println("Viajando------------->");
            sleep(1000);

            lock.unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void volver() {

    }

    @Override
    public void run() {
        ir();

        volver();
    }

}
