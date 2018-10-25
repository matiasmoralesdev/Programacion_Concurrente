package EjerciciosFinal.Ejercicio2;

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

    public Almacen() {

    }

    public synchronized void entrar(String miembro) {
        try {

            System.out.println("► ► ► ► El miembro " + miembro + " esta entrando en el almacen");
            sleep(200);

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized boolean ingredientesSuficientes() {
        boolean valor = false;
        if (envase5 >= 2 && paqLevadura >= 1) {
            valor = true;
        }
        return valor;
    }

    public synchronized void tomarIngredientes(String miembro) {
        try {
            while (jarras == 0) {
                System.out.println("NO HAY JARRAS DISPONIBLES");
                wait();
            }
            while (!ingredientesSuficientes()) {
                System.out.println("NO HAY INGREDIENTES SUFICIENTES - SE AVISARA AL ADMINISTRADOR");
                notifyAll();
                wait();
            }
            while (uniFermentacion == 0) {
                System.out.println("NO HAY UNIDADES DE FERMENTACION DISPONIBLES");
                wait();
            }

            System.out.println("El miembro " + miembro + " esta tomando los ingredientes");
            //El miembro toma 2 jarras, 2 envases de 5 litros de jugo (10 litros)
            jarras = jarras - 2;
            envase5 = envase5 - 2;
            paqLevadura = paqLevadura - 1;
            uniFermentacion = uniFermentacion - 1;
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void prepararVino(String miembro) {
        try {
            while (estacionesDeMezcla == 0) {
                System.out.println("NO HAY ESTACIONES DE MEZCLA DISPONIBLES.....ESPERANDO QUE SE DESOCUPEN");
                wait();
            }
            estacionesDeMezcla--;
            System.out.println("El miembro " + miembro + " esta preparando vino");

        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void retirarVino(String miembro) {
        System.out.println("El miembro " + miembro + " esta retirando el vino");
        estacionesDeMezcla++;
        notifyAll();
    }

    public synchronized void salir(String miembro) {
        System.out.println("◄ ◄ ◄ ◄ El miembro " + miembro + " esta saliendo del almacen");
        uniFermentacion++;
        jarras = jarras + 2;
        notifyAll();
    }

    public synchronized void reponer() {
        try {
            while (ingredientesSuficientes()) {
                System.out.println("$$$$ El administrador no detecto falta de ingredientes");
                wait();
            }

            System.out.println("$$$$ El Administrador esta reponiendo los ingredientes");

            if (paqLevadura < 1) {
                paqLevadura += 20;
            }

            if (envase5 < 2) {
                envase5 += 15;
            }

            notifyAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
