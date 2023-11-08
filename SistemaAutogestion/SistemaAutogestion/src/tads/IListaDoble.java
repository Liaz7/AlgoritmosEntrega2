package tads;


public interface IListaDoble<T extends Comparable<T>> {
public int getCantidad();
public void setCantidad(int cantidad);
public NodoDoble<T> getInicio();
public void setInicio(NodoDoble<T> inicio);
public NodoDoble<T> getFin();
public void setFin(NodoDoble<T> fin);
public NodoDoble<T> getIndice();
public void setIndice(NodoDoble<T> indice);
public boolean esVacia();
public void agregarInicio(T Objeto);
public void agregarFinal(T Objeto);
public void agregarOrd(T n);
public void borrarInicio();
public void borrarFin();
public void borrarElemento(T Objeto);
public boolean buscarElemento(T Objeto);
public NodoDoble<T> obtenerElemento(T Objeto);
public void vaciar();
public void mostrar();
public void mostrarRec();
public int cantElementos();
public T obtenerPrimerElemento();
public boolean estaOrdenada();
public ListaDoble invertir();
public boolean esIgual(ListaDoble l);
public ListaDoble intercalar(ListaDoble l);
public boolean estaIncluida(ListaDoble p);

}

