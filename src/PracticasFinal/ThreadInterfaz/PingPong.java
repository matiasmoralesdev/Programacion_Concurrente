package PracticasFinal.ThreadInterfaz;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PingPong extends Thread {

    private String pal; //Lo que va a escribir
    private int delay; //Tiempo entre escritura

    public PingPong(String cartel, int cantMS) {
        pal = cartel;
        delay = cantMS;
    }

    public void run() {
        while (true) {
            System.out.println(pal + " ");

            try {
                sleep(delay);
            } catch (InterruptedException ex) {
                return;
            }
        }
    } // Fin Run

} //Fin Clase
