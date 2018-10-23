package PracticasFinal.TPFinal2015;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Plato {

    private Semaphore turno;

    public Plato() {
        this.turno = new Semaphore(3, true);
    }

    public void come() {
        try {
            this.turno.acquire();

        } catch (InterruptedException ex) {
            Logger.getLogger(Plato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarComer() {
        this.turno.release();
    }

    public void comer(String nombre) {
        try {
            come();
            System.out.println("--->" + nombre + " esta comiendo! " +turno.availablePermits());
            Random rnd = new Random();
            sleep(500 + rnd.nextInt(1000));
            dejarComer();
            System.out.println("<---" + nombre + " dejo de comer!");
        } catch (InterruptedException ex) {
            Logger.getLogger(Plato.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
