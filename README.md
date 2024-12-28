# Multimedia Content Management System

## Description

This project is a robust system designed to manage a catalog of songs and other multimedia content, with CRUD (Create, Read, Update, Delete) operations and custom validations. It is built with a modular architecture to ensure scalability and maintainability. Each module has its own functionality and includes unit tests to ensure code quality.

## Table of Contents
- [Project Structure](#project-structure)
- [Module Description](#module-description)
- [License](#license)

## Project Structure

```
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

## Module Description

### **objetoServicio**
Utility services module:
- `Fecha`: Advanced date handling.
- `Periodo`: Represents time intervals.

### **objetosNegocio**
Module that defines the main domain classes:
- `Contenido`: Base class for multimedia content.
- `Cancion`: Represents a song with attributes like title, author, and duration.
- `Episodio`: Represents an episode, includes information about the episode number.
- `Reproduccion`: Stores information about an identifier key, the reproduction date, and whether the reproduction is complete or not.
- `ReproduccionCancion`: Extends the Reproduccion class to include the song content being played.
- `ReproduccionEpisodio`: Extends the Reproduccion class to include the episode content being played.

### **objetosPersistencia**
Module responsible for managing data persistence. It contains classes that allow adding, updating, deleting, and querying songs, content, and reproductions:
- `CatalogoCanciones`: Manages Cancion objects with custom validations.
- `CatalogoContenidos`: Manages Contenido objects with filters by name and year.
- `HistorialReproduccionesCanciones`: Persistence class that stores ReproduccionCancion objects in a history with a unique key.

### **tests**
Unit tests to ensure the system's functionality and robustness:
- `CatalogoCancionesTest`: Tests the operations of the song catalog.
- `CatalogoContenidosTest`: Tests the operations of the content catalog.
- `HistorialReproduccionesCancionesTest`: Tests the operations of the song reproduction history.

### **spotifyPresentacion**
Main entry point of the system:
- `MenuPrincipal`: Integrates all modules to provide a user interface.
- `SistemaSpotify`: Main class that runs the user interface.
   

## License
This project is licensed under the MIT License. See the [LICENSE](./LICENSE.md) file for more details.
