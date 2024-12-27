package objetosPersistencia;
import exceptions.CatalogoCanciones.*;
import exceptions.CatalogoContenidos.*;
import exceptions.Genericas.CancionNullException;
import exceptions.Genericas.ClaveNullException;
import exceptions.Genericas.FormatoDeClaveInvalidoException;
import objetosNegocio.Cancion;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Clase de Persistencia que permite guardar Objetos tipo Cancion en un Catalogo de Canciones con una Clave Unica.
 * Los Objetos tipo Cancion deben cumplir con ciertos requisitos para ser guardados, en caso de no cumplirlos se lanza
 * una excepcion personalizada que detalla el error.
 * Se puede obtener un Objeto tipo Cancion del Catalogo a partir de su Clave.
 * Esta clase tambien permite listar todos los Objetos tipo Cancion de su interior o listarlas por un Rango de Duracion (Minutos).
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class CatalogoCanciones {
    private HashMap<String, Cancion> catalogoCanciones;

    /**
     * Constructor por defecto de la Clase CatalogoCanciones que crea un HashMap que almacena Objetos tipo Cancion con una Clave Unica.
     */
    public CatalogoCanciones(){
        this.catalogoCanciones = new HashMap<String, Cancion>();
    }

    /**
     * Metodo que permite agregar un Objeto tipo Cancion al Catalogo de Canciones asegurandose que no haya sido guardado previamente.
     * @param clave String
     * @param cancion Cancion
     */
    public void agregarCancion(String clave, Cancion cancion) throws CancionExistenteException, NombreAutorInvalidoException, ClaveNullException, FormatoDeClaveInvalidoException, CancionNullException {
        validarCancion(clave, cancion);
        if (this.catalogoCanciones.containsValue(cancion)){
            throw new CancionExistenteException("Esta Cancion Ya Ha Sido Registrada En El Catalogo De Canciones Previamente");
        }
        this.catalogoCanciones.put(clave, cancion);
    }

    /**
     * Metodo que permite actualizar un Objeto tipo Cancion del Catalogo de Canciones asegurandose que este haya sido guardado previamente.
     * Se lanza una excepcion personalizada en caso de error.
     * @param clave String
     * @param nuevaCancion Cancion
     */
    public void actualizarCancion(String clave, Cancion nuevaCancion) throws CancionNoEncontradaException, ContenidoNullException, DuracionDeContenidoInvalidaException, AnioCreacionDeContenidoInvalidoException, ClaveNullException, FormatoDeClaveInvalidoException, NombreAutorInvalidoException, CancionNullException {
        CatalogoContenidos.validarContenido(clave, nuevaCancion);
        validarCancion(clave, nuevaCancion);
        if (!catalogoCanciones.containsKey(clave)){
            throw new CancionNoEncontradaException("La Cancion Que Intenta Actualizar No Ha Sido Registrada Previamente");
        }
        this.catalogoCanciones.put(clave, nuevaCancion);
    }

    /**
     * Metodo de validacion que asegura que el Objeto tipo Cancion a guardar o actualizar cumple con los requisitos del sistema.
     * Se lanza una excepcion personalizada en caso de error.
     * @param clave String
     * @param cancion Cancion
     */
    public void validarCancion(String clave, Cancion cancion) throws ClaveNullException, CancionNullException, FormatoDeClaveInvalidoException, NombreAutorInvalidoException {
        if (clave == null){
            throw new ClaveNullException("La Clave No Puede Ser Un Valor Nulo");
        }
        if (cancion == null){
            throw new CancionNullException("La Cancion No Puede Ser Un Valor Nulo");
        }
        if (!clave.matches("^[A-Z]{3}[0-9]{3}$")){
            throw new FormatoDeClaveInvalidoException("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos");
        }
        if (cancion.getAutor().length() < 3){
            throw new NombreAutorInvalidoException("El Nombre Del Autor No Puede Tener Menos De 3 Caracteres");
        }
        if (cancion.getAutor().length() > 50){
            throw new NombreAutorInvalidoException("El Nombre Del Autor No Puede Tener Mas de 50 Caracteres");
        }
        cancion.setAutor(cancion.getAutor().trim());
    }

    /**
     * Metodo que devuelve un Objeto tipo Cancion a partir de su clave.
     * Devuelve Null en caso de no encontrar coincidencias.
     * @return Cancion
     */
    public Cancion getCancion(String clave){
        return this.catalogoCanciones.get(clave);
    }

    /**
     * Metodo que elimina un Objeto tipo Cancion del Catalogo de Canciones a partir de su clave.
     * Lanza una Excepcion en caso de no encontrar coincidencias.
     * @param clave String
     */
    public void eliminarCancion(String clave) throws CancionNoEncontradaException {
        if (this.catalogoCanciones.get(clave) == null){
            throw new CancionNoEncontradaException("La Cancion Que Desea Eliminar No Ha Sido Registrada Aun");
        }
        this.catalogoCanciones.remove(clave);
    }

    /**
     * Metodo de verificacion que lanza una Excepcion personalizada cuando el Catalogo de Canciones esta vacio.
     */
    public void verificarCatalogoCancionesVacio() throws CatalogoCancionesVacioException {
        if (catalogoCanciones.isEmpty()){
            throw new CatalogoCancionesVacioException("El Catalogo De Canciones No Tiene Ningun Registro Todavia");
        }
    }

    /**
     * Metodo que devuelve un ArrayList con las Canciones que estan dentro de un mismo Rango de Duracion.
     * Devuelve una ArrayList vacio en caso de no encontrar ningun Objeto tipo Cancion que este dentro del Rango.
     * @param duracionMinima float
     * @param duracionMaxima float
     * @return ArrayList<Cancion>
     */
    public ArrayList<Cancion> consultarCancionesPorRangoDuracion(float duracionMinima, float duracionMaxima) throws CatalogoCancionesVacioException, DuracionMinimaEsMayorADuracionMaximaException, DuracionMinimaEsIgualADuracionMaximaException {
        verificarCatalogoCancionesVacio();
        if (duracionMinima > duracionMaxima){
            throw new DuracionMinimaEsMayorADuracionMaximaException("La Duracion Minima No Puede Ser Mayor A La Duracion Maxima");
        }
        if (duracionMinima == duracionMaxima){
            throw new DuracionMinimaEsIgualADuracionMaximaException("La Duracion Minima No Puede Ser Igual A La Duracion Maxima");
        }
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        for (Cancion cancion:this.catalogoCanciones.values()){
            if (cancion.getDuracion() >= duracionMinima && cancion.getDuracion() <= duracionMaxima){
                canciones.add(cancion);
            }
        }
        return canciones;
    }

    /**
     * Metodo que devuelve un ArrayList con todos los Objetos tipo Cancion guradados en el Catalogo de Canciones.
     * @return ArrayList<Cancion>
     */
    public ArrayList<Cancion> consultarCanciones() throws CatalogoCancionesVacioException {
        verificarCatalogoCancionesVacio();
        ArrayList<Cancion> canciones = new ArrayList<Cancion>();
        for (Cancion cancion:this.catalogoCanciones.values()){
            canciones.add(cancion);
        }
        return canciones;
    }
}
