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
public class TareaB implements Runnable {

    private final Valor dato;

    public TareaB(Valor data) {
        this.dato = data;
    }
    
    @Override
    public void run(){
       
            System.out.println("ESTOY EN TAREA B");
            System.out.println("ANTES EN TAREA B: "+this.dato.getTotal());
            dato.setTotal(dato.getTotal()*2);
            System.out.println("DESPUES EN TAREA B ( POR 2 ) :" +this.dato.getTotal());
            System.out.println("FIN TAREA B");
           
        
    }
    

}
