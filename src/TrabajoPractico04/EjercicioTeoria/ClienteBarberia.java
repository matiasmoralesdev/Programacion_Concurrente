/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.EjercicioTeoria;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LucasM
 */
public class ClienteBarberia implements Runnable {

    private String nombre;
    private Barberia barberia;
    
    public ClienteBarberia(String nomb, Barberia b) {
        this.nombre = nomb;
        this.barberia = b;        
    }
    
    public void run() {       
        
        this.barberia.entrarAlaBarberia(this.nombre);
        this.barberia.irASilla(nombre);        
        this.barberia.salirDeLaBarberia(this.nombre);
        
    }
}
