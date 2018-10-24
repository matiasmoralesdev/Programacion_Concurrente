package TpFinalOtraForma;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bote {
    private Semaphore semaforoDespertarCapitan;
    private Semaphore desembarcar;
    private CyclicBarrier barrera;
    private Semaphore semaforoEmbarcadero1;
    private Semaphore semaforoEmbarcadero2;
    

    private Semaphore controlPosBote1;
    private Semaphore controlPosBote2;
    private Semaphore controlTotal1;
    private Semaphore controlTotal2;
    private Semaphore bajarBote;
    private int falta;
    private int posBote;
    private int cantBote;
    private int cantCanibales;
    private int cantMisioneros;
    
    private boolean ayuda;

    public Bote() {
        this.barrera = new CyclicBarrier(4);
        this.semaforoEmbarcadero1 = new Semaphore(6);
        this.semaforoEmbarcadero2 = new Semaphore(6);
        this.controlPosBote1 = new Semaphore(1);//esta en 1 porque el bote esta en la posicion 1
        this.controlPosBote2 = new Semaphore(0);//esta en 0 porque el bote esta en la posicion 1
        this.controlTotal1 = new Semaphore(1);
        this.controlTotal2 = new Semaphore(0);
        this.bajarBote = new Semaphore(1);
        this.cantBote = 0;
        this.cantCanibales = 0;
        this.cantMisioneros = 0;
        this.falta = 0;
        this.posBote = 1;//El bote arranca en el embarcedero 1
        
        this.semaforoDespertarCapitan = new Semaphore(0);
        this.desembarcar = new Semaphore(0);
        
        this.ayuda=true;
    }

    public void entrarAlEmbarcadero(Persona p) {

        try {
            if (p.getPosEmbarcadero() == 1) {
                if(this.semaforoEmbarcadero1.availablePermits()==0)
                {
                    System.out.println("xxxxxxxxxxx "+p.getNombre()+" no puede entrar al embarcadero 1");
                }
                this.semaforoEmbarcadero1.acquire();
                p.setEntroEmbarcadero(true);
            } else {
                if(this.semaforoEmbarcadero2.availablePermits()==0)
                {
                    System.out.println("xxxxxxxxxxx "+p.getNombre()+" no puede entrar al embarcadero 2");
                }
                this.semaforoEmbarcadero2.acquire();
                p.setEntroEmbarcadero(true);//Indico que esta en el embarcadero
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println(p.getNombre() + " Esta en el embarcadero!!");
    }

    public void entrarBote(Persona persona) {
        if (persona.getPosEmbarcadero() == 1) {
            try {
                //Si no puede entrar al bote se queda esperando
                this.controlTotal1.acquire();
                entrarBotePorEmbarcadero1(persona);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                this.controlTotal2.acquire();//Este se libera cuando el bote llega a la posicion 2
                entrarBotePorEmbarcadero2(persona);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void entrarBotePorEmbarcadero1(Persona persona) {
        try {
            if (this.cantBote <= 2 || this.falta == persona.getTipo()) {
                //Entro al bote
                //this.semaforoEmbarcadero1.release();//dejo entrar al embarcadero
                this.cantBote++;
                if (persona.getTipo() == 1) {//Es misionero
                    this.cantMisioneros++;
                } else {//Es canibal
                    this.cantCanibales++;
                }
                //Verifico cuando hay en el bote
                if (this.cantBote >= 3) {//Cuando hay tres personas verifico quien falta
                    this.falta = quienFalta();
                }
                System.out.println("La Persona " + persona.getNombre() + " subio al bote en el embarcadero 1");
                persona.setEntroBote(true);//La persona entro al bote
                this.semaforoEmbarcadero1.release();//dejo entrar al embarcadero
                //si hay cuatro personas en el bote no dejo que entren a preguntar mas (el bote esta lleno)
                if (this.cantBote != 4) {
                    this.controlTotal1.release();//Dejo ingresar otra persona para ver
                }
                else
                {//no dejo entrar mas personas al bote
                    this.semaforoDespertarCapitan.release();//se lleno el bote
                }

                this.barrera.await();
                
                this.posBote = 2;//cuando llegue al otro lado esta es la nueva posicion

                
                
                this.desembarcar.acquire();//Este semaforo lo libera el capitan cuando llegan a la otra orilla
                if(this.ayuda)
                {
                    System.out.println("Llegaron Canibales: " + this.cantCanibales + ", Misioneros: " + this.cantMisioneros + " al embarcadero 2");
                    System.out.println("El Pasajero "+persona.getNombre()+" ayuda al capitan amarrar el barco");
                    this.ayuda=false;
                }
            } else {//Si la persona no entro al bote solo sale y se queda en el embarcadero
                System.out.println("La Persona: " + persona.getNombre() + " No pudo ingresar al bote, se queda en embarcadero 1");
                this.controlTotal1.release();
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Solo entra cuando hay tres personas en el bote
    //esta metodo nos dice quien es el ultimo que falta entrar al bote
    private int quienFalta() {//1:Misionero 2: Canibal
        int numFalta = 0;
        if (this.cantCanibales == 2) {
            numFalta = 1;//Falta un misionero
        }
        if (this.cantMisioneros == 2) {
            numFalta = 2;//Falta un canibal
        }
        if (this.cantMisioneros == 3) {
            numFalta = 1;//Solo puede entrar un misionero
        }
        if (this.cantCanibales == 3) {
            numFalta = 2;//Solo Puede entrar un canibal
        }
        return numFalta;
    }

    private void entrarBotePorEmbarcadero2(Persona persona) {
        try {
            if (this.cantBote <= 2 || this.falta == persona.getTipo()) {//espero porque no puede entrar al bote
                this.cantBote++;
                if (persona.getTipo() == 1) {//Es misionero
                    this.cantMisioneros++;
                } else {//Es canibal
                    this.cantCanibales++;
                }
                //Verifico cuando hay en el bote
                if (this.cantBote >= 3) {//Cuando hay tres personas verifico quien falta
                    this.falta = quienFalta();
                }
                System.out.println("La Persona " + persona.getNombre() + " subio al bote en el embarcadero 2");
                persona.setEntroBote(true);//La persona entro al bote
                this.semaforoEmbarcadero2.release();//dejo entrar al embarcadero
                //si hay cuatro personas en el bote no dejo que entren a preguntar mas (el bote esta lleno)
                if (this.cantBote != 4) {
                    this.controlTotal2.release();//Dejo ingresar otra persona para ver
                }
                else
                {
                    //La cuarta persona que entra despierta al capitan
                    this.semaforoDespertarCapitan.release();//Se lleno el bote
                }
                this.barrera.await();
                
                this.posBote = 1;//cuando llegue al otro lado esta es la nueva posicion

                
                
                this.desembarcar.acquire();//Este semaforo lo libera el capitan cuando llegan a la otra orilla
                
                if(this.ayuda)
                {
                    System.out.println("Llegaron Canibales: " + this.cantCanibales + ", Misioneros: " + this.cantMisioneros + " al embarcadero 1");
                    System.out.println("El Pasajero "+persona.getNombre()+" ayuda al capitan amarrar el barco");
                    this.ayuda=false;
                }
                
            } else {//Si la persona no entro al bote solo sale y se queda en el embarcadero
                System.out.println("La Persona: " + persona.getNombre() + " No pudo ingresar al bote, se queda en embarcadero 2");
                this.controlTotal2.release();
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajarBote(Persona persona) {
        try {
            this.bajarBote.acquire();
            this.cantBote--;
            
            if (this.posBote == 1) {
                persona.setPosEmbarcadero(1);//Cambio el lugar de la persona
                //System.out.println(persona.mostrarEmbarcaderos());//Para mostrar que esta en embarcadero
                persona.setEntroEmbarcadero(false);//La persona se va enseguida del embarcadero
                //System.out.println(persona.mostrarEmbarcaderos());
            } else {
                persona.setPosEmbarcadero(2);//Cambio el lugar de la persona
                //System.out.println(persona.mostrarEmbarcaderos());
                persona.setEntroEmbarcadero(false);
                //System.out.println(persona.mostrarEmbarcaderos());
            }
            persona.setEntroBote(false);//Salio del bote
            if (persona.getTipo() == 1) {
                this.cantMisioneros--;//Podria directamente setearlas en ceros las dos variables                
            } else {
                this.cantCanibales--;
            }
            if (this.cantBote == 0) {
                this.ayuda=true;
                System.out.println("Se bajaron todos, el bote esta vacio");
                System.out.println(persona.mostrarEmbarcaderos());
                if (this.posBote == 1) {
                    this.controlTotal1.release();//Libero para que puedan empezar a ingresar en el embarcadero 1
                } else {
                    this.controlTotal2.release();//Libero para que puedan empezar a ingresar en el embarcadero 2
                }

            }

            this.bajarBote.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salirDeEmbarcadero(Persona persona) {
        //persona.setEntroEmbarcadero(false);//lo estoy sacando en el sali de bote

    }
    
    public void comenzarViaje(Capitan c) {
        
        if(c.getDuerme())
        {
            System.out.println("El capitan esta durmiendo");
        }
        try {
            this.semaforoDespertarCapitan.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void amarrarBote(Capitan cap)
    {
        
        //despierto a un pasajero del bote
        this.desembarcar.release();
        System.out.println("El capitan desamarra el bote");
        try {
            Thread.sleep(5000);//Tiempo que tarda en amarrar el bote
        } catch (InterruptedException ex) {
            Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.desembarcar.release(3);//bajan los otros tres pasajeros
    
                }

}
