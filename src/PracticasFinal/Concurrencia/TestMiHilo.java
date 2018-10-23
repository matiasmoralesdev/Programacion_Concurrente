
package PracticasFinal.Concurrencia;

public class TestMiHilo {
    public static void main(String[] args) {
        Datos x = new Datos(100);
        ProcesoUno pUno = new ProcesoUno(x);
        ProcesoDos pDos = new ProcesoDos(x);
        Thread hilo1 = new Thread(pUno);
        Thread hilo2 = new Thread(pDos);
        hilo1.start();
        hilo2.start();
        System.out.println("fin");
        System.out.println("vuelta en el main");
    }
}
