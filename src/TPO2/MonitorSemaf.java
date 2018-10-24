package TPO2;

public class MonitorSemaf {

    private int permisos;

    public MonitorSemaf(int n) {
        //N representa la cantidad de permisos 
        this.permisos = n;
    }

    public synchronized void acquireM() {
        while (permisos == 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " espera. MutEx o vacio/lleno.");
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        permisos--;

    }

    public synchronized void releaseM() {
        permisos++;
        this.notifyAll();
    }

    public boolean tryAcquireM() {
        boolean res = permisos > 0;
        return res;
    }

    public synchronized void acquireM(int n) {
        while (permisos == 0 && permisos - n <= 0) {
            try {
                System.out.println(Thread.currentThread().getName() + " espero...");
                this.wait();
            } catch (InterruptedException ex) {
            }
        }
        permisos -= n;
    }

    public synchronized void releaseM(int n) {
        permisos += n;
        this.notifyAll();
    }
}
