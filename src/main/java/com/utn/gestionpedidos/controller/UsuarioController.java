package com.utn.gestionpedidos.controller;

import com.utn.gestionpedidos.dto.usuario.UsuarioCreate;
import com.utn.gestionpedidos.dto.usuario.UsuarioDto;
import com.utn.gestionpedidos.dto.usuario.UsuarioEdit;
import com.utn.gestionpedidos.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Gestión y búsqueda de usuarios")
public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(UsuarioService service) { this.service = service; }

    @GetMapping
    public List<UsuarioDto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public UsuarioDto buscarPorId(@PathVariable Long id) { return service.buscarPorId(id); }

    @GetMapping("/buscar")
    public UsuarioDto buscarPorMail(@RequestParam String mail) { return service.buscarPorMail(mail); }

    @PostMapping
    public ResponseEntity<UsuarioDto> crear(@Valid @RequestBody UsuarioCreate dto) {
        UsuarioDto creado = service.crear(dto);
        return ResponseEntity.created(URI.create("/api/usuarios/" + creado.id())).body(creado);
    }

    @PutMapping("/{id}")
    public UsuarioDto actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioEdit dto) {
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
