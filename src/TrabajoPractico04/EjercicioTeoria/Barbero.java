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
public class Barbero implements Runnable {

    private Barberia barberia;

    public Barbero(Barberia b) {
        this.barberia = b;
    }

    public void run() {
        while (true) {
            if(this.barberia.getPermisos()==5){
                System.out.println("Barbero durmiendo");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }else{
            this.barberia.atender();
               /* try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            this.barberia.terminarDeAfeitar();
            
            }
           
            }
        }
    }

