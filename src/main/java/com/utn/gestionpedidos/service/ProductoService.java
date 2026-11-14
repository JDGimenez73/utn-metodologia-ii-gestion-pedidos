package com.utn.gestionpedidos.service;

import com.utn.gestionpedidos.dto.producto.ProductoCreate;
import com.utn.gestionpedidos.dto.producto.ProductoDto;
import com.utn.gestionpedidos.dto.producto.ProductoEdit;
import com.utn.gestionpedidos.entities.Categoria;
import com.utn.gestionpedidos.entities.Producto;
import com.utn.gestionpedidos.exception.RecursoNoEncontradoException;
import com.utn.gestionpedidos.repository.CategoriaRepository;
import com.utn.gestionpedidos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository,
                           CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public ProductoDto crear(ProductoCreate dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría inexistente: " + dto.categoriaId()));

        Producto producto = Producto.builder()
                .nombre(dto.nombre())
                .precio(dto.precio())
                .descripcion(dto.descripcion())
                .stock(dto.stock())
                .imagen(dto.imagen())
                .disponible(dto.disponible())
                .categoria(categoria)
                .build();

        return convertirADto(productoRepository.save(producto));
    }

    public List<ProductoDto> listar() {
        return productoRepository.findAll().stream().map(this::convertirADto).toList();
    }

    public ProductoDto buscarPorId(Long id) {
        return convertirADto(obtenerEntidad(id));
    }

    public ProductoDto actualizar(Long id, ProductoEdit dto) {
        Producto producto = obtenerEntidad(id);
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoría inexistente: " + dto.categoriaId()));
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setDescripcion(dto.descripcion());
        producto.setStock(dto.stock());
        producto.setImagen(dto.imagen());
        producto.setDisponible(dto.disponible());
        producto.setCategoria(categoria);
        return convertirADto(productoRepository.save(producto));
    }

    public void eliminar(Long id) {
        productoRepository.delete(obtenerEntidad(id));
    }

    public long contar() {
        return productoRepository.count();
    }

    private Producto obtenerEntidad(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto inexistente: " + id));
    }

    private ProductoDto convertirADto(Producto producto) {
        return new ProductoDto(
                producto.getId(), producto.getNombre(), producto.getPrecio(),
                producto.getDescripcion(), producto.getStock(), producto.getImagen(),
                producto.getDisponible(), producto.getCategoria().getId(),
                producto.getCategoria().getNombre()
        );
    }
}
