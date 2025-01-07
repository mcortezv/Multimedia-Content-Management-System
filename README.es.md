# **Sistema de Gestión de Contenido Multimedia**

Este proyecto es un sistema robusto diseñado para gestionar un catálogo de canciones y otros contenidos multimedia, con operaciones CRUD (Crear, Leer, Actualizar, Eliminar) y validaciones personalizadas. Está construido con una arquitectura modular para garantizar escalabilidad y mantenibilidad. Cada módulo tiene su propia funcionalidad e incluye pruebas unitarias para asegurar la calidad del código.

## **Tabla de Contenidos**
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Descripción de Módulos](#descripción-de-módulos)
- [Licencia](#licencia)

## **Estructura del Proyecto**

```plaintext
|-- Multimedia Content Managment System/
    |-- objetoServicio/
        |-- Fecha.java
        |-- Periodo.java
    |-- objetosNegocio/
        |-- Cancion.java
        |-- Contenido.java
        |-- Episodio.java
        |-- Reproduccion.java
        |-- ReproduccionCancion.java
        |-- ReproduccionEpisodio.java
        |-- exceptions/
            |-- AnioCreacionInvalidoException
            |-- DuracionExcedeMaximoPermitidoException
    |-- pruebas/
        |-- Pruebas02.java
    |-- objetosPersistencia/
        |-- CatalogoCanciones.java
        |-- CatalogoContenidos.java
        |-- HistorialReproduccionesCanciones.java
        |-- exceptions/
            |-- Genericas/
            |-- CatalogoCanciones/
            |-- CatalogoContenidos/
            |-- HistorialReproduccionesCanciones/    
        |-- tests/
            |-- CatalogoCancionesTest.java
            |-- CatalogoContenidosTest.java
            |-- HistorialReproduccionesCancionesTest.java
    |-- spotifyPresentacion/
        |-- FachadaPersistencia.java
        |-- IFachadaPersistencia.java
        |-- MenuPrincipal.java
        |-- SistemaSpotify.java
```

## **Descripción de Módulos**

### **objetoServicio**
Módulo de servicios de utilidad:
- Fecha: Manejo avanzado de fechas.
- Periodo: Representa intervalos de tiempo.

### **objetosNegocio**
Módulo que define las principales clases de dominio:
- Contenido: Clase base para contenido multimedia.
- Cancion: Representa una canción con atributos como título, autor y duración.
- Episodio: Representa un episodio, incluye información sobre el número de episodio.
- Reproduccion: Almacena información sobre una clave identificadora, la fecha de reproducción y si la reproducción está completa o no.
- ReproduccionCancion: Extiende la clase Reproduccion para incluir el contenido de la canción que se está reproduciendo.
- ReproduccionEpisodio: Extiende la clase Reproduccion para incluir el contenido del episodio que se está reproduciendo.

### **objetosPersistencia**
Módulo encargado de gestionar la persistencia de datos. Contiene clases que permiten agregar, actualizar, eliminar y consultar canciones, contenidos y reproducciones:
- CatalogoCanciones: Administra objetos Cancion con validaciones personalizadas.
- CatalogoContenidos: Administra objetos Contenido con filtros por nombre y año.
- HistorialReproduccionesCanciones: Clase de persistencia que almacena objetos ReproduccionCancion en un historial con una clave única.

### **pruebas**
Pruebas unitarias para asegurar la funcionalidad y robustez del sistema:
- CatalogoCancionesTest: Prueba las operaciones del catálogo de canciones.
- CatalogoContenidosTest: Prueba las operaciones del catálogo de contenidos.
- HistorialReproduccionesCancionesTest: Prueba las operaciones del historial de reproducciones de canciones.

### **spotifyPresentacion**
Punto de entrada principal del sistema:
- MenuPrincipal: Integra todos los módulos para proporcionar una interfaz de usuario.
- SistemaSpotify: Clase principal que ejecuta la interfaz de usuario.

## **Licencia**
Este proyecto está bajo la licencia MIT. Consulta el archivo [LICENSE](./LICENSE.md) para más detalles.