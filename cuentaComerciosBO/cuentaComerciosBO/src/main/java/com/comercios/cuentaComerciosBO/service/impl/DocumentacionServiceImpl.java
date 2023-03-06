package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.constants.ErrorMsg;
import com.comercios.cuentaComerciosBO.dto.DocumentacionDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import com.comercios.cuentaComerciosBO.entity.Documentacion;
import com.comercios.cuentaComerciosBO.entity.Permiso;
import com.comercios.cuentaComerciosBO.enums.EstadoComercio;
import com.comercios.cuentaComerciosBO.enums.EstadoDocumentacion;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.exception.Unauthorized;
import com.comercios.cuentaComerciosBO.mapper.ComercioMapper;
import com.comercios.cuentaComerciosBO.mapper.DocumentacionMapper;
import com.comercios.cuentaComerciosBO.repository.ComercioRepository;
import com.comercios.cuentaComerciosBO.repository.DocumentacionRepository;
import com.comercios.cuentaComerciosBO.repository.PermisoRepository;
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
    private ComercioRepository comercioRepository;
    @Autowired
    private ComercioMapper comercioMapper;
    @Autowired
    private PermisoRepository permisoRepository;
    @Autowired
    private AuthorizationService authorizationService;


    @Override
    public List<DocumentacionDTO> buscarPorComercio(Long comercioId) {
        logger.info("iniciando servicio buscar documentacion por comercio");
        Comercio comercio = comercioRepository.findById(comercioId).orElseThrow(()->{
            logger.error(ErrorMsg.TRADE_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.TRADE_NOT_FOUND);
        });
        logger.info("inciando busqueda de documentos");
        return documentacionMapper.documentacionListToDocumentacionDTOList(
                documentacionRepository.findByComercio(comercio));
    }

    @Override
    public void aprobacionOperador(Long documentoId) {
        logger.info("inicio servicio autorizacion documento por operador");

        Documentacion documento = documentacionRepository.findById(documentoId).orElseThrow(()->{
            logger.error(ErrorMsg.DOCUMENT_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.DOCUMENT_NOT_FOUND);
        });
        Permiso permiso = permisoRepository.findByDescripcion("aprobacion nivel 1").orElseThrow(()->{
            logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
        });
        if(!authorizationService.authorizeDocumentacion("ROLE_OPERADOR", permiso.getId(), documento)){
            logger.error(ErrorMsg.UNAUTORIZED);
            throw new Unauthorized(ErrorMsg.UNAUTORIZED);
        }
        logger.info("aprobando documento por operador...");
        documento.setEstado(EstadoDocumentacion.APROBADO_OPERADOR);
        documentacionRepository.save(documento);
    }

    @Override
    public void rechazoOperador(Long documentoId) {
        logger.info("inicio servicio rechazo documento por operador");

        Documentacion documento = documentacionRepository.findById(documentoId).orElseThrow(()->{
            logger.error(ErrorMsg.DOCUMENT_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.DOCUMENT_NOT_FOUND);
        });
        Permiso permiso = permisoRepository.findByDescripcion("aprobacion nivel 1").orElseThrow(()->{
            logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
        });
        if(!authorizationService.authorizeDocumentacion("ROLE_OPERADOR", permiso.getId(), documento)){
            logger.error(ErrorMsg.UNAUTORIZED);
            throw new Unauthorized(ErrorMsg.UNAUTORIZED);
        }
        logger.info("rechazando documento por operador...");
        documento.setEstado(EstadoDocumentacion.RECHAZADO_OPERADOR);
        documentacionRepository.save(documento);
    }

    @Override
    public void aprobacionSupervisor(Long documentoId) {
        logger.info("inicio servicio autorizacion documento por supervisor");

        Documentacion documento = documentacionRepository.findById(documentoId).orElseThrow(()->{
            logger.error(ErrorMsg.DOCUMENT_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.DOCUMENT_NOT_FOUND);
        });
        Permiso permiso = permisoRepository.findByDescripcion("aprobacion nivel 2").orElseThrow(()->{
            logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
        });
        Comercio comercio = comercioRepository.findById(documento.getComercio().getId()).orElseThrow(()->{
            logger.error(ErrorMsg.TRADE_NOT_FOUND);
            throw new BadRequestException(ErrorMsg.TRADE_NOT_FOUND);
        });
        if(!authorizationService.authorizeDocumentacion("ROLE_SUPERVISOR", permiso.getId(), documento)){
            logger.error(ErrorMsg.UNAUTORIZED);
            throw new Unauthorized(ErrorMsg.UNAUTORIZED);
        }
        logger.info("aprobando documento por supervisor...");
        documento.setEstado(EstadoDocumentacion.APROBADO_SUPERVISOR);
        documentacionRepository.save(documento);
        comercio.setEstado(EstadoComercio.ACTIVO);
        comercio.setLimite(false);
        comercioRepository.save(comercio);
    }

    @Override
    public void rechazoSupervisor(Long documentoId) {
        logger.info("inicio servicio rechazo documento por supervisor");

        Documentacion documento = documentacionRepository.findById(documentoId).orElseThrow(()->{
            logger.error(ErrorMsg.DOCUMENT_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.DOCUMENT_NOT_FOUND);
        });
        Permiso permiso = permisoRepository.findByDescripcion("aprobacion nivel 2").orElseThrow(()->{
            logger.error(ErrorMsg.PERMISSION_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.PERMISSION_NOT_FOUND);
        });
        Comercio comercio = comercioRepository.findById(documento.getComercio().getId()).orElseThrow(()->{
            logger.error(ErrorMsg.TRADE_NOT_FOUND);
            throw new BadRequestException(ErrorMsg.TRADE_NOT_FOUND);
        });
        if(!authorizationService.authorizeDocumentacion("ROLE_SUPERVISOR", permiso.getId(), documento)){
            logger.error(ErrorMsg.UNAUTORIZED);
            throw new Unauthorized(ErrorMsg.UNAUTORIZED);
        }
        logger.info("rechazando documento por supervisor...");
        documento.setEstado(EstadoDocumentacion.RECHAZADO_SUPERVISOR);
        documentacionRepository.save(documento);
        comercio.setEstado(EstadoComercio.DOCUMENTACION_RECHAZADA);
        comercio.setLimite(true);
        comercioRepository.save(comercio);
    }
}
