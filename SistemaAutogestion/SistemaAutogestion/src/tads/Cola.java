package tads;

public class Cola <T extends Comparable<T>> implements ICola<T> {

    private NodoCola inicio;
    private NodoCola fin;
    private int cantidad;
    
    public Cola () {
        cantidad = 0;
    }
    /**
     * @return the inicio
     */
    public NodoCola getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(NodoCola inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fin
     */
    public NodoCola getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(NodoCola fin) {
        this.fin = fin;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public void encolar(NodoCola nodo) {
        NodoCola nuevo = new NodoCola((Comparable) nodo);        
        nuevo.setSig(this.getInicio());
        this.setInicio(nuevo);
        if (esVacia()) {
            fin = inicio;
        }
    }

    @Override
    public void desencolar() {
        if (!esVacia()) {
            this.inicio = this.inicio.getSig();           
        }
    }

    @Override
    public boolean esVacia() {
        return this.getCantidad() == 0;
    }

    @Override
    public boolean esLlena() {
        return false;
    }

    @Override
    public int elementos() {
        return this.elementos();
    }

    @Override
    public NodoCola frente() {
        if(!this.esVacia()){
            return this.getInicio();
        }
        return null;
    }

    @Override
    public void mostrarCola() {
        NodoCola mostrar = getInicio();
        while(mostrar != null) {
            System.out.println(mostrar.getDato());
            mostrar = mostrar.getSig();
        }
            
    }

    @Override
    public void mostrarREC(NodoCola nodo) {
        if(nodo!=null){
            System.out.println(nodo.getDato());
            mostrarREC(nodo.getSig());
        }
    }
}
