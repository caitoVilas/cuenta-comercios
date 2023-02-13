package com.comercios.cuentaComercios.repository;

import com.comercios.cuentaComercios.entity.Documentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentacionRepository extends JpaRepository<Documentacion, Long> {
}
