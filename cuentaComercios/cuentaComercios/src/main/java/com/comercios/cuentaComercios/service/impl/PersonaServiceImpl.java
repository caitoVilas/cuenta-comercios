package com.comercios.cuentaComercios.service.impl;

import com.comercios.cuentaComercios.repository.PersonaRepository;
import com.comercios.cuentaComercios.service.contract.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    private static final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);
    @Autowired
    private PersonaRepository personaRepository;


    @Override
    public boolean existePorDni(String dni) {
        logger.info("iniciando servicio si existe persona por dni");
        return personaRepository.existsByDni(dni);
    }

    @Override
    public boolean existePorMail(String email) {
        logger.info("iniciando servicio si existe por email");
        return personaRepository.existsByEmail(email);
    }
}
