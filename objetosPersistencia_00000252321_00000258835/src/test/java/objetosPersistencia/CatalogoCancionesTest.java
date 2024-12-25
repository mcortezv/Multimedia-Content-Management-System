package objetosPersistencia;
import exceptions.*;
import exceptions.CatalogoCanciones.*;
import exceptions.CatalogoContenidos.*;
import exceptions.Genericas.*;
import objetosNegocio.Cancion;
import objetosNegocio.Contenido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

/**
 * Clase de pruebas unitarias (JUnit 5) para la clase CatalogoCanciones que valida todos los caminos
 * alternativos para cada uno de los metodos, asegurandose de que funcionen de manera correcta.
 * Tambien se valida que los metodos lancen las Excepciones personalizadas adecuadas para cada caso.
 *
 * @author Escalante, Sebastian
 * @author Cortez, Manuel
 */

class CatalogoCancionesTest {
    private CatalogoCanciones catalogo = new CatalogoCanciones();


    @Test
    void testAgregarCancionValidaFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        catalogo.agregarCancion("AAA111", cancion);
        assertEquals(cancion, catalogo.getCancion("AAA111"));
    }

    @Test
    void testAgregarCancionExistenteLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        catalogo.agregarCancion("AAA111", cancion);
        Exception ex = assertThrows(CancionExistenteException.class, () -> catalogo.agregarCancion("AAA111", cancion));
        assertEquals("Esta Cancion Ya Ha Sido Registrada En El Catalogo De Canciones Previamente", ex.getMessage());
    }

    @Test
    void testAgregarCancionClaveVaciaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        Exception ex = assertThrows(ClaveNullException.class, () -> catalogo.agregarCancion(null, cancion));
        assertEquals("La Clave No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    void testAgregarCancionCancionVaciaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        Exception ex = assertThrows(CancionNullException.class, () -> catalogo.agregarCancion("AAA111", null));
        assertEquals("La Cancion No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    void testAgregarCancionFormatoClaveInvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        Exception ex = assertThrows(FormatoDeClaveInvalidoException.class, () -> catalogo.agregarCancion("AB123", cancion));
        assertEquals("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos", ex.getMessage());
    }

    @Test
    void testAgregarCancionAutorInvalidoAutorMenor3LanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        cancion.setAutor("A");
        Exception ex = assertThrows(NombreAutorInvalidoException.class, () -> catalogo.agregarCancion("AAA111", cancion));
        assertEquals("El Nombre Del Autor No Puede Tener Menos De 3 Caracteres", ex.getMessage());
    }

    @Test
    void testAgregarCancionAutorInvalidoAutorMayor50LanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        cancion.setAutor("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Exception ex = assertThrows(NombreAutorInvalidoException.class, () -> catalogo.agregarCancion("AAA111", cancion));
        assertEquals("El Nombre Del Autor No Puede Tener Mas de 50 Caracteres", ex.getMessage());
    }

    @Test
    void testActualizarCancionValidaFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException, DuracionDeContenidoInvalidaException, CancionNoEncontradaException, AnioCreacionDeContenidoInvalidoException, ContenidoNullException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        catalogo.agregarCancion("AAA111", cancion);
        Contenido nuevoContenido = new Contenido("AAA111", "Nuevo Titulo", 2020, 200);
        Cancion nuevaCancion = new Cancion(nuevoContenido, "Nuevo Autor");
        catalogo.actualizarCancion("AAA111", nuevaCancion);
        assertEquals(nuevaCancion, catalogo.getCancion("AAA111"));
    }

    @Test
    void testActualizarCancionNoEncontradaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido nuevoContenido = new Contenido("AAA111", "Nuevo Titulo", 2020, 200);
        Cancion nuevaCancion = new Cancion(nuevoContenido, "Nuevo Autor");
        Exception ex = assertThrows(CancionNoEncontradaException.class, () -> catalogo.actualizarCancion("AAA111", nuevaCancion));
        assertEquals("La Cancion Que Intenta Actualizar No Ha Sido Registrada Previamente", ex.getMessage());
    }

    @Test
    void testValidarCancionClaveVaciaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        Exception ex = assertThrows(ClaveNullException.class, () -> catalogo.validarCancion(null, cancion));
        assertEquals("La Clave No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    void testValidarCancionCancionVaciaLanzaExcepcion() {
        Exception ex = assertThrows(CancionNullException.class, () -> catalogo.validarCancion("AAA111", null));
        assertEquals("La Cancion No Puede Ser Un Valor Nulo", ex.getMessage());
    }

    @Test
    void testValidarCancionFormatoClaveInvalidoLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        Exception ex = assertThrows(FormatoDeClaveInvalidoException.class, () -> catalogo.validarCancion("AB123", cancion));
        assertEquals("La Clave del Contenido Se Debe Componer De 3 Caracteres Y 3 Dígitos", ex.getMessage());
    }

    @Test
    void testValidarCancionNombreAutorInvalidoAutorMenor3LanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        cancion.setAutor("A");
        Exception ex = assertThrows(NombreAutorInvalidoException.class, () -> catalogo.validarCancion("AAA111", cancion));
        assertEquals("El Nombre Del Autor No Puede Tener Menos De 3 Caracteres", ex.getMessage());
    }

    @Test
    void testValidarCancionNombreAutorInvalidoAutorMayor50LanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        cancion.setAutor("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        Exception ex = assertThrows(NombreAutorInvalidoException.class, () -> catalogo.validarCancion("AAA111", cancion));
        assertEquals("El Nombre Del Autor No Puede Tener Mas de 50 Caracteres", ex.getMessage());
    }

    @Test
    void testEliminarCancionFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNoEncontradaException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        catalogo.agregarCancion("AAA111", cancion);
        catalogo.eliminarCancion("AAA111");
        assertNull(catalogo.getCancion("AAA111"));
    }

    @Test
    void testEliminarCancionNoEncontradaLanzaExcepcion() {
        Exception ex = assertThrows(CancionNoEncontradaException.class, () -> catalogo.eliminarCancion("AAA111"));
        assertEquals("La Cancion Que Desea Eliminar No Ha Sido Registrada Aun", ex.getMessage());
    }

    @Test
    void testConsultarCancionesVacioLanzaExcepcion() {
        Exception ex = assertThrows(CatalogoCancionesVacioException.class, () -> catalogo.consultarCanciones());
        assertEquals("El Catalogo De Canciones No Tiene Ningun Registro Todavia", ex.getMessage());
    }

    @Test
    void testConsultarCancionesPorRangoDuracionFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException, CatalogoCancionesVacioException, DuracionMinimaEsMayorADuracionMaximaException, DuracionMinimaEsIgualADuracionMaximaException {
        Contenido contenido1 = new Contenido("AAA111", "November Rain", 1991, 38.95f);
        Cancion cancion1 = new Cancion(contenido1, "Queen");
        Contenido contenido2 = new Contenido("AAA222", "Love of my Life", 1975, 3.65f);
        Cancion cancion2 = new Cancion(contenido2, "Queen");
        catalogo.agregarCancion("AAA111", cancion1);
        catalogo.agregarCancion("AAA222", cancion2);
        ArrayList<Cancion> canciones = catalogo.consultarCancionesPorRangoDuracion(2,40);
        assertEquals(2, canciones.size());
        assertEquals(cancion2, canciones.get(0));
        assertEquals(cancion1, canciones.get(1));
    }

    @Test
    void testConsultarCancionesPorRangoDuracionMinimaMayorADuracionMaximaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException, CatalogoCancionesVacioException, DuracionMinimaEsMayorADuracionMaximaException, DuracionMinimaEsIgualADuracionMaximaException {
        Contenido contenido1 = new Contenido("AAA111", "November Rain", 1991, 38.95f);
        Cancion cancion1 = new Cancion(contenido1, "Queen");
        Contenido contenido2 = new Contenido("AAA222", "Love of my Life", 1975, 3.65f);
        Cancion cancion2 = new Cancion(contenido2, "Queen");
        catalogo.agregarCancion("AAA111", cancion1);
        catalogo.agregarCancion("AAA222", cancion2);
        Exception ex = assertThrows(DuracionMinimaEsMayorADuracionMaximaException.class, () -> catalogo.consultarCancionesPorRangoDuracion(40,2));
        assertEquals("La Duracion Minima No Puede Ser Mayor A La Duracion Maxima", ex.getMessage());
    }

    @Test
    void testConsultarCancionesPorRangoDuracionMinimaIgualADuracionMaximaLanzaExcepcion() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException, CatalogoCancionesVacioException, DuracionMinimaEsMayorADuracionMaximaException, DuracionMinimaEsIgualADuracionMaximaException {
        Contenido contenido1 = new Contenido("AAA111", "November Rain", 1991, 38.95f);
        Cancion cancion1 = new Cancion(contenido1, "Queen");
        Contenido contenido2 = new Contenido("AAA222", "Love of my Life", 1975, 3.65f);
        Cancion cancion2 = new Cancion(contenido2, "Queen");
        catalogo.agregarCancion("AAA111", cancion1);
        catalogo.agregarCancion("AAA222", cancion2);
        Exception ex = assertThrows(DuracionMinimaEsIgualADuracionMaximaException.class, () -> catalogo.consultarCancionesPorRangoDuracion(2,2));
        assertEquals("La Duracion Minima No Puede Ser Igual A La Duracion Maxima", ex.getMessage());
    }

    @Test
    void testConsultarCancionesFuncionaCorrectamente() throws DuracionExcedeMaximoPermitidoException, AnioCreacionInvalidoException, CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException, CatalogoCancionesVacioException {
        Contenido contenido = new Contenido("AAA111", "Love of my Life", 1975, 3.65f);
        Cancion cancion = new Cancion(contenido, "Queen");
        catalogo.agregarCancion("AAA111", cancion);
        ArrayList<Cancion> canciones = catalogo.consultarCanciones();
        assertEquals(1, canciones.size());
        assertEquals(cancion, canciones.get(0));
    }
}
