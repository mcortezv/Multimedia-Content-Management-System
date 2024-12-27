package spotifyPresentacion;
import exceptions.CatalogoContenidos.*;
import exceptions.CatalogoCanciones.*;
import exceptions.HistorialReproduccionesCanciones.*;
import exceptions.Genericas.*;
import objetoServicio.*;
import objetosNegocio.*;
import java.util.ArrayList;

/**
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public interface IFachadaPersistencia {

    public void agregarContenido(String clave, Contenido contenido) throws DuracionDeContenidoInvalidaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, ContenidoExistenteException, ContenidoNullException, FormatoDeClaveInvalidoException;

    public void agregarCancion(String clave, Cancion cancion) throws CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException;

    public void agregarReproduccionCancion(String clave, Fecha fecha, boolean completa, Cancion cancion) throws CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException;

    public void actualizarContenido(String clave, Contenido nuevoContenido) throws DuracionDeContenidoInvalidaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, ContenidoExistenteException, ContenidoNullException, FormatoDeClaveInvalidoException, ContenidoNoEncontradoException;

    public void actualizarCancion(String clave, Cancion nuevaCancion) throws CancionNullException, DuracionDeContenidoInvalidaException, CancionNoEncontradaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, NombreAutorInvalidoException, ContenidoNullException, FormatoDeClaveInvalidoException;

    public void actualizarReproduccionCancion(String clave, Fecha fecha, boolean completa, Cancion cancion) throws CancionNullException, AtributoCompletaNullException, ActualizarReproduccionCancionCompletaException, ReproduccionCancionNoEcontradaException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException;

    public void eliminarContenido(String clave) throws ContenidoNoEncontradoException;

    public void eliminarCancion(String clave) throws CancionNoEncontradaException;

    public Contenido obtenerContenido(String clave);

    public Cancion obtenerCancion(String clave);

    public ReproduccionCancion obtenerReproduccionCancion(String clave);

    public ArrayList<Contenido> consultarContenidos() throws CatalogoContenidosVacioException;

    public ArrayList<Contenido> consultarContenidosPorNombre(String nombre) throws CatalogoContenidosVacioException;

    public ArrayList<Contenido> consultarContenidosPorAnio(int anioCreacion) throws CatalogoContenidosVacioException;

    public ArrayList<Contenido> consultarContenidosPorNombreyAnio(String nombre, int anioCreacion) throws CatalogoContenidosVacioException;

    public ArrayList<Contenido> consultarContenidos(String clave) throws CatalogoContenidosVacioException;

    public ArrayList<Cancion> consultarCanciones() throws CatalogoCancionesVacioException;

    public ArrayList<Cancion> consultarCanciones(int duracionMinima, int duracionMaxima) throws DuracionMinimaEsMayorADuracionMaximaException, DuracionMinimaEsIgualADuracionMaximaException, CatalogoCancionesVacioException;

    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones() throws HistorialReproduccionesCancionesVacioException;

    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones(String autor) throws HistorialReproduccionesCancionesVacioException;

    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones(Periodo periodo) throws HistorialReproduccionesCancionesVacioException;
}

