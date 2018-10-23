package PracticasFinal.TPFinal2015;

import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rueda {

    private Semaphore turno;

    public Rueda() {
        this.turno = new Semaphore(1, true);
    }
    
    public Semaphore getTurno(){
        return this.turno;
    }
    

    public void accederRueda() {
        try {
            this.turno.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rueda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarRueda() {
        this.turno.release();
    }


/*
    public void girar(String nombre) {
        try {
            accederRueda();
            System.out.println("|||>"+nombre + " se subio a la rueda y esta girando!");
            sleep(1000);
            dejarRueda();
            System.out.println("<|||"+nombre + " dejo de girar y se bajo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Rueda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    */
    
    public synchronized void girar (String nombre){
        
        try {
            System.out.println(">>>>"+nombre + " se subio a la rueda y esta girando!");
            Random rnd = new Random();
             sleep(500 + rnd.nextInt(1000));
            
            System.out.println("<<<<"+nombre + " dejo de girar y se bajo");
        } catch (InterruptedException ex) {
            Logger.getLogger(Rueda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    } 

}
