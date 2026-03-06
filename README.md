<h1 align="center">📚 Challenge Literatura</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java" />
  <img src="https://img.shields.io/badge/SpringBoot-3-green?style=for-the-badge&logo=spring" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql" />
  <img src="https://img.shields.io/badge/Estado-Finalizado-brightgreen?style=for-the-badge" />
</p>

<p align="center">
Proyecto desarrollado como parte del programa <b>Oracle Next Education (ONE)</b>.
</p>

---

# 📖 Descripción del Proyecto

**Literatura** es una aplicación de consola desarrollada en **Java con Spring Boot** que permite buscar libros desde la API pública **Gutendex (Project Gutenberg)** y almacenarlos en una base de datos **PostgreSQL**.

El sistema permite consultar información de libros y autores, almacenarlos en la base de datos y realizar diferentes búsquedas mediante un menú interactivo.

Este proyecto integra:

- Consumo de API REST
- Manejo de JSON con Jackson
- Persistencia con Spring Data JPA
- Base de datos PostgreSQL
- Arquitectura por capas
- Uso de variables de entorno para seguridad

---

# 🎮 Funcionamiento

### Menú principal

![menu](https://github.com/vivianagalaviz/challenge-literatura/blob/b8fb87aaa098f874d2d79e31362e400dc6eef723/menu.png)

---

### Búsqueda de libro desde la API

![buscar](https://github.com/vivianagalaviz/challenge-literatura/blob/b8fb87aaa098f874d2d79e31362e400dc6eef723/buscar-libro.png)

---

### Libros registrados en la base de datos

![libros](https://github.com/vivianagalaviz/challenge-literatura/blob/b8fb87aaa098f874d2d79e31362e400dc6eef723/libros-registrados.png)

---

### Autores registrados en la base de datos

![libros](https://github.com/vivianagalaviz/challenge-literatura/blob/b8fb87aaa098f874d2d79e31362e400dc6eef723/autores-registrados.png)

---

### Autores vivos en determinado año

![autores](https://github.com/vivianagalaviz/challenge-literatura/blob/b8fb87aaa098f874d2d79e31362e400dc6eef723/autores-vivos.png)

---

### Libros por idioma

![autores](https://github.com/vivianagalaviz/challenge-literatura/blob/b8fb87aaa098f874d2d79e31362e400dc6eef723/libros-idioma.png)

---

# ⚙️ Funcionalidades

- 🔎 Buscar libros por título desde la API
- 💾 Registrar libros y autores en la base de datos
- 📚 Listar libros registrados
- 👨‍💻 Listar autores registrados
- 📅 Consultar autores vivos en determinado año
- 🌎 Listar libros por idioma

---

# 🛠️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Jackson (JSON)
- Maven
- IntelliJ IDEA
- Git & GitHub

---

# 🌐 API utilizada

Gutendex – Project Gutenberg API

https://gutendex.com/

---

# 🔐 Seguridad

Las credenciales de conexión a la base de datos **no están expuestas en el código**.

Se utilizan **variables de entorno**:


DB_URL
DB_USER
DB_PASSWORD


Configuradas en `application.properties`.

---

# 🚀 Cómo ejecutar el proyecto

1️⃣ Clonar el repositorio


git clone https://github.com/vivianagalaviz/challenge-literatura.git


2️⃣ Configurar variables de entorno


-DB_URL=jdbc:postgresql://localhost:5432/BD
-DB_USER=postgres
-DB_PASSWORD=tu_password



3️⃣ Ejecutar el proyecto


mvn spring-boot:run


---

# 📌 Posibles mejoras futuras

- Crear interfaz gráfica (JavaFX o web con Spring)
- Implementar estadísticas de libros
- Exportar datos a archivos
- Implementar paginación en consultas

---

# 👩‍💻 Desarrollado por

- ✨ **Viviana Galaviz** - [@vivianagalaviz](https://github.com/vivianagalaviz)
