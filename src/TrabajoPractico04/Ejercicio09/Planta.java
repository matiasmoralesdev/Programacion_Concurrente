/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio09;

/**
 *
 * @author Matthew
 */
public class Planta implements Runnable {

    private String nombre;
    private Edificio edificio;

    public Planta(String name, Edificio edif) {
        this.nombre = name;
        this.edificio = edif;
    }

    @Override
    public void run() {
        while (true) {
            this.edificio.pedirAscensor(this.nombre);
        }

    }

}
