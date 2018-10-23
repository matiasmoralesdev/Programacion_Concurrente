package PracticasFinal.Semaforos.LectorEscritor;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Libro {

    private Semaphore leyendo, escribiendo, mutex1, mutex2;
    private int totalPag; //Cantidad maxima de paginas del libro
    private int cantLec; //Cantidad de lectores
    private int cantEsc; //Cantidad de Escritores
    private int cantPag; //Escritas
    private String nombre;

    public Libro(String name, int cant) {
        this.leyendo = new Semaphore(1, true);
        this.escribiendo = new Semaphore(1, true);
        this.mutex1 = new Semaphore(1, true);
        this.mutex2 = new Semaphore(1, true);
        this.totalPag = cant;
        this.cantLec = 0;
        this.cantPag = 0;
        nombre = name;
    }

    public void empezarLeer(Lector lector) {
        try {
            leyendo.acquire();
            mutex1.acquire();
            this.cantLec++;
            System.out.println(">>>>El lector " + lector.getNombre() + " ha empezado a leer");
            if (this.cantLec == 1) {
                this.escribiendo.acquire();
            }
            leyendo.release();
            mutex1.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarLeer(Lector lector) {
        try {
            mutex1.acquire();
            cantLec--;
            System.out.println("El lector " + lector.getNombre() + " ha terminado de leer");
            if (cantLec == 0) {
                leyendo.release();
                System.out.println("No hay nadie leyendo");
            }
            mutex1.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void empezarEscribir(Escritor escritor) {
        try {
            escribiendo.acquire();
            mutex2.acquire();
            this.cantEsc++;
            System.out.println("++++El escritor " + escritor.getNombre() + " ha empezado a escribir");
            if (cantEsc == 1) {
                leyendo.acquire(); //Nadie puede leer si el escritor esta escribiendo
            }
            mutex2.release();
            //escribiendo.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarEscribir(Escritor escritor) {
        try {
            escribiendo.release();
            System.out.println("----El escritor " + escritor.getNombre() + " ha terminado a escribir");
            mutex2.acquire();
            cantEsc--;
            if (cantEsc == 0) {
                leyendo.release();
            }
            cantPag++;
            mutex2.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean hayEscrito() {
        return cantPag != 0;
    }

    public boolean finalizado() {
        return cantPag == this.totalPag;
    }

}
