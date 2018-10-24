/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio09;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Edificio {

    public Asensor asensores;

    public Edificio(Asensor as) {
        this.asensores = as;
    }

    public synchronized void pedirAscensor(String nombrePlanta) {

        while (asensores.asensoresLibre() == 0) {
            try {
                System.out.println("No hay asensores libres, esperando...");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Edificio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        asensores.llamar();

        System.out.println("La planta " + nombrePlanta + " pidio un asensor, esperando...");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Edificio.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("El usuario se subio al asensor, esperando que llegue a su piso...");
        try {
            sleep(new Random().nextInt(3000) + 1000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Edificio.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("El usuario llego a su piso");
        asensores.bajar();

        notifyAll();

    }

}
