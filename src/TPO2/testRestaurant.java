package TPO2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testRestaurant {

    public static void main(String[] args) {
        Ventana v = new Ventana();
       // Mozo pedro = new Mozo(v);
        Chef carlos = new Chef(v);
        Random r = new Random();
        int tiempo = r.nextInt(24) + 10;
      //  pedro.start();
        carlos.start();
        for (int i = 0; i < tiempo; i++) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(testRestaurant.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (i + 1 == tiempo) {
                //pedro.cerrar();
                carlos.cerrar();
            }
        }

    }
}
