/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio10;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Casa {

    private Semaphore sillas;
    private Blancanieves blancanieves;

    public Casa() {
        sillas = new Semaphore(4);
    }

    public synchronized int getSillas() {
        return this.sillas.availablePermits();
    }

    public synchronized void volverAComer(String nombre) {

        while (sillas.availablePermits() == 0) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Casa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            sillas.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Casa.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El enano " + nombre + " se ha sentado y pide comida!...");
        
    }

    public synchronized void comer(String nombre) {

        while (!this.blancanieves.estaOcupada()) {

            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Casa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("Blancanieves le sirve un plato de comida a " + nombre);
        System.out.println("Comiendo....");
        try {
            sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Casa.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("El enano deja el plato y se va");
        this.sillas.release();
        

    }

    public synchronized void atenderEnano(String nombre) {
        while (this.blancanieves.estaOcupada()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Casa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.blancanieves.pedir();

    }

}
