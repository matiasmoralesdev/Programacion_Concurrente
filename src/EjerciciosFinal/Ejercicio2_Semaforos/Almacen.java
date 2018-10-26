package EjerciciosFinal.Ejercicio2_Semaforos;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
El almacén contiene 2 estaciones de mezcla, 6 jarras (de 10 litros), 7 unidades para fermentación/descanso, 15 envases (de 5 litros)
de jugo de fruta azucarado, y 20 paquetes de levadura de vino (que alcanzan para preparar 10 litros de vino cada paquete).
 */
public class Almacen {

    //private Lock lock = new ReentrantLock();
    private Semaphore mutex = new Semaphore(1); //Semaforo que garantiza la exclusion mutua
    private Semaphore jarra = new Semaphore(6);
    private Semaphore envase = new Semaphore(15);
    private Semaphore levadura = new Semaphore(20);
    private Semaphore reposicion = new Semaphore(0);

    private int cantMiembros = 0;   //Cantidad de miembros dentro del almacen

    private int estacionesDeMezcla = 2;
    private int jarras = 6;
    private int uniFermentacion = 7;
    private int envase5 = 15;
    private int paqLevadura = 20; //cada paquete alcanza para preparar 10 litros de vino
    private boolean vinoTerminado = false;
    private String miembroVino = "";

    public void entrar(String miembro) {
        try {
            mutex.acquire();
            System.out.println("► ► " + miembro + " esta entrando en el almacen ► ►");
            cantMiembros++;
            sleep(500);
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tomarIngredientes(String miembro) {
        try {
            jarra.acquire(2);
            if (envase5 < 2 || paqLevadura < 1) {
                reposicion.release();
            }
            mutex.acquire();
            envase5 -= 2;
            paqLevadura -= 1;
            System.out.println("<>" + miembro + " esta tomando los ingredientes ");
            System.out.println("| JARRAS RESTANTES:" + jarra.availablePermits() + " | ENVASES RESTANTES: " + envase5 + " | PAQUETES DE LEVADURA RESTANTES: " + paqLevadura);
            mutex.release();

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ocupa una estacion de mezcla para mezclar el vino
    public void mezclarVino(String miembro) {

    }

    //Ocupa una unidad de fermentacion y libera una estacion de mezcla
    public void fermentar(String miembro) {

    }

    //Retira el vino de la unidad de fermentacion y decanta la levadura muerta
    public void retirarVino(String miembro) {

    }

    //Devuelve las jarras usadas 
    public void terminar(String miembro) {

    }

    //El administrador repone los ingredientes faltantes
    public void reponer() {
        try {
            reposicion.acquire();

            mutex.acquire();
            if (envase5 < 2) {
                envase5 += 15;
            }
            if (paqLevadura < 1) {
                paqLevadura += 20;
            }
            reposicion.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
