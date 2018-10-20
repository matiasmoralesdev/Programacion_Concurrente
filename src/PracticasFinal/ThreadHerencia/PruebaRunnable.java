package PracticasFinal.ThreadHerencia;

import static java.lang.Thread.sleep;

public class PruebaRunnable {

    public static void main(String[] args) {
        //2 objetos definen los metodos run
        PingPong t1 = new PingPong("PING", 33);
        PingPong t2 = new PingPong("PONG", 10);

        //Se actvan los Threads
        t1.start();
        t2.start();

        //Espera 2 segundos
        try {
            sleep(5000);
        } catch (InterruptedException e) {
        }
        t1.stop(); t2.stop();
    }
}
