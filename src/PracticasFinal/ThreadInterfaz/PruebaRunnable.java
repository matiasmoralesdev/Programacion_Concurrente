package PracticasFinal.ThreadInterfaz;

public class PruebaRunnable {
    public static void main(String[] args) {
         //2 objetos definen los metodos run
         PingPong o1 = new PingPong ("PING", 33);
         PingPong o2 = new PingPong ("PONG", 10);
         
         //Se crean los Threads
         Thread t1 = new Thread (o1);
         Thread t2 = new Thread (o2);
         
         //Se actvan los Threads
         t1.start();
         t2.start();
         
    }
}
