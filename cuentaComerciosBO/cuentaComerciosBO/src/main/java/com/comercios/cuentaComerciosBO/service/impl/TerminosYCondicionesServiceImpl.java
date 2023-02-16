package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.TerminnosYCondicionesNuevoDTO;
import com.comercios.cuentaComerciosBO.dto.TerminosYCondicionesDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import com.comercios.cuentaComerciosBO.entity.TerminosYCondiciones;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.TerminosYCondicionesMapper;
import com.comercios.cuentaComerciosBO.repository.TerminosYCondicionesRepository;
import com.comercios.cuentaComerciosBO.service.contract.TerminosYCondicionesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class TerminosYCondicionesServiceImpl implements TerminosYCondicionesService {
    private static final Logger logger = LoggerFactory.getLogger(TerminosYCondicionesServiceImpl.class);
    @Value("${file.tyc.path}")
    private String documentPath;
    @Autowired
    private TerminosYCondicionesRepository terminosYCondicionesRepository;
    @Autowired
    private TerminosYCondicionesMapper terminosYCondicionesMapper;
    @Autowired
    private AuthorizationService authorizationService;


    @Override
    public TerminosYCondicionesDTO guardarTyc(String fechaVencimiento, MultipartFile file) {
        logger.info("inicio servicio guardar terminos y condiciones");
        TerminosYCondiciones tyc = new TerminosYCondiciones();
        logger.info("subiendo documento...");
        try {
            if (file.isEmpty()){
                logger.error("archivo sin contenido");
                throw new BadRequestException("archivo sin contenido");
            }
            byte[] fileBytes = file.getBytes();
            String nombreArchivo = UUID.randomUUID().toString().concat(file.getOriginalFilename());
            Files.write(Path.of(documentPath+ "/" + nombreArchivo), fileBytes);
            tyc.setArchivo(nombreArchivo);
            tyc.setRuta(documentPath);
            DateTimeFormatter formater = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            fechaVencimiento = fechaVencimiento + " 00:00";
            LocalDateTime vencimiento = LocalDateTime.parse(fechaVencimiento, formater);
            tyc.setFechaVencimiento(vencimiento);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        terminosYCondicionesRepository.save(tyc);
        return terminosYCondicionesMapper.tycToTycDTO(tyc);
    }

    @Override
    public TerminosYCondicionesDTO buscarPorId(Long id) {
        logger.info("inicio servicio buscar termnos y condiciones por id");
        TerminosYCondiciones tyc = terminosYCondicionesRepository.findById(id).orElseThrow(()->{
            logger.error("terminos y condiciones no se encuentra");
            throw new NotFoundException("terminos y condiciones no se encuentra");
        });
        return terminosYCondicionesMapper.tycToTycDTO(tyc);
    }

    @Override
    public PageableResponseDTO<TerminosYCondicionesDTO> verTodosPaginado(int page, int size) {
        logger.info("inicio servicio buscar todos los terminos y condiciones paginado");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        logger.info("buscando terminos y condiciones...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<TerminosYCondiciones> u = terminosYCondicionesRepository.findAll(pageable);
        List<TerminosYCondicionesDTO> tyc = terminosYCondicionesMapper.tycListToTycDTOList(u.getContent());
        PageableResponseDTO<TerminosYCondicionesDTO> response = new PageableResponseDTO<>();
        response.setPagina(u.getNumber() + 1);
        response.setResultados(u.getTotalElements());
        response.setTotalPaginas(u.getTotalPages());
        response.setContent(tyc);
        return response;
    }
}
