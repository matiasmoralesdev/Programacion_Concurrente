
package TPO2;

public class Productor extends Thread {

    private Buffer buffer;

    public Productor(String nombre, Buffer bf) {
        super(nombre);
        this.buffer = bf;
    }

    public void run() {
        while (true) {
            buffer.agregar();
        }
    }
}
