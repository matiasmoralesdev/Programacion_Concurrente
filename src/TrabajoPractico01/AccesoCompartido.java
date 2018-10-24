/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class AccesoCompartido {
    
    public static void main(String[] args) {
   
            Valor dato = new Valor (3);
            TareaA t1= new TareaA (dato);
            TareaB t2 = new TareaB (dato);
            Thread hilo1 = new Thread (t1);
            Thread hilo2 = new Thread (t2);
            hilo1.start();
            hilo2.start();
            System.out.println("FIN DE TODO!");
  
        
        
        
    }
    
    
}
