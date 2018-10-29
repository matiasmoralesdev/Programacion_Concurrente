package EjerciciosFinal.Ejercicio2_Monitor;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
El almacén contiene 2 estaciones de mezcla, 6 jarras (de 10 litros), 7 unidades para fermentación/descanso, 15 envases (de 5 litros)
de jugo de fruta azucarado, y 20 paquetes de levadura de vino (que alcanzan para preparar 10 litros de vino cada paquete).
 */
public class Almacen {

    private int estacionesDeMezcla;
    private int jarras;
    private int uniFermentacion;
    private int envase5;
    private int paqLevadura; //cada paquete alcanza para preparar 10 litros de vino
    private boolean testearVino;
    private String miembroVino;

    public Almacen() {
        estacionesDeMezcla = 2;
        jarras = 6;
        uniFermentacion = 7;
        envase5 = 15;
        paqLevadura = 20;
        testearVino = false;
        miembroVino = "";
    }
    //Una sola persona puede entrar por la puerta 
    public synchronized void entrar(String miembro) {
        try {
            System.out.println("\u001B[34m► ►  El miembro " + miembro + " esta entrando en el almacen ► ►");
            sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Una sola persona puede salir por la puerta
    public synchronized void salir(String miembro) {
        try {
            System.out.println("\u001B[31m◄ ◄   El miembro " + miembro + " esta saliendo del almacen por 4 meses ◄ ◄ ");
            sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //Toma los ingredientes necesarios disponibles, si no hay jarras espera a que se desocupen
    //Si no hay ingredientes notifica al Administrador
    public synchronized void tomarIngredientes(String miembro) {
        try {
            //Mientras no haya jarras disponibles espera
            while (jarras < 2) {
                if (testearVino) {
                    System.out.println("\u001B[35m ♥ ♥ ♥ " + miembro + " esta probando el vino de " + miembroVino + " ♥ ♥ ♥");
                    sleep(300);
                    wait();
                }
                //System.out.println(miembro + ": NO HAY JARRAS DISPONIBLES "); //Debug
                wait();

            }
            //Mientras no haya suficientes ingredientes despierta al Administrador para que los reponga
            while (!ingredientesSuficientes()) {
                System.out.println(miembro + "NO HAY INGREDIENTES SUFICIENTES - SE AVISARA AL ADMINISTRADOR"); //Debug
                notifyAll(); //Notifica al administrador que reponga ingredientes y vuelve a esperar 
                wait();
            }
            //El miembro toma 2 jarras, 2 envases de 5 litros de jugo (10 litros)
            jarras = jarras - 2;    //Toma 2 jarras
            envase5 = envase5 - 2;  //Toma 10 litros de jugo
            paqLevadura = paqLevadura - 1;  //Toma un paquete de levadura
            System.out.println("<--" + miembro + " esta tomando los ingredientes -->");
            System.out.println("| JARRAS RESTANTES:" + jarras + " | ENVASES RESTANTES: " + envase5 + " | PAQUETES DE LEVADURA RESTANTES: " + paqLevadura);
            sleep(300);
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Retorna false si no hay minimo 2 envases de jugo o 1 paquete de levadura
    private synchronized boolean ingredientesSuficientes() {
        return (envase5 >= 2 && paqLevadura >= 1);
    }

    //Ocupa una estacion de mezcla para mezclar el vino
    public synchronized void mezclarVino(String miembro) {
        try {
            //Mientras no haya estaciones de mezcla disponibles, espera
            while (estacionesDeMezcla == 0) {
                //System.out.println("NO HAY ESTACIONES DE MEZCLA DISPONIBLES.....ESPERANDO QUE SE DESOCUPEN");
                wait();
                if (testearVino) {
                    System.out.println("\u001B[35m ♥ ♥ ♥ " + miembro + " esta probando el vino de " + miembroVino + " ♥ ♥ ♥");
                    wait();
                }
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
            //jarras++; //Como ya no usa mas una de las jarras que tomo, la libera
            //Mientras no haya unidades de fermentacion disponible, espera
            while (uniFermentacion == 0) {

                // System.out.println(miembro + " NO HAY UNIDADES DE FERMENTACION DISPONIBLE");
                wait();
                if (testearVino) {
                    System.out.println("\u001B[35m ♥ ♥ ♥ " + miembro + " esta probando el vino de " + miembroVino + " ♥ ♥ ♥");
                    wait();
                }
            }
            uniFermentacion--;
            System.out.println("###" + miembro + " Esta FERMENTANDO el vino");
            notifyAll();
            sleep(300);

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Retira el vino de la unidad de fermentacion y decanta la levadura muerta
    public synchronized void decantarVino(String miembro) {
        try {
            uniFermentacion++;
            System.out.println("↓↓↓↓↓↓↓↓ " + miembro + " esta Decantando ↓↓↓↓↓↓↓↓ ");
            sleep(300);
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Termina su vino y lo da a probar a los demas
    Solo lo probaran aquellos miembros que esten inactivos
    Todo miembro que este en alguna etapa de preparacion del vino seguira trabajando
     */
    public synchronized void darAProbar(String miembro) {
        try {
            while (testearVino) {
                System.out.println("\u001B[35m ♥ ♥ ♥ " + miembro + " esta probando el vino de " + miembroVino + " ♥ ♥ ♥ A PUNTO DE PRODUCIR OTRO VINO");
                wait();

            }
            System.out.println("\033[32m██████████████████████████████████████");
            System.out.println("\033[32m█      FELICITACIONES: " + miembro + " HA TERMINADO SU VINO       █");
            System.out.println("\033[32m██████████████████████████████████████");
            sleep(300);

            testearVino = true;
            miembroVino = miembro;
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Despues de terminar el vino todos los que esten esperando (ya sea porque no hay jarras, estaciones de mezcla
    //o fermentadores ) se despertaran, probaran el vino y volveran a esperar, aquellos miembros que se hayan ido
    //por 4 meses se los esperara un tiempo y si no entran al almacen no lo probaran y el el miebro que hizo el vino
    //devolvera las jarras y se pondra a hacer otro vino, tampoco probara el vino aquel que este realizando alguna accion
    //y no este en estado de espera
    public synchronized void terminar(String miembro) {
        try {
            sleep(1);
            jarras += 2; //Devuelve las 2 jarras que tomo para que otro pueda usarlas
            System.out.println("◊ ◊ ◊  " + miembro + " Se prepara nuevamente para hacer mas vino ◊ ◊ ◊");

            miembroVino = "";
            testearVino = false;

            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //El administrador repone los ingredientes faltantes
    public synchronized void reponer() {
        try {
            //Mientras haya ingredientes suficientes el administrador espera
            while (ingredientesSuficientes()) {
                wait();
            }
            System.out.println("\u001B[36m↕↕↕↕ El Administrador esta reponiendo los ingredientes ↕↕↕↕");
            if (paqLevadura < 1) {
                paqLevadura += 20;
            }
            if (envase5 < 2) {
                envase5 += 15;
            }
            //sleep(500);
            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
