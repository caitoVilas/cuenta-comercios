package com.comercios.cuentaComercios.repository;

import com.comercios.cuentaComercios.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
}
