/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico03.Ejercicio01;

/**
 *
 * @author Matthew
 */
public class MostrarLetras {

    public static void main(String[] args) {
        Turno toca = new Turno(3);
        ImprimirLetra a = new ImprimirLetra('a', 1, toca);
        ImprimirLetra b = new ImprimirLetra('b', 2, toca);
        ImprimirLetra c = new ImprimirLetra('c', 3, toca);
        Thread hiloa = new Thread(a);
        Thread hilob = new Thread(b);
        Thread hiloc = new Thread(c);
        hiloa.start();
        hilob.start();
        hiloc.start();

    }
}
