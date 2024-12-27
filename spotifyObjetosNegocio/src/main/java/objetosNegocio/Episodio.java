/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

/**
 * Clase que representa un Episodio, un tipo de Contenido.
 * Incluye información sobre el número de Episodio.
 * 
 * @author Escalante, Sebastian; Cortez, Manuel
 */
public class Episodio extends Contenido {
    private int numero;

    /**
     * Constructor por defecto que inicializa un episodio con valores por defecto.
     */
    public Episodio(){
        super();
    }

    /**
     * Constructor que inicializa un Episodio con un Contenido y un número de episodio especificado.
     * @param contenido Contenido
     * @param numero int
     */
    public Episodio(Contenido contenido, int numero){
        super(contenido);
        this.numero = numero;
    }

    /**
     * Constructor que inicializa un Episodio con un Contenido, estableciendo el número de Episodio en 0.
     * @param contenido Objeto de tipo Contenido a partir del cual se crea el episodio.
     */
    public Episodio(Contenido contenido){
        super(contenido);
        this.numero = 0;
    }

    /**
     * Establece el número del episodio.
     * @param numero int
     */
    public void setNumero(int numero){
        this.numero = numero;
    }

    /**
     * Obtiene el número del episodio.
     * @return numero int
     */
    public int getNumero(){
        return this.numero;
    }

    /**
     * Devuelve una representación en cadena del Episodio, incluye los detalles del Contenido y el número.
     * @return Atributos del Episodio
     */
    @Override
    public String toString(){
        return super.toString() + ", " + String.format("%d", this.getNumero());
    }
}


