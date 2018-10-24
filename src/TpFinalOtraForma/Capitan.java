
package TpFinalOtraForma;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Capitan implements Runnable {

    private  Bote unBote;
    private boolean duerme;

    public Capitan(Bote b) {
        this.unBote = b;
        this.duerme = true;
    }

    public boolean getDuerme() {
        return duerme;
    }

    public void setDuerme(boolean duerme) {
        this.duerme = duerme;
    }
    
    public void run() {
        try {
            while (true) {
                this.unBote.comenzarViaje(this);
                System.out.println("El capitan empieza el viaje");
                Thread.sleep(4000);
                this.unBote.amarrarBote(this);
                

            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Capitan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setEstado(boolean b) {
        this.duerme = b;
    }
}
