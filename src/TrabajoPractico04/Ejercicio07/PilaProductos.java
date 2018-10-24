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
public class PilaProductos {

    int cantidad;

    public PilaProductos() {
        this.cantidad = 0;
    }

    public void apilar() {
        this.cantidad = this.cantidad + 1;
    }

    public int getApilados() {
        return this.cantidad;
    }

}
