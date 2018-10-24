/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Letra {
    

    
    public Letra (char car){
       
    }
    
    public void imprimir (char a, int permisos){
        try {
            Semaphore semaforo = new Semaphore(3);
            semaforo.acquire(permisos);
            
            System.out.println(a);
            
            semaforo.release(permisos);
        } catch (InterruptedException ex) {
            Logger.getLogger(Letra.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        
        
    }
    
    
    
    
}
