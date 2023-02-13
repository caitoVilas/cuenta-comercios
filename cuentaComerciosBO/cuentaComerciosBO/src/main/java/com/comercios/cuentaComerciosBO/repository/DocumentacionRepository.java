package com.comercios.cuentaComerciosBO.repository;

import com.comercios.cuentaComerciosBO.dto.DocumentacionDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import com.comercios.cuentaComerciosBO.entity.Documentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentacionRepository extends JpaRepository<Documentacion, Long> {
    List<Documentacion> findByComercio(Comercio comercio);
}
