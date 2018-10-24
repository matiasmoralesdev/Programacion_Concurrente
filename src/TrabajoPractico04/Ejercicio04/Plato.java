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
public class Plato {

    private Semaphore turno;

    public Plato() {
        this.turno = new Semaphore(3);
    }

    public void come() {
        try {
            this.turno.acquire(1);
           
        } catch (InterruptedException ex) {
            Logger.getLogger(Plato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarComer() {
        this.turno.release();
    }

    
    public void comer (){
        try {
            come();
            sleep(1000);
            dejarComer();
        } catch (InterruptedException ex) {
            Logger.getLogger(Plato.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
