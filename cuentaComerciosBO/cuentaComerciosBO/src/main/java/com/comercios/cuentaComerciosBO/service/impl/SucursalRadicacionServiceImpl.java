package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.constants.ErrorMsg;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionDTO;
import com.comercios.cuentaComerciosBO.dto.SucursalRadicacionNuevaDTO;
import com.comercios.cuentaComerciosBO.entity.SucursalDeRadicacion;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.SucursalDeRadicacionMapper;
import com.comercios.cuentaComerciosBO.mapper.SucursaldeRadicacionNuevaMapper;
import com.comercios.cuentaComerciosBO.repository.SucursalDeRadicacionRepository;
import com.comercios.cuentaComerciosBO.service.contract.SucursalDeRadicacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalRadicacionServiceImpl implements SucursalDeRadicacionService {
    private static final Logger logger = LoggerFactory.getLogger(SucursalRadicacionServiceImpl.class);
    @Autowired
    private SucursalDeRadicacionRepository sucursalDeRadicacionRepository;
    @Autowired
    private SucursaldeRadicacionNuevaMapper sucursaldeRadicacionNuevaMapper;
    @Autowired
    private SucursalDeRadicacionMapper sucursalDeRadicacionMapper;


    @Override
    public SucursalRadicacionDTO createSucursal(SucursalRadicacionNuevaDTO dto) {
        logger.info("iniciando servicio creacion de sucursal de radicacion");
        if (sucursalDeRadicacionRepository.existsByCodigoSucursal(dto.getCodigoSucursal())){
            logger.error(ErrorMsg.BRANCH_CODE_EXISTS);
            throw new BadRequestException(ErrorMsg.BRANCH_CODE_EXISTS);
        }
        logger.info("creando sucursal de radicacion...");
        return sucursalDeRadicacionMapper.sucursalDeRadicacionToSucursalDeRadicionDTO(
                sucursalDeRadicacionRepository.save(sucursaldeRadicacionNuevaMapper
                        .sucursalDeRadicacionNuevaToSucursaldeRadicacionDTO(dto)));
    }

    @Override
    public SucursalRadicacionDTO buscarPorCodigo(Integer codigoSucursal) {
        logger.info("inicio servicio buscar sucursal de radicacion por codigo");
        SucursalDeRadicacion sucursalDeRadicacion = sucursalDeRadicacionRepository
                .findByCodigoSucursal(codigoSucursal);
        if (sucursalDeRadicacion == null){
            logger.error(ErrorMsg.BRANCH_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.BRANCH_NOT_FOUND);
        }
        logger.info("buscando sucursal de radicacion...");
        return sucursalDeRadicacionMapper.sucursalDeRadicacionToSucursalDeRadicionDTO(sucursalDeRadicacion);
    }

    @Override
    public List<SucursalRadicacionDTO> verTodos() {
        logger.info("inicio servicio buscar todas las sucursales de radicion");
        logger.info("buscando sucursales de radicacion...");
        return sucursalDeRadicacionMapper.sucursalDeRadicacionListToSucursalDeRadicacionDTOList(
                sucursalDeRadicacionRepository.findAll());
    }

    @Override
    public PageableResponseDTO<SucursalRadicacionDTO> verTodoPaginado(int page, int size) {
        logger.info("inicio servicio buscar todos las sucursales de radicion paginado");
        if (page <= 0){
            logger.error(ErrorMsg.PAGE_GREATER_ZERO);
            throw new BadRequestException(ErrorMsg.PAGE_GREATER_ZERO);
        }
        logger.info("buscando sucursales de radicacion...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<SucursalDeRadicacion> s = sucursalDeRadicacionRepository.findAll(pageable);
        List<SucursalRadicacionDTO> sucursales = sucursalDeRadicacionMapper
                .sucursalDeRadicacionListToSucursalDeRadicacionDTOList(
                        s.getContent());
        PageableResponseDTO<SucursalRadicacionDTO> response = new PageableResponseDTO<>();
        response.setPagina(s.getNumber() + 1);
        response.setResultados(s.getTotalElements());
        response.setTotalPaginas(s.getTotalPages());
        response.setContent(sucursales);
        return response;
    }
}
