/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio09;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Asensor {

    public Semaphore asensores;

    public Asensor(int n) {

        this.asensores = new Semaphore(n);
    }

    public void llamar() {
        try {
            this.asensores.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Asensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajar() {
        this.asensores.release();
    }

    public int asensoresLibre() {
        return this.asensores.availablePermits();
    }

}
