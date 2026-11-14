package com.utn.gestionpedidos.repository;

import com.utn.gestionpedidos.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByMailIgnoreCase(String mail);
    boolean existsByMailIgnoreCase(String mail);
}
