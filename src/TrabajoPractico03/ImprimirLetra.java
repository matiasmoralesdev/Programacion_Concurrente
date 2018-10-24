/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03;

/**
 *
 * @author Matthew
 */
public class ImprimirLetra implements Runnable {

    private final char car;
    private final int turno;
    private final Turno leToca;

    public ImprimirLetra(char a, int turn, Turno toca) {
        this.car = a;
        this.turno = turn;
        this.leToca = toca;
    }

    @Override
    public void run() {
        int j = 0;
        while (j < 5){
        while (!this.leToca.esTurno(this.turno)) {

        }

        if (this.leToca.esTurno(this.turno)) {

            for (int i = 0; i < this.turno; i++) {
                System.out.println("Letra: " + this.car);
            }

            this.leToca.sigTurno();
            j++;
        }
        }
    }
}
