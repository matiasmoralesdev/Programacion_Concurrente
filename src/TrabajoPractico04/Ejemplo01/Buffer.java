/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejemplo01;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Buffer {

    private int numLugares = 0;
    private double[] buffer = null;
    private int ponerEn = 0, sacarDe = 0;
    private int canti = 0;
//constuctor

    public Buffer(int cantSlots) {
        this.numLugares = cantSlots;
        buffer = new double[cantSlots];
    }

    public synchronized void insertar(double valor) {
        while (canti == numLugares) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        notifyAll(); //inserción en exclusión mutua.
    }

    public synchronized double extraer() {
        double valor = 0;
        int cont = 0;
        while (cont == 0)//condición buffer vacío
        {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println("….");
            }
        }
        //lo que hace despues

        this.canti--;
        notifyAll();
        return valor;
    }

}
