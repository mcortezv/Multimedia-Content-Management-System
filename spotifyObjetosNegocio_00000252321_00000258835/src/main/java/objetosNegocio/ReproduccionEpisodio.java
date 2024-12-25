/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;
import objetoServicio.Fecha;

/**
 * Clase que representa la Reproducción de un Episodio específico,
 * Extiende la clase Reproduccion para incluir el Contenido del Episodio reproducido.
 * 
 * @author Escalante, Sebastian; Cortez, Manuel
 */
public class ReproduccionEpisodio extends Reproduccion {
    private Episodio episodio;
    public ReproduccionEpisodio(){
        super();
    }

    /**
     * Constructor que inicializa una Reproducción de Episodio con la clave, fecha, estado de completitud, y el episodio especificado.
     * @param clave String
     * @param fecha Fecha
     * @param completa Boolean
     * @param episodio Episodio
     */
    public ReproduccionEpisodio(String clave, Fecha fecha, boolean completa, Episodio episodio){
        super(clave, fecha, completa);
        this.episodio = episodio;
    }

    /**
     * Constructor que inicializa una Reproducción de Episodio solo con la clave.
     * La fecha se inicializa en null, el estado de completitud en false, y el episodio en null.
     * @param clave String
     */
    public ReproduccionEpisodio(String clave){
        super(clave, null, false);
        this.episodio = null;
    }

    /**
     * Establece el Episodio que se reproduce.
     * @param episodio Episodio
     */
    public void setEpisodio(Episodio episodio){
        this.episodio = episodio;
    }

    /**
     * Obtiene el Episodio que se reproduce.
     * @return episodio Episodio
     */
    public Episodio getEpisodio(){
        return this.episodio;
    }

    /**
     * Devuelve una representación en cadena de la Reproducción de Episodio, que incluye los detalles de la Reproducción y del Episodio.
     * @return Atributos de la Reproducción de Episodio
     */
    @Override
    public String toString(){
        return super.toString() + ", " + this.getEpisodio().toString();
    }
}


