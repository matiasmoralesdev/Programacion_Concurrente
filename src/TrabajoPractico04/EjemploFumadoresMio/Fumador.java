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
public class Fumador implements Runnable {

    private String nombre;
    private int ingrediente;
    private Mesa mesa;

    public Fumador(String name, int ing, Mesa mesita) {
        this.ingrediente = ing;
        this.mesa = mesita;
        this.nombre = name;
    }

    @Override
    public void run() {

        while (true) {
            mesa.tomarIngredientes(this.nombre, this.ingrediente);
        }

    }

}
