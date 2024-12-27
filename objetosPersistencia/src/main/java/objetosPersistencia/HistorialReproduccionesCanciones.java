package objetosPersistencia;
import exceptions.Genericas.CancionNullException;
import exceptions.Genericas.ClaveNullException;
import exceptions.Genericas.FormatoDeClaveInvalidoException;
import exceptions.HistorialReproduccionesCanciones.*;
import objetoServicio.Fecha;
import objetoServicio.Periodo;
import objetosNegocio.Cancion;
import objetosNegocio.ReproduccionCancion;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase de Persistencia que permite guardar Objetos tipo Reproduccion Cancion en un Historial de Reproducciones con una Clave Unica.
 * Los Objetos tipo Reproduccion Cancion deben cumplir con ciertos requisitos para ser guardados, en caso de no cumplirlos se lanza
 * una excepcion personalizada que detalla el error.
 * Se puede obtener un Objeto tipo Reproduccion Cancion del Catalogo a partir de su Clave.
 * Esta clase tambien permite listar todos los Objetos tipo Reproduccion Cancion de su interior o listarlos por medio de algunos
 * filtros como el Nombre y Año de Creacion.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class HistorialReproduccionesCanciones {
    private HashMap<String, ReproduccionCancion> historialReproduccionesCanciones;
    private int limiteDiarioReproduccionesCompletas = 5;

    /**
     * Constructor por defecto de la Clase HistorialReproduccionesCanciones que crea un HashMap que almacena Objetos tipo Reproduccion Cancion con una Clave Unica.
     */
    public HistorialReproduccionesCanciones(){
        this.historialReproduccionesCanciones = new HashMap<String, ReproduccionCancion>();
    }

    /**
     * Metodo que permite agregar un Objeto tipo Reproduccion Cancion al Historial de Reproducciones Canciones, Instanciandolo
     * y asegurandose que no haya sido guardado previamente.
     * @param clave String
     * @param fecha Fecha
     * @param completa Boolean
     * @param cancion Cancion
     */
    public void agregarReproduccionCancion(String clave, Fecha fecha, Boolean completa, Cancion cancion) throws ReproduccionCancionExistenteException, AtributoCompletaNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ClaveNullException, FormatoDeClaveInvalidoException, CancionNullException, FechaInvalidaException {
        validarReproduccionCancion(clave, fecha, completa, cancion);
        if (historialReproduccionesCanciones.containsKey(clave)){
            throw new ReproduccionCancionExistenteException("Esta Reproduccion Ya Ha Sido Registrada En El Historial De Reproducciones Previamente");
        }
        ReproduccionCancion reproduccionCancion = new ReproduccionCancion(clave, fecha, completa, cancion);
        this.historialReproduccionesCanciones.put(clave, reproduccionCancion);
    }

    /**
     * Metodo que permite actualizar un Objeto tipo Reproduccion Cancion del Historial de Reproducciones Canciones, Instanciandolo
     * nuevamente, valida que el Objeto tipo Reproduccion Cancion haya sido guardado previamente y su atributo Completa este en False.
     * @param clave String
     * @param fecha Fecha
     * @param completa Boolean
     * @param cancion Cancion
     */
    public void actualizarReproduccionCancion(String clave, Fecha fecha, Boolean completa, Cancion cancion) throws ReproduccionCancionNoEcontradaException, ActualizarReproduccionCancionCompletaException, ReproduccionCancionExistenteException, AtributoCompletaNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ClaveNullException, FormatoDeClaveInvalidoException, CancionNullException, FechaInvalidaException {
        validarReproduccionCancion(clave, fecha, completa, cancion);
        if (!historialReproduccionesCanciones.containsKey(clave)){
            throw new ReproduccionCancionNoEcontradaException("La Reproduccion Que Intenta Actualizar No Ha Sido Registrada Previamente");
        }
        if (historialReproduccionesCanciones.get(clave).getCompleta()){
            throw new ActualizarReproduccionCancionCompletaException("Unicamente Pueden Actualizarse Reproducciones Incompletas");
        }
        ReproduccionCancion reproduccionCancion = new ReproduccionCancion(clave, fecha, completa, cancion);
        this.historialReproduccionesCanciones.put(clave, reproduccionCancion);
    }

    /**
     * Metodo de validacion que asegura que el Objeto tipo Reproduccion Cancion a guardar o actualizar cumple con los requisitos del sistema.
     * Se lanza una excepcion personalizada en caso de error.
     * @param clave String
     * @param fecha Fecha
     * @param completa Boolean
     * @param cancion Cancion
     */
    public void validarReproduccionCancion(String clave, Fecha fecha, Boolean completa, Cancion cancion) throws ClaveNullException, CancionNullException, FechaNullException, AtributoCompletaNullException, FormatoDeClaveInvalidoException, LimiteDiarioDeReproduccionConacionCompletaException, FechaInvalidaException {
        if (clave == null){
            throw new ClaveNullException("La Clave No Puede Ser Un Valor Nulo");
        }
        if (cancion == null){
            throw new CancionNullException("La Cancion No Puede Ser Un Valor Nulo");
        }
        if (fecha == null){
            throw new FechaNullException("La Fecha No Puede Ser Un Valor Nulo");
        }
        if (completa == null){
            throw new AtributoCompletaNullException("El Valor Del Atributo Completa No Puede Ser Un Valor Nulo");
        }
        if (!clave.matches("^[A-Z]{3}[0-9]{3}$")){
            throw new FormatoDeClaveInvalidoException("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos");
        }
        if (contadorReproduccionesCompletas(fecha, cancion) >= limiteDiarioReproduccionesCompletas) {
            throw new LimiteDiarioDeReproduccionConacionCompletaException("No Se Pueden Realizar Mas De 5 Reproducciones Completas De Una Misma Cancion Por Dia");
        }
        Fecha hoy = new Fecha();
        if (fecha.getTimeInMillis() < hoy.vencimiento(0, -1).getTimeInMillis()){
            throw new FechaInvalidaException("La Fecha No Puede Tener Mas De Un Mes De Antiguedad");
        }
        if (fecha.getTimeInMillis() > hoy.getTimeInMillis()){
            throw new FechaInvalidaException("La Fecha No Puede Ser Despues De La Fecha Actual");
        }
    }

    /**
     * Metodo que retorna un valor Entero con la cantidad de Reproducciones Canciones
     * Completas de una Cancion en especifico que han sido guardadas el mismo Dia.
     * @param fecha Fecha
     * @param cancion Cancion
     * @return int
     */
    public int contadorReproduccionesCompletas(Fecha fecha, Cancion cancion){
        int cantidadRepeticiones = 0;
        for (ReproduccionCancion reproduccionCancion:historialReproduccionesCanciones.values()){
            if (reproduccionCancion.getCancion().getClave().equals(cancion.getClave()) && reproduccionCancion.getCompleta() &&
                    reproduccionCancion.getFecha().getAnio() == fecha.getAnio() &&
                    reproduccionCancion.getFecha().getMes() == fecha.getMes() &&
                    reproduccionCancion.getFecha().getDia() == fecha.getDia()){
                cantidadRepeticiones ++;
            }
        }
        return cantidadRepeticiones;
    }

    /**
     * Metodo de verificacion que lanza una Excepcion personalizada cuando el Historial de Reproduccines Canciones esta vacio.
     */
    public void verificarHistorialReproduccionesCancionesVacio() throws HistorialReproduccionesCancionesVacioException {
        if (historialReproduccionesCanciones.isEmpty()){
            throw new HistorialReproduccionesCancionesVacioException("El Historial De Reproducciones No Tiene Ningun Registro Todavia");
        }
    }

    /**
     * Metodo que devuelve un Objeto tipo Reproduccion Cancion a partir de su clave.
     * Devuelve Null en caso de no encontrar coincidencias.
     * @return ReproduccionCancion
     */
    public ReproduccionCancion consultarReproduccionCancionPorClave(String clave) {
        return this.historialReproduccionesCanciones.get(clave);
    }

    /**
     * Metodo que devuelve un ArrayList con todas las Reproducciones Canciones de una misma Cancion a partir de su clave.
     * Devuelve una ArrayList vacio en caso de no encontrar coincidencias.
     * @param clave String
     * @return ArrayList<ReproduccionCancion>
     */
    public ArrayList<ReproduccionCancion> consultarReproduccionesCancionesPorClaveCancion(String clave) throws HistorialReproduccionesCancionesVacioException {
        verificarHistorialReproduccionesCancionesVacio();
        ArrayList<objetosNegocio.ReproduccionCancion> reproduccionesCanciones = new ArrayList<objetosNegocio.ReproduccionCancion>();
        for (objetosNegocio.ReproduccionCancion reproduccionCancion:this.historialReproduccionesCanciones.values()){
            if (reproduccionCancion.getCancion().getClave().equals(clave)){
                reproduccionesCanciones.add(reproduccionCancion);
            }
        }
        return reproduccionesCanciones;
    }

    /**
     * Metodo que devuelve un ArrayList con las Reproducciones Canciones de un mismo Autor.
     * Devuelve una ArrayList vacio en caso de no encontrar coincidencias.
     * @param autor String
     * @return ArrayList<ReproduccionCancion>
     */
    public ArrayList<ReproduccionCancion> consultarReproduccionesCancionesPorAutor(String autor) throws HistorialReproduccionesCancionesVacioException {
        verificarHistorialReproduccionesCancionesVacio();
        ArrayList<objetosNegocio.ReproduccionCancion> reproduccionesCanciones = new ArrayList<objetosNegocio.ReproduccionCancion>();
        for (objetosNegocio.ReproduccionCancion reproduccionCancion:this.historialReproduccionesCanciones.values()){
            if (reproduccionCancion.getCancion().getAutor().equals(autor)){
                reproduccionesCanciones.add(reproduccionCancion);
            }
        }
        return reproduccionesCanciones;
    }

    /**
     * Metodo que devuelve un ArrayList con las Reproducciones Canciones que estan dentro de un mismo Periodo.
     * Devuelve una ArrayList vacio en caso de no encontrar ningun Objeto tipo Reproduccion Cancion que este dentro del Periodo.
     * @param periodo Periodo
     * @return ArrayList<ReproduccionCancion>
     */
    public ArrayList<ReproduccionCancion> consultarReproduccionesCancionesPorPeriodo(Periodo periodo) throws HistorialReproduccionesCancionesVacioException {
        verificarHistorialReproduccionesCancionesVacio();
        ArrayList<ReproduccionCancion> reproduccionesCanciones = new ArrayList<ReproduccionCancion>();
        for (ReproduccionCancion reproduccionCancion:this.historialReproduccionesCanciones.values()){
            if (reproduccionCancion.getFecha().getTimeInMillis() >= periodo.getDesde().getTimeInMillis() &&
                    reproduccionCancion.getFecha().getTimeInMillis() <= periodo.getHasta().getTimeInMillis()){
                reproduccionesCanciones.add(reproduccionCancion);
            }
        }
        return reproduccionesCanciones;
    }

    /**
     * Metodo que devuelve un ArrayList con todos los Objetos tipo Reproduccion Cancion guradados en el Catalogo de
     * Reproducciones Canciones.
     * @return ArrayList<ReproduccionCancion>
     */
    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones() throws HistorialReproduccionesCancionesVacioException {
        verificarHistorialReproduccionesCancionesVacio();
        ArrayList<ReproduccionCancion> reproduccionesCanciones = new ArrayList<ReproduccionCancion>();
        for (ReproduccionCancion reproduccionCancion:this.historialReproduccionesCanciones.values()){
            reproduccionesCanciones.add(reproduccionCancion);
        }
        return reproduccionesCanciones;
    }
}

