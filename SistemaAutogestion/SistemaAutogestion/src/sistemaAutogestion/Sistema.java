package sistemaAutogestion;

import java.util.Date;
import Entidades.Medico;
import Entidades.Paciente;
import tads.Lista;
import tads.NodoLista;

public class Sistema implements IObligatorio {

    public Lista<Medico> _medicos = new Lista();
    public Lista<Paciente> _pacientes = new Lista();

    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        if (maxPacientesporMedico <= 0 || maxPacientesporMedico > 15) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        } else {
            return new Retorno(Retorno.Resultado.OK);
        }
    }

    @Override
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad) {

        NodoLista<Medico> nodoActual = _medicos.getInicio();
        while (nodoActual != null) {
            Medico medicoExistente = nodoActual.getDato();
            if (medicoExistente.getCodMedico() == codMedico) {
                return new Retorno(Retorno.Resultado.ERROR_1);
            }
            nodoActual = nodoActual.getSiguiente();
        }

        if (especialidad < 1 || especialidad > 20) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        if (nombre.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }

        if (codMedico <= 0 || tel <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_4);
        }

        Medico nuevoMedico = new Medico(nombre, codMedico, tel, especialidad);
        _medicos.agregarOrd(nuevoMedico);

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno eliminarMedico(int codMedico) {
        NodoLista<Medico> nodoActual = _medicos.getInicio();
        NodoLista<Medico> nodoAnterior = null;

        while (nodoActual != null) {
            Medico medicoExistente = nodoActual.getDato();
            if (medicoExistente.getCodMedico() == codMedico) {

                _medicos.borrarElemento(nodoActual);

                return new Retorno(Retorno.Resultado.OK);
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        if (nodoAnterior.getSiguiente() == null) {
            _medicos.borrarElemento(nodoAnterior);
            return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    @Override
    public Retorno agregarPaciente(String nombre, int Ci, String direccion) {

        NodoLista<Paciente> nodoActual = _pacientes.getInicio();
        while (nodoActual != null) {
            Paciente pacienteExistente = nodoActual.getDato();
            if (pacienteExistente.getCi() == Ci) {
                return new Retorno(Retorno.Resultado.ERROR_1);
            }
            nodoActual = nodoActual.getSiguiente();
        }

        if (nombre.isEmpty() || direccion.isEmpty()) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        if (Ci <= 0) {
            return new Retorno(Retorno.Resultado.ERROR_3);
        }

        Paciente nuevoPaciente = new Paciente(nombre, Ci, direccion);
        _pacientes.agregarOrd(nuevoPaciente);

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno eliminarPaciente(int Ci) {
        NodoLista<Paciente> nodoActual = _pacientes.getInicio();
        NodoLista<Paciente> nodoAnterior = null;

        while (nodoActual != null) {
            Paciente pacienteExistente = nodoActual.getDato();
            if (pacienteExistente.getCi() == Ci) {

                _pacientes.borrarElemento(nodoActual);

                return new Retorno(Retorno.Resultado.OK);
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        if (nodoAnterior.getSiguiente() == null) {
            _pacientes.borrarElemento(nodoAnterior);
            return new Retorno(Retorno.Resultado.OK);
        }

        return new Retorno(Retorno.Resultado.ERROR_1);
    }

    @Override
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno cerrarConsulta(int codMédico, Date fechaConsulta) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarMédicos() {

        Lista<Medico> medicos = _medicos;
        Lista<Medico> medicosOrdenada = new Lista();
        NodoLista<Medico> aux = medicos.getInicio();

        if (!medicos.esVacia()) {
            while (aux != null) {

                medicosOrdenada.agregarOrd(aux.getDato());

                aux = aux.getSiguiente();
            }
            medicosOrdenada.mostrar();

        }
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno listarPacientes() {
        Lista<Paciente> pacientes = _pacientes;
        Lista<Paciente> pacientesOrdenada = new Lista();
        NodoLista<Paciente> aux = pacientes.getInicio();

        if (!pacientes.esVacia()) {
            while (aux != null) {

                pacientesOrdenada.agregarOrd(aux.getDato());

                aux = aux.getSiguiente();
            }
            pacientesOrdenada.mostrar();

        }
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno listarConsultas(int codMédico
    ) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listarPacientesEnEspera(int codMédico, Date fecha
    ) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente
    ) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci
    ) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año
    ) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

}
