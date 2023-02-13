package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.UsuarioBO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioBO, Long> {
    Optional<UsuarioBO> findByUsername(String username);
    boolean existsByUsername(String username);
}
