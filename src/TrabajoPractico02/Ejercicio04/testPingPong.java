/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico02.Ejercicio04;

import static java.lang.Thread.sleep;

/**
 *
 * @author Matthew
 */
public class testPingPong {

    public static void main(String[] args) {
        PingPong o1 = new PingPong("PING", 33);
        PingPong o2 = new PingPong("PONG", 10);
// Se crean los threads
        Thread t1 = new Thread(o1);
        Thread t2 = new Thread(o2);
        // se activan los threads
        t1.start();
        t2.start();
    }
}
