package sistemaAutogestion;

import java.util.Date;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Consulta;
import java.text.SimpleDateFormat;
import tads.Lista;
import tads.ListaDoble;
import tads.NodoLista;
import tads.NodoDoble;

public class Sistema implements IObligatorio {

    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    public Lista<Medico> _medicos = new Lista();
    public Lista<Paciente> _pacientes = new Lista();
    public Lista<Consulta> _consultaPacientes = new Lista();
    public Lista<Consulta> _listaDeEsperaPorConsulta = new Lista();
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
        
        if(codMedico <= 0){
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

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
        String fechaFormateada = formato.format(fecha);
        NodoLista<Medico> m = obtenerMedicoPorCodigo(codMedico);

        if (m == null) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (m.getDato().getDiasDeConsulta().contains(fechaFormateada)) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }

        m.getDato().getDiasDeConsulta().add(fechaFormateada);
        return new Retorno(Retorno.Resultado.OK);
    }

    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha) {
        NodoLista<Consulta> nodoActual = _consultaPacientes.getInicio();
        NodoLista<Consulta> nodoFinal = _consultaPacientes.getFin();
        NodoLista<Consulta> nodoEspera = _listaDeEsperaPorConsulta.getInicio();
        NodoLista<Medico> medico = obtenerMedicoPorCodigo(codMedico);
        String fechaParametro = formato.format(fecha);

        int numeroConsulta = 1;

        if (existePacientePorCI(ciPaciente) == false) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }
        if (existeMedicoPorCodigo(codMedico) == false) {
            return new Retorno(Retorno.Resultado.ERROR_2);
        }
        if (!medico.getDato().getDiasDeConsulta().contains(fechaParametro)) {
            return new Retorno(Retorno.Resultado.ERROR_4);
        }

        {
            while (nodoActual != null) {

                Consulta consultaExistente = nodoActual.getDato();
                String fechaConsultaExistente = formato.format(consultaExistente.getFecha());

                if (consultaExistente.getCodMedico() == codMedico && consultaExistente.getCiPaciente() == ciPaciente && fechaConsultaExistente.equals(fechaParametro)) {
                    return new Retorno(Retorno.Resultado.ERROR_1);
                }

                if (nodoActual.getDato().getCodMedico() == codMedico && fechaConsultaExistente.equals(fechaParametro)) {
                    numeroConsulta++;
                }

                nodoActual = nodoActual.getSiguiente();

            }
            while (nodoEspera != null) {
                numeroConsulta++;
                nodoEspera = nodoEspera.getSiguiente();
            }

        }

        Consulta c = new Consulta(codMedico, ciPaciente, fecha, numeroConsulta, "pendiente");
        NodoLista<Consulta> nodoLista = new NodoLista<>(c);

        if (_consultaPacientes.esVacia()) {
            _consultaPacientes.agregarInicio(nodoLista.getDato());
        } else if (numeroConsulta > maximoPacientes) {
            _listaDeEsperaPorConsulta.agregarInicio(nodoLista.getDato());

        } else {
            _consultaPacientes.agregarInicio(nodoLista.getDato());
        }

        return new Retorno(Retorno.Resultado.OK);
    }

    @Override
    public Retorno cancelarReserva(int codMedico, int ciPaciente) {
        NodoLista<Consulta> nodoConsulta = _consultaPacientes.getInicio();
        NodoLista<Consulta> nodoEnEspera = _listaDeEsperaPorConsulta.getInicio();
        NodoLista<Consulta> nodoAnterior = null;
        NodoLista<Consulta> nodoConsultaExistente = existePacienteConConsultas(codMedico, ciPaciente);

        if (_consultaPacientes.esVacia()) {
            return new Retorno(Retorno.Resultado.ERROR_1);
        }

        if (_listaDeEsperaPorConsulta.esVacia() && nodoConsultaExistente != null) {
            Consulta consulta = nodoConsultaExistente.getDato();
            if (consulta.getEstado().equals("pendiente")) {
                _consultaPacientes.borrarElemento(nodoConsultaExistente);
            }
        } else if (!_listaDeEsperaPorConsulta.esVacia()) {
            nodoEnEspera.getDato().setNumero(nodoConsultaExistente.getDato().getNumero());
            _consultaPacientes.reemplazarNodo(nodoConsultaExistente, nodoEnEspera);
        }

        return new Retorno(Retorno.Resultado.OK);
    }

    //Pre: El atributo CI no puede ser vacio     
    //Post: Devuelve true si existe ese paciente y falso por si no.
    private boolean existePacientePorCI(int Ci) {
        NodoLista<Paciente> nodoActual = _pacientes.getInicio();
        NodoLista<Paciente> nodoAnterior = null;
        boolean existe = false;

        while (nodoActual != null && !existe) {
            Paciente pacienteExistente = nodoActual.getDato();
            if (pacienteExistente.getCi() == Ci) {
                existe = true;
            }
            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        return existe;
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

    private NodoLista<Consulta> existePacienteConCunsultaDadaUnaFecha(int codMedico, int CIPaciente, Date fecha) {
        NodoLista<Consulta> nodoActual = _consultaPacientes.getInicio();
        NodoLista<Consulta> nodoAnterior = null;
        String fechaFormateada = formato.format(fecha);

        while (nodoActual != null) {
            Consulta consultaActual = nodoActual.getDato();
            String fechaConsulta = formato.format(consultaActual.getFecha());

            if (consultaActual.getCiPaciente() == CIPaciente && consultaActual.getCodMedico() == codMedico && fechaConsulta.equals(fechaFormateada)) {
                return nodoActual;
            }

            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        return nodoActual;
    }

    //Pre: codMedico y CIPaciente no son nulos y son ambos de tipo int
    //Post: Devuelve true existe una cosnulta para el paciente:CIPaciente con el medico:codMedico
    private NodoLista<Consulta> existePacienteConConsultas(int codMedico, int CIPaciente) {
        NodoLista<Consulta> nodoActual = _consultaPacientes.getInicio();
        NodoLista<Consulta> nodoAnterior = null;

        while (nodoActual != null) {
            Consulta consultaActual = nodoActual.getDato();
            if (consultaActual.getCiPaciente() == CIPaciente && consultaActual.getCodMedico() == codMedico) {
                return nodoActual;
            }

            nodoAnterior = nodoActual;
            nodoActual = nodoActual.getSiguiente();
        }

        return null;
    }

    @Override
    public Retorno anunciaLlegada(int codMedico, int CIPaciente) {
        Date fechaActual = new Date();

        NodoLista<Consulta> NodoPaciente = existePacienteConCunsultaDadaUnaFecha(codMedico, CIPaciente, fechaActual);

        if (!existePacientePorCI(CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }

        if (NodoPaciente == null || existePacienteConConsultas(codMedico, CIPaciente) == null) {
            return new Retorno(Retorno.resultado.ERROR_2);
        }

        if (NodoPaciente != null) {
            Consulta consulta = NodoPaciente.getDato();
            consulta.setEstado("en espera");
            NodoLista<Medico> nodoMedico = obtenerMedicoPorCodigo(consulta.getCodMedico());
            Medico medico = nodoMedico.getDato();
            System.out.println("Tienes una consulta con el Medico: " + medico.getNombre());
            System.out.println("Tu número de consulta es: " + consulta.getNumero());
            System.out.println("");
        }

        return new Retorno(Retorno.resultado.OK);

    }

    @Override
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta) {                
        if (!existePacientePorCI(CIPaciente)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }
        
        NodoLista<Consulta> nodoConsulta = existePacienteConCunsultaDadaUnaFecha(codMedico, CIPaciente, fechaActual);
        Consulta consulta = nodoConsulta.getDato();

        if (nodoConsulta.getDato().getEstado() != "en espera") {
            return new Retorno(Retorno.resultado.ERROR_2);
        }

        consulta.setEstado("cerrada");
        consulta.setDetalle(detalleDeConsulta);
        _historialClinico.agregarOrd(consulta);
        return new Retorno(Retorno.resultado.OK);
    }

    private Lista<Consulta> obtenerConsultasDelDiaPorCodigoDeMedico(int codMedico, Date fechaConsulta) {
        NodoLista<Consulta> nodoConsulta = _consultaPacientes.getInicio();
        String fechaFormateada = formato.format(fechaConsulta);
        Lista nuevaLista = new Lista();

        while (nodoConsulta != null) {
            Consulta consulta = nodoConsulta.getDato();
            String fechaDeLaConsulta = formato.format(consulta.getFecha());

            if (codMedico == consulta.getCodMedico() && fechaFormateada.equals(fechaDeLaConsulta)) {
                nuevaLista.agregarInicio(nodoConsulta.getDato());
            }

            nodoConsulta = nodoConsulta.getSiguiente();
        }

        return nuevaLista;
    }

    private void agregarConsultasDePacienteAlHistorialClinico(Lista<Consulta> consultas) {
        while (!consultas.esVacia()) {
            NodoLista<Consulta> nodoConsulta = consultas.getInicio();
            Consulta consulta = nodoConsulta.getDato();
            consulta.setEstado("no asistió");
            _historialClinico.agregarOrd(consulta);
            consultas.borrarInicio();
        }
    }

    @Override
    public Retorno cerrarConsulta(int codMedico, Date fechaConsulta) {
        NodoLista<Medico> nodoMedico = obtenerMedicoPorCodigo(codMedico);

        if (nodoMedico == null) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }
        Lista<Consulta> consultas = obtenerConsultasDelDiaPorCodigoDeMedico(codMedico, fechaConsulta);

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
    public Retorno listarConsultas(int codMédico) {
        if (!_consultaPacientes.esVacia()) {
            _consultaPacientes.mostrarConsultasRec(_consultaPacientes.getInicio(), codMédico);
        }
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno listarPacientesEnEspera(int codMédico, Date fecha
    ) {
        Lista<Consulta> consultas = _listaDeEsperaPorConsulta;

        Lista<Consulta> consultasOrdenadas = new Lista();
        NodoLista<Consulta> aux = consultas.getInicio();
        consultasOrdenadas.mostrar();
        if (!consultas.esVacia()) {
            while (aux != null) {
                Consulta consulta = aux.getDato();

                if (consulta.getCodMedico() == codMédico && consulta.getFecha().equals(fecha)) {
                    consultasOrdenadas.agregarInicio(consulta);
                }

                aux = aux.getSiguiente();
            }
            consultasOrdenadas.mostrar();

        }
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno consultasPendientesPaciente(int CIPaciente) {
        if(!existePacientePorCI(CIPaciente)){
            return new Retorno(Retorno.resultado.ERROR_1);
        }
        _consultaPacientes.mostrarConsultasCiRec(_consultaPacientes.getInicio(), CIPaciente);        
        
        return new Retorno(Retorno.resultado.OK);
    }

    @Override
    public Retorno historiaClínicaPaciente(int ci) {
        if (!existePacientePorCI(ci)) {
            return new Retorno(Retorno.resultado.ERROR_1);
        }

        _historialClinico.mostrarRecPorCi(_historialClinico.getInicio(), ci);
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
        String fechaFormateada = formato.format(fecha);
        NodoDoble<Consulta> aux = _historialClinico.getInicio();
        Consulta consulta = aux.getDato();
        int cantidad = 0;

        while (aux != null) {
            String fechaDeConsulta = formato.format(consulta.getFecha());
            if (fechaDeConsulta.equals(fechaFormateada) && consulta.getEstado() == estado) {
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

        for (int dia = 0; dia < cantidadDias; dia++) {
            for (int especialidad = 0; especialidad < espMax; especialidad++) {
                Date fecha = new Date(año - 1900, mes - 1, dia);
                int cantidadConsultas = obtenerLaCantidadDeConsultasPorFechaEstadoYEspecialidad(fecha, estado, especialidad);
                mat[dia][especialidad] = cantidadConsultas;
            }
        }

    }

    public void mostrarTotalDeReservas(int[][] mat, int cantidadDias, int espMax) {
        int especialidad = 0;
        int dia = 0;

        for (int i = 0; i < mat.length; i++) {
            if (i == 0) {
                System.out.println("---------Especialidad");
            }

            System.out.print("dia: " + i + "\t");

            // Iterar sobre columnas                                    
            for (int j = especialidad; j < mat[i].length; j++) {
                System.out.print("-   " + mat[i][j] + "   -" + "\t");
                // Imprimir elemento seguido de un tabulador
            }
            System.out.println(""); // Nueva línea después de cada fila
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
