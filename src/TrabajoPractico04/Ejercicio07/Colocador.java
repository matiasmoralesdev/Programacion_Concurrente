package TrabajoPractico04.Ejercicio07;

public class Colocador implements Runnable {

    private CadenaMontaje cadena;

    public Colocador(CadenaMontaje chain) {
        this.cadena = chain;
    }

    @Override
    public void run() {

        while (true) {
            this.cadena.colocar();
        }
    }

}
