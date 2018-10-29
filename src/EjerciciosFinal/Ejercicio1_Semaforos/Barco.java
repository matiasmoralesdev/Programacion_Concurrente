package EjerciciosFinal.Ejercicio1_Semaforos;

import EjerciciosFinal.Ejercicio1_Monitor.*;

public class Barco implements Runnable {

    private Transbordador transbordador;

    public Barco(Transbordador t) {
        transbordador = t;
    }

    @Override
    public void run() {
        while(true){
            transbordador.ir();
            transbordador.volver();
        }
    }
    
    
    
    
}
