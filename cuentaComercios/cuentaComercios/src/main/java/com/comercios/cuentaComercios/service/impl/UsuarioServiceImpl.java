package com.comercios.cuentaComercios.service.impl;

import com.comercios.cuentaComercios.dto.*;
import com.comercios.cuentaComercios.entity.Rol;
import com.comercios.cuentaComercios.entity.Usuario;
import com.comercios.cuentaComercios.exception.BadRequestException;
import com.comercios.cuentaComercios.exception.NotFoundException;
import com.comercios.cuentaComercios.mapper.PersonaMapper;
import com.comercios.cuentaComercios.mapper.UsuarioMapper;
import com.comercios.cuentaComercios.repository.UsuarioRepository;
import com.comercios.cuentaComercios.security.jwt.JwtProvider;
import com.comercios.cuentaComercios.service.contract.PersonaService;
import com.comercios.cuentaComercios.service.contract.RolService;
import com.comercios.cuentaComercios.service.contract.UsuarioService;
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
    private RolService rolService;
    @Autowired
    private PersonaMapper personaMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProvider jwtProvider;


    @Override
    public UsuarioDTO crearusuario(UsuarioNuevoDTO dto) {
        logger.info("inicio servicio alta usuarios");
        if (personaService.existePorDni(dto.getPersona().getDni())){
            logger.error("el DNI ya esta registrado");
            throw new BadRequestException("el DNI ya esta registrado");
        }
        if (personaService.existePorMail(dto.getPersona().getEmail())){
            logger.error("el email ya esta registrado");
            throw new BadRequestException("el email ya esta registrado");
        }
        if (usuarioRepository.existsByUsername(dto.getUsername())){
            logger.error("el nombre de usuario ya esta registrado");
            throw new BadRequestException("el nombre de usuario ya esta registrado");
        }
        logger.info("creando usuario...");
        List<Rol> roles = new ArrayList<>();
        for (RolNuevoDTO rol: dto.getRoles()){
            Rol r = rolService.buscarPorNombre(rol.getRolName());
            roles.add(r);
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setPersona(personaMapper.personaDTOToPersona(dto.getPersona()));
        usuario.setRoles(roles);
        return usuarioMapper.usuarioToUsuarioDTO(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioDTO buscarrPorid(Long id) {
        logger.info("iniciando servicio buscar usuario por id");
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->{
            logger.error("el usuario no se encuentra");
            throw new NotFoundException("el usuario no se encuentra");
        });
        logger.info("buscando usuario...");
        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    @Override
    public List<UsuarioDTO> verTodos() {
        logger.info("inicio servicio buscar todos los usuarios");
        return usuarioMapper.usuarioListToUsuarioDTOList(usuarioRepository.findAll());
    }

    @Override
    public PageableResponseDTO<UsuarioDTO> verTodosPaginado(int page, int size) {
        logger.info("inicio servicio buscar todos los usuarios paginado");
        if (page <= 0){
            logger.error("la pagina debe ser mayor que 0");
            throw new BadRequestException("la pagina debe ser mayor que 0");
        }
        logger.info("buscando usuarios...");
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<Usuario> u = usuarioRepository.findAll(pageable);
        List<UsuarioDTO> usuarios = usuarioMapper.usuarioListToUsuarioDTOList(u.getContent());
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
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->{
            logger.error("el usuario no se encuentra");
            throw new NotFoundException("el usuario no se encuentra");
        });
        logger.info("eliminando usuario...");
        usuarioRepository.deleteById(id);
    }

    @Override
    public LoginResponseDTO login(LoginDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        Usuario usuario = usuarioRepository.findByUsername(dto.getUsername()).orElseThrow(()->{
            logger.error("el usuario no se encuentra");
            throw new NotFoundException("el usuario no se encuentra");
        });
        LoginResponseDTO response = new LoginResponseDTO();
        response.setToken(token);
        response.setUsuario(usuarioMapper.usuarioToUsuarioDTO(usuario));
        return response;
    }
}
