# Anexo de evidencias - Gestion de Pedidos

## 1. Identificacion

- Asignatura: Metodologia de Sistemas II
- Proyecto: Gestion de Pedidos
- Integrantes: Jose Dario Gimenez e Israel Garcia
- Fecha: 16/11/2026
- Rama de documentacion: israel/anexo-evidencias
- Base de integracion: 451dfdb

## 2. Refactorizaciones realizadas

| ID | Responsable | Descripcion | Commit | PR |
|---|---|---|---|---|
| R01 | Jose Dario Gimenez | Centralizar busqueda de usuarios | 2dfee8a | #3 |
| R02 | Jose Dario Gimenez | Unificar creacion de pedidos | 2dfee8a | #3 |
| R03 | Israel Garcia | Centralizar calculo de subtotales | 15175c5 | #5 |
| R04 | Israel Garcia | Extraer PedidoMapper | 15175c5 | #5 |

Las cuatro refactorizaciones fueron integradas a main.

## 3. Pruebas antes y despues

### 3.1 Pruebas base

Responsable: Israel Garcia
Commit: 8f13c02
Integracion: PR #2

Resultados registrados:

| Prueba | Operacion | HTTP |
|---|---|---|
| P01 | Listar pedidos | 200 |
| P02 | Consultar pedido existente | 200 |
| P03 | Crear pedido | 201 |
| P04 | Actualizar pedido | 200 |
| P05 | Consultar pedido inexistente | 404 |
| P06 | Crear pedido con cantidad cero | 400 |

Las evidencias se encuentran en:

    evidencias/israel/pruebas-base/

### 3.2 R01 y R02

Responsable: Jose Dario Gimenez
Commit: 2dfee8a
Integracion: PR #3

Evidencias:

    evidencias/dario/r01-r02/antes.json
    evidencias/dario/r01-r02/despues.json
    evidencias/dario/r01-r02/registro_pruebas.md

Resultados registrados:

- Creacion con usuario inexistente: HTTP 404.
- Actualizacion con usuario inexistente: HTTP 404.
- Creacion con producto inexistente: HTTP 404.
- Actualizacion rechazada sin modificar el pedido.
- Creacion rechazada sin insertar pedidos adicionales.
- Pruebas automatizadas de la revision: 1 aprobada.

La comparacion antes/despues se fundamenta en
las respuestas registradas y en la revision del codigo.

### 3.3 R03 y R04

Responsable: Israel Garcia
Commit: 15175c5
Integracion: PR #5
Commit de merge: 451dfdb

Cambios realizados:

- R03: eliminacion del calculo duplicado de subtotales
  en PedidoService, reutilizando DetallePedido.
- R04: extraccion de la conversion Pedido a PedidoDto
  hacia el componente PedidoMapper.

Pruebas automatizadas:

- Tests run: 5.
- Failures: 0.
- Errors: 0.
- Skipped: 0.
- BUILD SUCCESS.

Prueba funcional GET /api/pedidos:

- HTTP 200.
- Tres pedidos consultados.
- Cero diferencias entre totales y subtotales.

| Pedido | Total | Suma subtotales |
|---|---:|---:|
| 1 | 5300 | 5300 |
| 2 | 7000 | 7000 |
| 3 | 6600 | 6600 |

La prueba GET no identifica de manera concluyente
el commit ejecutado por el servidor.

La prueba POST realizada durante R03 obtuvo un
total de 3600, pero precede a la finalizacion de R04.

Documentacion detallada:

    evidencias/israel/r03-r04/registro_refactorizacion.md
    evidencias/israel/r03-r04/registro_pruebas.md

## 4. Revisiones cruzadas

### 4.1 Revision de R01/R02

Autor: Jose Dario Gimenez
Revisor: Israel Garcia
Commit revisado: 2dfee8a
PR revisado: #3
Commit de documentacion: fcf6bf9
Integracion de la revision: PR #4

Resultado:

La revision estatica y las pruebas ejecutadas
no identificaron regresiones funcionales en
los escenarios evaluados.

Documento:

    evidencias/israel/revision-r01-r02/revision_tecnica.md

### 4.2 Revision de R03/R04

Autor: Israel Garcia
Revisor solicitado: Jose Dario Gimenez
Commit revisado: 15175c5
PR: #5
Commit de integracion: 451dfdb

Resultado confirmado:

El PR #5 fue integrado a main.

La integracion confirma el merge, pero por si sola
no documenta el alcance de una revision tecnica
independiente.

Queda pendiente incorporar el detalle de la
revision de Dario si existe un registro verificable.

## 5. Conclusion individual - Israel Garcia

Mi participacion en el proyecto incluyo la ejecucion
y documentacion de pruebas funcionales sobre la
version base, la revision cruzada de las
refactorizaciones R01/R02 y la implementacion
de R03/R04.

En R03 elimine la duplicacion del calculo de
subtotales, reutilizando la responsabilidad
existente en DetallePedido.

En R04 separe la conversion de entidades a DTO
mediante PedidoMapper, reduciendo las
responsabilidades concentradas en PedidoService.

Las pruebas automatizadas finalizaron
satisfactoriamente y las verificaciones
funcionales disponibles no mostraron diferencias
en los totales evaluados.

El trabajo permitio aplicar criterios de
mantenibilidad, responsabilidad unica,
pruebas de regresion y revision colaborativa.

Considero que las modificaciones mejoraron la
organizacion interna del codigo sin evidenciar
regresiones en los escenarios comprobados.

## 6. Limitaciones pendientes

- No se confirmo el commit exacto ejecutado por
  el servidor utilizado para la ultima prueba GET.
- Falta una prueba POST documentada posterior a R04.
- Las pruebas automatizadas no cubren todos los
  escenarios posibles.
- La base H2 en memoria puede reiniciarse al
  detener la aplicacion.
- El puerto 8081 se utilizo porque el 8080
  estaba ocupado.
- Se observaron advertencias no bloqueantes
  de Mockito y Byte Buddy.
- Falta incorporar el alcance documentado de
  la revision cruzada de Dario sobre R03/R04,
  si se encuentra disponible.

## 7. Estado final

Las refactorizaciones R01, R02, R03 y R04
se encuentran integradas en main.

El presente anexo consolida las evidencias
disponibles y explicita sus limitaciones.

No reemplaza el informe final del equipo.