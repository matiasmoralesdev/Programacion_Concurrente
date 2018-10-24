package TPFinalModificado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Pasajero implements Runnable {

    private int tipo;
    private String nombre;
    private Bote bote;
    private int puerto;

    public Pasajero(String name, int type, int port, Bote ship) {

        this.nombre = name;
        this.tipo = type;
        this.bote = ship;
        this.puerto = port;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getTipo() {
        return this.tipo;
    }

    public int getPuerto() {
        return this.puerto;
    }

    public void setPuerto(int port) {
        this.puerto = port;
    }

    @Override
    public void run() {

        while (true) {
            bote.accederAlPuerto(this.nombre, this.tipo, this.puerto);
            bote.subirAlBote(this.nombre, this.tipo, this.puerto);
            bote.bajarDelBote(this);
            bote.irAMontañas(this);
            try {
                sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
