package com.utn.gestionpedidos.controller;

import com.utn.gestionpedidos.dto.categoria.CategoriaCreate;
import com.utn.gestionpedidos.dto.categoria.CategoriaDto;
import com.utn.gestionpedidos.dto.categoria.CategoriaEdit;
import com.utn.gestionpedidos.dto.categoria.CategoriaPatch;
import com.utn.gestionpedidos.service.CategoriaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorías", description = "Gestión de categorías de productos")
public class CategoriaController {
    private final CategoriaService service;

    public CategoriaController(CategoriaService service) { this.service = service; }

    @GetMapping
    public List<CategoriaDto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public CategoriaDto buscar(@PathVariable Long id) { return service.buscarPorId(id); }

    @PostMapping
    public ResponseEntity<CategoriaDto> crear(@Valid @RequestBody CategoriaCreate dto) {
        CategoriaDto creada = service.crear(dto);
        return ResponseEntity.created(URI.create("/api/categorias/" + creada.id())).body(creada);
    }

    @PutMapping("/{id}")
    public CategoriaDto actualizar(@PathVariable Long id, @Valid @RequestBody CategoriaEdit dto) {
        return service.actualizar(id, dto);
    }

    @PatchMapping("/{id}")
    public CategoriaDto actualizarParcial(@PathVariable Long id, @RequestBody CategoriaPatch dto) {
        return service.actualizarParcial(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
