package com.utn.gestionpedidos.config;

import com.utn.gestionpedidos.dto.categoria.CategoriaCreate;
import com.utn.gestionpedidos.dto.categoria.CategoriaDto;
import com.utn.gestionpedidos.dto.detallePedido.DetallePedidoDto;
import com.utn.gestionpedidos.dto.pedido.PedidoDto;
import com.utn.gestionpedidos.dto.producto.ProductoCreate;
import com.utn.gestionpedidos.dto.producto.ProductoDto;
import com.utn.gestionpedidos.dto.usuario.UsuarioCreate;
import com.utn.gestionpedidos.dto.usuario.UsuarioDto;
import com.utn.gestionpedidos.enums.Estado;
import com.utn.gestionpedidos.enums.FormaPago;
import com.utn.gestionpedidos.enums.Rol;
import com.utn.gestionpedidos.service.CategoriaService;
import com.utn.gestionpedidos.service.PedidoService;
import com.utn.gestionpedidos.service.ProductoService;
import com.utn.gestionpedidos.service.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicialDatos implements CommandLineRunner {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;
    private final boolean cargaInicialHabilitada;

    public CargaInicialDatos(CategoriaService categoriaService,
                             ProductoService productoService,
                             UsuarioService usuarioService,
                             PedidoService pedidoService,
                             @Value("${app.carga-inicial:true}") boolean cargaInicialHabilitada) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
        this.cargaInicialHabilitada = cargaInicialHabilitada;
    }

    @Override
    public void run(String... args) {
        if (!cargaInicialHabilitada || categoriaService.contar() > 0) {
            return;
        }

        CategoriaDto bebidas = categoriaService.crear(
                new CategoriaCreate("Bebidas", "Bebidas frías y calientes"));
        CategoriaDto almacen = categoriaService.crear(
                new CategoriaCreate("Almacén", "Productos de almacén"));
        CategoriaDto limpieza = categoriaService.crear(
                new CategoriaCreate("Limpieza", "Productos de higiene y limpieza"));

        List<ProductoDto> productos = List.of(
                productoService.crear(new ProductoCreate("Agua Mineral", 900.0, "Botella 1.5 L", 50, "agua.jpg", true, bebidas.id())),
                productoService.crear(new ProductoCreate("Gaseosa Cola", 1800.0, "Botella 2.25 L", 30, "cola.jpg", true, bebidas.id())),
                productoService.crear(new ProductoCreate("Café Molido", 3500.0, "Paquete 500 g", 20, "cafe.jpg", true, almacen.id())),
                productoService.crear(new ProductoCreate("Yerba Mate", 2800.0, "Paquete 1 kg", 40, "yerba.jpg", true, almacen.id())),
                productoService.crear(new ProductoCreate("Arroz", 1600.0, "Bolsa 1 kg", 35, "arroz.jpg", true, almacen.id())),
                productoService.crear(new ProductoCreate("Fideos", 1200.0, "Paquete 500 g", 45, "fideos.jpg", true, almacen.id())),
                productoService.crear(new ProductoCreate("Lavandina", 1400.0, "Botella 1 L", 25, "lavandina.jpg", true, limpieza.id())),
                productoService.crear(new ProductoCreate("Detergente", 1100.0, "Botella 750 ml", 28, "detergente.jpg", true, limpieza.id())),
                productoService.crear(new ProductoCreate("Jabón Líquido", 2200.0, "Envase 800 ml", 18, "jabon.jpg", true, limpieza.id())),
                productoService.crear(new ProductoCreate("Servilletas", 950.0, "Paquete por 100", 60, "servilletas.jpg", true, limpieza.id()))
        );

        UsuarioDto usuario = usuarioService.crear(new UsuarioCreate(
                "Guadalupe", "Contreras", "guada@mail.com", "1122334455", "1234", Rol.USUARIO));
        UsuarioDto administrador = usuarioService.crear(new UsuarioCreate(
                "José", "Giménez", "jgimenez@mail.com", "1166778899", "abcd", Rol.ADMIN));

        pedidoService.crear(nuevoPedido(Estado.CONFIRMADO, FormaPago.EFECTIVO, usuario.id(),
                detalle(2, productos.get(0)), detalle(1, productos.get(2))));
        pedidoService.crear(nuevoPedido(Estado.PENDIENTE, FormaPago.TARJETA, usuario.id(),
                detalle(3, productos.get(4)), detalle(2, productos.get(7))));
        pedidoService.crear(nuevoPedido(Estado.TERMINADO, FormaPago.TRANSFERENCIA, administrador.id(),
                detalle(1, productos.get(3)), detalle(4, productos.get(9))));

        System.out.println("Carga inicial completada: 2 usuarios, 3 categorías, 10 productos y 3 pedidos.");
    }

    private PedidoDto nuevoPedido(Estado estado, FormaPago formaPago, Long usuarioId,
                                   DetallePedidoDto... detalles) {
        return new PedidoDto(null, LocalDate.now(), estado, null, formaPago,
                usuarioId, List.of(detalles));
    }

    private DetallePedidoDto detalle(int cantidad, ProductoDto producto) {
        return new DetallePedidoDto(null, cantidad, null, producto.id(), producto.nombre());
    }
}
