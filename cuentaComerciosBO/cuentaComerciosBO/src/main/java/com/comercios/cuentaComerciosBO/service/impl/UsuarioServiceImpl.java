package com.comercios.cuentaComerciosBO.service.impl;

import com.comercios.cuentaComerciosBO.constants.ErrorMsg;
import com.comercios.cuentaComerciosBO.dto.*;
import com.comercios.cuentaComerciosBO.entity.Rol;
import com.comercios.cuentaComerciosBO.entity.SucursalDeRadicacion;
import com.comercios.cuentaComerciosBO.entity.UsuarioBO;
import com.comercios.cuentaComerciosBO.exception.BadRequestException;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.mapper.PersonaMapper;
import com.comercios.cuentaComerciosBO.mapper.PersonaNuevaMapper;
import com.comercios.cuentaComerciosBO.mapper.RolMapper;
import com.comercios.cuentaComerciosBO.mapper.UsuarioMapper;
import com.comercios.cuentaComerciosBO.repository.SucursalDeRadicacionRepository;
import com.comercios.cuentaComerciosBO.repository.UsuarioRepository;
import com.comercios.cuentaComerciosBO.secutity.jwt.JwtProvider;
import com.comercios.cuentaComerciosBO.service.contract.PersonaService;
import com.comercios.cuentaComerciosBO.service.contract.RolService;
import com.comercios.cuentaComerciosBO.service.contract.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private PersonaNuevaMapper personaNuevaMapper;
    @Autowired
    private RolService rolService;
    @Autowired
    private RolMapper rolMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private SucursalDeRadicacionRepository sucursalDeRadicacionRepository;


    @Override
    public UsuarioDTO crearUsuario(UsuarioNuevoDTO dto) {
        logger.info("inicio servicio alta usuarios");
        if (personaService.existeXDni(dto.getPersona().getDni())){
            logger.error(ErrorMsg.USER_DNI_EXISTS);
            throw new BadRequestException(ErrorMsg.USER_DNI_EXISTS);
        }
        if (personaService.existeXEmail(dto.getPersona().getEmail())){
            logger.error(ErrorMsg.USER_MAIL_EXISTS);
            throw new BadRequestException(ErrorMsg.USER_MAIL_EXISTS);
        }
        if (usuarioRepository.existsByUsername(dto.getUsername())){
            logger.error(ErrorMsg.USER_USERNAME_EXISTS);
            throw new BadRequestException(ErrorMsg.USER_USERNAME_EXISTS);
        }
        logger.info("creando usuario...");
        List<Rol> roles = new ArrayList<>();
        for (RolNuevoDTO rol: dto.getRoles()){
            Rol r = rolService.buscarPorNombre(rol.getRolName());
            roles.add(r);
        }
        SucursalDeRadicacion sr = sucursalDeRadicacionRepository.findByCodigoSucursal(
                dto.getSucursalRadicacion());
        if (sr == null){
            sr = sucursalDeRadicacionRepository.findById(1l).get();
        }
        UsuarioBO usuario = new UsuarioBO();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setPersona(personaNuevaMapper
                .personaNuevaDTOToPersona(dto.getPersona()));
        usuario.setRoles(roles);
        usuario.setSucursalDeRadicacion(sr);
        return usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO buscarPorId(Long id) {
        logger.info("iniciando servicio buscar usuario por id");
        UsuarioBO usuario = usuarioRepository.findById(id).orElseThrow(()->{
            logger.error(ErrorMsg.USER_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.USER_NOT_FOUND);
        });
        logger.info("buscando usuario...");
        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> buscarTodos() {
        logger.info("inicio servicio buscar todos los usuarios");
        return usuarioMapper.usuarioListToUsarioDTOList(usuarioRepository.findAll());
    }

    @Override
    public PageableResponseDTO<UsuarioDTO> buscarTodosPaginado(int page, int size) {
        logger.info("inicio servicio buscar todos los usuarios paginado");
        if (page <= 0){
            logger.error(ErrorMsg.PAGE_GREATER_ZERO);
            throw new BadRequestException(ErrorMsg.PAGE_GREATER_ZERO);
        }
        logger.info("buscando usuarios...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<UsuarioBO> u = usuarioRepository.findAll(pageable);
        List<UsuarioDTO> usuarios = usuarioMapper.usuarioListToUsarioDTOList(u.getContent());
        PageableResponseDTO<UsuarioDTO> response = new PageableResponseDTO<>();
        response.setPagina(u.getNumber() + 1);
        response.setResultados(u.getTotalElements());
        response.setTotalPaginas(u.getTotalPages());
        response.setContent(usuarios);
        return response;
    }

    @Override
    public void eliminarUsuario(Long id) {
        logger.info("inicio servicio eliminar usuario");
        UsuarioBO usuario = usuarioRepository.findById(id).orElseThrow(()->{
            logger.error(ErrorMsg.USER_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.USER_NOT_FOUND);
        });
        logger.info("eliminando usuario...");
        usuarioRepository.deleteById(id);
    }

    @Override
    public LoginResponseDTO login(LoginDTO dto) {
        logger.info("incio servicio login");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        UsuarioBO usuarioBO = usuarioRepository.findByUsername(dto.getUsername()).orElseThrow(()->{
            logger.error(ErrorMsg.USER_NOT_FOUND);
            throw new NotFoundException(ErrorMsg.USER_NOT_FOUND);
        });
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsuario(usuarioMapper.usuarioToUsuarioDTO(usuarioBO));
        return response;
    }
}
