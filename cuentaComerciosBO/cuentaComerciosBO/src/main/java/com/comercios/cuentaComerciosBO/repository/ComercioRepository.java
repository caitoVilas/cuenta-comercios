package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.Comercio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long> {
}
