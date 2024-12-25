/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoServicio;

/**
 * Clase que representa un conjunto de dos objetos tipo Fecha como un intervalo de tiempo.
 * Esta clase permite construir y manipular los atributos de un objeto tipo Periodo.
 * 
 * @author Escalante, Sebastián; Cortez, Manuel
 * */
public class Periodo {
    private Fecha desde;
    private Fecha hasta;

    /**
     * Constructor que genera un nuevo objeto tipo Periodo a partir de dos objetos tipo Fecha que recibe como parámetros.
     * @param desde Fecha
     * @param hasta Fecha
     * */
    public Periodo(Fecha desde, Fecha hasta){
        this.desde = desde;
        this.hasta = hasta;
    }

    /**
     * Método que establece la Fecha inicial de un objeto tipo Periodo a partir del objeto tipo Fecha que recibe como parámetro.
     * @param fecha Fecha
     * */
    public void setDesde(Fecha fecha){
        this.desde = fecha;
    }

    /**
     * Método que establece la Fecha de termino de un objeto tipo Periodo a partir del objeto tipo Fecha que recibe como parámetro.
     * @param fecha Fecha
     * */
    public void setHasta(Fecha fecha){
        this.hasta = fecha;
    }

    /**
     * Método que devuelve el objeto tipo Fecha correspondiente a la Fecha de inicio de un objeto tipo Periodo.
     * @return fecha Fecha
     * */
    public Fecha getDesde(){
        return this.desde;
    }

    /**
     * Método que devuelve el objeto tipo Fecha correspondiente a la Fecha de término de un objeto tipo Periodo.
     * @return fecha Fecha
     * */
    public Fecha getHasta(){
        return this.hasta;
    }

    /**
     * Método de validación que determina si un objeto tipo Fecha esta dentro del intervalo de tiempo de un objeto tipo Periodo, recibe un objeto tipo Fecha como parámetro.
     * @param fecha Fecha
     * @return boolean
     * */
    public boolean contiene(Fecha fecha){
        long desdeMilisegundos = this.getDesde().getTimeInMillis();
        long hastaMilisegundos = this.getHasta().getTimeInMillis();
        long fechaMilisegundos = fecha.getTimeInMillis();
        if (fechaMilisegundos >= desdeMilisegundos && fechaMilisegundos <= hastaMilisegundos ){
            return true;
        }
        return false;
    }

    /**
     * Método que devuelve un objeto tipo Periodo en String con el Formato "dd/mm/aaaa a dd/mm/aaaa".
     * @return texto
     * */
    @Override
    public String toString(){
        String texto = String.format("%02d/%02d/%d a %02d/%02d/%d", this.desde.getDia(), this.desde.getMes(), this.desde.getAnio(), this.hasta.getDia(), this.hasta.getMes(), this.hasta.getAnio());
        return texto;
    }
}
