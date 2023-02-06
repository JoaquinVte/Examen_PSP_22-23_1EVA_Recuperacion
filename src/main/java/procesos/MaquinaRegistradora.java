package procesos;

import comunicacion.Cliente;
import comunicacion.Mensaje;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.io.BufferedReader;

import java.io.IOException;


public class MaquinaRegistradora {

    private static MaquinaRegistradora registro;
    private Cliente cliente;

    private MaquinaRegistradora(){
        cliente = Cliente.getInstance("127.0.0.1",8000);
    }

    public static MaquinaRegistradora getInstance(){
        if(registro==null)
            registro = new MaquinaRegistradora();

        return registro;
    }

    public void registrar(String nombre, int cantidad){
        ProcessBuilder pb = new ProcessBuilder("/usr/bin/date","+%c");

        try {

            Process p = pb.start();

            try (InputStream is = p.getInputStream();
                 InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader br = new BufferedReader(isr)) {

                int codRet = p.waitFor();
                String linea;
                linea = br.readLine();

                Mensaje mensaje = new Mensaje(linea, nombre, cantidad);
                System.out.println("Enviando " + mensaje);
                cliente.send(mensaje);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

    }

} 