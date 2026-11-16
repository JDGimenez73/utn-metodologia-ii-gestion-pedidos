package com.utn.gestionpedidos.service;

import com.utn.gestionpedidos.mapper.PedidoMapper;
import com.utn.gestionpedidos.dto.pedido.PedidoDto;
import com.utn.gestionpedidos.dto.pedido.PedidoCreate;
import com.utn.gestionpedidos.dto.pedido.PedidoEdit;
import com.utn.gestionpedidos.entities.DetallePedido;
import com.utn.gestionpedidos.entities.Pedido;
import com.utn.gestionpedidos.entities.Producto;
import com.utn.gestionpedidos.entities.Usuario;
import com.utn.gestionpedidos.exception.RecursoNoEncontradoException;
import com.utn.gestionpedidos.repository.PedidoRepository;
import com.utn.gestionpedidos.repository.ProductoRepository;
import com.utn.gestionpedidos.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoService(PedidoRepository pedidoRepository,
                         UsuarioRepository usuarioRepository,
                         ProductoRepository productoRepository,
                         PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.productoRepository = productoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    @Transactional
    public PedidoDto crear(PedidoCreate dto) {
        Usuario usuario = obtenerUsuario(dto.usuarioId());
        Pedido pedido = Pedido.builder()
                .fecha(dto.fecha()).estado(dto.estado()).formaPago(dto.formaPago())
                .total(0.0).usuario(usuario).build();
        dto.detalles().forEach(item -> {
            Producto producto = productoRepository.findById(item.productoId())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Producto inexistente: " + item.productoId()));
            pedido.addDetallePedido(new DetallePedido(null, item.cantidad(), producto));
        });
        return pedidoMapper.convertirADto(pedidoRepository.save(pedido));
    }

    @Transactional(readOnly = true)
    public List<PedidoDto> listar() {
        return pedidoRepository.findAll().stream().map(pedidoMapper::convertirADto).toList();
    }

    @Transactional(readOnly = true)
    public PedidoDto buscarPorId(Long id) {
        return pedidoMapper.convertirADto(obtenerEntidad(id));
    }

    @Transactional
    public PedidoDto actualizar(Long id, PedidoEdit dto) {
        Pedido pedido = obtenerEntidad(id);
        Usuario usuario = obtenerUsuario(dto.usuarioId());
        pedido.setFecha(dto.fecha());
        pedido.setEstado(dto.estado());
        pedido.setFormaPago(dto.formaPago());
        pedido.setUsuario(usuario);
        return pedidoMapper.convertirADto(pedidoRepository.save(pedido));
    }

    public void eliminar(Long id) {
        pedidoRepository.delete(obtenerEntidad(id));
    }

    public long contar() {
        return pedidoRepository.count();
    }

    private Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario inexistente: " + id));
    }

    private Pedido obtenerEntidad(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pedido inexistente: " + id));
    }

}
