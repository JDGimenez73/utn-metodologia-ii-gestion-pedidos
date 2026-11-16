# Registro de refactorizacion R03 y R04

## Identificacion

- Proyecto: Gestion de Pedidos - Metodologia de Sistemas II
- Responsable: Israel Garcia
- Fecha: 16/11/2026
- Rama: israel/r03-r04
- Commit: 15175c5
- Pull Request: #5
- Revisor solicitado: Jose Dario Gimenez
- Estado de revision: pendiente

## Objetivo

Mejorar la mantenibilidad del modulo de pedidos mediante
la eliminacion de codigo duplicado y la separacion de
responsabilidades, preservando el comportamiento esperado
de la aplicacion.

## R03 - Centralizacion del calculo de subtotales

### Situacion anterior

El calculo del subtotal de cada detalle se encontraba
implementado en dos lugares:

- En el constructor de DetallePedido.
- En el metodo de creacion de PedidoService.

Esto generaba duplicacion de logica y aumentaba el riesgo
de inconsistencias ante modificaciones futuras.

### Refactorizacion aplicada

Se modifico PedidoService para utilizar el constructor
existente de DetallePedido:

    new DetallePedido(null, item.cantidad(), producto)

El constructor calcula el subtotal utilizando la cantidad
y el precio del producto.

Se elimino el calculo duplicado que anteriormente se
realizaba mediante el builder dentro del servicio.

### Resultado

El calculo del subtotal queda centralizado en DetallePedido
para el flujo de creacion de pedidos evaluado.

El total del pedido continua calculandose a partir de
los subtotales de sus detalles.

## R04 - Extraccion de PedidoMapper

### Situacion anterior

PedidoService contenia el metodo privado convertirADto,
responsable de transformar entidades Pedido en PedidoDto.

El servicio combinaba operaciones de negocio y persistencia
con la preparacion de objetos de respuesta.

### Refactorizacion aplicada

Se creo la clase PedidoMapper dentro del paquete:

    com.utn.gestionpedidos.mapper

La clase se registro como componente de Spring mediante
la anotacion @Component.

Se incorporo PedidoMapper a PedidoService mediante
inyeccion por constructor.

Las operaciones de creacion, listado, busqueda y
actualizacion delegan la conversion al mapper.

Se elimino el metodo privado convertirADto del servicio.

### Resultado

La responsabilidad de convertir Pedido a PedidoDto queda
separada de la logica del servicio.

Se conservaron los campos de respuesta del DTO y
la conversion de los detalles del pedido.

## Archivos involucrados

- src/main/java/com/utn/gestionpedidos/service/PedidoService.java
- src/main/java/com/utn/gestionpedidos/mapper/PedidoMapper.java
- src/test/java/com/utn/gestionpedidos/entities/DetallePedidoTest.java
- src/test/java/com/utn/gestionpedidos/mapper/PedidoMapperTest.java

## Verificacion automatizada

Comando ejecutado:

    .\mvnw.cmd clean test

Resultado observado:

- Tests run: 5
- Failures: 0
- Errors: 0
- Skipped: 0
- BUILD SUCCESS

Las pruebas incluyen:

- Tres pruebas unitarias del calculo de subtotales.
- Una prueba unitaria de PedidoMapper.
- Una prueba de carga del contexto de Spring Boot.

## Verificacion funcional

Durante la implementacion de R03 se verificaron
consultas GET y la creacion POST de pedidos.

Se comprobaron los totales de los tres pedidos iniciales.

Tambien se creo un pedido de prueba con dos detalles
de subtotal 1800 cada uno y total 3600.

Estas comprobaciones se realizaron antes de completar
R04. Por lo tanto, no constituyen una prueba funcional
posterior de ambas refactorizaciones integradas.

## Control de versiones

Los cambios se registraron en el commit:

    15175c5

Mensaje:

    refactor(pedidos): centralizar subtotales y extraer mapper

Se publico la rama israel/r03-r04 en GitHub.

Pull Request:

https://github.com/JDGimenez73/utn-metodologia-ii-gestion-pedidos/pull/5

Se solicito la revision de Jose Dario Gimenez.

## Observaciones

- Las cinco pruebas automatizadas finalizaron correctamente.
- No se registraron fallos ni errores en Maven.
- Se observaron advertencias no bloqueantes de Mockito
  y Byte Buddy durante las pruebas.
- No se realizo todavia la integracion del PR #5 a main.
- La aprobacion del revisor permanece pendiente.

## Conclusion

Las refactorizaciones R03 y R04 reducen la duplicacion
de codigo y mejoran la separacion de responsabilidades.

Las pruebas automatizadas ejecutadas no identificaron
regresiones en los escenarios evaluados.

La validacion funcional completa posterior a R04 y la
revision cruzada quedan pendientes de cierre.

Este registro no implica la aprobacion ni la fusion
del Pull Request #5.