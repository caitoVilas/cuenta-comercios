package com.comercios.cuentaComercios.service.impl;

import com.comercios.cuentaComercios.dto.ComercioDTO;
import com.comercios.cuentaComercios.dto.ComercioNuevoDTO;
import com.comercios.cuentaComercios.dto.PageableResponseDTO;
import com.comercios.cuentaComercios.entity.Comercio;
import com.comercios.cuentaComercios.entity.Documentacion;
import com.comercios.cuentaComercios.entity.Usuario;
import com.comercios.cuentaComercios.enums.DocumentacionTipo;
import com.comercios.cuentaComercios.enums.EstadoComercio;
import com.comercios.cuentaComercios.enums.EstadoDocumentacion;
import com.comercios.cuentaComercios.exception.BadRequestException;
import com.comercios.cuentaComercios.exception.NotFoundException;
import com.comercios.cuentaComercios.mapper.ComercioMapper;
import com.comercios.cuentaComercios.repository.ComercioRepository;
import com.comercios.cuentaComercios.repository.DocumentacionRepository;
import com.comercios.cuentaComercios.repository.UsuarioRepository;
import com.comercios.cuentaComercios.service.contract.ComercioService;
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
import java.util.List;
import java.util.UUID;

@Service
public class ComercioServiceImpl implements ComercioService {
    private static final Logger logger = LoggerFactory.getLogger(ComercioServiceImpl.class);
    @Value("${file.documents.path}")
    private String documentPath;
    @Autowired
    private ComercioRepository comercioRepository;
    @Autowired
    private ComercioMapper comercioMapper;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DocumentacionRepository documentacionRepository;


    @Override
    public ComercioDTO crearComercio(ComercioNuevoDTO dto) {
        logger.info("inicio servicio alta comercio");
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(()->{
            logger.error("el usuario no se encuentra");
            throw new NotFoundException("el usuario no se encuentra");
        });
        if (comercioRepository.existsByCuit(dto.getCuit())){
            logger.error("CUIT ya registrado");
            throw new BadRequestException("CUIT ya registrado");
        }
        if (comercioRepository.existsByEmail(dto.getEmail())){
            logger.error("email ya registrado");
            throw new BadRequestException("email ya registrado");
        }
        if (comercioRepository.existsByUsuario(usuario)){
            logger.error("el usuario esta asignado a otro comercio");
            throw new BadRequestException("el usuario esta asignado a otro comercio");
        }
        logger.info("creando comercio...");
        Comercio comercio = new Comercio();
        comercio.setRazonSocial(dto.getRazonSocial());
        comercio.setDomicilio(dto.getDomicilio());
        comercio.setLocalidad(dto.getLocalidad());
        comercio.setProvincia(dto.getProvincia());
        comercio.setEmail(dto.getEmail());
        comercio.setTelefono(dto.getTelefono());
        comercio.setCuit(dto.getCuit());
        comercio.setCategoria(dto.getCategoria());
        comercio.setLimite(true);
        comercio.setUsuario(usuario);
        comercio.setEstado(EstadoComercio.DOCUMENTACION_PENDIENTE);
        return comercioMapper.comercioToComercioDTO(comercioRepository.save(comercio));
    }

    @Override
    public ComercioDTO buscarPorId(Long id) {
        logger.info("iniciando servicio buscar comercio por id");
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

    @Override
    public void guardarDocumento(MultipartFile file, Long comercioId, DocumentacionTipo dt) {
        logger.info("inicio servicio guardar archivo");
        Comercio comercio = comercioRepository.findById(comercioId).orElseThrow(()->{
            logger.error("el comercio no se encuentra");
            throw new NotFoundException("el comercio no se encuentra");
        });
        logger.info("subiendo documento...");
        Documentacion documento = new Documentacion();
        try {
            if (file.isEmpty()){
                logger.error("archivo sin contenido");
                throw new BadRequestException("archivo sin contenido");
            }
            byte[] fileBytes = file.getBytes();
            String nombreArchivo = UUID.randomUUID().toString().concat(file.getOriginalFilename());
            Files.write(Path.of(documentPath + "/" + nombreArchivo) , fileBytes);
            documento.setDocumentacionTipo(dt);
            documento.setArchivo(nombreArchivo);
            documento.setRuta(documentPath);
            documento.setComercio(comercio);
            documento.setEstado(EstadoDocumentacion.PENDIENTE_APROBACION);
            documentacionRepository.save(documento);
            comercio.setEstado(EstadoComercio.DOCUMENTACION_PRESENTADA);
            comercioRepository.save(comercio);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }
}
