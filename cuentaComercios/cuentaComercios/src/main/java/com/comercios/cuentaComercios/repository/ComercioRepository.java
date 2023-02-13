package com.comercios.cuentaComercios.repository;

import com.comercios.cuentaComercios.entity.Comercio;
import com.comercios.cuentaComercios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {
    boolean existsByEmail(String email);
    boolean existsByCuit(String cuit);
    boolean existsByUsuario(Usuario usuario);
}
