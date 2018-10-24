/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio04;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Rueda {

    private Semaphore turno;

    public Rueda() {
        this.turno = new Semaphore(1);
    }

    public void accederRueda() {
        try {
            this.turno.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rueda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarRueda() {
        this.turno.release();
    }

    public void girar() {
        try {
            accederRueda();
            sleep(1000);
            dejarRueda();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rueda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
