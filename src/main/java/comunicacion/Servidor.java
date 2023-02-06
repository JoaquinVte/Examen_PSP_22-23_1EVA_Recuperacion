package comunicacion;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {

        int port = 8000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {

            while (true) {

                try (Socket socket = serverSocket.accept()) {
                    System.out.println("Conexion aceptada desde " + socket.getInetAddress().getHostAddress());
                    try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                         ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

                        Mensaje mensaje;

                        while ((mensaje = (Mensaje) ois.readObject()) !=null ){
                            System.out.println("Servidor ha recibido "+ mensaje);
                            registrarEnFichero(mensaje);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }

        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    private static void registrarEnFichero(Mensaje mensaje){
        ProcessBuilder pb = new ProcessBuilder("/usr/bin/echo",mensaje.getFecha() + " - " + mensaje.getNombre() + " ha gastado " + mensaje.getCantidad() +"ml.");

        pb.redirectOutput(ProcessBuilder.Redirect.appendTo(new File("mifichero.txt")));

        // Se da por buena la siguiente linea aunque se pierdan los datos
        //pb.redirectOutput(new File("mifichero.txt"));

        try {

            Process p = pb.start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
