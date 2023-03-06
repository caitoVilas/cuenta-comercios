package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.constants.ErrorMsg;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.PermisoDTO;
import com.comercios.cuentaComerciosBO.dto.PermisoNuevoDTO;
import com.comercios.cuentaComerciosBO.entity.Permiso;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.PermisoMapper;
import com.comercios.cuentaComerciosBO.mapper.PermisoNuevoMapper;
import com.comercios.cuentaComerciosBO.repository.PermisoRepository;
import com.comercios.cuentaComerciosBO.service.contract.PermisoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoServiceImpl implements PermisoService {
    private static final Logger logger = LoggerFactory.getLogger(PermisoServiceImpl.class);
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private PermisoNuevoMapper permisoNuevoMapper;
    @Autowired
    private PermisoMapper permisoMapper;


    @Override
    public PermisoDTO crearPermiso(PermisoNuevoDTO dto) {
        logger.info("inicio servicio crear permiso");
        if (permisoRepository.existsByDescripcion(dto.getDescripcion())){
            logger.error(ErrorMsg.PERMISSION_EXISTS);
            throw new BadRequestException(ErrorMsg.PERMISSION_EXISTS);
        }
        logger.info("guardando permiso...");
        return permisoMapper.permisoToPermisoDTO(permisoRepository.save(permisoNuevoMapper
                .permisoNuevoToPermiso(dto)));
    }

    @Override
    public PermisoDTO buscarXId(Long id) {
        logger.info("iniciando servicio buscar permiso por id");
        Permiso permiso = permisoRepository.findById(id).orElseThrow(()->{
            logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
        });
        logger.info("buscando permiso...");
        return permisoMapper.permisoToPermisoDTO(permiso);
    }

    @Override
    public List<PermisoDTO> buscarTodos() {
        logger.info("iniciando servicio buscar todos los permisos");
        logger.info("buscando permisos...");
        return permisoMapper.permisoListToPermisoDTOList(permisoRepository.findAll());
    }

    @Override
    public PageableResponseDTO<PermisoDTO> buscarTodosPaginado(int page, int size) {
        logger.info("iniciando servicio buscar todos permisos paginado");
        if (page <= 0){
            logger.error(ErrorMsg.PAGE_GREATER_ZERO);
            throw new BadRequestException(ErrorMsg.PAGE_GREATER_ZERO);
        }
        logger.info("buscando permisos...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Permiso> p = permisoRepository.findAll(pageable);
        List<PermisoDTO> permisos = permisoMapper.permisoListToPermisoDTOList(p.getContent());
        PageableResponseDTO<PermisoDTO> response = new PageableResponseDTO<>();
        response.setPagina(p.getNumber() + 1);
        response.setResultados(p.getTotalElements());
        response.setTotalPaginas(p.getTotalPages());
        response.setContent(permisos);
        return response;
    }

    @Override
    public void eliminarPermiso(Long id) {
        logger.info("inicio servicio eliminar permiso");
        Permiso permiso = permisoRepository.findById(id).orElseThrow(()->{
            logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
        });
        logger.info("eliminando permiso...");
        permisoRepository.deleteById(id);
    }
}
