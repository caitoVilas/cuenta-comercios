package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.entity.TerminosYCondiciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminosYCondicionesRepository extends JpaRepository<TerminosYCondiciones, Long> {
}
