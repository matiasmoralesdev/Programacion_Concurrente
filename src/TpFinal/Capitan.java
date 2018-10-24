/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TpFinal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LucasM
 */
public class Capitan implements Runnable {

    private Bote unBote;
    private boolean duerme;

    public Capitan(Bote b) {
        this.unBote = b;
        this.duerme = true;
    }

    public void run() {
        try {
            while (true) {
                this.unBote.comenzarViaje(this);
                Thread.sleep(1000);
                if (this.duerme != true) {
                    System.out.println("Holaaa");
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Capitan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setEstado(boolean b) {
        this.duerme = b;
    }
}
