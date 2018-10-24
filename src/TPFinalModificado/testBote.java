package TPFinalModificado;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Matthew
 */
public class testBote {

    public static void main(String[] args) {
        Bote ship = new Bote();
        Thread t0 = new Thread(new Capitan(ship));
        Thread t1 = new Thread(new Pasajero("MISIONERO1", 1, 1, ship));
        Thread t2 = new Thread(new Pasajero("MISIONERO2", 1, 1, ship));
        Thread t3 = new Thread(new Pasajero("MISIONERO3", 1, 1, ship));
        Thread t4 = new Thread(new Pasajero("MISIONERO4", 1, 1, ship));
        Thread t5 = new Thread(new Pasajero("MISIONERO5", 1, 2, ship));
        Thread t6 = new Thread(new Pasajero("MISIONER6", 1, 2, ship));
        Thread t7 = new Thread(new Pasajero("MISIONERO7", 1, 2, ship));
        Thread t8 = new Thread(new Pasajero("MISIONERO8", 1, 2, ship));
        Thread t9 = new Thread(new Pasajero("CANIBAL1", 0, 1, ship));
        Thread t10 = new Thread(new Pasajero("CANIBAL2", 0, 1, ship));
        Thread t11 = new Thread(new Pasajero("CANIBAL3", 0, 1, ship));
        Thread t12 = new Thread(new Pasajero("CANIBAL4", 0, 1, ship));
        Thread t13 = new Thread(new Pasajero("CANIBAL5", 0, 2, ship));
        Thread t14 = new Thread(new Pasajero("CANIBAL6", 0, 2, ship));
        Thread t15 = new Thread(new Pasajero("CANIBAL7", 0, 2, ship));
        Thread t16 = new Thread(new Pasajero("CANIBAL8", 0, 2, ship));
        t0.start();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();
        t11.start();
        t12.start();
        t13.start();
        t14.start();
        t15.start();
        t16.start();

    }
}
