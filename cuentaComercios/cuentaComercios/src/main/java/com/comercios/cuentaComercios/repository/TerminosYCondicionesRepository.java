package com.comercios.cuentaComercios.repository;

import com.comercios.cuentaComercios.entity.TerminosYCondiciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminosYCondicionesRepository extends JpaRepository<TerminosYCondiciones, Long> {
}
