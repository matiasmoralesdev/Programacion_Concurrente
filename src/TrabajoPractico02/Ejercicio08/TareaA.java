/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio08;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class TareaA implements Runnable {

    private final Valor dato;

    public TareaA(Valor data) {
        this.dato = data;
    }
    
    @Override
    public void run(){
        
        
            
            System.out.println("ESTOY EN TAREA A");
            System.out.println("ANTES EN TAREA A: "+this.dato.getTotal());
            dato.setTotal(dato.getTotal()+1);
            System.out.println("DESPUES EN TAREA A (SUMO 1):" +this.dato.getTotal());
            System.out.println("FIN TAREA A");
            
       
        
    }
    

}
