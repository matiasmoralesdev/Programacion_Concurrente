/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO_2;

/**
 *
 * @author Matias
 */
public class Chef extends Thread {

    private Ventana ventana;
    private boolean dia = true;

    public Chef(Ventana v) {
        this.ventana = v;
    }

    public void cerrar() {
        this.dia = false;
    }

    public void run() {
        while (dia) {
            ventana.tomarPedido();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
            ventana.entregarPedido();
        }
        System.out.println("Soy un chef cansado. A dormir");
    }
}
