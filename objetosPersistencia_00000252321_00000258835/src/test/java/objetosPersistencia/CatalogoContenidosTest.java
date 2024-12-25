package objetosPersistencia;
import exceptions.*;
import exceptions.CatalogoContenidos.*;
import exceptions.Genericas.*;
import objetoServicio.Fecha;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import objetosNegocio.Contenido;
import java.util.ArrayList;

/**
 * Clase de pruebas unitarias (JUnit 5) para la clase CatalogoContenidos que valida todos los caminos
 * alternativos para cada uno de los metodos, asegurandose de que funcionen de manera correcta.
 * Tambien se valida que los metodos lancen las Excepciones personalizadas adecuadas para cada caso.
 *
 * @author Escalante, Sebastian
 * @author Cortez, Manuel
 */

public class CatalogoContenidosTest {
    private CatalogoContenidos catalogo = new CatalogoContenidos();
    private String claveValida = "AAA111";
    private String claveInvalida = "AAA11";

    @Test
    public void testAgregarContenidoFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, DuracionDeContenidoInvalidaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, ContenidoExistenteException, ContenidoNullException, FormatoDeClaveInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(claveValida, contenido);
        assertEquals(contenido, catalogo.getContenido(claveValida));
    }

    @Test
    public void testAgregarContenidoClaveNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        Exception ex = assertThrows(ClaveNullException.class, () -> catalogo.agregarContenido(null, contenido));
        assertEquals("La Clave No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testAgregarContenidoNullLanzaExcepcion() {
        Exception ex = assertThrows(ContenidoNullException.class, () -> catalogo.agregarContenido(claveValida, null));
        assertEquals("El Contenido No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testAgregarContenidoClaveFormatoInvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        Exception ex = assertThrows(FormatoDeClaveInvalidoException.class, () -> catalogo.agregarContenido(claveInvalida, contenido));
        assertEquals("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos", ex.getMessage());
    }

    @Test
    public void testAgregarContenidoAnioCreacionInvalidoPrevio1970LanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1969, 8.95f);
        Exception ex = assertThrows(AnioCreacionDeContenidoInvalidoException.class, () -> catalogo.agregarContenido(claveValida, contenido));
        assertEquals("El Anio De Creacion Del Contenido No Puede Ser Previo A 1970", ex.getMessage());
    }

    @Test
    public void testAgregarContenidoAnioCreacionInvalidoDespuesFechaActualLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 2030, 8.95f);
        Exception ex = assertThrows(AnioCreacionDeContenidoInvalidoException.class, () -> catalogo.agregarContenido(claveValida, contenido));
        assertEquals("El Anio De Creacion Del Contenido No Puede Ser Despues De La Fecha Actual", ex.getMessage());
    }

    @Test
    public void testAgregarContenidoDuracionInvalidaMenorUnMintuoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 2000, 0.4f);
        Exception ex = assertThrows(DuracionDeContenidoInvalidaException.class, () -> catalogo.agregarContenido(claveValida, contenido));
        assertEquals("La Duracion Del Contenido No Puede Ser Menor A Medio Minuto", ex.getMessage());
    }

    @Test
    public void testAgregarContenidoDuracionInvalidaMayor10HorasLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 2000, 601f);
        Exception ex = assertThrows(DuracionDeContenidoInvalidaException.class, () -> catalogo.agregarContenido(claveValida, contenido));
        assertEquals("La Duracion Del Contenido No Puede Ser Mayor A 10 Horas", ex.getMessage());
    }

    @Test
    public void testActualizarContenidoFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, DuracionDeContenidoInvalidaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, ContenidoExistenteException, ContenidoNullException, FormatoDeClaveInvalidoException, ContenidoNoEncontradoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(claveValida, contenido);
        Contenido nuevoContenido = new Contenido(claveValida, "Bohemian Rhapsody", 1975, 5.92f);
        catalogo.actualizarContenido(claveValida, nuevoContenido);
        assertEquals(nuevoContenido, catalogo.getContenido(claveValida));
    }

    @Test
    public void testActualizarContenidoNoExistenteLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        Exception ex = assertThrows(ContenidoNoEncontradoException.class, () -> catalogo.actualizarContenido(claveValida, contenido));
        assertEquals("El Contenido Que Intenta Actualizar No Ha Sido Registrado Previamente", ex.getMessage());
    }


    @Test
    public void testValidarContenidoClaveNullLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        Exception ex = assertThrows(ClaveNullException.class, () -> CatalogoContenidos.validarContenido(null, contenido));
        assertEquals("La Clave No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testValidarContenidoNullLanzaExcepcion() {
        Exception ex = assertThrows(ContenidoNullException.class, () -> CatalogoContenidos.validarContenido(claveValida, null));
        assertEquals("El Contenido No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    public void testValidarContenidoClaveFormatoInvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        Exception ex = assertThrows(FormatoDeClaveInvalidoException.class, () -> CatalogoContenidos.validarContenido(claveInvalida, contenido));
        assertEquals("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos", ex.getMessage());
    }

    @Test
    public void testValidarContenidoAnioCreacionPrevio1970InvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1969, 8.95f);
        Exception ex = assertThrows(AnioCreacionDeContenidoInvalidoException.class, () -> CatalogoContenidos.validarContenido(claveValida, contenido));
        assertEquals("El Anio De Creacion Del Contenido No Puede Ser Previo A 1970", ex.getMessage());
    }

    @Test
    public void testValidarContenidoAnioCreacionInvalidoDespuesFechaActualLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 2030, 8.95f);
        Exception ex = assertThrows(AnioCreacionDeContenidoInvalidoException.class, () -> CatalogoContenidos.validarContenido(claveValida, contenido));
        assertEquals("El Anio De Creacion Del Contenido No Puede Ser Despues De La Fecha Actual", ex.getMessage());
    }

    @Test
    public void testValidarContenidoDuracionInvalidaMenorUnMinutoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 2000, 0.4f);
        Exception ex = assertThrows(DuracionDeContenidoInvalidaException.class, () -> CatalogoContenidos.validarContenido(claveValida, contenido));
        assertEquals("La Duracion Del Contenido No Puede Ser Menor A Medio Minuto", ex.getMessage());
    }

    @Test
    public void testValidarContenidoDuracionMayor10HorasInvalidaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido(claveValida, "November Rain", 2000, 601f);
        Exception ex = assertThrows(DuracionDeContenidoInvalidaException.class, () -> CatalogoContenidos.validarContenido(claveValida, contenido));
        assertEquals("La Duracion Del Contenido No Puede Ser Mayor A 10 Horas", ex.getMessage());
    }

    @Test
    public void testEliminarContenidoFuncionaCorrectamente() throws Exception {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(claveValida, contenido);
        catalogo.eliminarContenido(claveValida);
        assertNull(catalogo.getContenido(claveValida));
    }

    @Test
    public void testEliminarContenidoNoExistenteLanzaExcepcion() {
        Exception ex = assertThrows(ContenidoNoEncontradoException.class, () -> catalogo.eliminarContenido(claveValida));
        assertEquals("El Contenido Que Desea Eliminar No Ha Sido Registrado Aun", ex.getMessage());
    }

    @Test
    public void testVerificarCatalogoContenidosVacioLanzaExcepcion() {
        Exception ex = assertThrows(CatalogoContenidosVacioException.class, () -> catalogo.verificarCatalogoContenidosVacio());
        assertEquals("El Catalogo De Contenidos No Tiene Ningun Registro Todavia", ex.getMessage());
    }

    @Test
    public void testConsultarContenidosPorClaveParecidaFuncionaCorrectamente() throws Exception {
        String clave = "AAA111";
        Contenido contenido = new Contenido(clave, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(clave, contenido);
        ArrayList<Contenido> contenidos = catalogo.consultarContenidosPorClaveParecida("A1");
        assertEquals("November Rain", contenidos.getFirst().getNombre());
    }

    @Test
    public void testConsultarContenidosPorClaveNoParecidaDevuelveListaVacia() throws Exception {
        String clave = "AAA111";
        Contenido contenido = new Contenido(clave, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(clave, contenido);
        ArrayList<Contenido> contenidos = catalogo.consultarContenidosPorClaveParecida("6");
        assertEquals(0, contenidos.size());
    }

    @Test
    public void testConsultarContenidosPorNombreFuncionaCorrectamente() throws Exception {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(claveValida, contenido);
        ArrayList<Contenido> contenidos = catalogo.consultarContenidosPorNombre("November Rain");
        assertEquals("November Rain", contenidos.getFirst().getNombre());
    }

    @Test
    public void testConsultarContenidosPorAnioFuncionaCorrectamente() throws Exception {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(claveValida, contenido);
        ArrayList<Contenido> contenidos = catalogo.consultarContenidosPorAnio(1991);
        assertEquals(1991, contenidos.get(0).getAnioCreacion());
    }

    @Test
    public void testConsultarContenidosFuncionaCorrectamente() throws Exception {
        Contenido contenido = new Contenido(claveValida, "November Rain", 1991, 8.95f);
        catalogo.agregarContenido(claveValida, contenido);
        ArrayList<Contenido> contenidos = catalogo.consultarContenidos();
        assertEquals(contenido, contenidos.get(0));
    }
}

