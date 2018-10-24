/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.EjemploFumadoresMio;

/**
 *
 * @author Matthew
 */
public class testFumadores {

    public static void main(String[] args) {

        Mesa table = new Mesa(3);
        Fumador fum1 = new Fumador("Alfa", 0, table);
        Fumador fum2 = new Fumador("Beta", 1, table);
        Fumador fum3 = new Fumador("Gama", 2, table);
        Agente agente = new Agente(table);
        Thread t1 = new Thread(fum1);
        Thread t2 = new Thread(fum2);
        Thread t3 = new Thread(fum3);
        Thread t4 = new Thread(agente);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
