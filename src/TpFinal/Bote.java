/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TpFinal;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LucasM
 */
public class Bote {

    private CyclicBarrier barreraBote;
    private Semaphore semaforoEmbarcadero1;
    private Semaphore semaforoEmbarcadero2;
    private Semaphore semaforoBloqueo;
    private Semaphore semaforoDespertarCapitan;
    private Semaphore mutex1;
    private int cantidadMisioneros;
    private int cantidadCanibales;
    private int posicionBote;

    public Bote() {
        this.barreraBote = new CyclicBarrier(4);
        this.semaforoEmbarcadero1 = new Semaphore(6);
        this.semaforoEmbarcadero2 = new Semaphore(6);
        this.semaforoBloqueo = new Semaphore(0);
        this.mutex1 = new Semaphore(1);
        this.semaforoDespertarCapitan = new Semaphore(0);
        this.cantidadMisioneros = 0;
        this.cantidadCanibales = 0;
        this.posicionBote = 1;
    }

    public void entrarAlEmbarcadero(Pasajero p) {

        try {
            if (p.getPosicionPasajero() == 1) {
                this.semaforoEmbarcadero1.acquire();
            } else {
                this.semaforoEmbarcadero2.acquire();
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(p.getNombre() + " Esta en el embarcadero!!");
    }

    public void subirAlBarco(Pasajero p) {
        try {
            while (!p.getCruzo()) {
               
                if (this.cantidadCanibales + this.cantidadMisioneros < 4) {
                    
                    while (p.getPosicionPasajero() != this.posicionBote) {
                        System.out.println(p.getNombre() + " esta esperando en puerto: " + p.getPosicionPasajero());
                        this.semaforoBloqueo.acquire();
                    }
                     
                    if (this.cantidadCanibales + this.cantidadMisioneros == 3) {

                        if ((this.cantidadMisioneros == 2 && this.cantidadCanibales == 1 && p.getTipo() == 0) || (this.cantidadCanibales == 3 && p.getTipo() == 0)) {
                            System.out.println("No pueden entrar mas misioneros!");
                            this.semaforoBloqueo.acquire();
                        } else if ((this.cantidadCanibales == 2 && p.getTipo() == 1) || (this.cantidadCanibales == 3 && p.getTipo() == 0)) {
                            System.out.println("No pueden subir mas canibales!");
                            this.semaforoBloqueo.acquire();

                        }

                    }
                    this.barreraBote.await();
                    if (p.getTipo() == 0) {
                        this.cantidadMisioneros++;
                    } else {
                        this.cantidadCanibales++;
                    }
                    System.out.println(p.getNombre() + " subio al bote.");
                    p.setCruzo(true);
                   
                    
                    
                } else {
                    System.out.println(p.getNombre() + " esta esperando en puerto: " + p.getPosicionPasajero());
                }
                 
            }

        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.semaforoDespertarCapitan.release();
    }

    public void comenzarViaje(Capitan c) {

        if (this.semaforoDespertarCapitan.availablePermits() == 0) {
            System.out.println("El capitan Duerme!");
        } else {
            System.out.println("El capitan comienza el viaje!!");
            c.setEstado(false);
        }

    }

    public void bajarDelBarco(Pasajero p) {

    }
}
