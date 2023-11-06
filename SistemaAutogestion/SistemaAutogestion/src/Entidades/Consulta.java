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

    public Consulta(int codMedico, int ciPaciente, Date fecha) {
        this.codMedico = codMedico;
        this.ciPaciente = ciPaciente;
        this.fecha = fecha;

    }

    private int codMedico;
    private int ciPaciente;
    private Date fecha;

    

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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "Consulta{" + "codMedico=" + codMedico + ", ciPaciente=" + ciPaciente + ", fecha=" + fecha + '}';
    }
    
    
}
