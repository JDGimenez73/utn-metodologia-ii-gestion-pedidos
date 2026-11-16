# Verificación de R01 y R02

- Responsable: Jose Dario Gimenez.
- Fecha: 16/11/2026.
- Versión anterior: `8d51553`, con los PR 1 y 2 integrados.
- Versión posterior: cambios de `dario/r01-r02` incluidos en este commit.
- Entorno: Java 21, Spring Boot 4.0.7 y H2 en memoria reiniciada para cada ronda.
- URL anterior: `http://localhost:18081`.
- URL posterior: `http://localhost:18082`, instancia nueva sin reinicio de DevTools.
- Respuestas completas: [antes.json](antes.json) y [despues.json](despues.json).

## Resultados HTTP

| Caso | Verificación | Antes | Después |
|---|---|---|---|
| P01 | Listado inicial con 3 pedidos y totales 5300, 7000 y 6600 | 200, correcto | 200, correcto |
| P02 | Pedido 1, cantidades 2 y 1, subtotales 1800 y 3500, total 5300 | 200, correcto | 200, correcto |
| P03 | Alta con usuario 1 y productos 1 y 3; Location coincide con el ID creado | 201, correcto | 201, correcto |
| P04 | Actualización a CONFIRMADO; conserva detalles y total | 200, correcto | 200, correcto |
| P05 | Consulta de pedido 999999 inexistente | 404, correcto | 404, correcto |
| P06 | Alta con cantidad cero | 400, correcto | 400, correcto |
| R01-A | Alta con usuario 999999; mensaje de usuario inexistente | 404, correcto | 404, correcto |
| R01-B | Actualización con usuario 999999; mismo mensaje | 404, correcto | 404, correcto |
| R02-A | Alta con producto 999999; mensaje de producto inexistente | 404, correcto | 404, correcto |
| R01-C | Consultar el pedido luego de la actualización rechazada | Sin cambios | Sin cambios |
| R02-B | Listar después de las altas rechazadas | 4 pedidos | 4 pedidos |

Las once respuestas resultaron equivalentes al comparar los cuerpos JSON,
los códigos HTTP y Location. Para la comparación se normalizó el orden de las
listas y se omitió únicamente `fecha` en los cuerpos de error, que representa
el instante de cada petición. Los campos fecha de los pedidos se conservaron.

## Compilación y prueba de contexto

`mvnw.cmd test`: BUILD SUCCESS. Una prueba ejecutada, cero fallos, cero errores
y cero omisiones. Esta prueba comprueba la carga del contexto; los casos HTTP
anteriores se ejecutaron por separado y no forman parte del test de Maven.

La inspección del código confirmó una sola entrada `crear(PedidoCreate)`,
una sola consulta de usuario en `obtenerUsuario` y la carga inicial construida
con `PedidoCreate` y `DetallePedidoCreate`.
