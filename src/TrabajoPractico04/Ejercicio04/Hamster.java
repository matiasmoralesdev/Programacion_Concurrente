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
public class Hamster implements Runnable {

    private String nombre;
    private Plato plato;
    private Rueda rueda;

    public Hamster(String name, Plato p, Rueda r) {
        this.nombre = name;
        this.plato = p;
        this.rueda = r;
    }

    @Override
    public void run() {

        for (int i = 0; i < 1; i++) {

            plato.comer();
            System.out.println(this.nombre + " Esta comiendo");

            rueda.girar();
            System.out.println(this.nombre + " Esta girando");

        }

    }

}
