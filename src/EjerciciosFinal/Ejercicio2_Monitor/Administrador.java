package EjerciciosFinal.Ejercicio2_Monitor;

public class Administrador implements Runnable{

    private String nombre;
    private Almacen almacen;
    public Administrador(String name,Almacen store) {
        nombre = name;
        almacen = store;
    }
    
    public void run(){
        while(true){
            almacen.reponer();
        }
    }

}
