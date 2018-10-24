/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio10;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Blancanieves implements Runnable {

    private Semaphore ocupada;
    private Casa casa;

    public Blancanieves(Casa house) {
        this.ocupada = new Semaphore(1);
        this.casa = house;
    }

    public boolean estaOcupada() {
        return (this.ocupada.availablePermits() == 0);
    }

    public void pedir() {
        try {
            this.ocupada.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Blancanieves.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejar() {
        this.ocupada.release();
    }

    @Override
    public void run() {

        while (true) {

            if (casa.getSillas() == 4) {

                System.out.println("Blancanieves se fue a dar un paseo");
                try {
                    sleep(new Random().nextInt(3000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Blancanieves.class.getName()).log(Level.SEVERE, null, ex);
                }

            }else{
                
                
                
            }
            
            
            

        }

    }

}
