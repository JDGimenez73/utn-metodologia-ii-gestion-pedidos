# Gestión de Pedidos - API REST

Trabajo práctico de Programación IV desarrollado con Java 21, Spring Boot,
Spring Data JPA, H2, Lombok, validaciones Jakarta y Swagger/OpenAPI.

## Ejecución

En Windows, desde la carpeta que contiene `pom.xml`:

```powershell
.\mvnw.cmd clean compile
.\mvnw.cmd spring-boot:run
```

La carga inicial crea mediante DTOs:

- 2 usuarios.
- 3 categorías.
- 10 productos.
- 3 pedidos con 2 detalles cada uno.

## Verificación

- Inicio: <http://localhost:8080/>
- Resumen de datos: <http://localhost:8080/api/resumen>
- Pedidos: <http://localhost:8080/api/pedidos>
- Consola H2: <http://localhost:8080/h2-console>
- Swagger UI: <http://localhost:8080/swagger-ui.html>
- OpenAPI JSON: <http://localhost:8080/v3/api-docs>

Para ingresar en H2:

- JDBC URL: `jdbc:h2:mem:gestionpedidos`
- User Name: `sa`
- Password: dejar vacío.

## Conceptos aplicados

- El Application Context crea y administra los beans.
- `@Component` ejecuta la carga inicial.
- `@Service` contiene la lógica de creación y conversión de DTOs.
- `@Repository` representa el acceso a datos mediante Spring Data JPA.
- `@RestController` expone los resultados para su verificación.
- Todas las dependencias se reciben mediante inyección por constructor.
- `application.properties` configura el servidor, H2, JPA y la carga inicial.

## Endpoints principales

| Método | Ruta | Acción |
|---|---|---|
| GET / POST | `/api/usuarios` | Listar o crear usuarios |
| GET / PUT / DELETE | `/api/usuarios/{id}` | Buscar, actualizar o eliminar |
| GET | `/api/usuarios/buscar?mail=correo` | Buscar usuario por correo |
| GET / POST | `/api/categorias` | Listar o crear categorías |
| GET / PUT / PATCH / DELETE | `/api/categorias/{id}` | Gestionar una categoría |
| GET / POST | `/api/productos` | Listar o crear productos |
| GET / PUT / DELETE | `/api/productos/{id}` | Gestionar un producto |
| GET / POST | `/api/pedidos` | Listar o crear pedidos |
| GET / PUT / DELETE | `/api/pedidos/{id}` | Gestionar un pedido |

## Ejemplos para Postman

Crear un usuario:

```json
{
  "nombre": "Ana",
  "apellido": "Pérez",
  "mail": "ana@mail.com",
  "celular": "1122334455",
  "contrasena": "1234",
  "rol": "USUARIO"
}
```

Actualizar parcialmente una categoría:

```json
{
  "descripcion": "Descripción actualizada desde Postman"
}
```

Crear un pedido con dos detalles:

```json
{
  "fecha": "2026-08-30",
  "estado": "PENDIENTE",
  "formaPago": "EFECTIVO",
  "usuarioId": 1,
  "detalles": [
    { "cantidad": 2, "productoId": 1 },
    { "cantidad": 1, "productoId": 3 }
  ]
}
```
