package com.utn.gestionpedidos.controller;

import com.utn.gestionpedidos.service.CategoriaService;
import com.utn.gestionpedidos.service.PedidoService;
import com.utn.gestionpedidos.service.ProductoService;
import com.utn.gestionpedidos.service.UsuarioService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SistemaController {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    public SistemaController(CategoriaService categoriaService,
                             ProductoService productoService,
                             UsuarioService usuarioService,
                             PedidoService pedidoService) {
        this.categoriaService = categoriaService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/resumen")
    public Map<String, Long> resumen() {
        return Map.of(
                "usuarios", usuarioService.contar(),
                "categorias", categoriaService.contar(),
                "productos", productoService.contar(),
                "pedidos", pedidoService.contar()
        );
    }

}
