package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.dto.ComercioDTO;
import com.comercios.cuentaComerciosBO.dto.PageableResponseDTO;
import com.comercios.cuentaComerciosBO.dto.UsuarioAppDTO;
import com.comercios.cuentaComerciosBO.entity.Comercio;
import com.comercios.cuentaComerciosBO.entity.Usuario;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.UsuarioAppMapper;
import com.comercios.cuentaComerciosBO.repository.UsuarioAppRepository;
import com.comercios.cuentaComerciosBO.service.contract.UsuariosAppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuariosAppServiceImpl implements UsuariosAppService {
    private static final Logger logger = LoggerFactory.getLogger(UsuariosAppServiceImpl.class);
    @Autowired
    private UsuarioAppRepository usuarioAppRepository;
    @Autowired
    private UsuarioAppMapper usuarioAppMapper;


    @Override
    public UsuarioAppDTO buscarPorId(Long id) {
        logger.info("inicio servicio buscar usuario app por id");
        Usuario usuario = usuarioAppRepository.findById(id).orElseThrow(()->{
            logger.error("el usuario no se encuentra");
            throw new NotFoundException("el usuario no se encuentra");
        });
        logger.info("buscando usuario...");
        return usuarioAppMapper.usuarioToUsuarioAppDTO(usuario);
    }

    @Override
    public List<UsuarioAppDTO> verTodos() {
        logger.info("inicio servicio buscar todos los usuarios");
        logger.info("buscando usuarios...");
        return usuarioAppMapper.usuarioListToUsuarioAppDTOList(usuarioAppRepository.findAll());
    }

    @Override
    public PageableResponseDTO<UsuarioAppDTO> verTodosPaginado(int page, int size) {
        logger.info("inicio servicio buscar todos los usuarios paginado");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        logger.info("buscando usuarios...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Usuario> u = usuarioAppRepository.findAll(pageable);
        List<UsuarioAppDTO> usuarios = usuarioAppMapper.usuarioListToUsuarioAppDTOList(u.getContent());
        PageableResponseDTO<UsuarioAppDTO> response = new PageableResponseDTO<>();
        response.setPagina(u.getNumber() + 1);
        response.setResultados(u.getTotalElements());
        response.setTotalPaginas(u.getTotalPages());
        response.setContent(usuarios);
        return response;
    }
}
