# Registro de pruebas R03 y R04

## Identificacion

- Proyecto: Gestion de Pedidos - Metodologia de Sistemas II
- Responsable: Israel Garcia
- Fecha: 16/11/2026
- Commit de refactorizacion: 15175c5
- Commit de integracion: 451dfdb
- Pull Request: #5
- Rama de documentacion: israel/anexo-evidencias

## Objetivo

Verificar el comportamiento del calculo de subtotales
y de la conversion de pedidos a DTO despues de las
refactorizaciones R03 y R04.

## Pruebas automatizadas

Comando ejecutado:

    .\mvnw.cmd clean test

Resultados observados:

- Tests run: 5
- Failures: 0
- Errors: 0
- Skipped: 0
- BUILD SUCCESS

Distribucion:

- DetallePedidoTest: 3 pruebas.
- PedidoMapperTest: 1 prueba.
- GestionpedidosApplicationTests: 1 prueba.

## Prueba funcional GET

Metodo: GET
Ruta: /api/pedidos
Puerto: 8081

Resultado observado:

- HTTP: 200
- Pedidos encontrados: 3
- Pedidos con diferencias entre total y subtotales: 0

| Pedido | Total | Suma de subtotales | Resultado |
|---|---:|---:|---|
| 1 | 5300 | 5300 | OK |
| 2 | 7000 | 7000 | OK |
| 3 | 6600 | 6600 | OK |

## Verificacion del proceso

Se identifico un proceso java.exe escuchando
en el puerto 8081.

- PID observado: 13592
- Java: Eclipse Adoptium JDK 21
- Clase principal: GestionpedidosApplication
- Argumento: --server.port=8081

No se confirmo el commit exacto correspondiente
a la instancia Java que respondio la consulta.

Por lo tanto, esta prueba confirma consistencia
funcional de la instancia consultada, pero no
demuestra por si sola la version desplegada.

## Prueba de creacion realizada durante R03

Se realizo una solicitud POST con dos detalles.

Resultados observados:

- Subtotal del primer detalle: 1800
- Subtotal del segundo detalle: 1800
- Total: 3600

La prueba fue realizada antes de completar R04.

No se presenta como prueba funcional posterior
a la integracion de ambas refactorizaciones.

## Comparacion antes y despues

### Antes de R03

PedidoService calculaba directamente el subtotal
y DetallePedido tambien contenia esa responsabilidad.

### Despues de R03

PedidoService utiliza el constructor de DetallePedido
para calcular el subtotal.

### Antes de R04

PedidoService realizaba internamente la conversion
de Pedido a PedidoDto.

### Despues de R04

La conversion se delega al componente PedidoMapper.

Las pruebas automatizadas finalizaron correctamente
despues de aplicar ambas refactorizaciones.

## Limitaciones

- No se confirmo el commit ejecutado por el servidor 8081.
- Israel no registro POST posterior a R04 en su ronda original; Dario ejecuto P03 posteriormente sobre 15175c5.
- La consulta GET no cubre todos los casos de error.
- Las pruebas automatizadas cubren escenarios seleccionados,
  no todas las combinaciones posibles de datos.
- Las advertencias de Mockito y Byte Buddy no bloquearon
  la ejecucion de las pruebas.

## Conclusion

Las cinco pruebas automatizadas resultaron satisfactorias.

La consulta funcional GET devolvio tres pedidos con
totales consistentes respecto de sus subtotales.

No se identificaron diferencias en los escenarios
evaluados, dentro de las limitaciones documentadas.
## Verificaciones complementarias de Dario

Durante la revision formal del PR #5,
Jose Dario Gimenez verifico el commit 15175c5
en una instancia nueva, puerto 18085.

Resultados informados:

- Cinco pruebas Maven satisfactorias.
- Once verificaciones HTTP correctas.
- P03 (POST) ejecutada despues de R04.
- Aprobacion formal del PR #5.
- Integracion mediante 451dfdb.

Evidencia:

https://github.com/JDGimenez73/utn-metodologia-ii-gestion-pedidos/pull/5#pullrequestreview-5924384850

Estas pruebas fueron realizadas por Dario,
no por Israel.

La instancia original de Israel en el puerto 8081
no quedo vinculada a un commit exacto.

La cobertura continua limitada a los escenarios
efectivamente verificados.