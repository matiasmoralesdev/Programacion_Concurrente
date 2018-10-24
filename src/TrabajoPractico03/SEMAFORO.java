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
 * @author mamorales
 */
public class SEMAFORO {
    
    public static void main(String[] args) {
        try {
            Semaphore semaforo = new Semaphore (1);
            
            System.out.println(semaforo.availablePermits());
            
            semaforo.release();   semaforo.release();   semaforo.release();   semaforo.release();   semaforo.release();   semaforo.release();   semaforo.release();   semaforo.release();
            
            System.out.println("SEMAFORO" +semaforo.availablePermits());
            
            
            
            
            
            
            
            
            
            
            
            
            semaforo.acquire();
            
            
            

            System.out.println("Despues del acquire");
            System.out.println(semaforo.availablePermits());
        } catch (InterruptedException ex) {
            Logger.getLogger(SEMAFORO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }
    
    
}
