package spotifyPresentacion;
import exceptions.CatalogoContenidos.*;
import exceptions.CatalogoCanciones.*;
import exceptions.HistorialReproduccionesCanciones.*;
import exceptions.Genericas.*;
import objetoServicio.*;
import objetosNegocio.*;
import objetosPersistencia.*;
import java.util.ArrayList;

/**
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class FachadaPersistencia implements IFachadaPersistencia {
    CatalogoContenidos catalogoContenidos;
    CatalogoCanciones catalogoCanciones;
    HistorialReproduccionesCanciones historialReproduccionesCanciones;

    public FachadaPersistencia(){
        catalogoContenidos = new CatalogoContenidos();
        catalogoCanciones = new CatalogoCanciones();
        historialReproduccionesCanciones = new HistorialReproduccionesCanciones();
    }

    @Override
    public void agregarContenido(String clave, Contenido contenido) throws DuracionDeContenidoInvalidaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, ContenidoExistenteException, ContenidoNullException, FormatoDeClaveInvalidoException {
        catalogoContenidos.agregarContenido(clave, contenido);
    }

    @Override
    public void agregarCancion(String clave, Cancion cancion) throws CancionNullException, ClaveNullException, NombreAutorInvalidoException, CancionExistenteException, FormatoDeClaveInvalidoException {
        catalogoCanciones.agregarCancion(clave, cancion);
    }

    @Override
    public void agregarReproduccionCancion(String clave, Fecha fecha, boolean completa, Cancion cancion) throws CancionNullException, AtributoCompletaNullException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        historialReproduccionesCanciones.agregarReproduccionCancion(clave, fecha, completa, cancion);
    }

    @Override
    public void actualizarContenido(String clave, Contenido nuevoContenido) throws ContenidoNoEncontradoException, DuracionDeContenidoInvalidaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, ContenidoNullException, FormatoDeClaveInvalidoException {
        catalogoContenidos.actualizarContenido(clave, nuevoContenido);
    }

    @Override
    public void actualizarCancion(String clave, Cancion nuevaCancion) throws CancionNullException, DuracionDeContenidoInvalidaException, CancionNoEncontradaException, ClaveNullException, AnioCreacionDeContenidoInvalidoException, NombreAutorInvalidoException, ContenidoNullException, FormatoDeClaveInvalidoException {
        catalogoCanciones.actualizarCancion(clave, nuevaCancion);
    }

    @Override
    public void actualizarReproduccionCancion(String clave, Fecha fecha, boolean completa, Cancion cancion) throws CancionNullException, AtributoCompletaNullException, ActualizarReproduccionCancionCompletaException, ReproduccionCancionNoEcontradaException, ClaveNullException, FechaNullException, LimiteDiarioDeReproduccionConacionCompletaException, ReproduccionCancionExistenteException, FormatoDeClaveInvalidoException, FechaInvalidaException {
        historialReproduccionesCanciones.actualizarReproduccionCancion(clave, fecha, completa, cancion);
    }

    @Override
    public void eliminarContenido(String clave) throws ContenidoNoEncontradoException {
        catalogoContenidos.eliminarContenido(clave);
    }

    @Override
    public void eliminarCancion(String clave) throws CancionNoEncontradaException {
        catalogoCanciones.eliminarCancion(clave);
    }

    @Override
    public Contenido obtenerContenido(String clave){
        return catalogoContenidos.getContenido(clave);
    }

    @Override
    public Cancion obtenerCancion(String clave){
        return catalogoCanciones.getCancion(clave);
    }

    @Override
    public ReproduccionCancion obtenerReproduccionCancion(String clave){
        return historialReproduccionesCanciones.consultarReproduccionCancionPorClave(clave);
    }

    @Override
    public ArrayList<Contenido> consultarContenidos() throws CatalogoContenidosVacioException {
        return catalogoContenidos.consultarContenidos();
    }

    @Override
    public ArrayList<Contenido> consultarContenidosPorNombre(String nombre) throws CatalogoContenidosVacioException {
        return catalogoContenidos.consultarContenidosPorNombre(nombre);
    }

    @Override
    public ArrayList<Contenido> consultarContenidosPorAnio(int anioCreacion) throws CatalogoContenidosVacioException {
        return catalogoContenidos.consultarContenidosPorAnio(anioCreacion);
    }

    @Override
    public ArrayList<Contenido> consultarContenidosPorNombreyAnio(String nombre, int anioCreacion) throws CatalogoContenidosVacioException {
        return catalogoContenidos.consultarContenidosPorNombreyAnio(nombre, anioCreacion);
    }

    @Override
    public ArrayList<Contenido> consultarContenidos(String clave) throws CatalogoContenidosVacioException {
        return catalogoContenidos.consultarContenidosPorClaveParecida(clave);
    }

    @Override
    public ArrayList<Cancion> consultarCanciones() throws CatalogoCancionesVacioException {
        return catalogoCanciones.consultarCanciones();
    }

    @Override
    public ArrayList<Cancion> consultarCanciones(int duracionMinima, int duracionMaxima) throws DuracionMinimaEsMayorADuracionMaximaException, DuracionMinimaEsIgualADuracionMaximaException, CatalogoCancionesVacioException {
        return catalogoCanciones.consultarCancionesPorRangoDuracion(duracionMinima, duracionMaxima);
    }

    @Override
    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones() throws HistorialReproduccionesCancionesVacioException {
        return historialReproduccionesCanciones.consultarReproduccionesCanciones();
    }

    @Override
    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones(String autor) throws HistorialReproduccionesCancionesVacioException {
        return historialReproduccionesCanciones.consultarReproduccionesCancionesPorAutor(autor);
    }

    @Override
    public ArrayList<ReproduccionCancion> consultarReproduccionesCanciones(Periodo periodo) throws HistorialReproduccionesCancionesVacioException {
        return historialReproduccionesCanciones.consultarReproduccionesCancionesPorPeriodo(periodo);
    }
}
