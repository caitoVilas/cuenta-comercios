package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.ComercioMapper;
import com.comercios.cuentaComerciosBO.repository.ComercioRepository;
import com.comercios.cuentaComerciosBO.service.contract.ComercioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComercioServiceImpl implements ComercioService {
    private static final Logger logger = LoggerFactory.getLogger(ComercioServiceImpl.class);
    @Autowired
    private ComercioRepository comercioRepository;
    @Autowired
    private ComercioMapper comercioMapper;


    @Override
    public ComercioDTO buscarPorId(Long id) {
        logger.info("inicio servicio buscar comercio por id");
        Comercio comercio = comercioRepository.findById(id).orElseThrow(()->{
            logger.error("el comercio no se encuentra");
            throw new NotFoundException("el comercio no se encuentra");
        });
        logger.info("buscando comercio...");
        return comercioMapper.comercioToComercioDTO(comercio);
    }

    @Override
    public List<ComercioDTO> verTodos() {
        logger.info("inicio servicio buscar todos los comercios");
        logger.info("buscando comercios...");
        return comercioMapper.comercioListToComercioDTOList(comercioRepository.findAll());
    }

    @Override
    public PageableResponseDTO<ComercioDTO> verTodosPaginado(int page, int size) {
        logger.info("inicio servicio buscar todos los comercios paginado");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        logger.info("buscando comercios...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Comercio> u = comercioRepository.findAll(pageable);
        List<ComercioDTO> comercios = comercioMapper.comercioListToComercioDTOList(u.getContent());
        PageableResponseDTO<ComercioDTO> response = new PageableResponseDTO<>();
        response.setPagina(u.getNumber() + 1);
        response.setResultados(u.getTotalElements());
        response.setTotalPaginas(u.getTotalPages());
        response.setContent(comercios);
        return response;
    }
}
