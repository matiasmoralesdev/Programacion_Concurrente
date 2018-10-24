/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio04;

import static java.lang.Thread.sleep;

/**
 *
 * @author Matthew
 */
public class PingPong implements Runnable {

    private String pal; // Lo que va a escribir.
    private int delay; // Tiempo entre escritura

    public PingPong(String cartel, int cantMs) {
        pal = cartel;
        delay = cantMs;
    }

    ;
    @Override
    public void run() { //implementa el método run() dela interfaz
        while (true) {
            System.out.println(pal + " ");
              try {
             sleep(delay);
             } catch (InterruptedException e) {
             return;
             }
                  
             
        }
    } //fin método run()
} //fin clase PingPong
