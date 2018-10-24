/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TpFinal;

/**
 *
 * @author LucasM
 */
public class TestMisionero {
    public static void main(String[] args) {
        Bote b1=new Bote();
        Capitan c=new Capitan(b1);
        Pasajero p1=new Pasajero("Misionero1",1,0,b1);
        Pasajero p2=new Pasajero("Misionero2",1,0,b1);
        Pasajero p3=new Pasajero("Misionero3",1,0,b1);
        Pasajero p4=new Pasajero("Misionero6",1,0,b1);
        //Pasajero p5=new Pasajero("Canibal2",1,1,b1);
        //Pasajero p6=new Pasajero("Canibal3",2,1,b1);
        //Pasajero p7=new Pasajero("Canibal4",2,1,b1);
        //Pasajero p8=new Pasajero("Canibal5",2,1,b1);
        Pasajero p9=new Pasajero("Misionero4",1,0,b1);
        Pasajero p10=new Pasajero("Misionero5",2,0,b1);
        Thread t1=new Thread(p1);
        Thread t2=new Thread(p2);
        Thread t3=new Thread(p3);
        Thread t4=new Thread(p4);
        /*
        Thread t5=new Thread(p5);
        Thread t6=new Thread(p6);
        Thread t7=new Thread(p7);
        Thread t8=new Thread(p8);*/
        Thread t9=new Thread(p9);
        Thread t10=new Thread(p10);
        Thread t11=new Thread(c);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        /*
        t5.start();
        t6.start();
        t7.start();
        t8.start();*/
        t9.start();
        t10.start();
        t11.start();
                
    }
}
