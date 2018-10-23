package PracticasFinal.Concurrencia;

public class ProcesoUno implements Runnable {

    private Datos unDato;

    public ProcesoUno(Datos d) {
        unDato = d;
    }

    @Override
    public void run() {
        System.out.println("estoy en Proceso Uno");
        if (unDato.getDato() > 100) {
            System.out.println(unDato.getDato());
        } else {
            System.out.println(unDato.getDato() - 50);
        }
    }

}
