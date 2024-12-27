package objetosPersistencia;
import exceptions.CatalogoContenidos.*;
import exceptions.Genericas.ClaveNullException;
import exceptions.Genericas.FormatoDeClaveInvalidoException;
import objetoServicio.Fecha;
import objetosNegocio.Contenido;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase de Persistencia que permite guardar Objetos tipo Contenido en un Catalogo de Contenidos con una Clave Unica.
 * Los Objetos tipo Contenido deben cumplir con ciertos requisitos para ser guardados, en caso de no cumplirlos se lanza
 * una excepcion personalizada que detalla el error.
 * Se puede obtener un Objeto tipo Contenido del Catalogo a partir de su Clave.
 * Esta clase tambien permite listar todos los Objetos tipo Contenido de su interior o listarlos por medio de algunos
 * filtros como el Nombre y Año de Creacion.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CatalogoContenidos {
    private HashMap<String, Contenido> catalogoContenidos;

    /**
     * Constructor por defecto de la Clase CatalogoContenidos que crea un HashMap que almacena Objetos tipo Contenido con una Clave Unica.
     */
    public CatalogoContenidos(){
        this.catalogoContenidos = new HashMap<String, Contenido>();
    }

    /**
     * Metodo que permite agregar un Objeto tipo Contenido al Catalogo de Contenidos asegurandose que no haya sido guardado previamente.
     * @param clave String
     * @param contenido Contenido
     */
    public void agregarContenido(String clave, Contenido contenido) throws ContenidoExistenteException, ContenidoNullException, DuracionDeContenidoInvalidaException, AnioCreacionDeContenidoInvalidoException, ClaveNullException, FormatoDeClaveInvalidoException {
        validarContenido(clave, contenido);
        if (catalogoContenidos.containsKey(clave)){
            throw new ContenidoExistenteException("Este Contenido Ya Ha Sido Registrado En El Catalogo De Contenidos Previamente");
        }
        this.catalogoContenidos.put(clave, contenido);
    }

    /**
     * Metodo que permite actualizar un Objeto tipo Contenido del Catalogo de Contenidos asegurandose que este haya sido guardado previamente.
     * Se lanza una excepcion personalizada en caso de error.
     * @param clave String
     * @param nuevoContenido Contenido
     */
    public void actualizarContenido(String clave, Contenido nuevoContenido) throws ContenidoNoEncontradoException, ContenidoNullException, DuracionDeContenidoInvalidaException, AnioCreacionDeContenidoInvalidoException, ClaveNullException, FormatoDeClaveInvalidoException {
        validarContenido(clave, nuevoContenido);
        if (!catalogoContenidos.containsKey(clave)){
            throw new ContenidoNoEncontradoException("El Contenido Que Intenta Actualizar No Ha Sido Registrado Previamente");
        }
        this.catalogoContenidos.put(clave, nuevoContenido);
    }

    /**
     * Metodo de validacion que asegura que el Objeto tipo Contenido a guardar o actualizar cumple con los requisitos del sistema.
     * Se lanza una excepcion personalizada en caso de error.
     * @param clave String
     * @param contenido Contenido
     */
    public static void validarContenido(String clave, Contenido contenido) throws ClaveNullException, ContenidoNullException, AnioCreacionDeContenidoInvalidoException, FormatoDeClaveInvalidoException, DuracionDeContenidoInvalidaException {
        if (clave == null){
            throw new ClaveNullException("La Clave No Puede Ser Un Valor Nulo");
        }
        if (contenido == null){
            throw new ContenidoNullException("El Contenido No Puede Ser Un Valor Nulo");
        }
        if (!clave.matches("^[A-Z]{3}[0-9]{3}$")){
            throw new FormatoDeClaveInvalidoException("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos");
        }
        if (contenido.getAnioCreacion() < 1970){
            throw new AnioCreacionDeContenidoInvalidoException("El Anio De Creacion Del Contenido No Puede Ser Previo A 1970");
        }
        if (contenido.getAnioCreacion() > new Fecha().getAnio()){
            throw new AnioCreacionDeContenidoInvalidoException("El Anio De Creacion Del Contenido No Puede Ser Despues De La Fecha Actual");
        }
        if (contenido.getDuracion() < .5){
            throw new DuracionDeContenidoInvalidaException("La Duracion Del Contenido No Puede Ser Menor A Medio Minuto");
        }
        if (contenido.getDuracion() > 600){
            throw new DuracionDeContenidoInvalidaException("La Duracion Del Contenido No Puede Ser Mayor A 10 Horas");
        }
    }

    /**
     * Metodo que devuelve un Objeto tipo Contenido a partir de su clave.
     * Devuelve Null en caso de no encontrar coincidencias.
     * @return Contenido
     */
    public Contenido getContenido(String clave){
        return this.catalogoContenidos.get(clave);
    }

    /**
     * Metodo que elimina un Objeto tipo Contenido del Catalogo de Contenidos a partir de su clave.
     * Lanza una Excepcion en caso de no encontrar coincidencias.
     * @param clave String
     */
    public void eliminarContenido(String clave) throws ContenidoNoEncontradoException {
        if (this.catalogoContenidos.get(clave) == null){
            throw new ContenidoNoEncontradoException("El Contenido Que Desea Eliminar No Ha Sido Registrado Aun");
        }
        this.catalogoContenidos.remove(clave);
    }

    /**
     * Metodo de verificacion que lanza una Excepcion personalizada cuando el Catalogo de Contenidos esta vacio.
     */
    public void verificarCatalogoContenidosVacio() throws CatalogoContenidosVacioException {
        if (catalogoContenidos.isEmpty()){
            throw new CatalogoContenidosVacioException("El Catalogo De Contenidos No Tiene Ningun Registro Todavia");
        }
    }

    /**
     * Metodo que devuelve un ArrayList con los Contenidos que tienen una Clave parecida.
     * Devuelve una ArrayList vacio en caso de no encontrar ningun Objeto tipo Contenido que coincida con el Nombre
     * que recibe como parametro.
     * @param clave String
     * @return ArrayList<Contenido>
     */
    public ArrayList<Contenido> consultarContenidosPorClaveParecida(String clave) throws CatalogoContenidosVacioException {
        verificarCatalogoContenidosVacio();
        ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
        for (Contenido contenido:this.catalogoContenidos.values()){
            if (contenido.getClave().toLowerCase().contains(clave.toLowerCase())){
                contenidos.add(contenido);
            }
        }
        return contenidos;
    }

    /**
     * Metodo que devuelve un ArrayList con los Contenidos que tienen el mismo Nombre en caso de existir.
     * Devuelve una ArrayList vacio en caso de no encontrar ningun Objeto tipo Contenido que coincida con el Nombre
     * que recibe como parametro.
     * @param nombre String
     * @return ArrayList<Contenido>
     */
    public ArrayList<Contenido> consultarContenidosPorNombre(String nombre) throws CatalogoContenidosVacioException {
        verificarCatalogoContenidosVacio();
        ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
        for (Contenido contenido:this.catalogoContenidos.values()){
            if (contenido.getNombre().equals(nombre)){
                contenidos.add(contenido);
            }
        }
        return contenidos;
    }

    /**
     * Metodo que devuelve un ArrayList con los Contenidos que tienen el mismo Año de Creacion.
     * Devuelve una ArrayList vacio en caso de no encontrar ningun Objeto tipo Contenido que coincida con el Año
     * que recibe como parametro.
     * @param anio int
     * @return ArrayList<Contenido>
     */
    public ArrayList<Contenido> consultarContenidosPorAnio(int anio) throws CatalogoContenidosVacioException {
        verificarCatalogoContenidosVacio();
        ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
        for (Contenido contenido:this.catalogoContenidos.values()){
            if (contenido.getAnioCreacion() == anio){
                contenidos.add(contenido);
            }
        }
        return contenidos;
    }

    /**
     * Metodo que devuelve un ArrayList con los Contenidos que tienen el mismo Nombre y Año en caso de existir.
     * Devuelve una ArrayList vacio en caso de no encontrar ningun Objeto tipo Contenido que coincida con el Nombre
     * y Año que recibe como parametros.
     * @param nombre String
     * @param anio int
     * @return ArrayList<Contenido>
     */
    public ArrayList<Contenido> consultarContenidosPorNombreyAnio(String nombre, int anio) throws CatalogoContenidosVacioException {
        verificarCatalogoContenidosVacio();
        ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
        for (Contenido contenido:this.catalogoContenidos.values()){
            if (contenido.getNombre().equals(nombre) && contenido.getAnioCreacion() == anio){
                contenidos.add(contenido);
            }
        }
        return contenidos;
    }

    /**
     * Metodo que devuelve un ArrayList con todos los Objetos tipo Contenido guradados en el Catalogo de Contenidos.
     * @return ArrayList<Contenido>
     */
    public ArrayList<Contenido> consultarContenidos() throws CatalogoContenidosVacioException {
        verificarCatalogoContenidosVacio();
        ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
        for (Contenido contenido:this.catalogoContenidos.values()){
            contenidos.add(contenido);
        }
        return contenidos;
    }
}
