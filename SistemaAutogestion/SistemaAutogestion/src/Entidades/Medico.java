package Entidades;

public class Medico implements Comparable<Medico> {

    public Medico(String nombre, int codMedico, int tel, int especialidad) {
        this.nombre = nombre;
        this.codMedico = codMedico;
        this.tel = tel;
        this.especialidad = especialidad;
    }
    private String nombre;
    private int codMedico;
    private int tel;
    private int especialidad;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public int compareTo(Medico o) {

        return this.getNombre().toUpperCase().compareTo(o.getNombre().toUpperCase());

    }

    @Override
    public String toString() {
        return "Medico{" + "nombre=" + nombre + ", codMedico=" + codMedico + ", tel=" + tel + ", especialidad=" + especialidad + '}';
    }

}
