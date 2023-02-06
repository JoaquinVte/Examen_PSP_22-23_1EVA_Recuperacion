package pintar;

public class Bote {

    private boolean abierto;
    private int capacidad;

    public Bote() {
        abierto = false;
        capacidad = 1000;
    }

    public synchronized boolean abrir(){
        if(abierto==true)
            return false;

        abierto = true;
        return abierto;
    }

    public synchronized boolean cerrar(){
        if(abierto==false)
            return false;

        abierto = false;
        return !abierto;
    }

    public synchronized boolean recargar(){
        if(!abierto)
            return false;

        capacidad = 1000;
        return true;
    }

    // segun enunciado siempre es 250, por lo que no es necesario pasar cantidad;
    public synchronized boolean cogerPintura(int cantidad){

        if(cantidad<0)
            return false;

        if(capacidad>=cantidad){
            capacidad-=cantidad;
            return true;
        }else
            return false;
    }

    @Override
    public String toString() {
        return "pintar.Bote{" +
                "capacidad=" + capacidad +
                "ml}";
    }
}
