/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetoServicio;
import java.util.GregorianCalendar;

/**
 * Clase que permite construir y manipular un objeto tipo Fecha.
 * Posee los atributos principales (HOUR, MINUTE, SECOND, MILLISECOND, DAY_OF_MONTH, MONTH, YEAR) además de los heredados por la clase GregorianCalendar.
 *
 * @author Escalante, Sebastián; Cortez, Manuel
 **/
public class Fecha extends GregorianCalendar {

    /**
     * Constructor por defecto de la clase Fecha que establece el valor de los atributos (HOUR, MINUTE, SECOND, MILLISECOND) en cero.
     **/
    public Fecha(){
        this.set(HOUR, 0);
        this.set(MINUTE, 0);
        this.set(SECOND, 0);
        this.set(MILLISECOND, 0);
    }

    /**
     * Constructor que recibe el Día, Mes y Año como parámetros enteros para construir una nuevo objeto tipo Fecha.
     * Establece el valor de los atributos (HOUR, MINUTE, SECOND, MILLISECOND) en cero.
     * @param dia int
     * @param mes int
     * @param anio int
     * */
    public Fecha(int dia, int mes, int anio){
        this.set(HOUR, 0);
        this.set(MINUTE, 0);
        this.set(SECOND, 0);
        this.set(MILLISECOND, 0);
        this.set(DAY_OF_MONTH, dia);
        this.set(MONTH, mes - 1);
        this.set(YEAR, anio);
    }

    /**
     * Constructor que genera un nuevo objeto tipo Fecha a partir de otro que recibe como parámetro, permite crear una copia de un objeto tipo Fecha ya existente con un nombre distinto.
     * @param fecha Fecha
     * */
    public Fecha(Fecha fecha){
        this.set(HOUR, fecha.get(HOUR));
        this.set(MINUTE, fecha.get(MINUTE));
        this.set(SECOND, fecha.get(SECOND));
        this.set(MILLISECOND, fecha.get(MILLISECOND));
        this.set(DAY_OF_MONTH, fecha.get(DAY_OF_MONTH));
        this.set(MONTH, fecha.get(MONTH));
        this.set(YEAR, fecha.get(YEAR));
    }

    /**
     * Constructor que genera un nuevo objeto tipo Fecha, recibiendo como parámetro un String en formato "dd/mm/aaaa".
     * @param fecha String
     * */
    public Fecha(String fecha){
        this.set(HOUR, 0);
        this.set(MINUTE, 0);
        this.set(SECOND, 0);
        this.set(MILLISECOND, 0);
        String[] array = fecha.split("/");
        int dia = Integer.parseInt(array[0]);
        int mes = Integer.parseInt(array[1]);
        int anio = Integer.parseInt(array[2]);
        this.set(DAY_OF_MONTH, dia);
        this.set(MONTH, mes - 1);
        this.set(YEAR, anio);
    }

    /**
     * Método que establece el Día a un objeto tipo Fecha desde el parámetro entero que recibe.
     * @param dia int
     * */
    public void setDia(int dia){
        this.set(DAY_OF_MONTH, dia);
    }

    /**
     * Método que establece el Mes a un objeto tipo Fecha desde el parámetro entero que recibe.
     * @param mes int
     * */
    public void setMes(int mes){
        this.set(MONTH, mes - 1);
    }

    /**
     * Método que establece el Año a un objeto tipo Fecha desde el parámetro entero que recibe.
     * @param anio int
     * */
    public void setAnio(int anio){
        this.set(YEAR, anio);
    }

    /**
     * Método que establece el Día, Mes y Año a un objeto tipo Fecha desde los parámetros tipo entero que recibe.
     * @param mes int
     * */
    public void setFecha(int dia, int mes, int anio){
        this.set(DAY_OF_MONTH, dia);
        this.set(MONTH, mes - 1);
        this.set(YEAR, anio);
    }

    /**
     * Método que devuelve el valor del Día del objeto tipo Fecha que lo llama.
     * @return dia int
     * */
    public int getDia(){
        return this.get(DAY_OF_MONTH);
    }

    /**
     * Método que devuelve el valor del Mes del objeto tipo Fecha que lo llama.
     * @return mes int
     * */
    public int getMes(){
        return this.get(MONTH) + 1;
    }

    /**
     * Método que devuelve el valor del Año del objeto tipo Fecha que lo llama.
     * @return año
     * */
    public int getAnio(){
        return this.get(YEAR);
    }

    /**
     * Método que crea un nuevo objeto tipo Fecha, sumando los Dias, Meses y Años recibidos como parámetros a la Fecha que llama al método.
     * @param dias int
     * @param meses int
     * @param anios int
     * */
    public Fecha vencimiento(int dias, int meses, int anios) {
        Fecha nuevaFecha = new Fecha(this);
        nuevaFecha.add(DAY_OF_MONTH, dias);
        nuevaFecha.add(MONTH, meses);
        nuevaFecha.add(YEAR, anios);
        return nuevaFecha;
    }

    /**
     * Método que crea un nuevo objeto tipo Fecha, sumando los Días y Meses recibidos como parámetros a la Fecha que llama al método.
     * @param dias int
     * @param meses int
     * */
    public Fecha vencimiento(int dias, int meses) {
        Fecha nuevaFecha = new Fecha(this);
        nuevaFecha.add(DAY_OF_MONTH, dias);
        nuevaFecha.add(MONTH, meses);
        return nuevaFecha;
    }

    /**
     * Método que crea un nuevo objeto tipo Fecha, sumando los Días recibidos como parámetro a la Fecha que llama al método.
     * @param dias int
     * */
    public Fecha vencimiento(int dias) {
        Fecha nuevaFecha = new Fecha(this);
        nuevaFecha.add(DAY_OF_MONTH, dias);
        return nuevaFecha;
    }

    /**
     * Método que devuelve la cantidad de Días entre la Fecha que recibe como parámetro y la Fecha del objeto que lo llama.
     * @param desde Fecha
     * @return dias int
     * */
    public int lapso(Fecha desde){
        long fechaClaseMilisegundos = this.getTimeInMillis();
        long desdeMilisegundos = desde.getTimeInMillis();
        long dias = Math.abs((desdeMilisegundos - fechaClaseMilisegundos) / 86400000);
        return (int) dias;
    }

    /**
     * Método que devuelve una Fecha en objeto tipo String con el Formato "dd/mm/aaaa".
     * @return texto String
     * */
    @Override
    public String toString(){
        String texto = String.format("%02d/%02d/%d", this.getDia(), this.getMes(), this.getAnio());
        return texto;
    }
}