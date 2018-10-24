/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio07;

/**
 *
 * @author Matthew
 */
public class Colocador implements Runnable {

    private CadenaMontaje cadena;

    public Colocador(CadenaMontaje chain) {
        this.cadena = chain;
    }

    @Override
    public void run() {

        while (true) {
            this.cadena.colocar();
        }
    }

}
