package EjerciciosFinal.Ejercicio2_Monitor;

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

    @Override
    public void run() {

        try {
            // sleep(300);
            almacen.entrar(nombre);
            //sleep(300);
            while (true) {
                almacen.tomarIngredientes(nombre);
                //sleep(300);
                almacen.mezclarVino(nombre);
                almacen.fermentar(nombre);
                almacen.salir(nombre);
                sleep(4000);
                almacen.entrar(nombre);
                almacen.decantarVino(nombre);
                sleep(300);
                almacen.darAProbar(nombre);
                sleep(1000);
                almacen.terminar(nombre);
                sleep(400);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Miembro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
