
package PracticasFinal.Concurrencia;

public class ProcesoDos implements Runnable{
    private Datos unDato;
    
    public ProcesoDos(Datos d){
        unDato = d;
    }
    
    @Override
    public void run(){
        System.out.println("Estoy en Proceso Dos");
        unDato.setDato(unDato.getDato()+10);
    }
}
