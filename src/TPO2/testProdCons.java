
package TPO2;

public class testProdCons {

    public static void main(String[] args) {
        Buffer bf = new Buffer();
        Productor[] productores = new Productor[3];
        Consumidor[] consumidores = new Consumidor[4];

        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i] = new Consumidor("Consumidor "+i,bf);
        }

        for (int i = 0; i < productores.length; i++) {
            productores[i] = new Productor("Productor "+i,bf);
        }

        for (int i = 0; i < consumidores.length; i++) {
            consumidores[i].start();
        }
        for (int i = 0; i < productores.length; i++) {
            productores[i].start();
        }

    }

}
