/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03.Ejercicio02;

/**
 *
 * @author Matthew
 */
public class VariableCompartida {

    private int v;

    public VariableCompartida() {

    }

    public VariableCompartida(int variable) {
        this.v = variable;
    }

    public void setVariable(int variable) {
        this.v = variable;
    }

    public int getVariable() {
        return this.v;
    }

    public void incrementar() {
        this.v = this.v + 1;
    }

    public void incrementar(int cant) {
        this.v = this.v + cant;
    }

}
