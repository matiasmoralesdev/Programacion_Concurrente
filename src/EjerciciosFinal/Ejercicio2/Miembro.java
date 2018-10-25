package EjerciciosFinal.Ejercicio2;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Miembro implements Runnable {

    private String nombre;
    private Almacen almacen;

    public Miembro(String name, Almacen store) {
        nombre = name;
        almacen = store;
    }

    public void run() {
        while (true) {
            try {
                almacen.entrar(nombre);
                sleep(300);
                almacen.tomarIngredientes(nombre);
                sleep(300);
                almacen.prepararVino(nombre);
                //almacen.esperar4semanas
                sleep(300);
                almacen.retirarVino(nombre);
                sleep(300);
                almacen.salir(nombre);
                sleep(300);
            } catch (InterruptedException ex) {
                Logger.getLogger(Miembro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
