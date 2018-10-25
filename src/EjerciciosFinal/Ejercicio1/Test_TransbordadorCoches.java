package EjerciciosFinal.Ejercicio1;

public class Test_TransbordadorCoches {

    public static void main(String[] args) {

        int CANTCOCHES = 100;    //Cantidad de Coches totales
        Transbordador transbordador = new Transbordador();  //Crea un transbordador
        Thread hiloTransbordador = new Thread(transbordador);   //Crea el hilo Runnable de transbordador
        Thread[] coleccionDeHilos = new Thread[CANTCOCHES]; //Crea la coleccion de Hilos (autos)

        for (int i = 0; i < CANTCOCHES; i++) {
            coleccionDeHilos[i] = new Thread(new Coche(transbordador, ("Coche_" + i))); //Cada hilo es un auto nuevo con su propio nombre
        }

        hiloTransbordador.start();  //Comienza la ejecucion del Transbordador
        for (int i = 0; i < CANTCOCHES; i++) {
            coleccionDeHilos[i].start();    //Comienza la ejecucion de los coches
        }

    }//Fin del main
}
