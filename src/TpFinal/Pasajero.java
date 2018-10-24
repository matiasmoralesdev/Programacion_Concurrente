/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TpFinal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LucasM
 */
public class Pasajero implements Runnable {

    private Bote unBote;
    private String nombre;
    private int posPasajero;
    private int tipo;
    private boolean cruzo;

    public Pasajero(String n, int p, int t,Bote b) {
        this.unBote=b;
        this.nombre = n;
        this.posPasajero = p;
        this.tipo = t;
        this.cruzo=false;
    }
    
    public void run(){
        
    
    
        this.unBote.subirAlBarco(this);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pasajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    }

    public int getPosicionPasajero() {
        return this.posPasajero;
    }

    public String getNombre() {
        return this.nombre;
    }
    
    public int getTipo(){
    return this.tipo;
    }
    
    public boolean getCruzo(){
    return this.cruzo;
    }
    
    public void setCruzo(boolean b){
    this.cruzo=b;
    }
}
