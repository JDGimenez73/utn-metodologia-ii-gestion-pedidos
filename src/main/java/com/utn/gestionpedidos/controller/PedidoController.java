package com.utn.gestionpedidos.controller;

import com.utn.gestionpedidos.dto.pedido.PedidoCreate;
import com.utn.gestionpedidos.dto.pedido.PedidoDto;
import com.utn.gestionpedidos.dto.pedido.PedidoEdit;
import com.utn.gestionpedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Gestión de pedidos y sus detalles")
public class PedidoController {
    private final PedidoService service;

    public PedidoController(PedidoService service) { this.service = service; }

    @GetMapping
    public List<PedidoDto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public PedidoDto buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<PedidoDto> crear(@Valid @RequestBody PedidoCreate dto) {
        PedidoDto creado = service.crear(dto);
        return ResponseEntity.created(URI.create("/api/pedidos/" + creado.id())).body(creado);
    }

    @PutMapping("/{id}")
    public PedidoDto actualizar(@PathVariable Long id, @Valid @RequestBody PedidoEdit dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
