package tads;

public interface ILista<T extends Comparable<T>> {    

    //Inicio
    public void setInicio(NodoLista<T> i);

    public NodoLista getInicio();

    //Fin
    public void setFin(NodoLista<T> f);

    public NodoLista getFin();

    
    //PRE:
    //POS: Retorna true si la lista no tiene nodos
    public boolean esVacia();

    //PRE: 
    //POS: Agrega un nuevo Nodo al principio de la lista
    public void agregarInicio(T n);

    //PRE:
    //POS: Agrega un nuevo Nodo al final de la lista
    public void agregarFinal(T x);

    //PRE:
    //POS: Borra el primer nodo
    public void borrarInicio();

    //PRE:
    //POS: Borra el último nodo
    public void borrarFin();

    //PRE:
    //POS: elimina todos los nodos de una lista dada
    public void vaciar();
    //en java alcanza con apuntar inicio y fin a null
    //inicio=fin=null;

    //PRE:
    //POS: Recorre y muestra los datos de lista
    public void mostrar();

    /**
     * ***Otros Métodos (iterativos)****
     */
    //PRE: lista ordenada => mantiena orden
    //POS: inserta nuevo elemento en orden ascendente
    public void agregarOrd(T x);
    //lista vacía o primer elemento es mayor o igual => agrego al ppio
    //último elemento es menor o igual => agrego al final

    //PRE: lista ordenada
    //POS: Borra la primer ocurrencia de un elemento dado
    public void borrarElemento(NodoLista<T> nodo);

    //PRE: 
    //POS: Retorna la cantidad de nodos que tiene la lista
    public int cantidadElementos();

    //PRE: //POS:
    public NodoLista<T> obtenerElemento(T n);    

    /**
     * *** Métodos RECURSIVOS  ****
     */
    //PRE:
    //POS: muestra los datos de la lista en orden de enlace
    public void mostrarRec(NodoLista<T> nodo);

    //PRE:
    //POS: muestra los datos de la lista en orden inverso
    public void mostrarInversoRec(NodoLista<T> nodo);
/*
    //PRE:
    //POS: retorna true si el elemento pertenece a la lista
    public boolean existeDatoRec(NodoLista<T> nodo, int n);

    public boolean pertenece(int x);

    public void borrar(int x);

    public int largo();

    public void snoc(int x);

    public void snocR(NodoLista<T> indice, int x);   


    


    public int cantidadElementos();*/
    
    public boolean estaElemento(NodoLista<T> nodo);
}
