
package tads;

/**
 *
 * @author usuario
 */
public interface ICola <T extends Comparable<T>>{
    public void encolar(NodoCola nodo);
    public void desencolar();
    public boolean esVacia();
    public boolean esLlena();
    public int elementos();
    public NodoCola frente();
    public void mostrarCola();
    public void mostrarREC(NodoCola nodo);
}
