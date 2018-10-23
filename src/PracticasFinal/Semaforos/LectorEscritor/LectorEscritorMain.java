package PracticasFinal.Semaforos.LectorEscritor;

public class LectorEscritorMain {

    public static void main(String[] args) {

        Libro libro = new Libro("El señor de los anillos", 100);
        Escritor e1 = new Escritor(libro, "Tolkien");
        Lector l1 = new Lector(libro, "Matias");
        Lector l2 = new Lector(libro, "Gabriela");
        Thread t1 = new Thread(e1);
        Thread t2 = new Thread(l1);
        Thread t3 = new Thread(l2);
        t1.start();
        t2.start();
        t3.start();

    }
}
