package TPFinalModificado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Capitan implements Runnable {

    private Bote bote;

    public Capitan(Bote ship) {

        this.bote = ship;

    }

    @Override
    public void run() {
        while (true) {

            this.bote.viajar();
            System.out.println("........VIAJANDO........");
            try {
                sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Capitan.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.bote.amarrar();
        }

    }
}
