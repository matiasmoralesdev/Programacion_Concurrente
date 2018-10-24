  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03;

/**
 *
 * @author Matthew
 */
public class TestPizarron {

    public static void main(String[] args) {
        Pizarron pizarron = new Pizarron ();
        
        Tiza a = new Tiza(pizarron, "Alfa");
        Tiza b = new Tiza(pizarron, "Beta");
        Tiza c = new Tiza(pizarron, "Gama");
        Tiza d = new Tiza(pizarron, "Delta");
        Thread hilo1 = new Thread(a);
        Thread hilo2 = new Thread(b);
        Thread hilo3 = new Thread(c);
        Thread hilo4 = new Thread(d);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

    }
}
