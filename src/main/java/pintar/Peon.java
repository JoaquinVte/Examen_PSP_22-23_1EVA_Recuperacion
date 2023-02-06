package pintar;

public class Peon implements Runnable {

    private static final int TIEMPO_INTERVALO = 13000;
    private Bote bote;
    private Palanca palanca;

    public Peon(Bote bote) {
        this.bote = bote;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(TIEMPO_INTERVALO);
                synchronized (bote) {
                    while (!bote.recargar())
                        this.bote.wait();

                    this.bote.notifyAll();
                    System.out.println("El peon ha recargado el bote.");
                    System.out.println(bote);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
