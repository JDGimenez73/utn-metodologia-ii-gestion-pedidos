# Registro de pruebas funcionales iniciales

## Información general

- Responsable: Israel Gaddiel García Moscoso
- Fecha de ejecución: 15/11/2026
- Rama: israel/pruebas-base
- Commit evaluado: c2c1d20
- Aplicación: Gestión de Pedidos
- Entorno: Spring Boot, Java 21, H2 en memoria
- URL de pruebas: http://localhost:8081

## Resultados

| ID | Prueba | Endpoint | HTTP observado | Resultado |
|---|---|---|---|---|
| P01 | Listar pedidos | GET /api/pedidos | 200 | Aprobada |
| P02 | Consultar pedido existente | GET /api/pedidos/1 | 200 | Aprobada |
| P03 | Crear pedido | POST /api/pedidos | 201 | Aprobada |
| P04 | Actualizar pedido | PUT /api/pedidos/4 | 200 | Aprobada |
| P05 | Consultar pedido inexistente | GET /api/pedidos/999999 | 404 | Aprobada |
| P06 | Rechazar cantidad inválida | POST /api/pedidos | 400 | Aprobada |

## Observaciones

### P01
Se obtuvieron tres pedidos iniciales:
- Pedido 1: CONFIRMADO, total 5300.
- Pedido 2: PENDIENTE, total 7000.
- Pedido 3: TERMINADO, total 6600.

### P02
El pedido 1 contiene dos detalles:
- Agua Mineral: cantidad 2, subtotal 1800.
- Café Molido: cantidad 1, subtotal 3500.

Total verificado: 5300.

### P03
Se creó el pedido 4 correctamente.
- Estado: PENDIENTE.
- Total: 5300.
- Cabecera Location: /api/pedidos/4.
- Subtotales: 1800 y 3500.

### P04
Se actualizó el pedido 4.
- Estado anterior: PENDIENTE.
- Estado nuevo: CONFIRMADO.
- Total conservado: 5300.
- Se conservaron los dos detalles.

### P05
La consulta del pedido inexistente 999999 devolvió HTTP 404.

### P06
Se intentó crear un pedido con cantidad igual a cero.
La API rechazó la solicitud con HTTP 400.

## Conclusión

Las seis pruebas funcionales manuales ejecutadas sobre
la versión inicial finalizaron satisfactoriamente.

Se verificaron las operaciones de consulta, creación y
actualización de pedidos, además del tratamiento de
un recurso inexistente y una cantidad inválida.

Estos resultados representan una línea base para
comparar el comportamiento de la aplicación después
de las modificaciones y refactorizaciones posteriores.

## Datos de entrada y reproducibilidad

### P01 - Listado
- Método: GET
- Ruta: /api/pedidos
- Sin cuerpo de solicitud.
- Resultado observado: 3 pedidos.

### P02 - Consulta individual
- Método: GET
- Ruta: /api/pedidos/1
- Sin cuerpo de solicitud.
- Resultado observado: pedido 1, total 5300.

### P03 - Creación
- Método: POST
- Ruta: /api/pedidos
- Datos enviados:

```json
{
  "fecha": "2026-11-15",
  "estado": "PENDIENTE",
  "formaPago": "EFECTIVO",
  "usuarioId": 1,
  "detalles": [
    {"cantidad": 2, "productoId": 1},
    {"cantidad": 1, "productoId": 3}
  ]
}
```

- Resultado observado: HTTP 201, pedido creado con ID 4.

### P04 - Actualización
- Método: PUT
- Ruta: /api/pedidos/4
- Datos enviados:

```json
{
  "fecha": "2026-11-15",
  "estado": "CONFIRMADO",
  "formaPago": "EFECTIVO",
  "usuarioId": 1
}
```

- Resultado observado: HTTP 200, total conservado en 5300.
- Dependencia: requiere haber creado previamente el pedido 4.

### P05 - Recurso inexistente
- Método: GET
- Ruta: /api/pedidos/999999
- Sin cuerpo de solicitud.
- Resultado observado: HTTP 404.

### P06 - Validación de cantidad
- Método: POST
- Ruta: /api/pedidos
- Datos enviados:

```json
{
  "fecha": "2026-11-15",
  "estado": "PENDIENTE",
  "formaPago": "EFECTIVO",
  "usuarioId": 1,
  "detalles": [
    {"cantidad": 0, "productoId": 1}
  ]
}
```

- Resultado observado: HTTP 400.

## Consideraciones del entorno

La aplicación se ejecutó en el puerto 8081 porque
el puerto 8080 estaba ocupado por otro servicio.

La base H2 funciona en memoria, por lo que sus
datos pueden reiniciarse al detener la aplicación.

El identificador 4 corresponde al pedido creado
durante esta ejecución y puede variar en otras pruebas.


## Referencias a evidencias guardadas

### Evidencia P01

Archivo: `P01_listado.json`

La ejecución inicial de P01 devolvió HTTP 200 y tres
pedidos, correspondientes a los datos precargados.

El archivo JSON fue capturado posteriormente, después
de ejecutar P03 y P04. Por ese motivo contiene cuatro
pedidos, incluido el pedido 4 creado durante las pruebas.

Esta diferencia corresponde al estado de la base de
datos en momentos distintos y no representa un fallo
de la aplicación.

### Evidencia P02

Archivo: `P02_pedido_1.json`

Contiene la respuesta de la consulta del pedido 1,
con dos detalles y un total de 5300.


### Evidencia P04

Archivo: `P04_pedido_actualizado.json`

La operación original PUT /api/pedidos/4 devolvió
HTTP 200 y actualizó el estado de PENDIENTE a CONFIRMADO.

Posteriormente se realizó una consulta
GET /api/pedidos/4, que también devolvió HTTP 200.

La respuesta guardada confirma:
- ID del pedido: 4.
- Estado final: CONFIRMADO.
- Total conservado: 5300.
- Cantidad de detalles: 2.
- Subtotales conservados: 1800 y 3500.

Este archivo representa el estado posterior a P04,
no la respuesta original de la solicitud PUT.


### Evidencia P05

Archivo: `P05_pedido_inexistente.txt`

Se repitió la consulta GET /api/pedidos/999999.
El servidor devolvió HTTP 404, coincidiendo con
el resultado esperado para un pedido inexistente.

La evidencia registra el código HTTP observado
en esta nueva ejecución.

### Evidencia P06

Archivo: `P06_cantidad_invalida.txt`

Se repitió la solicitud POST /api/pedidos enviando
un detalle con cantidad igual a cero.

El servidor devolvió HTTP 400, rechazando
correctamente la solicitud inválida.

La evidencia registra el código HTTP observado
en esta nueva ejecución.


### Evidencia P03

La prueba original se ejecutó mediante:
POST /api/pedidos

Datos enviados:
- Fecha: 2026-11-15.
- Estado inicial: PENDIENTE.
- Forma de pago: EFECTIVO.
- Usuario ID: 1.
- Producto ID 1: cantidad 2.
- Producto ID 3: cantidad 1.

Resultados observados durante la ejecución original:
- Código HTTP: 201 Created.
- Cabecera Location: /api/pedidos/4.
- ID generado: 4.
- Estado inicial: PENDIENTE.
- Subtotales: 1800 y 3500.
- Total: 5300.

La respuesta original del POST no se conservó
como archivo independiente.

El pedido 4 fue posteriormente actualizado en P04.
El archivo P04_pedido_actualizado.json permite
verificar su existencia y estado final, pero no
constituye evidencia directa del HTTP 201 original.

