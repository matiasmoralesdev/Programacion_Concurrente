/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.EjemploFumadoresMio;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Mesa {

    public boolean mesaVacia;
    public int cantFumadores;
    public int ingrediente01, ingrediente02;

    public Mesa(int cant) {
        this.mesaVacia = true;
        this.cantFumadores = cant;
    }

    public boolean estaVacia() {
        return this.mesaVacia;
    }

    public synchronized void ponerEnMesa() {

        while (!this.mesaVacia) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.ingrediente01 = new Random().nextInt(this.cantFumadores);
        this.ingrediente02 = new Random().nextInt(this.cantFumadores);

        while (this.ingrediente01 == this.ingrediente02) {
            this.ingrediente02 = new Random().nextInt(this.cantFumadores);
        }

        System.out.println("El Agente coloco el ingrediente " + this.ingrediente01 + " y el ingrediente " + this.ingrediente02 + " en la mesa.");
        this.mesaVacia = false;

        notifyAll();

    }

    public synchronized void tomarIngredientes(String nombre, int ingredienteFumador) {

        while ((this.mesaVacia) || this.ingrediente01 == ingredienteFumador || this.ingrediente02 == ingredienteFumador) {
            try {
                wait();

            } catch (InterruptedException ex) {
                Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("El Fumador " + nombre + " tomo los ingredientes "
                + this.ingrediente01 + " y " + this.ingrediente02 + " de la mesa y se armo un cigarro con su ingrediente " + ingredienteFumador);
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Mesa.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.mesaVacia = true;
        notifyAll();

    }

}
