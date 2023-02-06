package comunicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {

    private static Cliente cliente;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private Cliente(String ip, int port) {
        try {
            Socket socket = new Socket(ip, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Cliente getInstance(String ip, int port) {
        if (cliente == null)
            cliente = new Cliente(ip, port);

        return cliente;
    }



    public void send(Mensaje mensaje) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    oos.writeObject(mensaje);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
