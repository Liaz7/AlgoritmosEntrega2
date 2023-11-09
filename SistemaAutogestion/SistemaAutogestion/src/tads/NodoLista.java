package tads;
import Entidades.Medico;
import Entidades.Paciente;

public class NodoLista<T extends Comparable<T>>{
    private T dato;
    private NodoLista<T> sig;

    //Constructor 
    public NodoLista(T n){
        this.dato=n;
        this.sig=null;
    }

    //Dato
    public void setDato(T d){
        this.dato=d;
    }
    public T getDato(){
        return this.dato;
    }

    //Siguiente
    public void setSiguiente(NodoLista<T> s){
        this.sig=s;
    }
    public NodoLista getSiguiente(){
        return this.sig;
    }

}
