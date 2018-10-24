/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio07;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class CadenaMontaje {

    private int cantidadColocadores;
    private int cantElementos;
    private int[] cadena;
    private PilaProductos pila;

    public CadenaMontaje(int cant, int colocadores) {
        pila = new PilaProductos();
        this.cantidadColocadores = colocadores;
        this.cantElementos = cant;
        this.cadena = new int[cant];

        for (int i = 0; i < cant; i++) {
            this.cadena[i] = 0;
        }

    }

    public synchronized void retirar(String id, int tipoProd) {

        while (encontrar(tipoProd) == -1) {
            try {
                System.out.println("El Empaquetador "+id+" no encontro un producto de su tipo, esperando...");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(CadenaMontaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.cadena[encontrar(tipoProd)] = 0;

        System.out.println("El Empaquetador " + id + " retiro un producto de tipo " + tipoProd);
        System.out.println("Procesando...");
        try {
            sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CadenaMontaje.class.getName()).log(Level.SEVERE, null, ex);
        }

        pila.apilar();

        System.out.println("SE HAN APILADO UN TOTAL DE " + pila.getApilados() + " PRODUCTOS");

        showCadena();

        notifyAll();

    }

    public synchronized void colocar() {

        while (estaLlena()) {
            try {
                System.out.println("El Colocador no encontro huecos en la cadena y se Apago momentaneamente !!");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(CadenaMontaje.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int encontrado = encontrarHueco();

        int nuevoProducto = new Random().nextInt(this.cantidadColocadores) +1;

        System.out.println("El colocador encontro un hueco en la posicion " + encontrado + " y coloco un producto de tipo " + nuevoProducto);

        this.cadena[encontrado] = nuevoProducto;
        
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(CadenaMontaje.class.getName()).log(Level.SEVERE, null, ex);
        }

        showCadena();

        notifyAll();

    }

    private  void showCadena() {
        System.out.println("");
        System.out.print(" [ ");
        for (int i = 0; i < this.cantElementos; i++) {
            System.out.print(this.cadena[i] + " ");
        }
        System.out.print(" ] ");
        System.out.println("");
        System.out.println("");
    }

    private  int encontrarHueco() {
        int i = 0;
        boolean encontrado = false;
        while (i < this.cantElementos && !encontrado) {
            if (this.cadena[i] == 0) {
                encontrado = true;
            } else {
                i++;
            }
        }
        return i;
    }

    private boolean estaLlena() {
        boolean lleno = true;
        int i = 0;
        while (i < this.cantElementos && lleno) {
            if (this.cadena[i] == 0) {
                lleno = false;
            }
            i++;

        }

        return lleno;

    }

    private int encontrar(int tipo) {
        int posicion = -1;
        int i = 0;
        boolean encontrado = false;
        while (i < this.cantElementos && !encontrado) {
            if (this.cadena[i] == tipo) {
                encontrado = true;
            } else {
                i++;
            }
        }

        if (encontrado) {
            posicion = i;
        }

        return posicion;
    }

}
