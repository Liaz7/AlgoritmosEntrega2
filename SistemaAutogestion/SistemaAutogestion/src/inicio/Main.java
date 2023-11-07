package inicio;

import java.util.Date;
import sistemaAutogestion.Retorno;
import sistemaAutogestion.Sistema;
import sistemaAutogestion.Prueba;
import java.text.ParseException;
import Entidades.Medico;
import Entidades.Paciente;

public class Main {

    public static void main(String[] args) throws ParseException {

        interfaz();
        Sistema s = new Sistema();
        Prueba p = new Prueba();
        juegoDePruebaSistemaDeAutogestion(s, p);
        juegoDePruebaMedicos(s, p);
        juegoDePruebaPacientes(s, p);
        //juegoDePruebaNoImplementada(s, p);
        juegoDePruebaAnunciarLlegadaAlTotem(s, p);

        p.imprimirResultadosPrueba();
    }

    public static void juegoDePruebaSistemaDeAutogestion(Sistema s, Prueba p) {
        p.ver(s.crearSistemaDeAutogestion(15).resultado, Retorno.Resultado.ERROR_1, "Se crea sistema para 15 pacientes");
        p.ver(s.crearSistemaDeAutogestion(5).resultado, Retorno.Resultado.OK, "Se crea sistema para 5 pacientes");
        p.ver(s.crearSistemaDeAutogestion(18).resultado, Retorno.Resultado.ERROR_1, "Se crea sistema para 18 pacientes");
        p.ver(s.crearSistemaDeAutogestion(24).resultado, Retorno.Resultado.OK, "Se crea sistema para 24 pacientes");
    }

    public static void juegoDePruebaMedicos(Sistema s, Prueba p) {
        p.ver(s.registrarMedico("Arya", 500, 9581853, 2).resultado, Retorno.Resultado.OK, "Se registra medico: Arya");
        p.ver(s.registrarMedico("Gabodoc", 420, 9581853, 21).resultado, Retorno.Resultado.ERROR_4, "Especialidad fuera de rango");        
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se logro listar los medicos exitosamente");
        p.ver(s.registrarMedico("Abrya", 502, 9581853, 2).resultado, Retorno.Resultado.OK, "Se registra medico: Arya");
        p.ver(s.registrarMedico("Baltimor", 150, 9581853, 15).resultado, Retorno.Resultado.OK, "Se registra medico: Baltimor");
        p.ver(s.registrarMedico("Aarya", 501, 9581853, 2).resultado, Retorno.Resultado.OK, "Se registra medico: Arya");
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se logro listar los medicos exitosamente");
        System.out.println("====================  Se Vacia Lista Medicos  ========================");
        s._medicos.vaciar();
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se logro listar los medicos exitosamente");
        p.ver(s.registrarMedico("Jon", 820, 9581853, 15).resultado, Retorno.Resultado.OK, "Se registra medico: Jon");
        p.ver(s.registrarMedico("Ned", 920, 9581853, 15).resultado, Retorno.Resultado.OK, "Se registra medico: Ned");
        p.ver(s.registrarMedico("Oberyn", 777, 9581853, 15).resultado, Retorno.Resultado.OK, "Se registra medico: Oberyn");
        Medico medicoUno = new Medico("Pepe", 152, 9518351, 2);
        Medico medicoDos = new Medico("carlos", 152, 9518351, 2); // Se ingresa con la inicial minuscula para comprobar el compareTo()
        p.ver(s.registrarMedico("Abrya", -502, 9581853, 2).resultado, Retorno.Resultado.ERROR_2, "Codigo de medico en negativo");
        p.ver(s.registrarMedico("Baltimor", 150, 9581853, 16).resultado, Retorno.Resultado.ERROR_4, "Especialidad fuera de rango");
        p.ver(s.registrarMedico("Aarya", 501, -9581853, 2).resultado, Retorno.Resultado.ERROR_2, "Telefono fuera de rango");
        System.out.println("====================  Se Muestra Lista Medicos  ========================");
        s._medicos.mostrar();
        p.ver(s.eliminarMedico(920).resultado, Retorno.Resultado.OK, "Se elimina medico: Ned");
        p.ver(s.eliminarMedico(-820).resultado, Retorno.Resultado.ERROR_1, "Se elimina medico: Ned");
        s._medicos.mostrar();
        p.ver(s.registrarMedico("Cersei", 122, 9581853, 15).resultado, Retorno.Resultado.OK, "Se registra medico: Cersei");
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se logro listar los medicos exitosamente");
        p.ver(s.registrarMedico("Sansa", 526, 9581853, 5).resultado, Retorno.Resultado.OK, "Se registra medico: Sansa");
        s._medicos.mostrar();
        p.ver(s.eliminarMedico(777).resultado, Retorno.Resultado.OK, "Se elimina medico: Oberyn");
        p.ver(s.registrarMedico("Martin", 999, 98432233, 5).resultado, Retorno.Resultado.OK, "Se registra medico: Martin");
        p.ver(s.eliminarMedico(526).resultado, Retorno.Resultado.OK, "Se elimina medico: Sansa");
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se logro listar los medicos exitosamente");
        System.out.println("====================   Se Agrega Medico Pepe al Inicio   =======================");
        s._medicos.agregarInicio(medicoUno);
        System.out.println("====================   Se Agrega Medico Carlos al Final   =======================");
        s._medicos.agregarFinal(medicoDos);        
        p.ver(s.listarMédicos().resultado, Retorno.Resultado.OK, "Se logro listar los medicos exitosamente");
    }

    public static void juegoDePruebaPacientes(Sistema s, Prueba p) {
        p.ver(s.agregarPaciente("Augusto", 38294382, "Bulevar General Artigas, 2333").resultado, Retorno.Resultado.OK, "Se agrega paciente: Augusto");
        p.ver(s.agregarPaciente("Susana", 38948572, "Luis Alberto de Herrera, 4503").resultado, Retorno.Resultado.OK, "Se agrega paciente: Susana");
        p.ver(s.agregarPaciente("Augusto", -38294382, "Bulevar General Artigas, 2333").resultado, Retorno.Resultado.OK, "Numero de documento invalido");
        p.ver(s.agregarPaciente("Susana", -38948572, "Luis Alberto de Herrera, 4503").resultado, Retorno.Resultado.OK, "Numero de documento invalido");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                
        p.ver(s.agregarPaciente("Bruno", -38948572, "18 de Julio, 1029").resultado, Retorno.Resultado.ERROR_1, "Numero de documento invalido");
        p.ver(s.agregarPaciente("Ramon", 22342235, "Avenida Italia, 8484").resultado, Retorno.Resultado.ERROR_3, "Se agrega paciente: Ramon");
        p.ver(s.agregarPaciente("Eduardo", 48392203, "Paraguay, 1873").resultado, Retorno.Resultado.ERROR_3, "Se agrega paciente: Eduardo");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        System.out.println("====================  Se Vacia Lista Pacientes  ========================");
        s._pacientes.vaciar();
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        p.ver(s.agregarPaciente("Nicolas", 53829943, "Tomphson, 1804").resultado, Retorno.Resultado.OK, "Se agrega paciente: Nicolas");
        p.ver(s.agregarPaciente("Moana", 56563329, "Rio Negro, 1872").resultado, Retorno.Resultado.OK, "Se agrega paciente: Moana");
        p.ver(s.agregarPaciente("Nadia", 29858692, "Mercedez, 4583").resultado, Retorno.Resultado.OK, "Se agrega paciente: Nadia");
        Paciente pacienteUno = new Paciente("Zonar", 41238985, "Quareim, 3422");
        Paciente pacienteDos = new Paciente("Gaston", 49212856, "Canelones, 2222");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        p.ver(s.eliminarPaciente(38948572).resultado, Retorno.Resultado.OK, "Se elimina paciente: Bruno");
        p.ver(s.eliminarPaciente(-38948572).resultado, Retorno.Resultado.OK, "Se elimina paciente: Bruno");
        p.ver(s.eliminarPaciente(99999).resultado, Retorno.Resultado.OK, "Se elimina paciente: Bruno");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        p.ver(s.agregarPaciente("Juanjose", 28785574, "Neptunia, 3333").resultado, Retorno.Resultado.OK, "Se agrega paciente: Juanjose");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        p.ver(s.agregarPaciente("Leticia", 44457483, "Lagomar, 8322").resultado, Retorno.Resultado.OK, "Se agrega paciente: Leticia");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        p.ver(s.eliminarPaciente(48392203).resultado, Retorno.Resultado.OK, "Se elimina paciente: Eduardo");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        p.ver(s.agregarPaciente("Ximena", 20859902, "Las piedras, 1282").resultado, Retorno.Resultado.OK, "Se agrega paciente: Ximena");
        p.ver(s.eliminarPaciente(38948572).resultado, Retorno.Resultado.OK, "Se elimina paciente: Susana");
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
        System.out.println("====================   Se Agrega Paciente 'Zonar' al Inicio   =======================");
        s._pacientes.agregarInicio(pacienteUno);
        System.out.println("====================   Se Agrega Paciente 'Gaston' al Final   =======================");
        s._pacientes.agregarFinal(pacienteDos);        
        p.ver(s.listarPacientes().resultado, Retorno.Resultado.OK, "Se logro listar los pacientes exitosamente");                        
    }

    public static void juegoDePruebaNoImplementada(Sistema s, Prueba p) {
        p.ver(s.reservaConsulta(420, 53243471, new Date(1998, 3, 28)).resultado, Retorno.Resultado.OK, "Funcionalidad no implementada");
        p.ver(s.cancelarReserva(520, 53243471).resultado, Retorno.Resultado.ERROR_1, "Funcionalidad no implementada");
        p.ver(s.anunciaLlegada(520, 53243471).resultado, Retorno.Resultado.ERROR_2, "Funcionalidad no implementada");
        p.ver(s.terminarConsultaMedicoPaciente(520, 53243471, "risa cronica").resultado, Retorno.Resultado.ERROR_4, "Funcionalidad no implementada");
        p.ver(s.cerrarConsulta(520, new Date(1998, 3, 28)).resultado, Retorno.Resultado.ERROR_5, "Funcionalidad no implementada");

        p.ver(s.listarConsultas(282).resultado, Retorno.Resultado.ERROR_1, "Funcionalidad no implementada");
        p.ver(s.listarPacientesEnEspera(295, new Date(2021, 2, 15)).resultado, Retorno.Resultado.ERROR_2, "Funcionalidad no implementada");
        p.ver(s.consultasPendientesPaciente(53243471).resultado, Retorno.Resultado.ERROR_3, "Funcionalidad no implementada");
        p.ver(s.historiaClínicaPaciente(53243471).resultado, Retorno.Resultado.ERROR_4, "Funcionalidad no implementada");
        p.ver(s.reporteDePacientesXFechaYEspecialidad(04, 2010).resultado, Retorno.Resultado.ERROR_5, "Funcionalidad no implementada");

    }

    public static void juegoDePruebaAnunciarLlegadaAlTotem(Sistema s, Prueba p) {
        p.ver(s.anunciaLlegada(420, 20859902).resultado, Retorno.Resultado.ERROR_2, "No Existe una consulta para este conjunto");
        p.ver(s.anunciaLlegada(420, 20859902).resultado, Retorno.Resultado.ERROR_1, "No Existe una consulta para este paciente.");
        //Faltan casos de prueba
    }

    public static void interfaz() {
        System.out.println("¡Bienvenido al Sistema de Gestión de Consultas Médicas!");
        System.out.println("=======================================================");
        System.out.println("En este totem, puedes programar tus consultas médicas,");
        System.out.println("ver tu historial clínico y obtener información sobre");
        System.out.println("nuestros servicios. ¡Estamos aquí para cuidar de ti!");
        System.out.println("=======================================================");
    }
}
