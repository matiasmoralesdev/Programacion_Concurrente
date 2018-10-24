package TPFinalModificado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Matthew
 */
public class Bote {

    private int aBordo, cantCanibal, cantMisionero, cantPuerto1, cantPuerto2, muelleActual, muelleDestino;
    private boolean llego, enViaje;
    private boolean ayuda;

    public Bote() {
        this.aBordo = 0; //Cuenta cuantos pasajero hay a bordo del bote
        this.cantCanibal = 0; //Cuenta la cantidad de canibales a bordo del bote
        this.cantMisionero = 0; //Cuenta la cantidad de misioneros a bordo del bote
        this.cantPuerto1 = 0; //Cuenta la cantidad de pasajeros esperando en el puerto 1
        this.cantPuerto2 = 0; //Cuenta la cantidad de pasajeros esperando en el puerto 1
        this.muelleActual = 1;  //El muelle actual donde esta amarrado el bote
        this.muelleDestino = 2; //El muelle opuesto a donde esta amarrado el bote
        this.llego = false; //Boolean que sirve de bandera para evitar que los pasajeros bajen del bote antes de llegar a destino
        this.enViaje = false; //Boolean que sirve para evitar que personas suban al bote antes que todas bajen
        this.ayuda=true;
    }

    public synchronized void accederAlPuerto(String nombre, int tipo, int muelle) {
        //Es la primera accion que toma un pasajero, la de acceder al puerto
        while ((muelle == 1 && this.cantPuerto1 >= 6) || (muelle == 2 && this.cantPuerto2 >= 6)) {
            //Si alguno de los 2 muelles tienen 6 personas, las que quieran entrar volveran a las montañas
            try {
                System.out.println("El Pasajero " + nombre + " no pudo entrar al puerto " + muelle + ", volviendo a las montañas...");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        System.out.println("El Pasajero " + nombre + " accedio al puerto " + muelle);

        //Suma el pasajero a su respectivo muelle
        if (muelle == 1) {
            this.cantPuerto1 = this.cantPuerto1 + 1;
        } else {
            this.cantPuerto2 = this.cantPuerto2 + 1;
        }

        System.out.println("HAY " + this.cantPuerto1 + " EN EL MUELLE 1 Y " + this.cantPuerto2 + " EN EL MUELLE 2");
    }

    public synchronized void subirAlBote(String nombre, int tipo, int muelle) {
        //Una vez en el puerto, el pasajero intentara subir al bote

        /* Razones por las cual, un pasajero no puede subir al bote:
         1- El bote esta viajando, es decir que aunque haya llegado al otro muelle, primero se tienen que bajar todas las personas "this.enViaje"
         2- El bote no este en el muelle donde esta el pasajero "this.muelleActual != muelle"
         3- El bote esta lleno "this.aBordo == 4"
         4- Hay 3 canibales en el bote y el que quiere ingresar es un misionero "(this.cantCanibal == 3 && tipo == 1)"
         5- Hay 3 misioneros en el bote y el que quiere ingresar es un canibal "(this.cantMisionero == 3 && tipo == 0)"
         6- Hay 2 canibales y 1 misionero, y quiere ingresar un canibal (se comerian al misionero) "((this.cantCanibal == 2 && this.cantMisionero == 1) && tipo == 0)"
         7- Hay 2 misionero y 1 canibal, y quiere ingresar un misionero (convertirian al canibal) "((this.cantCanibal == 1 && this.cantMisionero == 2) && tipo == 1)"
         */
        while (this.enViaje || this.muelleActual != muelle || (this.aBordo == 4 || (this.cantCanibal == 3 && tipo == 1) || (this.cantMisionero == 3 && tipo == 0)
                || ((this.cantCanibal == 2 && this.cantMisionero == 1) && tipo == 0)
                || ((this.cantCanibal == 1 && this.cantMisionero == 2) && tipo == 1))) {
            try {
                System.out.println("El pasajero " + nombre + " no pudo subir al bote...");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Suma el respectivo pasajero al bote
        if (tipo == 0) {
            this.cantCanibal = this.cantCanibal + 1;
        } else {
            this.cantMisionero = this.cantMisionero + 1;
        }

        //Como el pasajero ya no esta en el muelle, deja el espacio para que otro pasajero entre al muelle
        if (muelle == 1) {
            this.cantPuerto1 = this.cantPuerto1 - 1;
        } else {
            this.cantPuerto2 = this.cantPuerto2 - 1;
        }

        //Suma la cantidad de pasajeros a bordo
        this.aBordo = this.aBordo + 1;
        System.out.println("El Pasajero " + nombre + " ha subido al barco!!");
        System.out.println("SE HAN SUBIDO " + this.aBordo + " PERSONAS! -- CANIBALES:" + this.cantCanibal + " MISIONEROS:" + this.cantMisionero);

        notifyAll();
    }

    public synchronized void viajar() {
        //Accion del Capitan

        //Mientras haya gente en el bote (despues de un viaje) el capitan no intentara viajar nuevamente
        while (enViaje) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Mientras el bote no tenga 4 pasajeros a bordo, el capitan dormira
        while (this.aBordo != 4) {
            try {
                System.out.println("El Bote no esta lleno, el capitan duerme... ZZZzzzzZZzzzz....");
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Una vez hayan 4 pasajeros en el bote el capitan zarpa hacia el otro puerto
        this.enViaje = true;
        System.out.println("EL CAPITAN ENCENDIO EL MOTOR Y ESTA ZARPANDO HACIA EL MUELLE DE DESTINO!!!");

    }

    public synchronized void amarrar() {
        //Elige uno de sus pasajeros (preguntar si el capitan elige algun pasajero especifico o es solo simbolico)

        System.out.println("El Capitan se baja a amarrar un cabo del bote en el puerto " + this.muelleDestino + " .-.-.-.-.-. ");
        this.llego = true;
        this.notify();
        

        //Levanta la bandera para que los pasajeros puedan empezar a bajarse del bote
        
        notifyAll();
    }

    public synchronized void bajarDelBote(Pasajero p) {
        //Accion del Pasajero

        //No puede bajarse del bote hasta que este no llegue a destino
        while (!llego) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(this.ayuda){
            System.out.println(p.getNombre()+" ayuda a amarrar!!");
            this.ayuda=false;
        }
        //Si llego a destino se baja del bote
        this.aBordo = this.aBordo - 1;

        //depende que tipo de pasajero sea, se resta a la cantidad de pasajero de ese tipo
        if (p.getTipo() == 0) {
            this.cantCanibal = this.cantCanibal - 1;
        } else {
            this.cantMisionero = this.cantMisionero - 1;
        }

        //Cambia el puerto de origen de dicho pasajero y le asigna el nuevo puerto al que llego
        System.out.println("<<<< " + p.getNombre() + " Se ha bajado del Bote en el puerto " + this.muelleDestino);
        p.setPuerto(this.muelleDestino);

        //Si ya se bajaron todos del bote, cambia el muelle de destino por el de origen y viceversa
        //Setea el "llego" en false, para que el viaje vuelva a comenzar
        // y a enViaje para que el capitan intente viajar nuevamente
        if (this.aBordo == 0) {
            int cambio = this.muelleActual;
            this.muelleActual = this.muelleDestino;
            this.muelleDestino = cambio;
            llego = false;
            this.enViaje = false;
            this.ayuda=true;
            System.out.println("EL BARCO ESTA LIBRE NUEVAMENTE EN EL PUERTO " + this.muelleActual);
        }

        notifyAll();

    }

    public synchronized void irAMontañas(Pasajero p) {
        //Una vez que todos se bajaron, empezaran a irse a las montañas

        /*
         while (this.aBordo != 0) {
         try {
         wait();
         } catch (InterruptedException ex) {
         Logger.getLogger(Bote.class.getName()).log(Level.SEVERE, null, ex);
         }
         }
        
        
         */
        System.out.println("El Pasajero " + p.getNombre() + " fue a las montañas  ^^^ ^^ ^^^ ");
        notifyAll();
    }

}
