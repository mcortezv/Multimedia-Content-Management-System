/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

/**
 * La clase que representa un Objeto tipo Canción, que es un tipo de Contenido.
 * Incluye información sobre el autor de la Canción.
 * 
 * @author Escalante, Sebastian; Cortez, Manuel
 */
public class Cancion extends Contenido {
    private String autor;

    /**
     * Constructor por defecto que inicializa una Canción con valores por defecto.
     */
    public Cancion(){
        super();
    }

    /**
     * Constructor que inicializa una Canción con un Contenido y un autor especifico.
     * @param contenido Contenido
     * @param autor Autor
     */
    public Cancion(Contenido contenido, String autor){
        super(contenido);
        this.autor = autor;
    }

    /**
     * Constructor que inicializa una Canción con un Contenido, estableciendo el autor como una cadena vacía.
     * @param contenido Contenido
     */
    public Cancion(Contenido contenido){
        super(contenido);
        this.autor = "";
    }

    /**
     * Establece el nombre del autor de la canción.
     * @param autor Nombre
     */
    public void setAutor(String autor){
        this.autor = autor;
    }

    /**
     * Obtiene el nombre del autor de la canción.
     * @return nombre
     */
    public String getAutor(){
        return this.autor;
    }

    /**
     * Devuelve una cadena de la Canción, que incluye los detalles del Contenido y el autor.
     * @return Atributos de la canción
     */
    @Override
    public String toString(){
        return super.toString() + ", " + String.format("%s", this.getAutor());
    }
}

