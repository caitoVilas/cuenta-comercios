package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.constants.ErrorMsg;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.RolDTO;
import com.comercios.cuentaComerciosBO.dto.RolNuevoDTO;
import com.comercios.cuentaComerciosBO.entity.Permiso;
import com.comercios.cuentaComerciosBO.entity.Rol;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.PermisoMapper;
import com.comercios.cuentaComerciosBO.mapper.RolMapper;
import com.comercios.cuentaComerciosBO.mapper.RolNuevoMapper;
import com.comercios.cuentaComerciosBO.repository.PermisoRepository;
import com.comercios.cuentaComerciosBO.repository.RolRepository;
import com.comercios.cuentaComerciosBO.service.contract.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    private static final Logger logger = LoggerFactory.getLogger(RolServiceImpl.class);
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private RolNuevoMapper rolNuevoMapper;
    @Autowired
    private RolMapper rolMapper;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private PermisoMapper permisoMapper;


    @Override
    public RolDTO createRol(RolNuevoDTO dto) {
        logger.info("inicio servicio alta de rol");
        if (rolRepository.existsByRolName(dto.getRolName())){
            logger.error(ErrorMsg.ROL_EXISTS);
            throw new BadRequestException(ErrorMsg.ROL_EXISTS);
        }
        logger.info("guardando rol...");
        return rolMapper.rolToRolDTO(rolRepository.save(rolNuevoMapper.rolNuevoDTOToRol(dto)));
    }

    @Override
    public RolDTO buscarPorId(Long rolId) {
        logger.info("inicio servicio busqueda de rol por id");
        Rol rol = rolRepository.findById(rolId).orElseThrow(()->{
            logger.error(ErrorMsg.ROL_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.ROL_EXISTS);
        });
        logger.info("buscando rol...");
        return rolMapper.rolToRolDTO(rol);
    }

    @Override
    public List<RolDTO> buscarTodos() {
        logger.info("iniciando servicio buscar roles");
        logger.info("buscando roles...");
        return rolMapper.rolListToRolDTOList(rolRepository.findAll());
    }

    @Override
    public PageableResponseDTO<RolDTO> buscarPaginado(int page, int size) {
        logger.info("inicio servivio roles paginado");
        if (page <= 0){
            logger.error(ErrorMsg.PAGE_GREATER_ZERO);
            throw new BadRequestException(ErrorMsg.PAGE_GREATER_ZERO);
        }
        logger.info("buscando roles...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Rol> r = rolRepository.findAll(pageable);
        List<RolDTO> roles = rolMapper.rolListToRolDTOList(r.getContent());
        PageableResponseDTO<RolDTO> response = new PageableResponseDTO<>();
        response.setPagina(r.getNumber() + 1);
        response.setResultados(r.getTotalElements());
        response.setTotalPaginas(r.getTotalPages());
        response.setContent(roles);
        return response;
    }

    @Override
    public void eliminarRol(Long rolId) {
        logger.info("inicio servicio eliminar rol");
        Rol rol = rolRepository.findById(rolId).orElseThrow(()->{
            logger.error(ErrorMsg.ROL_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.ROL_NOT_FOUND);
        });
        logger.info("eliminando rol...");
        rolRepository.deleteById(rolId);
    }

    @Override
    public Rol buscarPorNombre(String rolName) {
        return rolRepository.findByRolName(rolName);
    }

    @Override
    public RolDTO asignarPermisos(Long rolId, List<Long> permisos) {
        logger.info("inicio servicio asignar permisis a roles");
        Rol rol = rolRepository.findById(rolId).orElseThrow(()->{
            logger.error(ErrorMsg.ROL_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.ROL_NOT_FOUND);
        });
        List<Permiso> p = new ArrayList<>();
        permisos.stream().forEach(permiso ->{
            Permiso per = permisoRepository.findById(permiso).orElseThrow(()->{
                logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
                throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
            });
            p.add(per);
        });
        rol.setPermisos(p);
        return rolMapper.rolToRolDTO(rolRepository.save(rol));
    }
}
