/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico01;

/**
 *
 * @author Matthew
 */
public class TestearHilos {

    public static void main(String[] args) {
        Imprimir uno = new Imprimir(5, "Impresora 1");
        Imprimir dos = new Imprimir(5, "Impresora 2");
        Imprimir tres = new Imprimir(5, "Impresora 3");
        Thread hilo1 = new Thread(uno);
        Thread hilo2 = new Thread(dos);
        Thread hilo3 = new Thread(tres);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        System.out.println("FIN!!!!!!!!!!!!!");

    }
}
