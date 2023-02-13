package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {
    boolean existsByDescripcion(String descripcion);
}
