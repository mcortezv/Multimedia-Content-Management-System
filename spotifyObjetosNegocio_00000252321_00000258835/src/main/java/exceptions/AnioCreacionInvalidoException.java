package exceptions;

/**
 * Excepcion que se lanza cuando el Año de Creacion de un objeto tipo Contenido es previo a 1950.
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 **/
public class AnioCreacionInvalidoException extends Exception{
    public AnioCreacionInvalidoException(String texto){
        super(texto);
    }
}
