package TP_5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matias
 */
public class Soldado extends Thread {

    private int tipoComida;
    private Recinto recintoSoldados;

    public Soldado(int tc, Recinto rs) {
        this.tipoComida = tc;
        this.recintoSoldados = rs;
    }

    @Override
    public void run() {
        try {
            recintoSoldados.entrar(tipoComida);
            Thread.sleep(5000);
            recintoSoldados.salir();
        } catch (InterruptedException ex) {
        }

    }
}
