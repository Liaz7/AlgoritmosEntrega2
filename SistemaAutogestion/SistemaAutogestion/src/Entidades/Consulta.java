/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.Date;

/**
 *
 * @author Lucas
 */
public class Consulta implements Comparable<Consulta> {

    public Consulta(int codMedico, int ciPaciente, Date fecha, int numero, String estado) {
        this.codMedico = codMedico;
        this.ciPaciente = ciPaciente;
        this.fecha = fecha;
        this.numero = numero;
        this.estado = estado;

    }

    private int numero;
    private int codMedico;
    private int ciPaciente;
    private Date fecha;
    private String estado;

    /**
     * @return the codMedico
     */
    public int getCodMedico() {
        return codMedico;
    }

    /**
     * @param codMedico the codMedico to set
     */
    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
    }

    /**
     * @return the ciPaciente
     */
    public int getCiPaciente() {
        return ciPaciente;
    }

    /**
     * @param ciPaciente the ciPaciente to set
     */
    public void setCiPaciente(int ciPaciente) {
        this.ciPaciente = ciPaciente;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
public int compareTo(Consulta o) {
    return Integer.compare(this.getNumero(), o.getNumero());
}

    @Override
    public String toString() {
        return "Consulta{" + "numero=" + numero + ", codMedico=" + codMedico + ", ciPaciente=" + ciPaciente + ", fecha=" + fecha + ", estado=" + estado + '}';
    }

   

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

}
