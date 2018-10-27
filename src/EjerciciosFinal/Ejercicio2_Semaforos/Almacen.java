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
    private Semaphore mesaIngredientes = new Semaphore(1); //Semaforo que garantiza la exclusion mutua
    private Semaphore puerta = new Semaphore(1);
    private Semaphore jarra = new Semaphore(6);
    private Semaphore envase = new Semaphore(15);
    private Semaphore levadura = new Semaphore(20);
    private Semaphore reposicion = new Semaphore(0);
    private Semaphore mezcla = new Semaphore(2);
    private Semaphore fermentador = new Semaphore(7);
    private Semaphore esperarReposicion = new Semaphore(0);
    private Semaphore hayQueTestear = new Semaphore(1);

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
            puerta.acquire();
            System.out.println("\u001B[34m► ► " + miembro + " esta entrando en el almacen ► ►");
            cantMiembros++;
            sleep(500);
            puerta.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void salir(String miembro) {
        try {
            System.out.println("\u001B[31m◄ ◄   El miembro " + miembro + " esta saliendo del almacen por 4 meses ◄ ◄ ");
            cantMiembros--;
            sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tomarIngredientes(String miembro) {
        try {
            mesaIngredientes.acquire();
            jarra.acquire(2);
            if (paqLevadura < 1 || envase5 < 2) {
                reposicion.release();
                esperarReposicion.acquire();
            }
            envase5 -= 2;
            paqLevadura -= 1;

            System.out.println("<<" + miembro + " esta tomando los ingredientes >>");
            System.out.println("| JARRAS RESTANTES:" + jarra.availablePermits() + " | ENVASES RESTANTES: " + envase5 + " | PAQUETES DE LEVADURA RESTANTES: " + paqLevadura);
            sleep(300);
            mesaIngredientes.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ocupa una estacion de mezcla para mezclar el vino
    public void mezclarVino(String miembro) {
        try {
            mezcla.acquire();
            System.out.println("+++" + miembro + " esta MEZCLANDO el vino");
            sleep(500);
            mezcla.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ocupa una unidad de fermentacion y libera una estacion de mezcla
    public void fermentar(String miembro) {
        try {
            fermentador.acquire();
            System.out.println("###" + miembro + " Esta FERMENTANDO el vino");
            sleep(1000);
            fermentador.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Retira el vino de la unidad de fermentacion y decanta la levadura muerta
    public void decantarVino(String miembro) {
        try {
            //fermentador.release();
            System.out.println("↓↓↓↓↓↓↓↓ " + miembro + " esta Decantando ↓↓↓↓↓↓↓↓ ");
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Devuelve las jarras usadas 
    public void darAProbar(String miembro) {
        try {
            hayQueTestear.acquire();
            System.out.println("\033[32m██████████████████████████████████████");
            System.out.println("\033[32m█      FELICITACIONES: " + miembro + " HA TERMINADO SU VINO       █");
            System.out.println("\033[32m██████████████████████████████████████");
            sleep(5000);
            hayQueTestear.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminar(String miembro) {
        jarra.release(2);
        System.out.println("◊ ◊ ◊  " + miembro + " Se prepara nuevamente para hacer mas vino ◊ ◊ ◊");
    }

    //El administrador repone los ingredientes faltantes
    public void reponer() {
        try {
            reposicion.acquire();

            //mesaIngredientes.acquire();
            System.out.println("\u001B[36m↕↕↕↕ El Administrador esta reponiendo los ingredientes ↕↕↕↕");
            if (envase5 < 2) {
                envase5 += 15;
            }
            if (paqLevadura < 1) {
                paqLevadura += 20;
            }
            esperarReposicion.release();
            //mesaIngredientes.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
