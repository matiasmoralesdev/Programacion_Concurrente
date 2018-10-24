package TpFinalOtraForma;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Persona implements Runnable {

    private String nombre;
    private int tipo;
    private Bote unBote;
    private boolean entroBote;
    private boolean entroEmbarcadero;
    private int posEmbarcadero;
    private Vista vista;

    public Persona(String nombre, int tipo, int pos, Bote bote, Vista vis) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.unBote = bote;
        this.entroBote = false;
        this.entroEmbarcadero = false;
        this.posEmbarcadero = pos;
        this.vista=vis;
        
    }

    public String getNombre() {
        return nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean getEntroBote() {
        return entroBote;
    }

    public void setEntroBote(boolean entroBote) {
        if(entroBote)
        {
            this.vista.quitarPersona(this.nombre, this.posEmbarcadero);
        }//Cuando entra al bote la borro del embarcadero
        else
        {//Cuando sale del bote lo agrego al embarcadero
            //this.vista.agregarPersona(this.nombre, this.posEmbarcadero);
        }
        this.entroBote = entroBote;
    }

    public boolean getEntroEmbarcadero() {
        return entroEmbarcadero;
    }

    public void setEntroEmbarcadero(boolean entroEmbarcadero) {
        if(entroEmbarcadero)
        {
            //System.out.println("********************La persona "+this.nombre+" entro al embarcadero "+this.posEmbarcadero);
            this.vista.agregarPersona(this.nombre, this.posEmbarcadero);
        }//Agrego la persona al embarcadero
        else
        {
            this.vista.quitarPersona(this.nombre, this.posEmbarcadero);
        }//Cuando entra al bote la borro del embarcadero
        this.entroEmbarcadero = entroEmbarcadero;
    }

    public int getPosEmbarcadero() {
        return posEmbarcadero;
    }

    public void setPosEmbarcadero(int posEmbarcadero) {
        
        this.posEmbarcadero = posEmbarcadero;
        this.vista.agregarPersona(this.nombre, this.posEmbarcadero);//*****agrego persona al embarcadero cuando se baja del bote
        
    }

    public void run() {
        while (true) {
                //System.out.println("************************Embarcadero1: "+this.vista.mostrarEmbarcadero1());
                //System.out.println("************************Embarcadero2: "+this.vista.mostrarEmbarcadero1());
            try {
                
                Thread.sleep((int) (Math.random() * (1000 - 500) + 500));//Esto es para que no entren enseguida, asi pueden entrar en forma aleatoria
                if(!this.entroEmbarcadero)//esta condicion esta porque si la persona esta en el emabarcadero y no puede entrar al bote tiene que volver a entrar
                {//No esta en el embarcadero 
                    this.unBote.entrarAlEmbarcadero(this);
                    mostrarCantidadEmbarcadero();//Muetra quien ingresa y cuantas personas hay en cada embarcadero
                }
                
                if (this.entroEmbarcadero) {
                    //Puede entrar al bote
                    this.unBote.entrarBote(this);
                    if (this.entroBote) {
                        //System.out.println("Van viajando Canibales: "+this.cantCanibales+", Misioneros: "+this.cantMisioneros);
                        //el capitan despierta a los pasajeros que llegaron
                        //Thread.sleep(4000);
                        System.out.println("==="+this.nombre+ " se bajo del bote");
                        this.unBote.bajarBote(this);
                        Thread.sleep((int) (Math.random() * (3000 - 100) + 500));//Tiempo que permanecen en el embarcadero
                        this.unBote.salirDeEmbarcadero(this);
                    }
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String mostrarEmbarcaderos()
    {
        String salida="";
        salida+="--------------->Embarcadero1: "+this.vista.mostrarEmbarcadero1()+"\n";
        salida+="--------------->Embarcadero2: "+this.vista.mostrarEmbarcadero2()+"\n";
        return salida;
    }
    public void mostrarCantidadEmbarcadero()
    {
        System.out.println("    ---------->La persona: "+this.nombre+" ingreso al embarcadero ["+this.posEmbarcadero+"] Embarcadero1: "+this.vista.getCantEmbarcadero1()+" Embarcadero 2: "+this.vista.getCantEmbarcadero2());
    }
}
