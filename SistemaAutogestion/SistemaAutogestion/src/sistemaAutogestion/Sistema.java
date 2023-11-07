package sistemaAutogestion;

import java.util.Date;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Consulta;
import tads.Lista;
import tads.NodoLista;
import tads.Cola;
import tads.NodoCola;

public class Sistema implements IObligatorio {

    public Lista<Medico> _medicos = new Lista();
    public Lista<Paciente> _pacientes = new Lista();
    public Cola<Paciente> _consultaPacientes = new Cola();

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

        NodoCola<Consulta> nodoActual = _consultaPacientes.getInicio();
        int numeroConsulta = 0;
        while (nodoActual != null) {

            Consulta consultaExistente = nodoActual.getDato();

            if (consultaExistente.getCodMedico() == codMedico && consultaExistente.getCiPaciente() == ciPaciente && consultaExistente.getFecha() == fecha) {
                return new Retorno(Retorno.Resultado.ERROR_1);
            }

            nodoActual = nodoActual.getSig();

        }

        if (nodoActual == null) {
            numeroConsulta = 1;
        } else {
            Consulta consultaExistente = nodoActual.getDato();
            numeroConsulta = consultaExistente.getNumero() + 1;
        }

        Consulta c = new Consulta(codMedico, ciPaciente, fecha, numeroConsulta);

        if (_consultaPacientes.esVacia()) {
            NodoCola nodoCola = new NodoCola<>(c);
            _consultaPacientes.encolar(nodoCola);
            _consultaPacientes.setFin(nodoCola);
            _consultaPacientes.setInicio(nodoCola);
        } else if (numeroConsulta >= 16) {
            //  ACA SE MANDA PARA LA LISTA DE ESPERA
        } else {
            _consultaPacientes.encolar(new NodoCola<>(c));
        }

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    //Pre: El atributo CI no puede ser vacio     
    //Post: Devuelve true si existe ese paciente y falso por si no.
    private boolean existePacientePorCI(int Ci) {
        NodoLista<Paciente> nodoActual = _pacientes.getInicio();
        NodoLista<Paciente> nodoAnterior = null;

        while (nodoActual != null) {
            Paciente pacienteExistente = nodoActual.getDato();
            if (pacienteExistente.getCi() == Ci) {
                return true;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        return false;
    }

    private NodoCola<Consulta> existePacienteConCunsultaDadaUnaFecha(int codMedico, int CIPaciente, Date fecha) {
        NodoCola<Consulta> nodoActual = _consultaPacientes.getInicio();
        NodoCola<Consulta> nodoAnterior = null;

        while (nodoActual != null) {
            Consulta consultaActual = nodoActual.getDato();
            if (consultaActual.getCiPaciente() == CIPaciente && consultaActual.getCodMedico() == codMedico) {
                return nodoActual;
            }

            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSig();
        }

        return nodoActual;
    }

    //Pre: codMedico y CIPaciente no son nulos y son ambos de tipo int
    //Post: Devuelve true existe una cosnulta para el paciente:CIPaciente con el medico:codMedico
    private boolean existePacienteConConsultas(int codMedico, int CIPaciente) {
        NodoCola<Consulta> nodoActual = _consultaPacientes.getInicio();
        NodoCola<Consulta> nodoAnterior = null;

        while (nodoActual != null) {
            Consulta consultaActual = nodoActual.getDato();
            if (consultaActual.getCiPaciente() == CIPaciente && consultaActual.getCodMedico() == codMedico) {
                return true;
            }

            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSig();
        }

        return false;
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        Date fechaActual = new Date();

        if (!existePacientePorCI(CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }

        if (!existePacienteConConsultas(codMedico, CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_2);
        }

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
