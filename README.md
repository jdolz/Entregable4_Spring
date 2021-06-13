# Entregable4_Spring
---
Aplicación de series y películas que consulta la API de OMDB para obtener datos de las mismas y permite al usuario guardar sus favoritas.
(Práctica final de Spring del módulo de Desarrollo de Aplicaciones Multiplataforma)

Se trata de una aplicación monolítica con templates .html con Thymeleaf+Bootstrap, utilizando Gson para convertir los objetos Java en Json y viceversa, y
una base de datos SQL, en concreto mySQL.

## Rutas:

### 1. Login
http://localhost:${port}/omdb/login nos lleva a la template inicial del formulario de login

### 2. Crear Usuario
http://localhost:${port}/omdb/altaUsuario nos lleva al formulario de registro de usuario

### 3. Buscar película
http://localhost:${port}/omdb/buscarPelicula tras un login correcto podemos buscar la película o serie deseada o ver las películas que tenemos guardadas

### 4. Ver película
http://localhost:${port}/omdb/verpelicula tras buscar una película nos muestra un resumen de los datos de la misma

### 5. Guardar película
http://localhost:${port}/omdb/guardarPeliUsuario guarda en favoritos la película del usuario

### 6. Listar películas
http://localhost:${port}/omdb/listarPeliculas lista todas las películas que el usuario haya guardado

### 7. Listar Detalles películas
http://localhost:${port}/omdb/listarDetalle/:id detalla la película seleccionada de la lista de películas guardadas
