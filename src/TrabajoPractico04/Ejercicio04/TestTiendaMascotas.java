/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio04;

/**
 *
 * @author Matthew
 */
public class TestTiendaMascotas {

    public static void main(String[] args) {
        Plato plato = new Plato();
        Rueda rueda = new Rueda();
        Hamster ham1 = new Hamster("Alfa", plato, rueda);
        Hamster ham2 = new Hamster("Beta", plato, rueda);
        Hamster ham3 = new Hamster("Gama", plato, rueda);
        Hamster ham4 = new Hamster("Delta", plato, rueda);
        Thread h1 = new Thread(ham1);
        Thread h2 = new Thread(ham2);
        Thread h3 = new Thread(ham3);
        Thread h4 = new Thread(ham4);
        h1.start();
        h2.start();
        h3.start();
        h4.start();

    }
}
