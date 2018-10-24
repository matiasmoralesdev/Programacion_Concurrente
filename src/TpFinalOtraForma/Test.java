package TpFinalOtraForma;

public class Test {

    public static void main(String[] args) {
        /*aleatorio va indicar de que lado del embarcadero se creo la persona*/
        int Max = (2) + 1;// cota sup mas 1
        int Min = 1;
        int lugarEmbarcadero;

        Bote unBote = new Bote();
        Capitan c=new Capitan(unBote);
        Vista vistaEmbarcaderos=new Vista();

        Persona persona[] = new Persona[20];

        Thread arregloH[] = new Thread[20];
        //1 Misionero, 2:Canibal
        for (int i = 0; i < persona.length; i++) {
            lugarEmbarcadero=((int)(Math.random()*(Max-Min))+Min);
            if (i < 10) {
                persona[i] = new Persona("Misionero-" + i, 1, lugarEmbarcadero, unBote,vistaEmbarcaderos);
            } else {
                persona[i] = new Persona(("Canibal-" + (i - 10)), 2, lugarEmbarcadero, unBote,vistaEmbarcaderos);
            }
        }

        for (int i = 0; i < arregloH.length; i++) {
            arregloH[i] = new Thread(persona[i]);
        }

        for (int i = 0; i < arregloH.length; i++) {
            arregloH[i].start();
        }
        
        Thread hiloCapitan=new Thread(c);
        hiloCapitan.start();
    }
}
