package com.comercios.cuentaComercios.service.impl;

import com.comercios.cuentaComercios.dto.RolDTO;
import com.comercios.cuentaComercios.entity.Rol;
import com.comercios.cuentaComercios.exception.NotFoundException;
import com.comercios.cuentaComercios.mapper.RolMapper;
import com.comercios.cuentaComercios.repository.RolRepository;
import com.comercios.cuentaComercios.service.contract.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    private static final Logger logger = LoggerFactory.getLogger(RolServiceImpl.class);

    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private RolMapper rolMapper;


    @Override
    public Rol buscarPorNombre(String roleName) {
        logger.info("inicio servicio buscar rol por nombre");
        logger.info("buscando rol...");
        Rol rol = rolRepository.findByRolName(roleName);
        if (rol == null){
            logger.error("el rol no se encuentra");
            throw new NotFoundException("el rol no se encuentra");
        }
        return rol;
    }

    @Override
    public List<RolDTO> verTodos() {
        logger.info("inicio servio buscar roles");
        logger.info("buscando roles...");
        return rolMapper.rolListToRolDTOList(rolRepository.findAll());
    }
}
