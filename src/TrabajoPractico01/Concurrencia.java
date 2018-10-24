/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico01;

/**
 *
 * @author Matthew
 */
import java.util.concurrent.Delayed;

public class Concurrencia {

    public static void main(String[] args) throws InterruptedException {
        EjemploThread et1 = new EjemploThread("Thread #1",
                "Hola mundo.");
        Thread t1 = new Thread(et1);
        t1.start();
        EjemploThread et2 = new EjemploThread("Thread #2",
                "Adiós mundo.");
        Thread t2 = new Thread(et2);
        t2.start();
    }
}
