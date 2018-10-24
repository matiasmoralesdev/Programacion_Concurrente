/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrabajoPractico04.Ejercicio09;

/**
 *
 * @author Matthew
 */
public class testEdificio {

    public static void main(String[] args) {
        Thread plantas[] = new Thread[20];
        Asensor asensores = new Asensor(4);
        Edificio edificio = new Edificio(asensores);

        for (int i = 0; i < 20; i++) {
            String nombre = "Planta " + i;
            plantas[i] = new Thread(new Planta(nombre, edificio));

        }

        for (int i = 0; i < 20; i++) {
            plantas[i].start();
        }

    }

}
