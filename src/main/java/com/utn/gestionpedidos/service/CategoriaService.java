package com.utn.gestionpedidos.service;

import com.utn.gestionpedidos.dto.categoria.CategoriaCreate;
import com.utn.gestionpedidos.dto.categoria.CategoriaDto;
import com.utn.gestionpedidos.dto.categoria.CategoriaEdit;
import com.utn.gestionpedidos.dto.categoria.CategoriaPatch;
import com.utn.gestionpedidos.entities.Categoria;
import com.utn.gestionpedidos.exception.RecursoNoEncontradoException;
import com.utn.gestionpedidos.repository.CategoriaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDto crear(CategoriaCreate dto) {
        Categoria categoria = Categoria.builder()
                .nombre(dto.nombre())
                .descripcion(dto.descripcion())
                .build();
        return convertirADto(categoriaRepository.save(categoria));
    }

    public List<CategoriaDto> listar() {
        return categoriaRepository.findAll().stream().map(this::convertirADto).toList();
    }

    public CategoriaDto buscarPorId(Long id) {
        return convertirADto(obtenerEntidad(id));
    }

    public CategoriaDto actualizar(Long id, CategoriaEdit dto) {
        Categoria categoria = obtenerEntidad(id);
        categoria.setNombre(dto.nombre());
        categoria.setDescripcion(dto.descripcion());
        return convertirADto(categoriaRepository.save(categoria));
    }

    public CategoriaDto actualizarParcial(Long id, CategoriaPatch dto) {
        Categoria categoria = obtenerEntidad(id);
        if (dto.nombre() != null && !dto.nombre().isBlank()) {
            categoria.setNombre(dto.nombre());
        }
        if (dto.descripcion() != null && !dto.descripcion().isBlank()) {
            categoria.setDescripcion(dto.descripcion());
        }
        return convertirADto(categoriaRepository.save(categoria));
    }

    public void eliminar(Long id) {
        categoriaRepository.delete(obtenerEntidad(id));
    }

    public long contar() {
        return categoriaRepository.count();
    }

    private Categoria obtenerEntidad(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría inexistente: " + id));
    }

    private CategoriaDto convertirADto(Categoria categoria) {
        return new CategoriaDto(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }
}
