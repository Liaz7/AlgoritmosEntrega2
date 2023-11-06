package Entidades;

public class Paciente implements Comparable<Paciente> {
    public Paciente(String nombre, int Ci, String direccion){
        this.nombre = nombre;
        this.ci = Ci;
        this.direccion = direccion;
    }
    
    private String nombre;
    private int ci;
    private String direccion;
    
     public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String dir) {
        this.direccion = dir;
    }

    public int compareTo(Paciente p){
        return this.getNombre().compareTo(p.getNombre());
    }    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Paciente other = (Paciente) obj;
        return this.ci == other.ci;
    }

    @Override
    public String toString() {
        return "Paciente{" + "nombre=" + nombre + ", ci=" + ci + ", direccion=" + direccion + '}';
    }
    
    
}
