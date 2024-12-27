package objetosPersistencia;
import exceptions.*;
import exceptions.Genericas.*;
import exceptions.HistorialReproduccionesCanciones.*;
import objetoServicio.Fecha;
import objetoServicio.Periodo;
import objetosNegocio.Cancion;
import objetosNegocio.Contenido;
import objetosNegocio.ReproduccionCancion;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas unitarias (JUnit 5) para la clase HistorialReproduccionesCanciones que valida todos los caminos
 * alternativos para cada uno de los metodos, asegurandose de que funcionen de manera correcta.
 * Tambien se valida que los metodos lancen las Excepciones personalizadas adecuadas para cada caso.
 *
 * @author Escalante, Sebastian
 * @author Cortez, Manuel
 */

public class HistorialReproduccionesCancionesTest {
    private HistorialReproduccionesCanciones historial = new HistorialReproduccionesCanciones();
    private Fecha fecha = new Fecha();

    @Test
    public void testAgregarReproduccionCancionFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException, HistorialReproduccionesCancionesVacioException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA111", fecha, true, cancion);
        assertEquals(cancion, historial.consultarReproduccionesCanciones().get(0).getCancion());
    }

    @Test
    public void testAgregarReproduccionCancionExistenteLanzaExcepcion() throws CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException, DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("ABC123", fecha, true, cancion);
        Exception ex = assertThrows(ReproduccionCancionExistenteException.class, () -> historial.agregarReproduccionCancion("ABC123", fecha, true, cancion));
        assertEquals("Esta Reproduccion Ya Ha Sido Registrada En El Historial De Reproducciones Previamente", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionClaveNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(ClaveNullException.class, () -> historial.agregarReproduccionCancion(null, fecha, true, cancion));
        assertEquals("La Clave No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionCancionNullLanzaExcepcion() {
        Exception ex = assertThrows(CancionNullException.class, () -> historial.agregarReproduccionCancion("AAA111", fecha, true, null));
        assertEquals("La Cancion No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionFechaNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FechaNullException.class, () -> historial.agregarReproduccionCancion("AAA111", null, true, cancion));
        assertEquals("La Fecha No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionAtributoCompletaNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(AtributoCompletaNullException.class, () -> historial.agregarReproduccionCancion("AAA111", fecha, null, cancion));
        assertEquals("El Valor Del Atributo Completa No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionFormatoClaveInvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FormatoDeClaveInvalidoException.class, () -> historial.agregarReproduccionCancion("AAA11", fecha, true, cancion));
        assertEquals("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionFechaMasUnMesAntiguedadLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FechaInvalidaException.class, () -> historial.agregarReproduccionCancion("AAA111", fecha.vencimiento(0, -2), true, cancion));
        assertEquals("La Fecha No Puede Tener Mas De Un Mes De Antiguedad", ex.getMessage());
    }

    @Test
    public void testAgregarReproduccionCancionFechaDespuesDeLaActualLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FechaInvalidaException.class, () -> historial.agregarReproduccionCancion("AAA111", fecha.vencimiento(1), true, cancion));
        assertEquals("La Fecha No Puede Ser Despues De La Fecha Actual", ex.getMessage());
    }

    @Test
    public void testActualizarReproduccionCancionFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException, ActualizarReproduccionCancionCompletaException, ReproduccionCancionNoEcontradaException, HistorialReproduccionesCancionesVacioException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA111", fecha, false, cancion);
        historial.actualizarReproduccionCancion("AAA111", fecha, true, cancion);
        assertEquals(true, historial.consultarReproduccionesCanciones().get(0).getCompleta());
    }

    @Test
    public void testActualizarReproduccionCancionInexistenteLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(ReproduccionCancionNoEcontradaException.class, () -> historial.actualizarReproduccionCancion("BBB111", fecha, true, cancion));
        assertEquals("La Reproduccion Que Intenta Actualizar No Ha Sido Registrada Previamente", ex.getMessage());
    }

    @Test
    public void testActualizarReproduccionCancionCompletaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, AtributoCompletaNullException, ActualizarReproduccionCancionCompletaException, ReproduccionCancionNoEcontradaException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA111", fecha, true, cancion);
        Exception ex = assertThrows(ActualizarReproduccionCancionCompletaException.class, () -> historial.actualizarReproduccionCancion("AAA111", fecha, true, cancion));
        assertEquals("Unicamente Pueden Actualizarse Reproducciones Incompletas", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionClaveNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(ClaveNullException.class, () -> historial.validarReproduccionCancion(null, fecha, true, cancion));
        assertEquals("La Clave No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionCancionNullLanzaExcepcion() {
        Exception ex = assertThrows(CancionNullException.class, () -> historial.validarReproduccionCancion("AAA111", fecha, true, null));
        assertEquals("La Cancion No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionFechaNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FechaNullException.class, () -> historial.validarReproduccionCancion("AAA111", null, true, cancion));
        assertEquals("La Fecha No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionAtributoCompletaNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(AtributoCompletaNullException.class, () -> historial.validarReproduccionCancion("AAA111", fecha, null, cancion));
        assertEquals("El Valor Del Atributo Completa No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionFormatoClaveInvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FormatoDeClaveInvalidoException.class, () -> historial.validarReproduccionCancion("AAA11", fecha, true, cancion));
        assertEquals("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos", ex.getMessage());
    }

    @Test
    public void testLimiteDiarioReproduccionesCompletasLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(LimiteDiarioDeReproduccionConacionCompletaException.class, () -> {
            for (int i = 1; i < 7; i++) {
                historial.agregarReproduccionCancion("AAA11" + i, fecha, true, cancion);
            }
        });
        assertEquals("No Se Pueden Realizar Mas De 5 Reproducciones Completas De Una Misma Cancion Por Dia", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionFechaMasUnMesAntiguedadLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FechaInvalidaException.class, () -> historial.validarReproduccionCancion("AAA111", fecha.vencimiento(0, -2), true, cancion));
        assertEquals("La Fecha No Puede Tener Mas De Un Mes De Antiguedad", ex.getMessage());
    }

    @Test
    public void testValidarReproduccionCancionFechaDespuesDeLaActualLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        Exception ex = assertThrows(FechaInvalidaException.class, () -> historial.validarReproduccionCancion("AAA111", fecha.vencimiento(1), true, cancion));
        assertEquals("La Fecha No Puede Ser Despues De La Fecha Actual", ex.getMessage());
    }

    @Test
    public void testContadorReproduccionesCompletasFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA111", fecha, true, cancion);
        historial.agregarReproduccionCancion("AAA222", fecha, false, cancion);
        historial.agregarReproduccionCancion("AAA333", fecha, true, cancion);
        assertEquals(2, historial.contadorReproduccionesCompletas(fecha, cancion));
    }

    @Test
    public void testConsultarReproduccionesCancionesVacioLanzaExcepcion() {
        Exception ex = assertThrows(HistorialReproduccionesCancionesVacioException.class, () -> historial.consultarReproduccionesCanciones());
        assertEquals("El Historial De Reproducciones No Tiene Ningun Registro Todavia", ex.getMessage());
    }

    @Test
    public void testConsultarReproduccionCancionPorClaveFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA111", fecha, true, cancion);
        ReproduccionCancion reproduccionCancionRetorno = historial.consultarReproduccionCancionPorClave("AAA111");
        assertEquals(cancion.getClave(), reproduccionCancionRetorno.getCancion().getClave());
    }

    @Test
    public void testConsultarReproduccionesCancionesPorClaveCancionFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, HistorialReproduccionesCancionesVacioException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA222", fecha, true, cancion);
        historial.agregarReproduccionCancion("AAA333", fecha, false, cancion);
        ArrayList<ReproduccionCancion> reproduccionesCanciones = historial.consultarReproduccionesCancionesPorClaveCancion("AAA111");
        assertEquals(reproduccionesCanciones.size(),2);
        assertEquals(cancion.getClave(), reproduccionesCanciones.get(0).getCancion().getClave());
        assertEquals(cancion.getClave(), reproduccionesCanciones.get(1).getCancion().getClave());
    }

    @Test
    public void testConsultarReproduccionesCancionesPorAutorFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, HistorialReproduccionesCancionesVacioException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion = new Cancion(contenido, "Guns N Roses");
        historial.agregarReproduccionCancion("AAA222", fecha, true, cancion);
        historial.agregarReproduccionCancion("AAA333", fecha, false, cancion);
        ArrayList<ReproduccionCancion> reproduccionesCanciones = historial.consultarReproduccionesCancionesPorClaveCancion("AAA111");
        assertEquals(reproduccionesCanciones.size(),2);
        assertEquals(cancion.getAutor(), reproduccionesCanciones.get(0).getCancion().getAutor());
        assertEquals(cancion.getAutor(), reproduccionesCanciones.get(1).getCancion().getAutor());
    }

    @Test
    public void testConsultarReproduccionesCancionesPorPeriodoFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, HistorialReproduccionesCancionesVacioException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Fecha fechaMesAnterior = new Fecha().vencimiento(0,-1);
        Fecha fechaSemanaAnterior = new Fecha().vencimiento(-7);
        Periodo periodo = new Periodo(fechaSemanaAnterior, fecha);
        Contenido contenido1 = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion1 = new Cancion(contenido1, "Guns N Roses");
        Contenido contenido2 = new Contenido("BBB111", "Highway to Hell", 1979, 3.45f);
        Cancion cancion2 = new Cancion(contenido2, "AC DC");
        Contenido contenido3 = new Contenido("CCC111", "Seek and Destroy", 1982, 6.9f);
        Cancion cancion3 = new Cancion(contenido3, "Metallica");
        historial.agregarReproduccionCancion("AAA222", fechaSemanaAnterior, true, cancion1);
        historial.agregarReproduccionCancion("BBB333", fecha, false, cancion2);
        historial.agregarReproduccionCancion("CCC444",fechaMesAnterior,true,cancion3);
        ArrayList<ReproduccionCancion> reproduccionesCanciones = historial.consultarReproduccionesCancionesPorPeriodo(periodo);
        assertEquals(reproduccionesCanciones.size(),2);
        assertEquals(cancion2.getClave(), reproduccionesCanciones.get(0).getCancion().getClave());
        assertEquals(cancion1.getClave(), reproduccionesCanciones.get(1).getCancion().getClave());
    }

    @Test
    public void testConsultarReproduccionesCancionesFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, HistorialReproduccionesCancionesVacioException, CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        Contenido contenido1 = new Contenido("AAA111", "Welcome to the Jungle", 1987, 4.55f);
        Cancion cancion1 = new Cancion(contenido1, "Guns N Roses");
        Contenido contenido2 = new Contenido("BBB111", "Highway to Hell", 1979, 3.45f);
        Cancion cancion2 = new Cancion(contenido2, "AC DC");
        Contenido contenido3 = new Contenido("CCC111", "Seek and Destroy", 1982, 6.9f);
        Cancion cancion3 = new Cancion(contenido3, "Metallica");
        historial.agregarReproduccionCancion("AAA111", fecha, true, cancion1);
        historial.agregarReproduccionCancion("BBB222", fecha, false, cancion2);
        historial.agregarReproduccionCancion("CCC333",fecha,true,cancion3);
        ArrayList<ReproduccionCancion> reproduccionesCanciones = historial.consultarReproduccionesCanciones();
        assertEquals(reproduccionesCanciones.size(),3);
        assertEquals(cancion2.getClave(), reproduccionesCanciones.get(0).getCancion().getClave());
        assertEquals(cancion3.getClave(), reproduccionesCanciones.get(1).getCancion().getClave());
        assertEquals(cancion1.getClave(), reproduccionesCanciones.get(2).getCancion().getClave());
    }
}
