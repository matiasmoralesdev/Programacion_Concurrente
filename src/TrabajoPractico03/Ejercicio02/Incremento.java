/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03.Ejercicio02;

import TrabajoPractico03.Ejercicio02.VariableCompartida;

/**
 *
 * @author Matthew
 */
public class Incremento implements Runnable {

    private VariableCompartida variable;
    private int cantIncrementos;

    public Incremento(int cantIncrem, VariableCompartida Comp) {
        this.variable = Comp;
        this.cantIncrementos = cantIncrem;
    }

    public void run() {

        for (int i = 0; i < this.cantIncrementos; i++) {
            System.out.println("VARIABLE ANTES DEL INCREMENTO: " + this.variable.getVariable());

            this.variable.incrementar();
            System.out.println("VARIABLE DESPUES DEL INCREMENTO: " + this.variable.getVariable());
        }
    }
}
