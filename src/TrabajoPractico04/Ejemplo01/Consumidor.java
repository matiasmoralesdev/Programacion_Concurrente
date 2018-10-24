/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejemplo01;

/**
 *
 * @author Matthew
 */
public class Consumidor implements Runnable {

    private Buffer bb = null;

    public Consumidor(Buffer bb) {
        this.bb = bb;
    }

    @Override
    public void run() {
        double item;
        while (true) {
            item = bb.extraer();
            System.out.println("Consume "
                    + item);
        }
    }//run
}//Consumidor

