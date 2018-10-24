/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio03;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Imprimir implements Runnable {

private final int nVeces;
private final String name;


public Imprimir (int numeroVeces, String nombre){
    this.nVeces = numeroVeces;
    this.name = nombre;
}
    
@Override
public void run(){
    for (int i = 0; i<nVeces; i++){
        try {
            System.out.println("Mi nombre es: "+this.name);
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

}
