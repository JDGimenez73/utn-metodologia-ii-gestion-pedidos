package com.utn.gestionpedidos.service;

import com.utn.gestionpedidos.dto.usuario.UsuarioCreate;
import com.utn.gestionpedidos.dto.usuario.UsuarioDto;
import com.utn.gestionpedidos.dto.usuario.UsuarioEdit;
import com.utn.gestionpedidos.entities.Usuario;
import com.utn.gestionpedidos.exception.RecursoDuplicadoException;
import com.utn.gestionpedidos.exception.RecursoNoEncontradoException;
import com.utn.gestionpedidos.repository.UsuarioRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDto crear(UsuarioCreate dto) {
        if (usuarioRepository.existsByMailIgnoreCase(dto.mail())) {
            throw new RecursoDuplicadoException("Ya existe un usuario con el correo: " + dto.mail());
        }
        Usuario usuario = Usuario.builder()
                .nombre(dto.nombre())
                .apellido(dto.apellido())
                .mail(dto.mail())
                .celular(dto.celular())
                .contrasena(dto.contrasena())
                .rol(dto.rol())
                .build();
        return convertirADto(usuarioRepository.save(usuario));
    }

    public List<UsuarioDto> listar() {
        return usuarioRepository.findAll().stream().map(this::convertirADto).toList();
    }

    public UsuarioDto buscarPorId(Long id) {
        UsuarioDto usuario = convertirADto(obtenerEntidad(id));
        System.out.println("Usuario encontrado por id: " + usuario);
        return usuario;
    }

    public UsuarioDto buscarPorMail(String mail) {
        UsuarioDto usuario = usuarioRepository.findByMailIgnoreCase(mail)
                .map(this::convertirADto)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario inexistente con correo: " + mail));
        System.out.println("Usuario encontrado por mail: " + usuario);
        return usuario;
    }

    public UsuarioDto actualizar(Long id, UsuarioEdit dto) {
        Usuario usuario = obtenerEntidad(id);
        usuarioRepository.findByMailIgnoreCase(dto.mail())
                .filter(otro -> !otro.getId().equals(id))
                .ifPresent(otro -> { throw new RecursoDuplicadoException("Ya existe un usuario con el correo: " + dto.mail()); });
        usuario.setNombre(dto.nombre());
        usuario.setApellido(dto.apellido());
        usuario.setMail(dto.mail());
        usuario.setCelular(dto.celular());
        usuario.setContrasena(dto.contrasena());
        usuario.setRol(dto.rol());
        return convertirADto(usuarioRepository.save(usuario));
    }

    public void eliminar(Long id) {
        usuarioRepository.delete(obtenerEntidad(id));
    }

    public long contar() {
        return usuarioRepository.count();
    }

    private Usuario obtenerEntidad(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario inexistente: " + id));
    }

    private UsuarioDto convertirADto(Usuario usuario) {
        return new UsuarioDto(
                usuario.getId(), usuario.getNombre(), usuario.getApellido(),
                usuario.getMail(), usuario.getCelular(), usuario.getRol()
        );
    }
}
