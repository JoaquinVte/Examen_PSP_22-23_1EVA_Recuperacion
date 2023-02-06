package pintar;

import procesos.MaquinaRegistradora;

public class Pintor implements Runnable {

    private static final int CANTIDAD = 250;
    private static final int TIEMPO_APERTURA = 1000;
    private static final int TIEMPO_CARGA = 2000;
    private static final int TIEMPO_PINTAR = 5000;
    private Palanca palanca;
    private Bote bote;
    private String nombre;

    public Pintor(Palanca palanca, Bote bote, String nombre) {
        this.palanca = palanca;
        this.bote = bote;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        while(true){

            synchronized (palanca) {
                synchronized (bote) {
                    try {
                        while (!bote.abrir())
                            this.bote.wait();

                        Thread.sleep(TIEMPO_APERTURA);

                        this.bote.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (bote) {
                System.out.println(nombre + " ha abierto el bote.");
                while (!bote.cogerPintura(CANTIDAD)) {
                    try {
                        System.out.println(nombre + " no puede coger pintura.");
                        System.out.println(bote);
                        this.bote.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    Thread.sleep(TIEMPO_CARGA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(nombre + " ha cogido " + CANTIDAD + "ml, del bote.");
                System.out.println(bote);
                MaquinaRegistradora.getInstance().registrar(nombre,CANTIDAD);
                bote.cerrar();

                this.bote.notifyAll();
            }
            try {
                Thread.sleep(TIEMPO_PINTAR);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
