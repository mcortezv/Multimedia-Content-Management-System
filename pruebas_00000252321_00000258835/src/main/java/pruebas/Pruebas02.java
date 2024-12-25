package pruebas;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import exceptions.AnioCreacionInvalidoException;
import exceptions.DuracionExcedeMaximoPermitidoException;
import objetoServicio.Fecha;
import objetosNegocio.Cancion;
import objetosNegocio.Contenido;
import objetosNegocio.Episodio;
import objetosNegocio.ReproduccionCancion;
import objetosNegocio.ReproduccionEpisodio;

/**
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class Pruebas02 {
    public static void main(String[] args) {
        if(!"UTF-8".equals(System.out.charset().displayName())){
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));
        }

        /* ---------------- PRUEBAS 02 ---------------- */

        // a). Crear seis objetos de tipo Contenido
        try{
            Contenido contenido1 = new Contenido("CON001", "Afuera", 1995, 4.21f);
            Contenido contenido2 = new Contenido("CON002", "Cachito", 1994, 3.20f);
            Contenido contenido3 = new Contenido("CON003", "Oye mi amor", 1993, 3.45f);
            Contenido contenido4 = new Contenido("CON004", "La escuela de ISW", 1999, 4.57f);
            Contenido contenido5 = new Contenido("CON005", "Crónicas de un programador", 2008, 3.35f);
            Contenido contenido6 = new Contenido("CON006", "La historia de hachiko", 2012, 4.10f);

            // b). Desplegar los datos de los seis Contenidos
            System.out.println(contenido1);
            System.out.println(contenido2);
            System.out.println(contenido3);
            System.out.println(contenido4);
            System.out.println(contenido5);
            System.out.println(contenido6);

            // c). Desplegar el nombre del Contenido contenido1
            System.out.println(contenido1.getNombre());

            // d). Corregir el nombre del Contenido contenido5 a "Crónicas de código"
            contenido5.setNombre("Crónicas de código");

            // e). Volver a desplegar los datos del Contenido contenido5
            System.out.println(contenido5);

            // f). Desplegar un mensaje indicando si contenido1 y contenido1 son iguales
            if (contenido1.equals(contenido1)) {
                System.out.printf("El Contenido %s es Igual al Contenido %s%n", contenido1.getClave(), contenido1.getClave());
            } else {
                System.out.printf("El Contenido %s es Diferente al Contenido %s%n", contenido1.getClave(), contenido1.getClave());
            }

            // g). Desplegar un mensaje indicando si contenido4 y contenido6 son diferentes
            if (contenido4.equals(contenido6)) {
                System.out.printf("El Contenido %s es Igual al Contenido %s%n", contenido4.getClave(), contenido6.getClave());
            } else {
                System.out.printf("El Contenido %s es Diferente al Contenido %s%n", contenido4.getClave(), contenido6.getClave());
            }

            // h). Crear 2 objetos del tipo Cancion: cancion1 y cancion2
            Cancion cancion1 = new Cancion(contenido1, "Caifanes");
            Cancion cancion2 = new Cancion(contenido2, "Maná");

            // i). Desplegar los datos de las dos canciones
            System.out.println(cancion1);
            System.out.println(cancion2);

            // j). Desplegar el autor de la cancion2
            System.out.println(cancion2.getAutor());

            // k). Desplegar un mensaje indicando si cancion1 y cancion2 son diferentes
            if (cancion1.equals(cancion2)) {
                System.out.printf("La Cancion %s es Igual a la Cancion %s%n", cancion1.getClave(), cancion2.getClave());
            } else {
                System.out.printf("La Cancion %s es Diferente a la Cancion %s%n", cancion1.getClave(), cancion2.getClave());
            }

            // l). Crear 2 objetos del tipo Episodio: episodio1 y episodio2
            Episodio episodio1 = new Episodio(contenido4, 10);
            Episodio episodio2 = new Episodio(contenido5, 5);

            // m). Desplegar los datos de los dos episodios
            System.out.println(episodio1);
            System.out.println(episodio2);

            // n). Desplegar el número del episodio1
            System.out.println(episodio1.getNumero());

            // o). Desplegar un mensaje indicando si episodio1 y episodio2 son iguales
            if (episodio1.equals(episodio2)) {
                System.out.printf("El Episodio %s es Igual al Episodio %s%n", episodio1.getClave(), episodio2.getClave());
            } else {
                System.out.printf("El Episodio %s es Diferente al Episodio %s%n", episodio1.getClave(), episodio2.getClave());
            }

            // p). Crear dos objetos del tipo ReproduccionCancion: reproduccionCancion1 y reproduccionCancion2
            Fecha hoy = new Fecha();
            ReproduccionCancion reproduccionCancion1 = new ReproduccionCancion("CRE001", hoy, false, cancion1);
            ReproduccionCancion reproduccionCancion2 = new ReproduccionCancion("CRE002", hoy.vencimiento(-1), false, cancion2);

            // q). Desplegar los datos de las dos reproducciones de canciones
            System.out.println(reproduccionCancion1);
            System.out.println(reproduccionCancion2);

            // r). Desplegar el autor de la reproduccionCancion1
            System.out.println(reproduccionCancion1.getCancion().getAutor());

            // s). Desplegar un mensaje indicando si reproduccionCancion1 y reproduccionCancion2 son diferentes
            if (reproduccionCancion1.equals(reproduccionCancion2)) {
                System.out.printf("La Reproduccion Cancion %s es Igual a la Reproduccion Cancion %s%n", reproduccionCancion1.getClave(), reproduccionCancion2.getClave());
            } else {
                System.out.printf("La Reproduccion Cancion %s es Diferente a la Reproduccion Cancion %s%n", reproduccionCancion1.getClave(), reproduccionCancion2.getClave());
            }

            // t). Crear dos objetos del tipo ReproduccionEpisodio: reproduccionEpisodio1 y reproduccionEpisodio2
            ReproduccionEpisodio reproduccionEpisodio1 = new ReproduccionEpisodio("CRE003", hoy.vencimiento(1), false, episodio1);
            ReproduccionEpisodio reproduccionEpisodio2 = new ReproduccionEpisodio("CRE004", hoy, false, episodio2);

            // u). Desplegar los datos de las dos reproducciones de episodios
            System.out.println(reproduccionEpisodio1);
            System.out.println(reproduccionEpisodio2);

            // v). Desplegar la fecha de la reproduccionEpisodio2
            System.out.println(reproduccionEpisodio2.getFecha());

            // w). Desplegar un mensaje indicando si reproduccionEpisodio1 y reproduccionEpisodio2 son iguales
            if (reproduccionEpisodio1.equals(reproduccionEpisodio2)) {
                System.out.printf("La Reproduccion Episodio %s es Igual a la Reproduccion Episodio %s%n", reproduccionEpisodio1.getClave(), reproduccionEpisodio2.getClave());
            } else {
                System.out.printf("La Reproduccion Episodio %s es Diferente a la Reproduccion Episodio %s%n", reproduccionEpisodio1.getClave(), reproduccionEpisodio2.getClave());
            }

        } catch (AnioCreacionInvalidoException ex){
            System.out.println(ex.getMessage());

        } catch (DuracionExcedeMaximoPermitidoException ex){
            System.out.println(ex.getMessage());
        }


        /* ---------------- PRUEBAS 01 ---------------- */


        /* Inciso a) Crea dos objetos de tipo Fecha llamados: hoy y ayer e inicializarlos a las fechas del sistema y a 15/08/2030, respectivamente.
        Fecha hoy = new Fecha();
        Fecha ayer = new Fecha("15/08/2030");

        // Inciso b) Crea un objeto de tipo Fecha: manhana copiándolo del objeto hoy.
        Fecha maniana = new Fecha(hoy);

        // Inciso c) Cambia el año de manhana agregándole un año.
        maniana.setAnio(2025);
        System.out.println(maniana);
        Fecha maniana2 = new Fecha().vencimiento(0, 0, 1);
        System.out.println(maniana2);

        // Inciso d) Despliega las tres fechas.
        System.out.printf("%nAyer: %s / Hoy: %s / Maniana: %s%n", ayer, hoy, maniana);

        // Inciso e) Despliega el mes de hoy.
        System.out.printf("%nMes de la Fecha Hoy: %d %n", hoy.getMes() + 1);

        // Inciso f) Cambia la fecha ayer a 12/06/2018
        ayer.setFecha(12, 06, 2018);

        // Inciso g) Despliega la fecha ayer.
        System.out.printf("%nAyer: %s %n", ayer);

        // Inciso h) Despliega un mensaje con el número de días entre hoy y manhana. Ambas fechas deben aparecer en el mensaje.
        System.out.printf("%nHay: %d dias entre Hoy: %s y Maniana: %s%n", hoy.lapso(maniana), hoy, maniana);

        // Inciso i) Despliega un mensaje indicando si hoy es diferente de hoy. Ambas fechas deben aparecer en el mensaje.
        if (hoy.equals(hoy)){
            System.out.printf("%nHoy: %s es Igual a Hoy: %s %n", hoy, hoy);
        } else {
            System.out.printf("%nHoy: %s es Diferente de Hoy: %s %n", hoy, hoy);
        }

        // Inciso j) Despliega un mensaje indicando si ayer está después o es igual a manhana. Ambas fechas deben aparecer en el mensaje.
        if (ayer.getTimeInMillis() >= maniana.getTimeInMillis()){
            System.out.printf("%nAyer: %s Esta Despues o es Igual a Maniana: %s %n", ayer, maniana);
        } else {
            System.out.printf("%nAyer: %s No Esta Despues ni es Igual a Maniana: %s %n", ayer, maniana);
        }

        // Inciso k) Despliega un mensaje indicando si hoy es antes que manhana. Ambas fechas deben aparecer en el mensaje.
        if (hoy.getTimeInMillis() < maniana.getTimeInMillis()){
            System.out.printf("%nHoy: %s Es Antes que Maniana: %s %n", hoy, maniana);
        } else {
            System.out.printf("%nHoy: %s Es Despues que Maniana: %s %n", hoy, maniana);
        }

        // Inciso l) Crea un objeto de tipo Periodo llamado periodo que va desde 05/02/2015 a hoy.
        Periodo periodo = new Periodo(new Fecha("05/02/2015"), hoy);

        // Inciso m) Despliega periodo.
        System.out.printf("%nPeriodo: %s %n", periodo);

        // Inciso n) Despliega un mensaje indicando si ayer está dentro de periodo. Tanto la fecha como el periodo deben aparecer en el mensaje.
        if (periodo.contiene(ayer)){
            System.out.printf("%nAyer: %s Esta dentro del Periodo: %s %n", ayer, periodo);
        } else {
            System.out.printf("%nAyer: %s No Esta dentro del Periodo: %s %n", ayer, periodo);
        }*/
    }
}



