import pintar.Bote;
import pintar.Palanca;
import pintar.Peon;
import pintar.Pintor;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Bote bote = new Bote();
        Palanca palanca = new Palanca();
        Peon peon = new Peon(bote);

        List<Pintor> pintors = new ArrayList<>();

        pintors.add(new Pintor(palanca,bote,"Luis"));
        pintors.add(new Pintor(palanca,bote,"Carlos"));
        pintors.add(new Pintor(palanca,bote,"Manuel"));
        pintors.add(new Pintor(palanca,bote,"Pedro"));
        pintors.add(new Pintor(palanca,bote,"Tomas"));

        for(Pintor pintor : pintors) {
            Thread t= new Thread(pintor);
            t.setDaemon(true);
            t.start();
        }

        Thread t= new Thread(peon);
        t.setDaemon(true);
        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
