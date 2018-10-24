/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio06;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class ThreadTesting {

    public static void main(String[] args) {
        Runnable threadTareas = new MiEjecucion();
        Thread miHilo = new Thread(threadTareas);
        miHilo.start();
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadTesting.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("En el main");
    }
}
