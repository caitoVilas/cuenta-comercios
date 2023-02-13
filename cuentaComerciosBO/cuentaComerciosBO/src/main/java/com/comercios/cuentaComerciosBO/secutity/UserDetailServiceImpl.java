package com.comercios.cuentaComerciosBO.secutity;

import com.comercios.cuentaComerciosBO.entity.UsuarioBO;
import com.comercios.cuentaComerciosBO.exception.NotFoundException;
import com.comercios.cuentaComerciosBO.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioBO> usuario = usuarioRepository.findByUsername(username);
        if (!usuario.isPresent()){
            logger.error("usuario no encontrado");
            throw new NotFoundException("usuario no encontrado");
        }
        return UsuarioPrincipal.build(usuario.get());
    }
}
