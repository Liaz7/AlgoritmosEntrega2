package sistemaAutogestion;

import java.util.Date;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Consulta;
import tads.Lista;
import tads.ListaDoble;
import tads.NodoLista;
import tads.Cola;
import tads.NodoCola;
import tads.NodoDoble;

public class Sistema implements IObligatorio {

    public Lista<Medico> _medicos = new Lista();
    public Lista<Paciente> _pacientes = new Lista();
    public Cola<Consulta> _consultaPacientes = new Cola();
    public Cola<Consulta> _listaDeEsperaPorConsulta = new Cola();
    public ListaDoble<Consulta> _historialClinico = new ListaDoble();
    Date fechaActual = new Date();
    int maximoPacientes = 0;

    @Override
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico) {
        if (maxPacientesporMedico <= 0 || maxPacientesporMedico > 15) {
            return new Retorno(Retorno.Resultado.ERROR_1);

        } else {
            maximoPacientes = maxPacientesporMedico;
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

    public NodoLista<Medico> obtenerMedicoPorCodigo(int codMedico) {
        NodoLista<Medico> nodoActual = _medicos.getInicio();
        NodoLista<Medico> nodoAnterior = null;

        while (nodoActual != null) {
            Medico medicoExistente = nodoActual.getDato();
            if (medicoExistente.getCodMedico() == codMedico) {
                return nodoActual;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        return nodoActual;
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

    public Retorno registrarDiaDeConsulta(int codMedico, Date fecha) {

        NodoLista<Medico> m = obtenerMedicoPorCodigo(codMedico);

        if (m == null) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (m.getDato().getDiasDeConsulta().contains(fecha)) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        m.getDato().getDiasDeConsulta().add(fecha);
        return new Retorno(Retorno.Resultado.OK);
    }

    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {

        NodoCola<Consulta> nodoActual = _consultaPacientes.getInicio();
        NodoCola<Consulta> nodoFinal = _consultaPacientes.getFin();
        NodoLista<Medico> medico = obtenerMedicoPorCodigo(codMedico);
        
        int numeroConsulta = 1;

       /* if (existePacientePorCI(ciPaciente) == false) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (existeMedicoPorCodigo(codMedico) == false) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        if (!medico.getDato().getDiasDeConsulta().contains(fecha)){
            return new Retorno(Retorno.Resultado.ERROR_4);
        }*/
        
        

           {
            while (nodoActual != null) {

                Consulta consultaExistente = nodoActual.getDato();

                if (consultaExistente.getCodMedico() == codMedico && consultaExistente.getCiPaciente() == ciPaciente && consultaExistente.getFecha().equals(fecha)) {
                    return new Retorno(Retorno.Resultado.ERROR_1);
                }

                if (nodoActual.getDato().getCodMedico() == codMedico) {
                    numeroConsulta++;
                }

                nodoActual = nodoActual.getSig();

            }
        }

        Consulta c = new Consulta(codMedico, ciPaciente, fecha, numeroConsulta, "pendiente");

        if (_consultaPacientes.esVacia()) {
            NodoCola nodoCola = new NodoCola<>(c);
            _consultaPacientes.encolar(nodoCola);
        } else if (numeroConsulta >= maximoPacientes) {
            _listaDeEsperaPorConsulta.encolar(new NodoCola<>(c));
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

    //Pre: El atributo CI no puede ser vacio     
    //Post: Devuelve true si existe ese paciente y falso por si no.
    private boolean existeMedicoPorCodigo(int codMedico) {
        NodoLista<Medico> nodoActual = _medicos.getInicio();
        NodoLista<Medico> nodoAnterior = null;

        while (nodoActual != null) {
            Medico medicoExistente = nodoActual.getDato();
            if (medicoExistente.getCodMedico() == codMedico) {
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

            if (consultaActual.getCiPaciente() == CIPaciente && consultaActual.getCodMedico() == codMedico && consultaActual.getFecha() == fecha) {

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

        NodoCola<Consulta> NodoPaciente = existePacienteConCunsultaDadaUnaFecha(codMedico, CIPaciente, fechaActual);

        if (!existePacientePorCI(CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }

        if (NodoPaciente == null || !existePacienteConConsultas(codMedico, CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_2);
        }

        if (NodoPaciente != null) {
            Consulta consulta = NodoPaciente.getDato();
            consulta.setEstado("en espera");
            NodoLista<Medico> nodoMedico = obtenerMedicoPorCodigo(consulta.getCodMedico());
            Medico medico = nodoMedico.getDato();
            System.out.println("Tienes una consulta con el Medico: " + medico.getNombre());
            System.out.println("Su número de consulta es: " + consulta.getNumero());
        }

        return new Retorno(Retorno.resultado.OK);

    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {
        NodoCola<Consulta> nodoConsulta = existePacienteConCunsultaDadaUnaFecha(CIPaciente, codMedico, fechaActual);
        Consulta consulta = nodoConsulta.getDato();

        if (!existePacientePorCI(CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }

        if (nodoConsulta.getDato().getEstado() == "en espera") {
            return new Retorno(Retorno.resultado.ERROR_2);
        }

        consulta.setEstado("cerrada");
        consulta.setDetalle(detalleDeConsulta);
        _historialClinico.agregarOrd(consulta);
        return new Retorno(Retorno.resultado.OK);
    }

    private Cola<Consulta> obtenerConsultasDelDiaPorCodigoDeMedico(int codMedico) {
        NodoCola<Consulta> nodoConsulta = _consultaPacientes.getInicio();
        Cola nuevaCola = new Cola();

        while (nodoConsulta != null) {
            Consulta consulta = nodoConsulta.getDato();

            if (codMedico == consulta.getCodMedico() && fechaActual == consulta.getFecha()) {
                nuevaCola.encolar(nodoConsulta);
            }

            nodoConsulta = nodoConsulta.getSig();
        }

        return nuevaCola;
    }

    private void agregarConsultasDePacienteAlHistorialClinico(Cola<Consulta> consultas) {
        while (!consultas.esVacia()) {
            NodoCola<Consulta> nodoConsulta = consultas.frente();
            Consulta consulta = nodoConsulta.getDato();
            consulta.setEstado("no asistió");
            _historialClinico.agregarOrd(consulta);
            consultas.desencolar();
        }
    }

    @Override
    public Retorno cerrarConsulta(int codMedico, Date fechaConsulta) {
        NodoLista<Medico> nodoMedico = obtenerMedicoPorCodigo(codMedico);

        if (nodoMedico == null) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }
        Cola<Consulta> consultas = obtenerConsultasDelDiaPorCodigoDeMedico(codMedico);

        if (consultas.esVacia()) {
            return new Retorno(Retorno.resultado.ERROR_2);
        }

        agregarConsultasDePacienteAlHistorialClinico(consultas);
        return new Retorno(Retorno.resultado.OK);
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
        Cola<Consulta> consultas = _consultaPacientes;

        Cola<Consulta> consultasOrdenadas = new Cola();
        NodoCola<Consulta> aux = consultas.getInicio();
        consultasOrdenadas.mostrarCola();
        if (!consultas.esVacia()) {
            while (aux != null) {
                Consulta consulta = aux.getDato();

                if (consulta.getCodMedico() == codMédico) {
                    consultasOrdenadas.encolar(aux);
                }

                aux = aux.getSig();
            }
            consultasOrdenadas.mostrarCola();

        }
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno listarPacientesEnEspera(int codMédico, Date fecha
    ) {
        Cola<Consulta> consultas = _listaDeEsperaPorConsulta;

        Cola<Consulta> consultasOrdenadas = new Cola();
        NodoCola<Consulta> aux = consultas.getInicio();
        consultasOrdenadas.mostrarCola();
        if (!consultas.esVacia()) {
            while (aux != null) {
                Consulta consulta = aux.getDato();

                if (consulta.getCodMedico() == codMédico && consulta.getFecha().equals(fecha)) {
                    consultasOrdenadas.encolar(aux);
                }

                aux = aux.getSig();
            }
            consultasOrdenadas.mostrarCola();

        }
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente
    ) {
        return new Retorno(Retorno.resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        if (!existePacientePorCI(ci)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }

        _historialClinico.mostrarRec();
        return new Retorno(Retorno.resultado.OK);
    }

    private int obtenerDiasDelMes(int mes) {
        switch (mes) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return 28;
            default:
                return -1;
        }
    }

    private int obtenerLaCantidadDeConsultasPorFechaEstadoYEspecialidad(Date fecha, String estado, int esp) {
        NodoDoble<Consulta> aux = _historialClinico.getInicio();
        Consulta consulta = aux.getDato();
        int cantidad = 0;

        while (aux != null) {
            if (consulta.getFecha() == fecha && consulta.getEstado() == estado) {
                NodoLista<Medico> nodoMedico = obtenerMedicoPorCodigo(consulta.getCodMedico());
                Medico medico = nodoMedico.getDato();
                if (medico.getEspecialidad() == esp) {
                    cantidad++;
                }
            }

            aux = aux.getSig();
        }

        return cantidad;
    }

    private void agregarCantidadConsultasDentroDeUnaMatriz(int[][] mat, int cantidadDias, int espMax, int mes, int año) {
        String estado = "cerrada";
        int especialidad = 0;
        int dia = 0;

        while (dia < cantidadDias && especialidad < espMax) {
            Date fecha = new Date(año, mes, dia);
            int cantidadConsultas = obtenerLaCantidadDeConsultasPorFechaEstadoYEspecialidad(fecha, estado, especialidad);
            mat[dia][especialidad] = cantidadConsultas;

            especialidad++;

            if (especialidad == espMax) {
                especialidad = 0;
                dia++;
            }
        }

    }

    public void mostrarTotalDeReservas(int[][] mat, int cantidadDias, int espMax) {
        int especialidad = 0;
        int dia = 0;

        while (dia < cantidadDias && especialidad < espMax) {
            int elemento = mat[dia][especialidad];

            if (dia == 0 && especialidad == 0) {
                System.out.println("-----Especialidad");
                System.out.println("Dia " + dia + "-     " + elemento + "     -");
            } else if (especialidad == 0) {
                System.out.println("Dia " + dia + "-     " + elemento + "     -");
            } else {
                System.out.println("-     " + elemento + "     -");
            }

            especialidad++;

            if (especialidad == espMax) {
                especialidad = 0;
                dia++;
            }
        }
    }

    @Override
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año) {
        Retorno retError = new Retorno(Retorno.resultado.ERROR_1);
        int cantidadDias = obtenerDiasDelMes(mes);
        int espMax = 20;

        if (cantidadDias == -1) {
            return retError;
        }

        if (año < 2020 || año > 2023) {
            return retError;
        }

        int reporte[][] = new int[cantidadDias][espMax];
        agregarCantidadConsultasDentroDeUnaMatriz(reporte, cantidadDias, espMax, mes, año);
        mostrarTotalDeReservas(reporte, cantidadDias, espMax);
        return new Retorno(Retorno.resultado.OK);
    }

}
