package objetosNegocio;
import exceptions.AnioCreacionInvalidoException;
import exceptions.DuracionExcedeMaximoPermitidoException;

/**
 * Clase que permite construir y manipular un objeto tipo Contenido.
 * Posee los atributos principales Clave, Nombre, AnioCreacion, Duracion)
 *
 * @author Escalante, Sebastián; Cortez, Manuel
 **/
public class Contenido {
    protected String clave;
    protected String nombre;
    protected int anioCreacion;
    protected float duracion;

    /**
     * Constructor por defecto de la clase Contenido
     **/
    public Contenido(){}

    /**
     * Constructor que inicializa los atributos de la clase Constructor al valor de sus parámetros (Clave, Nombre, AnioCreacion, Duracion)
     * Además, valida que el aniCreacion es posterior a 1950 y la Duracion no excede los 1000 minutos.
     * @param clave String
     * @param nombre String
     * @param anioCreacion int
     * @param duracion float
     */
    public Contenido(String clave, String nombre, int anioCreacion, float duracion) throws AnioCreacionInvalidoException, DuracionExcedeMaximoPermitidoException {
        this.clave = clave;
        this.nombre = nombre;
        if (anioCreacion < 1950){
            throw new AnioCreacionInvalidoException("El Año de Creacion es Antes de 1950");
        } else {
            this.anioCreacion = anioCreacion;
        }
        if (duracion > 1000){
            throw new DuracionExcedeMaximoPermitidoException("La Duracion Excede el Maximo Permitido");
        } else {
            this.duracion = duracion;
        }
    }

    /**
     *  Constructor que recibe sólo la clave del Contenido e inicializa con ella el atributo.
     *  Los demás atributos de la clase están inicializados en null o en 0 respectivamente.
     * @param clave String
     */
    public Contenido(String clave){
        this.clave = clave;
        this.nombre = "";
        this.anioCreacion = 0;
        this.duracion = 0;
    }

    /**
     * Constructor que recibe como parámetro un objeto de tipo Contenido
     * e inicializa los atributos de la clase con los valores del mismo.
     * @param contenido Contenido
     */
    public Contenido(Contenido contenido){
        this.clave = contenido.getClave();
        this.nombre = contenido.getNombre();
        this.anioCreacion = contenido.getAnioCreacion();
        this.duracion = contenido.getDuracion();
    }

    /**
     * Método que establece la clave a un objeto tipo Contenido desde el parámetro String que recibe.
     * @param clave String
     * */
    public void setClave(String clave){
        this.clave = clave;
    }

    /**
     * Método que devuelve el valor de la Clave del objeto tipo Contenido que lo llama.
     * @return clave String
     * */
    public String getClave(){
        return this.clave;
    }

    /**
     * Método que establece el nombre a un objeto tipo Contenido desde el parámetro String que recibe.
     * @param nombre String
     * */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Método que devuelve el valor del Nombre del objeto tipo Contenido que lo llama.
     * @return nombre String
     * */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Método que establece el anioCreacion a un objeto tipo Contenido desde el parámetro entero que recibe.
     * @param anioCreacion int
     * */
    public void setAnioCreacion(int anioCreacion){
        this.anioCreacion = anioCreacion;
    }

    /**
     * Método que devuelve el valor del anioCreacion del objeto tipo Contenido que lo llama.
     * @return anioCreacion int
     * */
    public int getAnioCreacion(){
        return this.anioCreacion;
    }

    /**
     * Método que establece la duracion a un objeto tipo Contenido desde el parámetro flotante que recibe.
     * @param duracion float
     * */
    public void setDuracion(float duracion){
        this.duracion = duracion;
    }

    /**
     * Método que devuelve el valor de la duracion del objeto tipo Contenido que lo llama.
     * @return duracion float
     * */
    public float getDuracion(){
        return this.duracion;
    }

    /**
     * Metodo que compara la clave y clase del objeto que lo llama y del que se le pasa como parametro
     * @param otroObjeto Object
     * @return boolean
     */
    @Override
    public boolean equals(Object otroObjeto){
        if (otroObjeto == null){
            return false;
        }
        if (this.getClass() != otroObjeto.getClass()){
            return false;
        }
        Contenido otroContenido = (Contenido) otroObjeto;
        if (this.getClave().equals(otroContenido.getClave())){
            return true;
        }
        return false;
    }

    /**
     * Metodo que genera un codigo unico para el objeto que lo llama.
     * @return int
     */
    @Override
    public int hashCode(){
        int hash = 7;
        hash = hash * 29 + this.getClave().hashCode();
        return hash;
    }

    /**
     * Método que devuelve un Contenido en objeto tipo String con el formato ("clave,nombre,anioCreacion,duracion")
     * @return texto String
     * */
    @Override
    public String toString(){
        return String.format("%s, %s, %d, %s", this.getClave(), this.getNombre(), this.getAnioCreacion(), this.getDuracion());
    }
}


