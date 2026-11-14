package com.utn.gestionpedidos.controller;

import com.utn.gestionpedidos.dto.producto.ProductoCreate;
import com.utn.gestionpedidos.dto.producto.ProductoDto;
import com.utn.gestionpedidos.dto.producto.ProductoEdit;
import com.utn.gestionpedidos.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Gestión del catálogo de productos")
public class ProductoController {
    private final ProductoService service;

    public ProductoController(ProductoService service) { this.service = service; }

    @GetMapping
    public List<ProductoDto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public ProductoDto buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<ProductoDto> crear(@Valid @RequestBody ProductoCreate dto) {
        ProductoDto creado = service.crear(dto);
        return ResponseEntity.created(URI.create("/api/productos/" + creado.id())).body(creado);
    }

    @PutMapping("/{id}")
    public ProductoDto actualizar(@PathVariable Long id, @Valid @RequestBody ProductoEdit dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
