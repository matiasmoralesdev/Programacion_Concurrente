/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosTeoria;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LucasM
 */
public class Barberia {

    private SillonAfeitar sillon;
    private int cantClientes;
    private Semaphore puerta;

    public Barberia() {
        this.sillon = new SillonAfeitar();
        this.puerta = new Semaphore(5);
        this.cantClientes = 0;
    }

    public synchronized void entrarAlaBarberia(String nombre) {
        try {
            this.puerta.acquire();
            System.out.println(nombre + " entro a la barberia.");
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public synchronized void irASilla(String nombre) {
        if (this.sillon.getEstado() == 1) {

            this.notify();
            System.out.println(nombre + " se esta cortando el pelo");
            try {
                Thread.sleep(1000);
                System.out.println(nombre+"termino de cortarse el pelo.");
            } catch (InterruptedException ex) {
                Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public synchronized void salirDeLaBarberia(String nombre) {
        this.puerta.release();
        System.out.println(nombre + " salio de la barberia");

    }

    public synchronized void atender() {
        while (this.sillon.getEstado() == 0) {
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Barberia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.sillon.ocupar();
    }

    public synchronized void terminarDeAfeitar() {
        this.sillon.liberar();
        this.notifyAll();
    }

    public synchronized int getPermisos() {
        return this.puerta.availablePermits();

    }

}
