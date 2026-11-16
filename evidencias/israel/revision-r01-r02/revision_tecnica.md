# Revision tecnica de R01 y R02

## Identificacion

- Proyecto: Gestion de Pedidos - Metodologia de Sistemas II
- Revisor: Israel Garcia
- Autor de los cambios revisados: Jose Dario Gimenez
- Pull Request revisado: #3
- Rama evaluada: dario/r01-r02
- Commit evaluado: 2dfee8a
- Fecha de revision: 16/11/2026

## Objetivo

Verificar que las refactorizaciones R01 y R02 mantengan el
comportamiento funcional del sistema, sin introducir regresiones.

- R01: centralizacion de la busqueda de usuarios.
- R02: unificacion del metodo de creacion de pedidos utilizado
  por la API y la carga inicial de datos.

## Revision estatica

Se revisaron los cambios en PedidoService y CargaInicialDatos.

Se verifico la extraccion del metodo obtenerUsuario(Long id)
y su reutilizacion en los flujos de creacion y actualizacion.

Se verifico la eliminacion del metodo de creacion duplicado
y el uso de crear(PedidoCreate) en la API y la carga inicial.

## Pruebas automatizadas

Comando ejecutado: .\mvnw.cmd test

Resultado:
- Tests run: 1
- Failures: 0
- Errors: 0
- Skipped: 0
- BUILD SUCCESS

## Pruebas funcionales

Aplicacion ejecutada localmente en http://localhost:8081.

| Prueba | Verificacion | Resultado |
|---|---|---|
| P01 | GET /api/pedidos | OK - HTTP 200 |
| P02 | GET /api/pedidos/1 | OK - HTTP 200 |
| P03 | POST /api/pedidos | OK - HTTP 201 |
| P04 | PUT /api/pedidos/4 | OK - HTTP 200 |
| P05 | GET /api/pedidos/999999 | OK - HTTP 404 |
| P06 | POST con cantidad 0 | OK - HTTP 400 |

## Verificaciones especificas de R01

1. Creacion con usuario inexistente: HTTP 404.
2. Actualizacion con usuario inexistente: HTTP 404.
3. Se comprobo que la actualizacion rechazada no altero
   el pedido 4.

Estado conservado del pedido 4:
- Estado: CONFIRMADO
- Usuario: 1
- Total: 5300
- Subtotales: 1800 y 3500

## Verificaciones especificas de R02

1. Creacion con producto inexistente: HTTP 404.
2. Se verifico que el listado conserva cuatro pedidos:
   - Pedido 1: total 5300.
   - Pedido 2: total 7000.
   - Pedido 3: total 6600.
   - Pedido 4: total 5300.
3. No se observo la creacion de un pedido adicional
   luego de la solicitud rechazada.

## Observaciones

- Las seis pruebas funcionales resultaron satisfactorias.
- Las verificaciones especificas de R01 y R02 resultaron
  satisfactorias.
- Las pruebas Maven ejecutadas finalizaron correctamente.
- La salida de PowerShell presento caracteres acentuados
  incorrectamente en algunos nombres de productos.
  No se determino aun si el problema corresponde a la
  codificacion de la respuesta o a su visualizacion.
- Las pruebas manuales se realizaron sobre el commit
  2dfee8a, no sobre la rama main.

## Conclusion

La revision estatica y las pruebas ejecutadas no
identificaron regresiones funcionales en los escenarios
evaluados de R01 y R02.

Se considera favorable la revision tecnica de los cambios
evaluados, con el alcance y las limitaciones documentados.

Esta conclusion no reemplaza las verificaciones finales
de integracion ni implica que el PR haya sido fusionado.
