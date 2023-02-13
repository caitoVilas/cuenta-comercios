package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.SucursalDeRadicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalDeRadicacionRepository extends JpaRepository<SucursalDeRadicacion, Long> {
    SucursalDeRadicacion findByCodigoSucursal(Integer cogigoSucursal);
    boolean existsByCodigoSucursal(Integer codigoSucursal);
}
