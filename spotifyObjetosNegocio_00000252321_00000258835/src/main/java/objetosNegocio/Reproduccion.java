/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;
import objetoServicio.Fecha;

/**
 * Clase que representa una Reproducción de un objeto,
 * almacenando información sobre una clave identificadora, la fecha de reproducción,
 * y si la reproducción está completa o no.
 * 
 * @author Escalante, Sebastian; Cortez, Manuel
 */
public class Reproduccion {
    protected String clave;
    protected Fecha fecha;
    protected boolean completa;

    /**
     * Constructor por defecto que inicializa una Reproducción sin valores específicos.
     */
    public Reproduccion(){}

    /**
     * Constructor que inicializa una Reproducción con los valores especificados.
     * @param clave String
     * @param fecha Fecha
     * @param completa Boolean
     */
    public Reproduccion(String clave, Fecha fecha, boolean completa){
        this.clave = clave;
        this.fecha = fecha;
        this.completa = completa;
    }

    /**
     * Constructor que inicializa una Reproducción solo con la clave.
     * La fecha se inicializa en null y el estado de completa en false.
     * @param clave String
     */
    public Reproduccion(String clave){
        this.clave = clave;
        this.fecha = null;
        this.completa = false;
    }

    /**
     * Establece la clave de la Reproducción.
     * @param clave String
     */
    public void setClave(String clave){
        this.clave = clave;
    }

    /**
     * Obtiene la clave de la Reproducción.
     * @return clave String
     */
    public String getClave(){
        return this.clave;
    }

    /**
     * Establece la fecha de la Reproducción.
     * @param fecha Fecha
     */
    public void setFecha(Fecha fecha){
        this.fecha = fecha;
    }

    /**
     * Obtiene la fecha de la Reproducción.
     * @return fecha Fecha
     */
    public Fecha getFecha(){
        return this.fecha;
    }

    /**
     * Establece el estado de completitud de la Reproducción.
     * @param completa Boolean
     */
    public void setCompleta(boolean completa){
        this.completa = completa;
    }

    /**
     * Obtiene el estado de completitud de la Reproducción.
     * @return completa Boolean
     */
    public boolean getCompleta(){
        return this.completa;
    }

    /**
     * Compara la Reproducción actual con otro objeto para determinar si son iguales con base en su clave.
     * @param otroObjeto Object
     * @return Boolean
     */
    @Override
    public boolean equals(Object otroObjeto){
        if (otroObjeto == null){
            return false;
        }
        if (this.getClass() != otroObjeto.getClass()){
            return false;
        }
        Reproduccion otraReproduccion = (Reproduccion) otroObjeto;
        if (this.getClave().equals(otraReproduccion.getClave())){
            return true;
        }
        return false;
    }

    /**
     * Devuelve una representación en cadena de la Reproducción que incluye la clave, fecha y el estado de completa.
     * @return Atributos de la Reproducción
     */
    @Override
    public String toString(){
        return String.format("%s, %s, %s", this.getClave(), this.getFecha(), this.getCompleta());
    }
}


