package EjerciciosFinal.Ejercicio1_1;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transbordador implements Runnable {

    private final int CANTMAXIMA = 10;

    private String[] espacio;
    private int cantSubidos;

    Semaphore ladoIzquierdo = new Semaphore(0);
    Semaphore ladoDerecho = new Semaphore(0);
    Semaphore cargando = new Semaphore(1);
    Semaphore mutex = new Semaphore(1);
    Semaphore lleno = new Semaphore(0);

    public Transbordador() {
        this.espacio = new String[CANTMAXIMA];
        this.cantSubidos = 0;
    }

    @Override
    public void run() {
        while (true) {
            ir();
            volver();
        }
    }

    public void subir(String auto) {
        try {

            cargando.acquire(); //Solo puede subir si el Transbordador no esta vacio, sino, espera

            mutex.acquire(); //Adquiere la exclusion mutua (solo puede subir un coche a la vez

            System.out.println(" ↑↑↑ El coche  " + auto + " esta subiendo ↑↑↑");
            sleep(1000);
            espacio[cantSubidos] = auto;
            cantSubidos++;
            //System.out.println("Hay "+cantSubidos+" autos subidos");

            if (cantSubidos == CANTMAXIMA) {
                ladoDerecho.release();
              //  System.out.println("ENTRO");
            } else {
                cargando.release(); //Si el Transbordador esta lleno no pueden subir mas autos
            }

            mutex.release(); //Libera la exclusion mutua
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ir() {
        try {
            ladoDerecho.acquire(); //Solo puede ir, si tiene permiso para ir al lado derecho
            mutex.acquire();
            //System.out.println(mostrarCarga());
            //System.out.println("►►►►►►►► VIAJANDO ►►►►►►►►");
            viajando(true);
            sleep(1000);

            lleno.release();

            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajar(String auto) {
        try {
            lleno.acquire();
            mutex.acquire();
            System.out.println(" ↓↓↓ El coche  " + auto + " esta bajando ↓↓↓");
            sleep(1000);
            cantSubidos--;
            if (cantSubidos == 0) {
                ladoIzquierdo.release();
            }

            lleno.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void volver() {
        try {
            ladoIzquierdo.acquire();
            mutex.acquire();
            viajando(false);
            //System.out.println("◄◄◄◄◄◄◄◄ VIAJANDO ◄◄◄◄◄◄◄◄");
            sleep(1000);
            //System.out.println("CANTIDAD DE SUBIDOS" + cantSubidos);
            lleno.acquire();
            cargando.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void viajando(boolean ir) {
        try {
            String s;
            if (ir) {
                s = "\u001B[32m►";
            } else {
                s = "\u001B[31m◄";
            }
            int t = 250;
            //System.out.print("VIAJANDO");
            //sleep(t);
            for (int i = 0; i < 20; i++) {
                System.out.print(s);
                sleep(t);
            }
            System.out.println("");
        } catch (InterruptedException ex) {
            Logger.getLogger(Transbordador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    public String mostrarCarga() {
        String s = "A BORDO: -> | ";
        for (int i = 0; i < CANTMAXIMA; i++) {
            s += espacio[i] + " | ";
        }
        return s;
    }
     */
}
