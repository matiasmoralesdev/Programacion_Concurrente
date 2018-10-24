package TPO2;

public class Consumidor extends Thread {

    private Buffer buffer;

    public Consumidor(String nombre, Buffer bf) {
        super(nombre);
        this.buffer = bf;

    }

    public void run() {
        while (true) {
            buffer.sacar();
        }
    }
}
