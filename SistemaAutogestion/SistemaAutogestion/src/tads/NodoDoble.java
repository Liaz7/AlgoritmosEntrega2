package tads;

public class NodoDoble<T extends Comparable<T>> {

    private T dato;
    private NodoDoble<T> sig;
    private NodoDoble<T> ant;

    //Constructor
    public NodoDoble(T n){
        this.dato=n;
        this.sig=null;
        this.ant=null;
    }

    //Dato
    public void setDato(T d){
        this.dato=d;
    }
    public T getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSig(NodoDoble s){
        this.sig=s;
    }
    public NodoDoble getSig(){
        return this.sig;
    }
        
    public NodoDoble getAnt() {
        return ant;
    }

    public void setAnt(NodoDoble ant) {
        this.ant = ant;
    }
}
