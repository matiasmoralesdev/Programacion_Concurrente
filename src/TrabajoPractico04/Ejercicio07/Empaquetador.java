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
public class Empaquetador implements Runnable {

    private String ID;
    private int tipoProducto;
    private CadenaMontaje cadena;

    public Empaquetador(String id, int tipo, CadenaMontaje chain) {
        this.ID = id;
        this.tipoProducto = tipo;
        this.cadena = chain;
    }

    @Override
    public void run() {
        while (true) {
            this.cadena.retirar(this.ID, this.tipoProducto);
        }
    }

}
