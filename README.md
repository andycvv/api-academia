# Sistema de Gestión Académica - API WebFlux

Este proyecto es una API reactiva desarrollada con **Spring Boot WebFlux** y MongoDB. Simula un sistema de gestión académica con funcionalidades para registrar **Estudiantes**, **Cursos** y **Matrículas**, aplicando arquitectura limpia, validaciones reactivas, pruebas unitarias y seguridad con JWT.

---

## 🔧 Tecnologías y Herramientas

- Java 21
- Spring Boot 3.3.x
   - Spring WebFlux
   - Spring Data Reactive MongoDB
   - Spring Security + JWT
- MongoDB 4.x+
- MapStruct para mapeo entre entidades y DTOs
- JUnit 5 + WebTestClient para pruebas unitarias
- Docker + Docker Compose (opcional)
- OpenAPI 3 (Swagger UI) habilitado con autenticación JWT

---

## 📁 Estructura del Proyecto

```
├── config/
├── controller/
├── dto/
├── exception/
├── handler/
├── mapper/
├── model/
├── repository/
├── security/
├── service/
│   ├── impl/
├── validator/
└── test/
```

---

## 🧪 Pruebas Unitarias

Se han implementado pruebas con `WebTestClient` para los siguientes controladores:

- `CursoControllerTest`
- `EstudianteControllerTest`
- `MatriculaControllerTest`

Cada prueba verifica:

- ✅ Respuestas correctas con `status` esperado
- ✅ Validación de contenido y headers
- ✅ Integración de seguridad con configuración de test `WebSecurityConfigTest`

---

## 🔐 Seguridad

La API utiliza **JWT** para proteger los endpoints. Para poder probar los endpoints en Swagger UI:

1. Ir a `/swagger-ui.html`
2. Click en "Authorize"
3. Ingresar el token con el prefijo `Bearer `

En el backend, Swagger está excluido de la seguridad dentro del `SecurityWebFilterChain`.

---

## 🗃️ Endpoints Principales
Se usó tanto el enfoque anotacional como el funcional (functional endpoints), en caso de querar usar
los functional endpoints, simplemente añada el prefijo `/v2/` en todos los endpoints al momento de
probar.

### Estudiantes
- `GET /estudiantes`
- `GET /estudiantes/{id}`
- `POST /estudiantes`
- `PUT /estudiantes/{id}`
- `DELETE /estudiantes/{id}`

### Cursos
- `GET /cursos`
- `GET /cursos/{id}`
- `POST /cursos`
- `PUT /cursos/{id}`
- `DELETE /cursos/{id}`

### Matrículas
- `POST /matriculas`

---

## ✅ Validaciones Reactivas

Se utiliza `@Valid` con DTOs para validar campos como `@NotBlank`, `@Size`, `@Min`, etc. En el enfoque funcional, se integró validación personalizada.

---

## 🧑‍💻 Autor

Desarrollado por **Andy Camilo Vargas Vargas** - [LinkedIn](https://www.linkedin.com/in/andyvargasvargas/)
