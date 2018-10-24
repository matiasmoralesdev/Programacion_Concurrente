/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio13;

/**
 *
 * @author Matthew
 */
import sun.misc.Cleaner;

public class EjemploThread implements Runnable {

    private String nombre;
    private String texto;
    private final int DELAY = 10000;

    public EjemploThread(String unNombre, String unTexto) {
        nombre = unNombre;
        texto = unTexto;
    }

    @Override
    public void run() {
        try {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("interrumpido el " + nombre);
                System.out.println(nombre + " dice \"" + texto + "\"");
            } else {
                Thread.sleep(DELAY);
                System.out.println(nombre + " dice \"" + texto + "\"");
            }
        } catch (InterruptedException exception) {
            System.out.println("Ocurrió un error en " + nombre);
        } finally {
            System.out.println(nombre + " ha terminado.");
        } //del finally

    }
}
