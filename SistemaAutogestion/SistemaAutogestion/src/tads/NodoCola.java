package tads;


public class NodoCola <T extends Comparable<T>>{


    private T dato;
    private NodoCola<T> sig;

    public NodoCola(T dato) {
        this.dato = dato;
        this.sig = null;
    }


    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoCola<T> getSig() {
        return sig;
    }

    public void setSig(NodoCola<T> sig) {
        this.sig = sig;
    }    
}
