package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    boolean existsByRolName(String rolName);
    Rol findByRolName(String rolName);
}
