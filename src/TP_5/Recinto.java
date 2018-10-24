package TP_5;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Matias
 */
public class Recinto {

    private Semaphore cantidad = new Semaphore(100);
    private Semaphore mostrador = new Semaphore(5);
    private Semaphore abridor = new Semaphore(10);
    private Semaphore postre = new Semaphore(3);

    public void entrar(int i) {
        try {
            cantidad.acquire();
            System.out.println(Thread.currentThread().getId() + "> Entré al recinto");
            this.irMostrador(i);
        } catch (InterruptedException ex) {
        }
    }

    public void irMostrador(int i) {
        try {
            mostrador.acquire();
            System.out.println(Thread.currentThread().getName() + "> Estoy en el mostrador");
            switch (i) {
                case 1:
                    mostrador.release();
                    System.out.println(Thread.currentThread().getName() + "> Sali del mostrador");
                    System.out.println(Thread.currentThread().getName() + "> Voy a buscar un abridor");
                    this.buscarAbridor();
                    break;
                case 2:
                    mostrador.release();
                    System.out.println(Thread.currentThread().getName() + "> Sali del mostrador");
                    System.out.println(Thread.currentThread().getName() + "> Voy a buscar un abridor y luego un postre >:v");
                    this.buscarAbridorConPostre();
                    break;
                case 0:
                    mostrador.release();
                    System.out.println(Thread.currentThread().getName() + "> Sali del mostrador");
                    break;
                default:
                    break;
            }
        } catch (InterruptedException ex) {
        }
    }

    private void buscarAbridor() {
        try {
            abridor.acquire();
            System.out.println(Thread.currentThread().getName() + "> Estoy abriendo la botella");
            Thread.sleep(1000);
            abridor.release();
            System.out.println(Thread.currentThread().getName() + "> Terminé de abrir la botella");
        } catch (InterruptedException ex) {
        }
    }

    private void buscarAbridorConPostre() {
        this.buscarAbridor();
        try {
            postre.acquire();
            System.out.println(Thread.currentThread().getName() + "> Buscando el postre");
            Thread.sleep(2000);
            postre.release();
        } catch (InterruptedException ex) {
        }
    }

    public void salir() {
        System.out.println(Thread.currentThread().getName() + "> Gracias por la comida!");
        cantidad.release();
    }
}
