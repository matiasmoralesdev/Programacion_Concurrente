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
public class TestIncremento {

    public static void main(String[] args) {

        VariableCompartida var = new VariableCompartida();
        Incremento a = new Incremento(10000, var);
        Thread hilo1 = new Thread (a);
        Thread hilo2 = new Thread (a);
        hilo1.start();
        hilo2.start();
        

    }

}
