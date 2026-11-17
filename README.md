# Videojuegos y Torneos

**Universidad Fidélitas**
Programación Cliente/Servidor Concurrente – III Cuatrimestre 2026
Profesor: Jonathan Quesada Campos
Avance de Proyecto Final #1

## Descripción

Este proyecto es un sistema de escritorio para organizar torneos de videojuegos. Permite registrar videojuegos, crear torneos asociados a cada juego y registrar las partidas entre los jugadores.

El sistema maneja tres roles de usuario (Administrador, Organizador y Participante), y cada uno tiene acceso a diferentes opciones dentro de la aplicación.

En este avance los datos se guardan en memoria mediante `ArrayList`, por lo que la información agregada se pierde al cerrar el programa.

## Usuarios de prueba

Para facilitar la revisión, el sistema incluye tres usuarios creados desde el inicio, uno por cada rol:

| Rol | Usuario | Clave |
| --- | --- | --- |
| Administrador | admin | 123 |
| Organizador | organizador | 123 |
| Participante | nelson | 123 |

Los usuarios que se registran desde la ventana de login quedan con el rol de Participante.

### Permisos por rol

| Rol | Videojuegos | Torneos | Competencia |
| --- | --- | --- | --- |
| Administrador | Agregar y consultar | Agregar y consultar | Registrar y consultar partidas |
| Organizador | Consultar | Agregar y consultar | Registrar y consultar partidas |
| Participante | Consultar | Consultar | Consultar solo sus partidas |

## Ejecución

1. Abrir el proyecto en NetBeans (File > Open Project).
2. Ejecutar el proyecto. La clase principal es `ProyectoAvance1.java`, que abre la ventana de login.

El proyecto está configurado con JDK 26 en el archivo `pom.xml`.

## Prueba sugerida

Como el sistema inicia sin videojuegos ni torneos, recomendamos probarlo en este orden:

1. Iniciar sesión con `admin` / `123`.
2. En **Videojuegos**, agregar un videojuego (por ejemplo: V001, EA Sports FC 25, Deportes, PlayStation 5).
3. En **Torneos**, crear un torneo con ese videojuego (por ejemplo: T001, Copa Fidélitas, cupo 8).
4. En **Competencia**, registrar una partida entre dos jugadores.
5. Cerrar sesión e ingresar con `nelson` / `123` para ver las opciones disponibles para un participante.

## Integrantes

| Integrante | Módulo a cargo |
| --- | --- |
| Nelson Cardona Rocha | Usuarios, login, registro, menú principal e integración |
| Gabriel Lizano Rodríguez | Videojuegos |
| Nicolás Gerardo Hernández Hernández | Torneos |
| Aaron Josue Jiménez Sancho | Competencia (inscripciones y partidas) |
