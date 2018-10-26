package EjerciciosFinal.Ejercicio2_Monitor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
El almacén contiene 2 estaciones de mezcla, 6 jarras (de 10 litros), 7 unidades para fermentación/descanso, 15 envases (de 5 litros)
de jugo de fruta azucarado, y 20 paquetes de levadura de vino (que alcanzan para preparar 10 litros de vino cada paquete).
 */
public class Almacen {

    private int estacionesDeMezcla = 2;
    private int jarras = 6;
    private int uniFermentacion = 7;
    private int envase5 = 15;
    private int paqLevadura = 20; //cada paquete alcanza para preparar 10 litros de vino

    private boolean vinoTerminado = false;
    private boolean testearVino = false;
    private String miembroVino = "";
    private int cantMiembros = 0; //Cantidad de miembros dentro del almacen
    private int hanProbado = 0;

    public synchronized void entrar(String miembro) {
        try {
            System.out.println("► ►  El miembro " + miembro + " esta entrando en el almacen ► ►");
            cantMiembros++;
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void salir(String miembro) {
        try {
            System.out.println("◄ ◄   El miembro " + miembro + " esta saliendo del almacen por 4 meses ◄ ◄ ");
            cantMiembros--;
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void tomarIngredientes(String miembro) {
        try {
            //Mientras no haya jarras disponibles espera
            while (jarras < 2) {
                //System.out.println(miembro + ": NO HAY JARRAS DISPONIBLES");
                wait();
            }
            //Mientras no haya suficientes ingredientes despierta al Administrador para que los reponga
            while (!ingredientesSuficientes()) {

                //System.out.println(miembro + "NO HAY INGREDIENTES SUFICIENTES - SE AVISARA AL ADMINISTRADOR");
                notifyAll();
                wait();
            }
            //El miembro toma 2 jarras, 2 envases de 5 litros de jugo (10 litros)
            jarras = jarras - 2;    //Toma 2 jarras
            envase5 = envase5 - 2;  //Toma 10 litros de jugo
            paqLevadura = paqLevadura - 1;  //Toma un paquete de levadura
            System.out.println("<>" + miembro + " esta tomando los ingredientes ");
            // System.out.println("| JARRAS RESTANTES:" + jarras + " | ENVASES RESTANTES: " + envase5 + " | PAQUETES DE LEVADURA RESTANTES: " + paqLevadura);
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Retorna false si no hay minimo 2 envases de jugo o 1 paquete de levadura
    public synchronized boolean ingredientesSuficientes() {
        return (envase5 >= 2 && paqLevadura >= 1);
    }

    //Ocupa una estacion de mezcla para mezclar el vino
    public synchronized void mezclarVino(String miembro) {
        try {
            //Mientras no haya estaciones de mezcla disponibles, espera
            while (estacionesDeMezcla == 0) {
                //System.out.println("NO HAY ESTACIONES DE MEZCLA DISPONIBLES.....ESPERANDO QUE SE DESOCUPEN");
                wait();
            }
            System.out.println("+++" + miembro + " esta MEZCLANDO el vino");
            estacionesDeMezcla--;
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Ocupa una unidad de fermentacion y libera una estacion de mezcla
    public synchronized void fermentar(String miembro) {
        try {
            estacionesDeMezcla++;
            jarras++; //Como ya no usa mas una de las jarras que tomo, la libera

            //Mientras no haya unidades de fermentacion disponible, espera
            while (uniFermentacion == 0) {
                // System.out.println(miembro + " NO HAY UNIDADES DE FERMENTACION DISPONIBLE");
                wait();
            }
            uniFermentacion--;
            System.out.println("###" + miembro + " Esta FERMENTANDO el vino");
            sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Retira el vino de la unidad de fermentacion y decanta la levadura muerta
    public synchronized void decantarVino(String miembro) {
        try {

            uniFermentacion++;
            //notifyAll();
            System.out.println("↓↓↓↓↓↓↓↓ " + miembro + " esta Decantando......");
            sleep(1000);

            notifyAll();

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Devuelve las jarras usadas 
    public synchronized void terminar(String miembro) {
        try {

            jarras++;
            //notifyAll();

            System.out.println("\033[32m██████████████████████████████████████");
            System.out.println("\033[32m█      FELICITACIONES: " + miembro + " HA TERMINADO SU VINO    ");
            System.out.println("\033[32m██████████████████████████████████████");

            sleep(500);
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void darAProbar(String miembro) {

        System.out.println(miembro + "Se prepara nuevamente para hacer mas vino");
    }

    //El administrador repone los ingredientes faltantes
    public synchronized void reponer() {
        try {
            //Mientras haya ingredientes suficientes el administrador espera
            while (ingredientesSuficientes()) {
                wait();
            }
            System.out.println("↕↕↕↕ El Administrador esta reponiendo los ingredientes ↕↕↕↕");
            if (paqLevadura < 1) {
                paqLevadura += 20;
            }

            if (envase5 < 2) {
                envase5 += 15;
            }
            sleep(500);
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
