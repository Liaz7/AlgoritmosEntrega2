package tads;

import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;

public class Lista<T extends Comparable<T>> implements ILista<T> {

    private NodoLista<T> inicio;
    private NodoLista<T> fin;

    public Lista() {
        this.inicio = null;
        this.fin = null;

    }

    @Override
    public NodoLista<T> getFin() {
        return fin;
    }

    @Override
    public void setFin(NodoLista<T> fin) {
        this.fin = fin;
    }

    @Override
    public NodoLista<T> getInicio() {
        return inicio;
    }

    @Override
    public void setInicio(NodoLista<T> inicio) {
        this.inicio = inicio;
    }

    @Override
    public boolean esVacia() {
        return this.inicio == null;
    }

    @Override
    public int cantidadElementos() {
        int cant = 0;
        NodoLista<T> aux = inicio;
        while (aux != null) {
            cant++;
            aux = aux.getSiguiente();
        }
        return cant;
    }

    @Override
    public void mostrar() {
        NodoLista<T> pactual = this.inicio;
        while (pactual != null) {
            System.out.println(pactual.getDato());
            pactual = pactual.getSiguiente();
        }
    }

    @Override
    public void vaciar() {
        this.inicio = null;

    }

    @Override
    public void agregarInicio(T x) {
        NodoLista<T> nuevo = new NodoLista<T>( x);
     
         if (this.esVacia()){
            this.setInicio(nuevo);
            this.setFin(nuevo);
        }
        else {
            nuevo.setSiguiente(this.getInicio());
            this.setInicio(nuevo);
        }
    }

    @Override
    public void agregarFinal(T x) {
        NodoLista<T> nuevo = new NodoLista(x);
        if (esVacia()) {
            inicio = nuevo;
        } else {
            NodoLista<T> actual = inicio;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            actual.setSiguiente(nuevo);
            this.setFin(nuevo);

        }

    }

    @Override
    public void borrarInicio() {
        if (!esVacia()) {
            this.inicio = this.inicio.getSiguiente();

        }
    }

    @Override
    public void borrarFin() {
        if (!esVacia()) {
            if (inicio.getSiguiente() == null) {
                this.vaciar();
            } else {

                NodoLista<T> actual = inicio;

                while (actual.getSiguiente().getSiguiente() != null) {
                    actual = actual.getSiguiente();

                }
                actual.setSiguiente(null);

            }
        }
    }

    @Override
    public boolean estaElemento(NodoLista<T> nodo) {
        NodoLista pactual = inicio;
        boolean estaElem = false;
        while (pactual != null && !estaElem) {
            if (pactual.getDato() == nodo) {
                estaElem = true;
            } else {
                pactual = pactual.getSiguiente();
            }
        }
        return estaElem;
    }
    
    public void reemplazarNodo(NodoLista nodoAntiguo, NodoLista nodoNuevo) {
        if (inicio == null) {
            // La lista está vacía, no se puede realizar la operación
         
            
        }

        if (inicio == nodoAntiguo) {
            // Si el nodo a reemplazar es el primer nodo            
            nodoNuevo.setSiguiente(inicio.getSiguiente());            
            inicio = nodoNuevo;
          
            
        }

        NodoLista<T> actual = inicio;

        while (actual.getSiguiente() != null && actual.getSiguiente() != nodoAntiguo) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() == null) {
            // El nodo antiguo no se encontró en la lista
           
            
        }

        // Se encontró el nodo anterior al nodo a reemplazar
        actual.setSiguiente(nodoNuevo);
        nodoNuevo.setSiguiente(nodoAntiguo.getSiguiente());
    }

    @Override
    public void borrarElemento(NodoLista<T> nodo) {
        if (!esVacia()) {
            NodoLista actual = inicio;
            if (inicio.getDato() == nodo.getDato()) {
                this.borrarInicio();
            } else if (fin.getDato() == nodo.getDato()) {
                borrarFin();
            } else {
                while (actual.getSiguiente() != null && actual.getSiguiente().getDato() != nodo.getDato()) {
                    actual = actual.getSiguiente();
                }
                if (actual.getSiguiente() != null) {
                    NodoLista<T> aborrar = actual.getSiguiente();
                    actual.setSiguiente(aborrar.getSiguiente());
                    aborrar.setSiguiente(null);

                }
            }
        }
    }

    @Override
    public NodoLista<T> obtenerElemento(T x) {
        NodoLista<T> aux = this.inicio;
        while (aux != null && aux.getDato().compareTo(x) != 0) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            return aux;
        } else {
            return null;
        }
    }

    @Override
    public void mostrarRec(NodoLista<T> nodo) {
        if (nodo != null) {
            System.out.println(nodo.getDato());
            mostrarRec(nodo.getSiguiente());
        }
    }
    
    public void mostrarConsultasCiRec(NodoLista<Consulta> nodo, int ci) {
        if (nodo != null) {
            if (nodo.getDato().getCiPaciente() == ci && nodo.getDato().getEstado().equals("pendiente")) {
                System.out.println(nodo.getDato());
            }
            mostrarConsultasCiRec(nodo.getSiguiente(), ci);
        }
    }

    public void mostrarConsultasRec(NodoLista<Consulta> nodo, int codMédico) {
        if (nodo != null) {
            if (nodo.getDato().getCodMedico() == codMédico) {
                System.out.println(nodo.getDato());
            }
            mostrarConsultasRec(nodo.getSiguiente(), codMédico);
        }
    }

    @Override
    public void agregarOrd(T x) {

        if (esVacia() || inicio.getDato().compareTo(x) >= 0) {
            this.agregarInicio(x);
        } else {
            NodoLista<T> aux = inicio;
            while (aux.getSiguiente() != null
                    && aux.getSiguiente().getDato().compareTo(x) < 0) {
                aux = aux.getSiguiente();
            }

            if (aux.getSiguiente() == null) {
                this.agregarFinal(x);
            } else {
                NodoLista<T> nuevo = new NodoLista<T>(x);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                //cantElementos++;
            }

        }
    }

    @Override
    public void mostrarInversoRec(NodoLista<T> nodo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
