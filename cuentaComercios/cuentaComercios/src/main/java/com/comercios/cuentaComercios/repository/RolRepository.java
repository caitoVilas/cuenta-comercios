package com.comercios.cuentaComercios.repository;

import com.comercios.cuentaComercios.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByRolName(String rolName);
}
