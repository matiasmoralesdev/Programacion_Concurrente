/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mamorales
 */
public class Tiza implements Runnable {

    private String ocupante;
    private Pizarron pizarra;

    public Tiza(Pizarron piz, String ocup) {
        this.pizarra = piz;
        this.ocupante = ocup;

    }

    public Tiza() {
    }

    public void setOcupante(String ocup) {
        this.ocupante = ocup;
    }

    public String getOcupante() {
        return this.ocupante;
    }

    public void run() {

        try {
            for (int i = 0; i < 10; i++) {

                this.pizarra.pedirPermiso();

                System.out.println("La tiza fue tomada por " + this.ocupante);
                System.out.println("El pizarron esta Ocupado");
                System.out.print("e");
                sleep(250);
                System.out.print("s");
                sleep(250);
                System.out.print("c");
                sleep(250);
                System.out.print("r");
                sleep(250);
                System.out.print("i");
                sleep(250);
                System.out.print("b");
                sleep(250);
                System.out.print("i");
                sleep(250);
                System.out.print("e");
                sleep(250);
                System.out.print("n");
                sleep(250);
                System.out.print("d");
                sleep(250);
                System.out.print("o");
                sleep(250);
                System.out.print(".");
                sleep(250);
                System.out.print(".");
                sleep(250);
                System.out.print(".");
                sleep(250);
                System.out.println("");
                sleep(1000);
                System.out.println("Borrando el pizarron!!");
                System.out.println("");
                sleep(1000);

                this.pizarra.desocupar();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Tiza.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
