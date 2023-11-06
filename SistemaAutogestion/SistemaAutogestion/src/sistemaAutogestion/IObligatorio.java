package sistemaAutogestion;

import java.util.Date;

public interface IObligatorio {

    /*
    **************** REGISTROS **************************************
     */
    //pre: Recibe int maxima cantidad de pacientes por medicos     
    // post: Retorna "OK" si la cantidad es >= 0 o < 15, 
    public Retorno crearSistemaDeAutogestion(int maxPacientesporMedico);

    /**
 * Pre: Recibe el nombre del médico como un string no vacía.
 *      El código de médico debe ser un int positivo único.
 *      El número de teléfono debe ser un int positivo válido.
 *      La especialidad debe ser un entero que identifique una especialidad válida.
 * 
 * Post: Registra un médico en el sistema con los datos proporcionados.
 *       Devuelve un objeto Retorno que contiene información sobre el resultado de la operación.
 *       - Si se registra exitosamente, el Retorno debe tener estado "OK".
 *       - Si el médico ya está registrado (duplicado), el Retorno debe tener estado "ERROR_1".
 *       - Si el dato de entrada en especialidad es inválido, el Retorno debe tener estado "ERROR_2".
 *       - Si el dato de entrada en nombre es vacio, el Retorno debe tener estado "ERROR_3".
 *       - Si los datos de entrada en cod medico y telefono son menor o igual a 0, el Retorno debe tener estado "ERROR_4".
 */
    public Retorno registrarMedico(String nombre, int codMedico, int tel, int especialidad);
       
    /**
 * Pre: Recibe el código de médico (codMedico) como un int positivo.
 * 
 * Post: Elimina un médico del sistema si existe un médico con el código proporcionado.
 *       - Si se encuentra y elimina exitosamente, devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra ningún médico con el código proporcionado, devuelve un objeto Retorno con estado "ERROR_1".
    */
    public Retorno eliminarMedico(int codMedico);
    

    /**
 * Pre: Recibe el nombre del paciente como un string no vacío.
 *      El número de cédula (Ci) debe ser un int positivo.
 *      La dirección del paciente debe ser un string no vacío.
 * 
 * Post: Agrega un nuevo paciente al sistema si cumple con los siguientes criterios:
 *       - No existe ningún paciente con el mismo número de cédula (Ci).
 *       - El nombre y la dirección no están vacíos.
 *       
 *       - Si el paciente se agrega exitosamente, devuelve un objeto Retorno con estado "OK".
 *       - Si ya existe un paciente con el mismo número de cédula (Ci), devuelve un objeto Retorno con estado "ERROR_1".
 *       - Si el nombre o la dirección están vacíos, devuelve un objeto Retorno con estado "ERROR_2".
 *       - Si el numero de cédula (Ci) es igual o menor a 0, devuelve un objeto Retorno con estado "ERROR_3".
 */
    public Retorno agregarPaciente(String nombre, int CI, String direccion);
    /**
 * Pre: Recibe el numero de cedula (ci) como un int positivo.
 * 
 * Post: Elimina un paciente del sistema si existe un paciente con el código proporcionado.
 *       - Si se encuentra y elimina exitosamente, devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra ningún médico con el código proporcionado, devuelve un objeto Retorno con estado "ERROR_1".
    */

    public Retorno eliminarPaciente(int CI);

    /*
    **************** GESTIÓN DE CONSULTAS **************************************
     */
    
    
    
    /**
 * Pre: Recibe el código de médico (codMedico) como un int positivo.
 *      Recibe el número de cédula del paciente (ciPaciente) como un int positivo.
 *      Recibe la fecha de la consulta (fecha) como un objeto Date válido.
 * 
 * Post: Realiza la reserva de una nueva consulta con el médico especificado y la fecha proporcionada.
 *       - Si la reserva se realiza exitosamente, devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra el paciente con el número de cédula proporcionado, devuelve un objeto Retorno con estado "ERROR 1".
 *       - Si no se encuentra el médico con el código proporcionado, devuelve un objeto Retorno con estado "ERROR 2".
 *       - Si el médico ya tiene una consulta programada con ese paciente en la misma fecha, devuelve un objeto Retorno con estado "ERROR 3".
 *       
 *       Notas adicionales:
 *       - La reserva inicialmente queda en estado "pendiente".
 *       - Cuando el paciente anuncie su llegada, la reserva pasará a estado "en espera".
 *       - Una vez que el médico atienda al paciente, la reserva pasará a estado "cerrada".
 *       - Cada reserva tiene un número asignado automáticamente comenzando en 1.
    */
    public Retorno reservaConsulta(int codMedico, int ciPaciente, Date fecha);

    /**
 * Pre: Recibe el código de médico (codMedico) como un int positivo.
 *      Recibe el número de cédula del paciente (ciPaciente) como un int positivo.
 * 
 * Post: Cancela la reserva de una consulta previamente programada por el paciente con el médico especificado.
 *       - Si la cancelación se realiza exitosamente, devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra el paciente con el número de cédula proporcionado, devuelve un objeto Retorno con estado "ERROR 1".
 *       - Si no se encuentra el médico con el código proporcionado, devuelve un objeto Retorno con estado "ERROR 2".
 *       - Si el paciente no ha tenido una reserva con ese médico o la reserva está cerrada, devuelve un objeto Retorno con estado "ERROR 3".
 *       - Si la reserva no está en estado "pendiente", devuelve un objeto Retorno con estado "ERROR 4".
 *       
 *       Notas adicionales:
 *       - Solo se pueden cancelar las reservas que estén en estado "pendiente".
 *       - Cuando se cancela una reserva, el número que tenía asignado el paciente queda disponible para el primer paciente en la "lista de espera por números disponibles".
 *       - El número de reserva solo se libera si la reserva se encuentra en estado "pendiente".
 */
    public Retorno cancelarReserva(int codMedico, int ciPaciente);

 /**
 * Pre: Recibe el código de médico (codMedico) como un int positivo.
 *      Recibe el número de cédula del paciente (CIPaciente) como un int positivo.
 * 
 * Post: El paciente anuncia su llegada en el tótem y se controla si tiene una consulta programada para la fecha actual.
 *       - Si el paciente tiene una consulta programada para el día de hoy, se imprimirá el nombre del médico con el que tiene consulta y el número de reserva,
 *       La consulta del paciente pasará a estado "en espera" y devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra el paciente con el número de cédula proporcionado, devuelve un objeto Retorno con estado "ERROR 1".
 *       - Si el paciente no tiene una consulta programada para hoy, devuelve un objeto Retorno con estado "ERROR 2".
 *       
 *       
 *       Notas adicionales:
 *       - El médico atiende a los pacientes según el número de reserva.
 *       - La consulta solo pasará a estado "en espera" si el paciente tiene una consulta programada para hoy.
 */
    public Retorno anunciaLlegada(int codMedico, int CIPaciente);

    /**
 * Pre: Recibe el número de cédula del paciente (CIPaciente) como un int positivo.
 *      Recibe el código de médico (codMedico) como un int positivo.
 *      Recibe el detalle de consulta (detalleConsulta) como un string no vacío.
 * 
 * Post: Cierra la consulta del paciente con el médico especificado y la marca como "terminada".
 *       - Agrega el detalle de la consulta con las indicaciones médicas al historial clínico del paciente.
 *       - Si la consulta se cierra exitosamente, devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra el paciente con el número de cédula proporcionado, devuelve un objeto Retorno con estado "ERROR 1".
 *       - Si el paciente no tiene una consulta "en espera" con ese médico en la fecha actual, devuelve un objeto Retorno con estado "ERROR 2".
 *       
 *       Notas adicionales:
 *       - La consulta pasará a formar parte de la historia clínica del paciente con los detalles de cada consulta y las indicaciones médicas.
 *       - El detalle de la consulta se registra mediante la cadena proporcionada en el parámetro "detalleConsulta".
 */
    public Retorno terminarConsultaMedicoPaciente(int CIPaciente, int codMedico, String detalleDeConsulta);

/**
 * Pre: Recibe el código de médico (codMédico) como un int positivo.
 *      Recibe la fecha de la consulta (fechaConsulta) como un objeto Date válido.
 * 
 * Post: Cierra las consultas en las cuales el paciente no se presentó y las marca como "no asistió".
 *       - Las consultas con estado "no asistió" pasarán a formar parte de la historia clínica del paciente.
 *       - Si se cierran las consultas correctamente, devuelve un objeto Retorno con estado "OK".
 *       - Si no se encuentra el médico con el código proporcionado, devuelve un objeto Retorno con estado "ERROR 1".
 *       - Si el médico no tiene consultas programadas para la fecha especificada, devuelve un objeto Retorno con estado "ERROR 2".
 *       
 *       Notas adicionales:
 *       - Las consultas marcadas como "no asistió" se agregan al historial clínico del paciente.
 *       - La fecha de consulta se especifica mediante el objeto Date proporcionado en el parámetro "fechaConsulta".
 */
    public Retorno cerrarConsulta(int codMédico, Date fechaConsulta);

    /*
    **************** LISTADO Y REPORTES **************************************
     */
    
    /**
 * Pre: Ninguna.
 * 
 * Post: Devuelve una lista de médicos ordenada y la muestra en algún formato especificado.
 *       - Si la lista de médicos (_medicos) no está vacía, devuelve una lista de médicos ordenada y la muestra.
 *       - Si la lista de médicos está vacía, no realiza ninguna acción y devuelve un objeto Retorno con estado "OK".
 *       
 */
    public Retorno listarMédicos();

 /**
 * Pre: Ninguna.
 * 
 * Post: Devuelve una lista de pacientes ordenada y la muestra en algún formato especificado.
 *       - Si la lista de pacientes (_pacientes) no está vacía, devuelve una lista de pacientes ordenada y la muestra.
 *       - Si la lista de pacientes está vacía, no realiza ninguna acción y devuelve un objeto Retorno con estado "OK".      
 */
    public Retorno listarPacientes();

 /**
 * Pre: Recibe el código de médico (codMédico) como un string no vacío.
 * 
 * Post: Muestra todas las consultas asignadas a ese médico agrupadas por día.
 *       - Si el código de médico existe y se pueden mostrar las consultas, devuelve un objeto Retorno con estado "OK".
 *       - Si el código de médico no existe, devuelve un objeto Retorno con estado "ERROR 1".
 */
    public Retorno listarConsultas(int codMedico);

 /**
 * Pre: Recibe el código de médico (codMédico) como un int positivo.
 *      Recibe la fecha de la consulta (fecha) como un objeto Date válido.
 * 
 * Post: Lista todos los pacientes que están anotados en una consulta para ese médico en la fecha indicada con estado "en espera".
 *       - Si el médico tiene consultas programadas para esa fecha y se pueden mostrar los pacientes "en espera", devuelve un objeto Retorno con estado "OK".
 *       - Si el médico no tiene consultas programadas para esa fecha, devuelve un objeto Retorno con estado "ERROR 1".
 */
    public Retorno listarPacientesEnEspera(int codMedico, Date fecha);

/**
 * Pre: Recibe el número de cédula del paciente (CIPaciente) como un int positivo.
 * 
 * Post: Lista todas las consultas pendientes de ese paciente.
 *       - Si el número de cédula del paciente existe y se pueden listar las consultas pendientes, devuelve un objeto Retorno con estado "OK".
 *       - Si el número de cédula del paciente no existe, devuelve un objeto Retorno con estado "ERROR 1".
 */
    public Retorno consultasPendientesPaciente(int CIPaciente);

/**
 * Pre: Recibe el número de cédula del paciente (ci) como un int positivo.
 * 
 * Post: Muestra todas las consultas a las que asistió (cerradas) o no asistió el paciente.
 *       - Si el número de cédula del paciente existe y se pueden listar las consultas, devuelve un objeto Retorno con estado "OK".
 *       - Si el número de cédula del paciente no existe, devuelve un objeto Retorno con estado "ERROR 1".
 */
    public Retorno historiaClínicaPaciente(int ci);

/**
 * Pre: Recibe el mes (mes) como un int dentro del rango válido (1-12).
 *      Recibe el año (año) como un int dentro del rango válido (2020-2023).
 * 
 * Post: Muestra un reporte en forma de matriz donde se indica, para cada día del mes (filas) y cada especialidad (columnas),
 *       la cantidad de pacientes que fueron atendidos (consultas cerradas) para cada especialidad.
 *       - Si el mes y el año están en el rango válido y se puede mostrar el reporte, devuelve un objeto Retorno con estado "OK".
 *       - Si el mes no está en el rango válido (1-12) o el año no está en el rango válido (2020-2023), devuelve un objeto Retorno con estado "ERROR 1".
 */
    public Retorno reporteDePacientesXFechaYEspecialidad(int mes, int año);

}
