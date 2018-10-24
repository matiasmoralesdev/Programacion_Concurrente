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
public class Agente implements Runnable {

    private Mesa mesa;

    public Agente(Mesa mesita) {
        this.mesa = mesita;
    }

    @Override
    public void run() {

        while (true) {
            mesa.ponerEnMesa();
        }

    }

}
