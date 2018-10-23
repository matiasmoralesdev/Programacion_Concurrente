package PracticasFinal.TPFinal2015;

public class Hamster implements Runnable {

    private String nombre;
    private Plato plato;
    private Rueda rueda;

    public Hamster(String name, Plato p, Rueda r) {
        this.nombre = name;
        this.plato = p;
        this.rueda = r;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {
            
            plato.comer(this.nombre);

            rueda.girar(this.nombre);
        }

    }

}
