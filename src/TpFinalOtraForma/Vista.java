package TpFinalOtraForma;

public class Vista {

    private int cantEmbarcadero1;
    private int cantEmbarcadero2;
    private String[] personasEmbarcadero1;
    private String[] personasEmbarcadero2;

    public Vista() {
        this.cantEmbarcadero1 = 0;
        this.cantEmbarcadero2 = 0;
        this.personasEmbarcadero1 = new String[15];
        this.personasEmbarcadero2 = new String[15];
        ponerBlancos();
    }

    public int getCantEmbarcadero1() {
        return cantEmbarcadero1;
    }

    public int getCantEmbarcadero2() {
        return cantEmbarcadero2;
    }
    
    private void ponerBlancos() {
        for (int i = 0; i < this.personasEmbarcadero1.length; i++) {
            personasEmbarcadero1[i] = "";
        }
        for (int i = 0; i < this.personasEmbarcadero2.length; i++) {
            personasEmbarcadero2[i] = "";
        }
    }

    public synchronized void agregarPersona(String nom, int posEmbar) {
        boolean agregado=false;
        if (posEmbar == 1) {
            this.cantEmbarcadero1++;
            int i = 0; 
            while (i < this.personasEmbarcadero1.length && !agregado ) {
                if(personasEmbarcadero1[i] == "")
                {
                    personasEmbarcadero1[i]=nom;
                    agregado=true;
                }
                i++;
            }
        }
        else
        {
            this.cantEmbarcadero2++;
            int i = 0; 
            while (i < this.personasEmbarcadero2.length && !agregado ) {
                if(personasEmbarcadero2[i] == "")
                {
                    personasEmbarcadero2[i]=nom;
                    agregado=true;
                    //System.out.println("-----------------------------"+nom+" fue agegado ---------------------");
                }
                i++;
            }
        }
    }
    public synchronized void quitarPersona(String nom, int posEmbar) {
        boolean quitado=false;
        if (posEmbar == 1) {
            this.cantEmbarcadero1--;
            int i = 0; 
            while (i < this.personasEmbarcadero1.length && !quitado ) {
                if(personasEmbarcadero1[i].equalsIgnoreCase(nom))
                {
                    personasEmbarcadero1[i]="";
                    quitado=true;
                }
                i++;
            }
        }
        else
        {
            this.cantEmbarcadero2--;
            int i = 0; 
            while (i < this.personasEmbarcadero2.length && !quitado ) {
                if(personasEmbarcadero2[i].equalsIgnoreCase(nom))
                {
                    personasEmbarcadero2[i]="";
                    quitado=true;
                }
                i++;
            }
        }
    }
    
    public String mostrarEmbarcadero1()
    {
        String salida="Total: "+this.cantEmbarcadero1 +"[";
        for (int i = 0; i < this.personasEmbarcadero1.length; i++) {
            if(!personasEmbarcadero1[i].equalsIgnoreCase(""))
            {
                salida+=personasEmbarcadero1[i]+", ";
            }
        }
        salida+="]";
        return salida;
    }
    public String mostrarEmbarcadero2()
    {
        String salida="Total: "+this.cantEmbarcadero2 +"[";
        for (int i = 0; i < this.personasEmbarcadero2.length; i++) {
            if(!personasEmbarcadero2[i].equalsIgnoreCase(""))
            {
                salida+=personasEmbarcadero2[i]+", ";
            }
        }
        salida+="]";
        return salida;
    }

}
