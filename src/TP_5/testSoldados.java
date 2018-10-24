package TP_5;

import java.util.Random;

/**
 *
 * @author Matias
 */
public class testSoldados {

    public static void main(String[] args) {
        Soldado[] soldados = new Soldado[150];
        Recinto rs = new Recinto();
        Random r = new Random();
        for (int i = 0; i < soldados.length; i++) {
            int j = r.nextInt(3);
            soldados[i] = new Soldado(j, rs);
        }
        for (Soldado soldado : soldados) {
            soldado.start();
        }
    }

}
