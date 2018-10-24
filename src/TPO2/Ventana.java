package TPO_2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Matias
 */
public class Ventana {

    Semaphore listo = new Semaphore(0);
    Semaphore pedido = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
    String comida;

    public void tomarPedido() {
        try {
            //Chef
            pedido.acquire();
            mutex.acquire();
            System.out.println("CHEF> Cocinando " + comida);
            mutex.release();
        } catch (InterruptedException ex) {
        }
    }

    public void entregarPedido() {
        try {
            //Chef
            mutex.acquire();
            System.out.println("CHEF> Termine pedido de " + comida);
            mutex.release();
            listo.release();
        } catch (InterruptedException ex) {
        }
    }

    public void dejarPedido(String comida) {
        try {
            mutex.acquire();
            this.comida = comida;
            System.out.println("MOZO> Dejando pedido " + comida);
            mutex.release();
            pedido.release();
        } catch (InterruptedException ex) {
        }
    }

    public void levantarPedido() {
        try {
            listo.acquire();
            mutex.acquire();
            System.out.println("MOZO>Llevando " + comida + " al cliente");
            mutex.release();
        } catch (InterruptedException ex) {
        }

    }

}
