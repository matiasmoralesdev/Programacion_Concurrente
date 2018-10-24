/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosTeoria;

/**
 *
 * @author LucasM
 */
public class TestBarbero {
    public static void main(String[] args) {
        Barberia nuevaBarberia= new Barberia();
        Barbero nuevoBarbero=new Barbero(nuevaBarberia);
        ClienteBarberia c1=new ClienteBarberia("Lucas",nuevaBarberia);
        ClienteBarberia c2=new ClienteBarberia("Matias",nuevaBarberia);
        ClienteBarberia c3=new ClienteBarberia("Exequiel",nuevaBarberia);
        ClienteBarberia c4=new ClienteBarberia("Carlos",nuevaBarberia);
        ClienteBarberia c5=new ClienteBarberia("Maria",nuevaBarberia);
        ClienteBarberia c6=new ClienteBarberia("Rosa",nuevaBarberia);        
        Thread hilo1= new Thread(nuevoBarbero);
        Thread hilo2= new Thread(c1);
        Thread hilo3= new Thread(c2);
        Thread hilo4= new Thread(c3);
        Thread hilo5= new Thread(c4);
        Thread hilo6= new Thread(c5);
        Thread hilo7= new Thread(c6);
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        
    }
}
