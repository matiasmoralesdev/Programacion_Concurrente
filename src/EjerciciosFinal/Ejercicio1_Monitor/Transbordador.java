package EjerciciosFinal.Ejercicio1_Monitor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transbordador {

    private final int CANTMAXIMA = 10;

    private String[] espacio;
    private int cantSubidos;
    private boolean cargando;
    private boolean descargando;
    private boolean viajar;

    public Transbordador() {
        this.espacio = new String[CANTMAXIMA];
        this.cantSubidos = 0;
        cargando = true;
        descargando = false;
        viajar = false;
    }

    public synchronized void subir(String auto) {
        try {
            //cargando.acquire(); //Solo puede subir si el Transbordador no esta vacio, sino, espera
            while (descargando || cantSubidos == 10) {
                wait();
            }
            // mutex.acquire(); //Adquiere la exclusion mutua (solo puede subir un coche a la vez
            System.out.println(" ↑↑↑ El coche  " + auto + " esta subiendo ↑↑↑");
            sleep(1000);
            //espacio[cantSubidos] = auto;
            cantSubidos++;
            if (cantSubidos == 10) {
                viajar = true;
                
            }
            notifyAll(); //despierta a los demas hilos para notificar que subio en un barco
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void ir() {
        try {
            while (!viajar) {
                wait();
            }
            viajando(true);
            cargando = false;
            descargando = true;
            viajar = false;
            sleep(1000);
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void bajar(String auto) {
        try {
            while (cargando || cantSubidos == 0) {
                wait();
            }
            System.out.println(" ↓↓↓ El coche  " + auto + " esta bajando ↓↓↓");
            sleep(1000);
            cantSubidos--;
            if (cantSubidos == 0) {
                viajar = true;
            }
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void volver() {
        try {
            while (!viajar) {
                wait();
            }
            viajando(false);
            cargando = true;
            descargando = false;
            viajar = false;
            sleep(1000);
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void viajando(boolean ir) {
        try {
            String s;
            if (ir) {
                s = "\u001B[32m►";
            } else {
                s = "\u001B[31m◄";
            }
            int t = 250;
            for (int i = 0; i < 20; i++) {
                System.out.print(s);
                sleep(t);
            }
            System.out.println("");
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
