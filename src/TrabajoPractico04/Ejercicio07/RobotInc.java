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
public class RobotInc {

    public static void main(String[] args) {

        CadenaMontaje cadena = new CadenaMontaje(5, 3);
        Colocador colocador = new Colocador(cadena);
        Empaquetador emp1 = new Empaquetador("ALFA", 1, cadena);
        Empaquetador emp2 = new Empaquetador("BETA", 2, cadena);
        Empaquetador emp3 = new Empaquetador("GAMA", 3, cadena);
        Thread t1 = new Thread(colocador);
        Thread t2 = new Thread(emp1);
        Thread t3 = new Thread(emp2);
        Thread t4 = new Thread(emp3);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

}
