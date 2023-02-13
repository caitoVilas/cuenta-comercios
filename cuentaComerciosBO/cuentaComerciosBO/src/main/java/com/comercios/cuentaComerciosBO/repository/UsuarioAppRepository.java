package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAppRepository extends JpaRepository<Usuario, Long> {
}
