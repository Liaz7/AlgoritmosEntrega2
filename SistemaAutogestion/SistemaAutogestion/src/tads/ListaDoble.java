package tads;
import Entidades.Consulta;

public class ListaDoble<T extends Comparable<T>> implements IListaDoble<T> {

    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;
    private NodoDoble<T> indice;
    private int cantidad;

    public ListaDoble() {
        this.cantidad = 0;
        this.inicio = null;
        this.fin = null;
        this.indice = null;
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public NodoDoble<T> getInicio() {
        return this.inicio;
    }

    @Override
    public void setInicio(NodoDoble<T> inicio) {
        this.inicio = inicio;
    }

    @Override
    public NodoDoble<T> getFin() {
        return this.fin;
    }

    @Override
    public void setFin(NodoDoble<T> fin) {
        this.fin = fin;
    }

    @Override
    public NodoDoble<T> getIndice() {
        return this.indice;
    }

    @Override
    public void setIndice(NodoDoble<T> indice) {
        this.indice = indice;
    }

    @Override
    public boolean esVacia() {
        return getInicio() == null;
    }

    @Override
    public void agregarInicio(T dato) {
        NodoDoble<T> nodo = new NodoDoble(dato);
        if (this.esVacia()) {
            this.setInicio(nodo);
            this.setFin(nodo);
            this.setCantidad(this.getCantidad() + 1);
            return;
        }
        nodo.setSig(this.getInicio());
        this.getInicio().setAnt(nodo);
        this.setInicio(nodo);
        this.setCantidad(this.getCantidad() + 1);
    }

    @Override
    public void agregarFinal(T dato) {
        NodoDoble<T> nodo = new NodoDoble(dato);
        if (this.esVacia()) {
            this.agregarInicio(dato);
            return;
        }
        nodo.setAnt(this.getFin());
        this.getFin().setSig(nodo);
        this.setFin(nodo);
        this.setCantidad(this.getCantidad() + 1);
    }

    @Override
    public void agregarOrd(T dato) {
        if (this.esVacia()) {
            this.agregarInicio(dato);
            return;
        }

        if (this.getInicio().getDato().compareTo(dato) >= 0) {
            this.agregarInicio(dato);
            return;
        }

        if (this.getFin().getDato().compareTo(dato) <= 0) {
            this.agregarFinal(dato);
            return;
        }

        NodoDoble aux = this.getInicio();
        while (aux != this.getFin() && aux.getDato().compareTo(dato) < 1) {
            aux = aux.getSig();
        }

        if (aux != this.getFin()) {
            NodoDoble nuevo = new NodoDoble(dato);
            aux.getAnt().setSig(nuevo);
            nuevo.setSig(aux);
            nuevo.setAnt(aux.getAnt());
            aux.setAnt(nuevo);
        }
    }

    @Override
    public void borrarInicio() {
        if (this.esVacia()) {
            return;
        }
        if (this.getInicio() == getFin()) {
            this.vaciar();
            return;
        }
        this.setInicio(this.getInicio().getSig());
        this.getInicio().setAnt(null);
        this.setCantidad(this.getCantidad() - 1);

    }

    @Override
    public void borrarFin() {
        if (this.esVacia()) {
            return;
        }
        if (this.getInicio() == getFin()) {
            this.vaciar();
            return;
        }
        this.setFin(this.getFin().getAnt());
        this.getFin().setSig(null);
    }

    @Override
    // PRE: Borra la primera ocurrencia del elemento dato en la lista
    public void borrarElemento(T dato) {
        if (this.esVacia()) {
            return;
        }
        if (this.getInicio().getDato().compareTo(dato) == 0) {
            this.borrarInicio();
            return;
        }
        if (this.getFin().getDato().compareTo(dato) == 0) {
            this.borrarFin();
            return;
        }
        NodoDoble aux = this.getInicio();
        while (aux != null && aux.getDato().compareTo(dato) != 0) {
            aux = aux.getSig();
        }
        if (aux != null) {
            aux.getAnt().setSig(aux.getSig());
            aux.getSig().setAnt(aux.getAnt());
            this.setCantidad(this.getCantidad() - 1);
        }

    }
        
    @Override
    public boolean buscarElemento(T dato) {
        if (this.esVacia()) {
            return false;
        }
        NodoDoble aux = this.getInicio();
        while (aux != null && aux.getDato().compareTo(dato) != 0) {
            aux = aux.getSig();
        }
        if (aux == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public NodoDoble<T> obtenerElemento(T dato) {
        if (this.esVacia()) {
            return null;
        }
        if (this.getInicio().getDato().compareTo(dato) == 0) {
            return this.getInicio();
        }
        if (this.getFin().getDato().compareTo(dato) == 0) {
            return this.getFin();
        }
        NodoDoble aux = this.getInicio();
        while (aux != null && aux.getDato().compareTo(dato) != 0) {
            aux = aux.getSig();
        }
        if (aux != null) {
            return aux;
        } else {
            return null;
        }
    }

    @Override
    public void vaciar() {
        this.setInicio(null);
        this.setFin(null);
        this.setCantidad(0);
    }

    @Override
    public void mostrar() {
        if (this.esVacia()) {
            System.out.println("La lista esta vacia");
            return;
        }
        NodoDoble aux = this.getInicio();
        while (aux != null) {
            System.out.println(aux.getDato().toString() + "-");
            aux = aux.getSig();
        }
        System.out.println();
    }

    @Override
    public void mostrarRec() {
        if (this.getIndice() == null) {
            return;
        }
        System.out.println(this.getIndice().getDato() + "");
        this.setIndice(this.getIndice().getSig());
        this.mostrarRec();
    }

    public void mostrarRec(NodoDoble aux) {
        if (aux == null) {
            return;
        }
        System.out.println(this.getIndice().getDato() + "");
        this.mostrarRec(aux.getSig());
    }

    @Override
    public int cantElementos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public T obtenerPrimerElemento() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean estaOrdenada() {
        if (this.esVacia()) {
            return true;
        }

        if (this.getInicio().getDato().equals(this.getFin().getDato())) {
            return true;
        }

        NodoDoble aux = this.getInicio();

        while (aux != this.getFin() && aux.getDato().compareTo(aux.getSig().getDato()) <= 0) {
            aux = aux.getSig();
        }

        if (aux != this.getFin()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ListaDoble invertir() {
        NodoDoble aux = this.getFin();
        ListaDoble listaInvertida = new ListaDoble();

        while (aux != this.getInicio()) {
            listaInvertida.agregarInicio(aux.getDato());
            aux = aux.getAnt();
        }

        if (aux.equals(this.getInicio())) {
            listaInvertida.agregarFinal(aux.getDato());
        }

        return listaInvertida;
    }

    @Override
    public boolean esIgual(ListaDoble l) {
        if (this.esVacia() && l.esVacia()) {
            return true;
        }
        NodoDoble aux = this.getInicio();
        NodoDoble aux2 = l.getInicio();
        while (aux != null && aux2 != null) {
            if (aux.getDato().compareTo(aux2.getDato()) == 0) {
                aux = aux.getSig();
                aux2 = aux2.getSig();
            } else {
                return false;
            }
        }

        if (aux != null || aux2 != null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ListaDoble intercalar(ListaDoble l) {
        ListaDoble auxLista = new ListaDoble();

        if (this.esVacia() && l.esVacia()) {
            return auxLista;
        }

        NodoDoble aux = this.getInicio();
        NodoDoble aux2 = l.getInicio();

        while (aux != null && aux2 != null) {
            if (aux.getDato().compareTo(aux2.getDato()) <= 0) {
                auxLista.agregarFinal(aux.getDato());
                aux = aux.getSig();
            } else {
                auxLista.agregarFinal(aux2.getDato());
                aux2 = aux2.getSig();
            }
        }

        while (aux != null) {
            auxLista.agregarFinal(aux.getDato());
            aux = aux.getSig();
        }

        while (aux2 != null) {
            auxLista.agregarFinal(aux2.getDato());
            aux2 = aux2.getSig();
        }

        return auxLista;
    }

    @Override
    public boolean estaIncluida(ListaDoble p) {
        if (p.esVacia() || this.esVacia()) {
            return true;
        }

        NodoDoble aux = this.getInicio();
        NodoDoble aux2 = p.getInicio();

        while (aux != null && aux2 != null) {
            if (aux.getDato().compareTo(aux2.getDato()) != 0) {
                aux = aux.getSig();
            } else {
                aux = aux.getSig();
                aux2 = aux2.getSig();
            }
        }

        if (aux2 == null) {
            return true;
        } else {
            return false;
        }
    }
}
