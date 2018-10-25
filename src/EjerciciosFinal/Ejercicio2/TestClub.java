package EjerciciosFinal.Ejercicio2;

public class TestClub {

    public static void main(String[] args) {

        String[] nombres = {"Alberto", "Belen", "Carlos", "Daniel", "Esteban", "Fernanda", "Gabriela", "Horacio", "Ivon", "Javier"};
        int cantidad = nombres.length;
        Miembro[] miembros = new Miembro[cantidad]; //Arreglo de miebros
        Almacen almacen = new Almacen();
        
        //Le asigna un nombre al administrador y a cada miembro
        Administrador admin = new Administrador("ADMINISTRADOR", almacen);
        for (int i = 0; i < cantidad; i++) {
            miembros[i] = new Miembro(nombres[i], almacen);
        }
        
        
        //Crea un arreglo de hilos con el administrador en la posicion 0 del arreglo
        Thread[] hilos = new Thread[cantidad + 1];
        for (int i = 1; i < (cantidad + 1); i++) {
            hilos[i] = new Thread(miembros[i-1]);
        }
        hilos[0] = new Thread(admin);
        
        
        //Pone a correr los hilos
        for (int i = 0; i < (cantidad + 1); i++) {
            hilos[i].start();
        }

    }
}
