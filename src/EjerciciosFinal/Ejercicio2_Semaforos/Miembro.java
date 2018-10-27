package EjerciciosFinal.Ejercicio2_Semaforos;

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
                sleep(300);
                almacen.mezclarVino(nombre);
                sleep(300);
                almacen.fermentar(nombre);
                sleep(300);
                //System.out.println(nombre + ": ◄ ◄ ◄ Esperando 4 semanas  ► ► ► ");
                almacen.salir(nombre);
                sleep(4000);
                almacen.entrar(nombre);
                sleep(300);
                //System.out.println(nombre + ": ► ► ► Vuelve de 4 semanas de reposo ◄ ◄ ◄");
                almacen.decantarVino(nombre);
                sleep(300);
                almacen.darAProbar(nombre);
                sleep(300);
                almacen.terminar(nombre);
                sleep(300);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Miembro.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
