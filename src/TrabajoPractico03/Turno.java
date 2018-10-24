/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mamorales
 */
public class Turno {

    private int turn;
    private int totalTurnos;

    public Turno(int tot) {
        this.turn = 1;
        this.totalTurnos = tot;

    }

    public int getTurno() {

        return this.turn;

    }

    public void sigTurno() {
        if (this.turn == this.totalTurnos) {
            this.turn = 1;
        } else {
            this.turn = this.turn + 1;

        }
    }

    public boolean esTurno(int valor) {
        boolean respuesta = false;
        try {

            Semaphore semaforo = new Semaphore(1);
            semaforo.acquire();
            respuesta = (turn == valor);
            semaforo.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Turno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return respuesta;
    }

}
