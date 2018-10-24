package EjerciciosFinal.Ejercicio1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Coche implements Runnable {

    Transbordador transbordador;
    String id;
    Semaphore estaSubiendo = new Semaphore(1);

    public Coche(Transbordador t, String nombre) {
        this.transbordador = t;
        this.id = nombre;
    }

    @Override
    public void run() {

        transbordador.subir(this);
        
        //bajar();
    }
    
    private void subir(){
        try {
            
            this.estaSubiendo.acquire(); 
            System.out.println(">>> El coche: "+this.id+" esta subiendo al transbordador");
            
            this.estaSubiendo.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Coche.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
