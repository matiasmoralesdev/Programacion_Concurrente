/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03.Ejercicio04;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Pizarron  {

    private Semaphore semaforo;

    public Pizarron() {
        this.semaforo = new Semaphore (1, true);
        
    }

    
    public void pedirPermiso (){
        try {
            this.semaforo.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Pizarron.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desocupar(){
        this.semaforo.release();
    }
    
    
   

}
