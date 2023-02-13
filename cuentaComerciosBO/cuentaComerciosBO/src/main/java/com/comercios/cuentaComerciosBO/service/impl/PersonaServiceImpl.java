package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.repository.PersonaRepository;
import com.comercios.cuentaComerciosBO.service.contract.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;


    private static final Logger logger = LoggerFactory.getLogger(PersonaServiceImpl.class);


    @Override
    public boolean existeXDni(String dni) {
        return personaRepository.existsByDni(dni);
    }

    @Override
    public boolean existeXEmail(String email) {
        return personaRepository.existsByEmail(email);
    }
}
