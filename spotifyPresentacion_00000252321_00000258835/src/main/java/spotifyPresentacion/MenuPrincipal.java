package spotifyPresentacion;
import exceptions.*;
import exceptions.Genericas.*;
import exceptions.CatalogoContenidos.*;
import exceptions.CatalogoCanciones.*;
import exceptions.HistorialReproduccionesCanciones.*;
import objetoServicio.*;
import objetosNegocio.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Escalante, Sebastian; Cortez, Manuel
 */

public class MenuPrincipal {
    private final IFachadaPersistencia persistencia;
    private final Scanner scanner;

    public MenuPrincipal(IFachadaPersistencia persistencia) {
        this.persistencia = persistencia;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        while (true) {
            try {
                System.out.println("\n------------------------------ Menú Principal ----------------------------------------");
                System.out.println("1. Agregar Nuevo Contenido                10. Consultar Contenido por Clave");
                System.out.println("2. Agregar Nueva Canción                  11. Consultar Contenido por Nombre y Anio");
                System.out.println("3. Agregar Reproducción de Canción        12. Consultar Contenido por Clave Parecida");
                System.out.println("4. Actualizar Contenido                   13. Consultar Catálogo de Canciones");
                System.out.println("5. Actualizar Canción                     14. Consultar Cancion por Clave");
                System.out.println("6. Actualizar Reproducción de Canción     15. Consultar Canciones por Rango Duracion");
                System.out.println("7. Eliminar Contenido                     16. Consultar Historial de Reproducciones");
                System.out.println("8. Eliminar Canción                       17. Consultar Reproducciones por Autor");
                System.out.println("9. Consultar Catálogo de Contenidos       18. Consultar Reproducciones por Periodo");
                System.out.printf("%nSeleccione una Opción (19. Salir): ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 1 -> agregarContenido();
                    case 2 -> agregarCancion();
                    case 3 -> agregarReproduccionCancion();
                    case 4 -> actualizarContenido();
                    case 5 -> actualizarCancion();
                    case 6 -> actualizarReproduccionCancion();
                    case 7 -> eliminarContenido();
                    case 8 -> eliminarCancion();
                    case 9 -> consultarCatalogoContenidos();
                    case 10 -> consultarContenidoPorClave();
                    case 11 -> consultarContenidosPorNombreyAnio();
                    case 12 -> consultarContenidosPorClaveParecida();
                    case 13 -> consultarCatalogoCanciones();
                    case 14 -> consultarCancionPorClave();
                    case 15 -> consultarCancionesPorRangoDuracion();
                    case 16 -> consultarHistorialReproduccionesCanciones();
                    case 17 -> consultarReproduccionesCancionesPorAutor();
                    case 18 -> consultarReproduccionesCancionesPorPeriodo();
                    case 19 -> salir();
                    default -> System.out.println("Opción Inválida. Por favor, Intente de Nuevo.");
                }
            } catch (InputMismatchException ex){
                System.out.println("La Opción es un Entero. Por favor, Intente de Nuevo.");
                scanner.nextLine();
            }
        }
    }

    private void agregarContenido() {
        System.out.println("\n--------------- Agregar Nuevo Contenido ------------------------------");
        try {
            System.out.print("Ingrese la Clave del Contenido: ");
            String clave = scanner.nextLine();
            System.out.print("Ingrese el Nombre del Contenido: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el Año de Creación: ");
            int anioCreacion = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese la Duración en Minutos: ");
            float duracion = scanner.nextFloat();
            scanner.nextLine();
            Contenido contenido = new Contenido(clave, nombre, anioCreacion, duracion);
            persistencia.agregarContenido(clave, contenido);
            System.out.println("*** Contenido Agregado Exitosamente ***");
        } catch (DuracionDeContenidoInvalidaException | ClaveNullException | AnioCreacionDeContenidoInvalidoException |
                 ContenidoExistenteException | ContenidoNullException | FormatoDeClaveInvalidoException |
                 AnioCreacionInvalidoException | DuracionExcedeMaximoPermitidoException ex) {
            System.out.println("*** Error al Agregar Contenido *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("*** Error al Agregar Contenido *** | El Año de Creacion no Puede ser Texto");
        } catch (InputMismatchException ex) {
            System.out.println("*** Error al Agregar Contenido *** | La Duracion no Puede ser Texto");
        }
    }

    private void agregarCancion() {
        System.out.println("\n--------------- Agregar Nueva Canción ------------------------------");
        try {
            System.out.print("Ingrese la Clave del Contenido: ");
            String clave = scanner.nextLine();
            Contenido contenido = persistencia.obtenerContenido(clave);
            if (contenido != null){
                System.out.print("Ingrese el Nombre del Autor: ");
                String autor = scanner.nextLine();
                Cancion cancion = new Cancion(new Contenido(clave, contenido.getNombre(), contenido.getAnioCreacion(), contenido.getDuracion()), autor);
                persistencia.agregarCancion(clave, cancion);
                System.out.println("*** Canción Agregada Exitosamente ***");
            } else {
                System.out.println("*** Contenido no Encontrado ***");
            }
        } catch (DuracionExcedeMaximoPermitidoException | AnioCreacionInvalidoException | CancionNullException |
                 ClaveNullException | NombreAutorInvalidoException | CancionExistenteException | FormatoDeClaveInvalidoException ex) {
            System.out.println("*** Error al Agregar Canción *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("*** Error al Agregar Cancion *** | El Año de Creacion no Puede ser Texto");
        } catch (InputMismatchException ex) {
            System.out.println("*** Error al Agregar Cancion *** | La Duracion no Puede ser Texto");
        }
    }

    private void agregarReproduccionCancion() {
        System.out.println("\n--------------- Agregar Reproducción de Canción ------------------------------");
        try {
            System.out.print("Ingrese la Clave de la Canción Reproducida: ");
            String clave = scanner.nextLine();
            Cancion cancion = persistencia.obtenerCancion(clave);
            if (cancion != null){
                System.out.print("Ingrese la Fecha de Reproducción (DD/MM/AAAA): ");
                String fecha = scanner.nextLine();
                boolean completa;
                while (true){
                    System.out.print("¿La Reproduccion Fue Completada? (S/N): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        completa = true;
                        break;
                    } else if (confirmacion.equalsIgnoreCase("n")) {
                        completa = false;
                        break;
                    } else {
                        System.out.println("*** Ingrese una Opcion Valida ***");
                    }
                }
                persistencia.agregarReproduccionCancion(clave, new Fecha(fecha), completa, cancion);
                System.out.println("*** Reproducción Agregada Exitosamente ***");
            } else {
                System.out.println("*** Canción no Encontrada ***");
            }
        } catch (CancionNullException | AtributoCompletaNullException | ClaveNullException | FechaNullException |
                 LimiteDiarioDeReproduccionConacionCompletaException | ReproduccionCancionExistenteException |
                 FormatoDeClaveInvalidoException | FechaInvalidaException ex) {
            System.out.println("*** Error al Agregar Reproducción *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("La Fecha Debe Contener Solo Números. Use el Formato DD/MM/AAAA.");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NullPointerException ex) {
            System.out.println("Formato de Fecha Inválido. Use el Formato DD/MM/AAAA.");
        }
    }

    private void actualizarContenido() {
        System.out.println("\n--------------- Actualizar Contenido ------------------------------");
        try {
            System.out.print("Ingrese la Clave del Contenido a Actualizar: ");
            String clave = scanner.nextLine();
            Contenido contenidoExistente = persistencia.obtenerContenido(clave);
            if (contenidoExistente != null) {
                System.out.println("Información Actual del Contenido:");
                System.out.println("Clave: " + contenidoExistente.getClave());
                System.out.println("Nombre: " + contenidoExistente.getNombre());
                System.out.println("Año de Creación: " + contenidoExistente.getAnioCreacion());
                System.out.println("Duración: " + contenidoExistente.getDuracion() + " minutos");
                while (true){
                    System.out.print("¿Desea Actualizarlo? (S/N): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        System.out.print("Ingrese el Nuevo Nombre del Contenido: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese el Nuevo Año de Creación: ");
                        int nuevoAnioCreacion = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese la Nueva Duración en Minutos: ");
                        float nuevaDuracion = scanner.nextFloat();
                        scanner.nextLine();
                        Contenido nuevoContenido = new Contenido(clave, nuevoNombre, nuevoAnioCreacion, nuevaDuracion);
                        persistencia.actualizarContenido(clave, nuevoContenido);
                        System.out.println("*** Contenido Actualizado Exitosamente ***");
                        break;
                    } else if (confirmacion.equalsIgnoreCase("n")) {
                        System.out.println("*** Actualización Cancelada ***");
                        break;
                    } else {
                        System.out.println("*** Ingrese una Opcion Valida ***");
                    }
                }
            } else {
                System.out.println("*** Contenido no Encontrado ***");
            }
        } catch (DuracionExcedeMaximoPermitidoException | AnioCreacionInvalidoException | ContenidoNoEncontradoException |
                 DuracionDeContenidoInvalidaException | ClaveNullException | AnioCreacionDeContenidoInvalidoException |
                 ContenidoExistenteException | ContenidoNullException | FormatoDeClaveInvalidoException ex) {
            System.out.println("*** Error al Actualizar Contenido *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("*** Error al Actualizar Contenido *** | El Año de Creacion no Puede ser Texto");
        } catch (InputMismatchException ex) {
            System.out.println("*** Error al Actualizar Contenido *** | La Duracion no Puede ser Texto");
        }
    }

    private void actualizarCancion() {
        System.out.println("\n--------------- Actualizar Cancion ------------------------------");
        try {
            System.out.print("Ingrese la Clave de la Cancion a Actualizar: ");
            String clave = scanner.nextLine();
            Cancion cancionExistente = persistencia.obtenerCancion(clave);
            if (cancionExistente != null) {
                System.out.println("Información Actual de la Cancion:");
                System.out.println("Clave: " + cancionExistente.getClave());
                System.out.println("Nombre: " + cancionExistente.getNombre());
                System.out.println("Autor: " + cancionExistente.getAutor());
                System.out.println("Año de Creación: " + cancionExistente.getAnioCreacion());
                System.out.println("Duración: " + cancionExistente.getDuracion() + " minutos");
                while (true){
                    System.out.print("¿Desea Actualizarla? (S/N): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        System.out.print("Ingrese el Nuevo Nombre de la Cancion: ");
                        String nuevoNombre = scanner.nextLine();
                        System.out.print("Ingrese el Nuevo Nombre del Autor: ");
                        String nuevoAutor = scanner.nextLine();
                        System.out.print("Ingrese el Nuevo Año de Creación: ");
                        int nuevoAnioCreacion = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Ingrese la Nueva Duración en Minutos: ");
                        float nuevaDuracion = scanner.nextFloat();
                        scanner.nextLine();
                        Cancion nuevaCancion = new Cancion(new Contenido(clave, nuevoNombre, nuevoAnioCreacion, nuevaDuracion), nuevoAutor);
                        persistencia.actualizarCancion(clave, nuevaCancion);
                        System.out.println("*** Coancion Actualizada Exitosamente ***");
                        break;
                    } else if (confirmacion.equalsIgnoreCase("n")) {
                        System.out.println("*** Actualización Cancelada ***");
                        break;
                    } else {
                        System.out.println("*** Ingrese una Opcion Valida ***");
                    }
                }
            } else {
                System.out.println("*** Cancion no Encontrada ***");
            }
        } catch (DuracionExcedeMaximoPermitidoException | AnioCreacionInvalidoException | CancionNullException |
                 DuracionDeContenidoInvalidaException | CancionNoEncontradaException | ClaveNullException |
                 AnioCreacionDeContenidoInvalidoException | NombreAutorInvalidoException | ContenidoNullException |
                 FormatoDeClaveInvalidoException ex) {
            System.out.println("*** Error al Actualizar Cancion *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("*** Error al Actualizar Cancion *** | El Año de Creacion no Puede ser Texto");
        } catch (InputMismatchException ex) {
            System.out.println("*** Error al Actualizar Cancion *** | La Duracion no Puede ser Texto");
        }
    }

    private void actualizarReproduccionCancion() {
        System.out.println("\n--------------- Actualizar Reproducción de Canción ------------------------------");
        try {
            System.out.print("Ingrese la Clave de la Canción: ");
            String clave = scanner.nextLine();
            Cancion cancion = persistencia.obtenerCancion(clave);
            if (cancion != null){
                System.out.print("Ingrese la Nueva Fecha de Reproducción (DD/MM/AAAA): ");
                String nuevaFecha = scanner.nextLine();
                boolean completa;
                while (true){
                    System.out.print("¿La Reproduccion Fue Completada? (S/N): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        completa = true;
                        break;
                    } else if (confirmacion.equalsIgnoreCase("n")) {
                        completa = false;
                        break;
                    } else {
                        System.out.println("*** Ingrese una Opcion Valida ***");
                    }
                }
                persistencia.actualizarReproduccionCancion(clave, new Fecha(nuevaFecha), completa, cancion);
                System.out.println("**** Reproducción Actualizada Exitosamente ***");
            } else {
                System.out.println("*** Canción no Encontrada ***");
            }
        } catch (CancionNullException | AtributoCompletaNullException | ActualizarReproduccionCancionCompletaException |
                 ReproduccionCancionNoEcontradaException | ClaveNullException | FechaNullException |
                 LimiteDiarioDeReproduccionConacionCompletaException | ReproduccionCancionExistenteException |
                 FormatoDeClaveInvalidoException | FechaInvalidaException ex) {
            System.out.println("*** Error al Actualizar Reproducción *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("La fecha debe contener solo números. Use el formato DD/MM/AAAA.");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NullPointerException ex) {
            System.out.println("Formato de Fecha Inválido. Use el Formato DD/MM/AAAA.");
        }
    }

    private void eliminarContenido() {
        System.out.println("\n--------------- Eliminar Contenido ------------------------------");
        try {
            System.out.print("Ingrese la Clave del Contenido a Eliminar: ");
            String clave = scanner.nextLine();
            Contenido contenido = persistencia.obtenerContenido(clave);
            if (contenido != null) {
                System.out.println("Información del Contenido a Eliminar:");
                System.out.println("Clave: " + contenido.getClave());
                System.out.println("Nombre: " + contenido.getNombre());
                while (true){
                    System.out.print("¿Desea Eliminar Este Contenido? (S/N): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        persistencia.eliminarContenido(clave);
                        System.out.println("*** Contenido Eliminado Exitosamente ***");
                        break;
                    } else if (confirmacion.equalsIgnoreCase("n")) {
                        System.out.println("*** Eliminación Cancelada ***");
                        break;
                    } else {
                        System.out.println("*** Ingrese una Opcion Valida ***");
                    }
                }
            } else {
                System.out.println("***Contenido no Encontrado ***");
            }
        } catch (ContenidoNoEncontradoException ex) {
            System.out.println("*** Error al Eliminar Contenido *** | " + ex.getMessage());
        }
    }

    private void eliminarCancion() {
        System.out.println("\n--------------- Eliminar Cancion ------------------------------");
        try {
            System.out.print("Ingrese la Clave de la Cancion a Eliminar: ");
            String clave = scanner.nextLine();
            Cancion cancion = persistencia.obtenerCancion(clave);
            if (cancion != null) {
                System.out.println("Información de la Cancion a Eliminar:");
                System.out.println("Clave: " + cancion.getClave());
                System.out.println("Nombre: " + cancion.getNombre());
                while (true){
                    System.out.print("¿Desea Eliminar Esta Cancion? (S/N): ");
                    String confirmacion = scanner.nextLine();
                    if (confirmacion.equalsIgnoreCase("s")) {
                        persistencia.eliminarCancion(clave);
                        System.out.println("*** Cancion Eliminada Exitosamente ***");
                        break;
                    } else if (confirmacion.equalsIgnoreCase("n")) {
                        System.out.println("*** Eliminación Cancelada ***");
                        break;
                    } else {
                        System.out.println("*** Ingrese una Opcion Valida ***");
                    }
                }
            } else {
                System.out.println("*** Cancion no Encontrado ***");
            }
        } catch (CancionNoEncontradaException ex) {
            System.out.println("*** Error al Eliminar Cancion *** | " + ex.getMessage());
        }
    }

    private void consultarCatalogoContenidos() {
        try {
            ArrayList<Contenido> contenidos = persistencia.consultarContenidos();
            System.out.println("\n--------------- Consultar Catálogo de Contenidos ------------------------------");
            System.out.println("Catálogo de Contenidos:");
            for (Contenido contenido : contenidos) {
                System.out.println("Clave: " + contenido.getClave());
                System.out.println("Nombre: " + contenido.getNombre());
                System.out.println("Año: " + contenido.getAnioCreacion());
                System.out.println("Duración: " + contenido.getDuracion() + " minutos");
                System.out.println("-----------------------");
            }
        } catch (CatalogoContenidosVacioException ex) {
            System.out.println("*** Error al Consultar el Catálogo *** | " + ex.getMessage());
        }
    }

    private void consultarContenidoPorClave() {
        System.out.println("\n--------------- Consultar Contenido por Clave ------------------------------");
        System.out.print("Ingrese la Clave del Contenido: ");
        String clave = scanner.nextLine();
        Contenido contenido = persistencia.obtenerContenido(clave);
        if (contenido != null) {
            System.out.println("Clave: " + contenido.getClave());
            System.out.println("Nombre: " + contenido.getNombre());
            System.out.println("Año de Creación: " + contenido.getAnioCreacion());
            System.out.println("Duración: " + contenido.getDuracion() + " minutos");
        } else {
            System.out.println("*** Contenido no Encontrado ***");
        }
    }

    private void consultarContenidosPorNombreyAnio() {
        System.out.println("\n--------------- Consultar Contenidos por Nombre y Año ------------------------------");
        try {
            while (true){
                System.out.print("¿Desea Consultar por (Nombre = 1 / Año = 2 / Ambos = 3)?: ");
                String confirmacion = scanner.nextLine();
                if (confirmacion.equals("1")) {
                    System.out.print("Ingrese el Nombre del Contenido: ");
                    String nombre = scanner.nextLine();
                    ArrayList<Contenido> contenidos = persistencia.consultarContenidosPorNombre(nombre);
                    if (!contenidos.isEmpty()){
                        for (Contenido contenido:contenidos){
                            System.out.println("Clave: " + contenido.getClave());
                            System.out.println("Nombre: " + contenido.getNombre());
                            System.out.println("Año de creación: " + contenido.getAnioCreacion());
                            System.out.println("Duración: " + contenido.getDuracion() + " minutos");
                            System.out.println("----------------");
                        }
                    } else {
                        System.out.println("*** Contenido no Encontrado ***");
                    }
                    break;
                } else if (confirmacion.equals("2")) {
                    System.out.print("Ingrese el Año de Creacion del Contenido: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Contenido> contenidos = persistencia.consultarContenidosPorAnio(anio);
                    if (!contenidos.isEmpty()){
                        for (Contenido contenido:contenidos){
                            System.out.println("Clave: " + contenido.getClave());
                            System.out.println("Nombre: " + contenido.getNombre());
                            System.out.println("Año de creación: " + contenido.getAnioCreacion());
                            System.out.println("Duración: " + contenido.getDuracion() + " minutos");
                        }
                    } else {
                        System.out.println("*** Contenido no Encontrado ***");
                    }
                    break;
                } else if (confirmacion.equals("3")) {
                    System.out.print("Ingrese el Nombre del Contenido: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el Año del Contenido: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Contenido> contenidos = persistencia.consultarContenidosPorNombreyAnio(nombre, anio);
                    if (!contenidos.isEmpty()){
                        for (Contenido contenido:contenidos){
                            System.out.println("Clave: " + contenido.getClave());
                            System.out.println("Nombre: " + contenido.getNombre());
                            System.out.println("Año de creación: " + contenido.getAnioCreacion());
                            System.out.println("Duración: " + contenido.getDuracion() + " minutos");
                        }
                    } else {
                        System.out.println("*** Contenido no Encontrado ***");
                    }
                    break;
                } else {
                    System.out.println("*** Ingrese una Opcion Valida ***");
                }
            }
        } catch (CatalogoContenidosVacioException ex) {
            System.out.println("*** Error al Consultar el Catálogo *** | " + ex.getMessage());
        } catch (InputMismatchException ex) {
            System.out.println("*** Error al Consultar el Catálogo *** | El Año de Creacion no Puede ser Texto");
        }
    }

    private void consultarContenidosPorClaveParecida() {
        System.out.println("\n--------------- Consultar Contenidos por Clave Parecida ------------------------------");
        try {
            System.out.print("Ingrese la Clave del Contenido: ");
            String clave = scanner.nextLine();
            ArrayList<Contenido> contenidos = persistencia.consultarContenidos(clave);
            if (!contenidos.isEmpty()){
                for (Contenido contenido:contenidos){
                    System.out.println("Clave: " + contenido.getClave());
                    System.out.println("Nombre: " + contenido.getNombre());
                    System.out.println("Año de creación: " + contenido.getAnioCreacion());
                    System.out.println("Duración: " + contenido.getDuracion() + " minutos");
                    System.out.println("----------------");
                }
            } else {
                System.out.println("*** Contenido no Encontrado ***");
            }
        } catch (CatalogoContenidosVacioException ex) {
            System.out.println("*** Error al Consultar el Catálogo *** | " + ex.getMessage());
        }
    }

    private void consultarCatalogoCanciones() {
        try {
            ArrayList<Cancion> canciones = persistencia.consultarCanciones();
            System.out.println("\n--------------- Mostrar Catálogo de Canciones ------------------------------");
            System.out.println("Catálogo de Canciones:");
            for (Cancion cancion : canciones) {
                System.out.println("Clave: " + cancion.getClave());
                System.out.println("Nombre: " + cancion.getNombre());
                System.out.println("Autor: " + cancion.getAutor());
                System.out.println("Año de Creacion: " + cancion.getAnioCreacion());
                System.out.println("Duración: " + cancion.getDuracion() + " minutos");
                System.out.println("-----------------------");
            }
        } catch (CatalogoCancionesVacioException ex) {
            System.out.println("*** Error al Consultar el Catálogo de Canciones *** | " + ex.getMessage());
        }
    }

    private void consultarCancionPorClave() {
        System.out.println("\n--------------- Consultar Canción por Clave ------------------------------");
        System.out.print("Ingrese la Clave de la Canción: ");
        String clave = scanner.nextLine();
        Cancion cancion = persistencia.obtenerCancion(clave);
        if (cancion != null) {
            System.out.println("Clave: " + cancion.getClave());
            System.out.println("Nombre: " + cancion.getNombre());
            System.out.println("Autor: " + cancion.getAutor());
            System.out.println("Año de Creacion: " + cancion.getAnioCreacion());
            System.out.println("Duración: " + cancion.getDuracion() + " minutos");
            System.out.println("-----------------------");
        } else {
            System.out.println("*** Canción no Encontrada ***");
        }
    }

    private void consultarCancionesPorRangoDuracion() {
        System.out.println("\n--------------- Consultar Canción por Rango Duracion ------------------------------");
        try {
            System.out.print("Ingrese la Duracion Minima: ");
            int duracionMinima = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Ingrese la Duracion Maxima: ");
            int duracionMaxima = scanner.nextInt();
            scanner.nextLine();
            ArrayList<Cancion> canciones = persistencia.consultarCanciones(duracionMinima, duracionMaxima);
            if (!canciones.isEmpty()) {
                for (Cancion cancion:canciones){
                    System.out.println("Clave: " + cancion.getClave());
                    System.out.println("Nombre: " + cancion.getNombre());
                    System.out.println("Autor: " + cancion.getAutor());
                    System.out.println("Año de Creacion: " + cancion.getAnioCreacion());
                    System.out.println("Duración: " + cancion.getDuracion() + " minutos");
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("*** Canción no Encontrada ***");
            }
        } catch (DuracionMinimaEsMayorADuracionMaximaException | DuracionMinimaEsIgualADuracionMaximaException |
                 CatalogoCancionesVacioException ex) {
            System.out.println("*** Error al Consultar el Catálogo de Canciones *** | " + ex.getMessage());
        } catch (InputMismatchException ex) {
            System.out.println("*** Error al Consultar el Catálogo de Canciones *** | La Duracion No Puede Ser Texto ");
        }
    }

    private void consultarHistorialReproduccionesCanciones() {
        try {
            ArrayList<ReproduccionCancion> historial = persistencia.consultarReproduccionesCanciones();
            System.out.println("\n--------------- Consultar Historial de Reproducciones de Canciones ------------------------------");
            System.out.println("Historial de Reproducciones:");
            for (ReproduccionCancion reproduccionCancion : historial) {
                System.out.println("Clave: " + reproduccionCancion.getClave());
                System.out.println("Fecha: " + reproduccionCancion.getFecha());
                System.out.println("Completa: " + reproduccionCancion.getCompleta());
                System.out.println("Cancion: " + reproduccionCancion.getCancion().getNombre());
                System.out.println("-----------------------");
            }
        } catch (HistorialReproduccionesCancionesVacioException ex) {
            System.out.println("*** Error al Consultar el Historial de Reproducciones *** | " + ex.getMessage());
        }
    }

    private void consultarReproduccionesCancionesPorAutor() {
        System.out.println("\n--------------- Consultar Reproducciones Canciones por Autor ------------------------------");
        try {
            System.out.print("Ingrese el Nombre del Autor de la Cancion: ");
            String autor = scanner.nextLine();
            ArrayList<ReproduccionCancion> historial = persistencia.consultarReproduccionesCanciones(autor);
            if (!historial.isEmpty()){
                System.out.println("Historial de Reproducciones:");
                for (ReproduccionCancion reproduccionCancion : historial) {
                    System.out.println("Clave: " + reproduccionCancion.getClave());
                    System.out.println("Fecha: " + reproduccionCancion.getFecha());
                    System.out.println("Completa: " + reproduccionCancion.getCompleta());
                    System.out.println("Cancion: " + reproduccionCancion.getCancion().getNombre());
                    System.out.println("-----------------------");
                }
            } else {
                System.out.println("*** Reproducción Canción no Encontrada ***");
            }
        } catch (HistorialReproduccionesCancionesVacioException ex) {
            System.out.println("*** Error al Consultar el Historial de Reproducciones *** | " + ex.getMessage());
        }
    }

    private void consultarReproduccionesCancionesPorPeriodo() {
        System.out.println("\n--------------- Consultar Reproducciones Canciones por Periodo ------------------------------");
        try {
            System.out.print("Ingrese la Fecha de Inicio del Periodo (DD/MM/AAAA): ");
            Fecha fechaInicio = new Fecha(scanner.nextLine());
            System.out.print("Ingrese la Fecha Fin del Periodo (DD/MM/AAAA): ");
            Fecha fechaFin = new Fecha(scanner.nextLine());
            if (fechaFin.getTimeInMillis() >= fechaInicio.getTimeInMillis()){
                ArrayList<ReproduccionCancion> historial = persistencia.consultarReproduccionesCanciones(new Periodo(fechaInicio, fechaFin));
                if (!historial.isEmpty()){
                    System.out.println("Historial de Reproducciones:");
                    for (ReproduccionCancion reproduccionCancion : historial) {
                        System.out.println("Clave: " + reproduccionCancion.getClave());
                        System.out.println("Fecha: " + reproduccionCancion.getFecha());
                        System.out.println("Completa: " + reproduccionCancion.getCompleta());
                        System.out.println("Cancion: " + reproduccionCancion.getCancion().getNombre());
                        System.out.println("-----------------------");
                    }
                } else {
                    System.out.println("*** Reproducción Canción no Encontrada ***");
                }
            } else {
                System.out.println("*** La Fecha Fin no Puede ser Previa a la Fecha de Inicio ***");
            }
        } catch (HistorialReproduccionesCancionesVacioException ex) {
            System.out.println("*** Error al Consultar el Historial de Reproducciones *** | " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.out.println("La Fecha Debe Contener Solo Números. Use el Formato DD/MM/AAAA.");
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NullPointerException ex) {
            System.out.println("Formato de Fecha Inválido. Use el Formato DD/MM/AAAA.");
        }
    }

    private static void salir(){
        System.out.println("\nSaliendo...");
        System.exit(0);
    }
}