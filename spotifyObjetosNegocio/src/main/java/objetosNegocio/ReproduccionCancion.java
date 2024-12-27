/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;
import objetoServicio.Fecha;

/**
 * Clase que representa una Reproducción de una Canción específica,
 * Extendiende la clase Reproduccion para incluir el Contenido de la Canción reproducida.
 * 
 * @author Escalante, Sebastian; Cortez, Manuel
 */
public class ReproduccionCancion extends Reproduccion {
    private Cancion cancion;

    /**
     * Constructor por defecto que inicializa una reproducción de canción con valores por defecto.
     */
    public ReproduccionCancion(){
        super();
    }

    /**
     * Constructor que inicializa una Reproducción de Canción con la clave, fecha, estado de completitud, y la canción especificada.
     * @param clave String
     * @param fecha Fecha
     * @param completa Boolean
     * @param cancion Cancion
     */
    public ReproduccionCancion(String clave, Fecha fecha, boolean completa, Cancion cancion){
        super(clave, fecha, completa);
        this.cancion = cancion;
    }

    /**
     * Constructor que inicializa una Reproducción de Canción solo con la clave.
     * La fecha se inicializa en null, el estado de completitud en false, y la Canción en null.
     * @param clave String
     */
    public ReproduccionCancion(String clave){
        super(clave, null, false);
        this.cancion = null;
    }

    /**
     * Establece la canción que se reproduce.
     * @param cancion Cancion
     */
    public void setCancion(Cancion cancion){
        this.cancion = cancion;
    }

    /**
     * Obtiene la canción que se reproduce.
     * @return cancion Cancion
     */
    public Cancion getCancion(){
        return this.cancion;
    }

    /**
     * Devuelve una representación en cadena de la Reproducción de Canción, que incluye los detalles de la reproducción y de la canción.
     * @return Atributos de la Reproducción de Canción
     */
    @Override
    public String toString(){
        return super.toString() + ", " + this.getCancion().toString();
    }
}

