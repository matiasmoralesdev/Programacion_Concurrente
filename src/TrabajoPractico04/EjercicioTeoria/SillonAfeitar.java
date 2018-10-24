/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.EjercicioTeoria;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LucasM
 */
public class SillonAfeitar {

    private Semaphore estado;

    public SillonAfeitar() {
        estado = new Semaphore(1);
    }

    public void ocupar() {
        try {
            estado.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(SillonAfeitar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void liberar() {
        estado.release();
    }
    
    public int getEstado(){        
    return this.estado.availablePermits();
    }
}
