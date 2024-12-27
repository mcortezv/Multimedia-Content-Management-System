package exceptions;

/**
 * Excepcion que se lanza cuando la Duracion de un objeto tipo Contenido es superior a 1000 minutos.
 * 
 * @author Escalante, Sebastian; Cortez, Manuel
 **/
public class DuracionExcedeMaximoPermitidoException extends Exception {
    public DuracionExcedeMaximoPermitidoException(String texto){
        super(texto);
    }
}
