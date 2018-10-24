package TPO2;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Mozo extends Thread {

    private Ventana ventana;
    private ArrayList<String> pedidos = new ArrayList<>();
    private boolean vacio = false;
    private boolean dia = true;

    public Mozo(Ventana v) {
        this.ventana = v;
    }

    public void cerrar() {
        this.dia = false;
    }

    public void run() {
        int i = 1;
        while (dia) {
            this.dar("Frijoles " + i);
            ventana.dejarPedido(pedidos.get(0));
            pedidos.remove(0);
            ventana.levantarPedido();
            i++;
        }
        System.out.println("Hemos cerrado con ganancias");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
    }

    public void dar(String c) {
        pedidos.add(c);
        vacio = false;
    }

}
