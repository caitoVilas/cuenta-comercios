package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.dto.DocumentacionDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import com.comercios.cuentaComerciosBO.entity.Documentacion;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.exception.Unauthorized;
import com.comercios.cuentaComerciosBO.mapper.ComercioMapper;
import com.comercios.cuentaComerciosBO.mapper.DocumentacionMapper;
import com.comercios.cuentaComerciosBO.repository.DocumentacionRepository;
import com.comercios.cuentaComerciosBO.service.contract.ComercioService;
import com.comercios.cuentaComerciosBO.service.contract.DocumentacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentacionServiceImpl implements DocumentacionService {
    private static final Logger logger = LoggerFactory.getLogger(DocumentacionServiceImpl.class);
    @Autowired
    private DocumentacionRepository documentacionRepository;
    @Autowired
    private DocumentacionMapper documentacionMapper;
    @Autowired
    private ComercioService comercioService;
    @Autowired
    private ComercioMapper comercioMapper;
    @Autowired
    private AuthorizationService authorizationService;


    @Override
    public List<DocumentacionDTO> buscarPorComercio(Long comercioId) {
        logger.info("iniciando servicio buscar documentacion por comercio");
        Comercio comercio = comercioMapper.comercioDTOToComercio(comercioService.buscarPorId(comercioId));
        if (comercio == null){
            logger.error("el comercio no se encuentra");
            throw new NotFoundException("el comercio no se encuentra");
        }
        logger.info("inciando busqueda de documentos");
        return documentacionMapper.documentacionListToDocumentacionDTOList(
                documentacionRepository.findByComercio(comercio));
    }

    @Override
    public void aprobacionOperador(Long documentoId) {
        logger.info("inicio servicio autorizacion documento por operador");

        Documentacion documento = documentacionRepository.findById(documentoId).orElseThrow(()->{
            logger.error("no se encuentra el documento");
            throw new NotFoundException("no se encuentra el documento");
        });
        if(!authorizationService.authorizeDocumentacion("ROLE_OPERADOR", 1l, documento)){
            logger.error("no autorizado");
            throw new Unauthorized("no autorizado");
        }
        logger.info("aprobando documento por operador...");
    }

    @Override
    public void rechazoOperador(Long documentoId) {
        logger.info("inicio servicio rechazo documento por operador");

        Documentacion documento = documentacionRepository.findById(documentoId).orElseThrow(()->{
            logger.error("no se encuentra el documento");
            throw new NotFoundException("no se encuentra el documento");
        });
        if(!authorizationService.authorizeDocumentacion("ROLE_OPERADOR", 1l, documento)){
            logger.error("no autorizado");
            throw new Unauthorized("no autorizado");
        }
        logger.info("rechazando documento por operador...");
    }
}
